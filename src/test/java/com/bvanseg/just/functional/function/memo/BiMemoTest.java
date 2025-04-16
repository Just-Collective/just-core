package com.bvanseg.just.functional.function.memo;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class BiMemoTest {

    @Test
    void testReferenceEqualityMemoization() {
        var callCount = new AtomicInteger(0);

        BiFunction<String, Integer, String> concat = (s, i) -> {
            callCount.incrementAndGet();
            return s + i;
        };

        var memo = new BiMemo<>(concat);

        var str1 = "item";
        var str2 = new String("item"); // different reference, same value

        var result1 = memo.apply(str1, 5);
        var result2 = memo.apply(str1, 5); // should hit cache
        var result3 = memo.apply(str2, 5); // new reference: cache miss

        assertEquals("item5", result1);
        assertEquals(result1, result2);
        assertEquals("item5", result3);
        assertEquals(2, callCount.get());
    }

    @Test
    void testValueEqualityMemoization() {
        var callCount = new AtomicInteger(0);

        BiFunction<String, Integer, String> concat = (s, i) -> {
            callCount.incrementAndGet();
            return s + "-" + i;
        };

        var memo = new BiMemo<>(concat, Objects::equals, Objects::equals);

        var s1 = new String("key");
        var s2 = new String("key");

        var r1 = memo.apply(s1, 42);
        var r2 = memo.apply(s2, 42); // value-equal, should reuse

        assertEquals(r1, r2);
        assertEquals(1, callCount.get());
    }

    @Test
    void testCacheInvalidatesOnFirstArgChange() {
        AtomicInteger callCount = new AtomicInteger(0);

        BiMemo<String, Integer, String> memo = new BiMemo<>(
            (s, i) -> {
                callCount.incrementAndGet();
                return s + ":" + i;
            }
        );

        String r1 = memo.apply("a", 1);
        String r2 = memo.apply("b", 1);

        assertNotEquals(r1, r2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testCacheInvalidatesOnSecondArgChange() {
        AtomicInteger callCount = new AtomicInteger(0);

        BiMemo<String, Integer, String> memo = new BiMemo<>(
            (s, i) -> {
                callCount.incrementAndGet();
                return s + ":" + i;
            }
        );

        String r1 = memo.apply("z", 100);
        String r2 = memo.apply("z", 200);

        assertNotEquals(r1, r2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testRepeatedCallWithSameArgs() {
        AtomicInteger callCount = new AtomicInteger(0);

        BiMemo<Integer, Integer, Integer> memo = new BiMemo<>(
            (a, b) -> {
                callCount.incrementAndGet();
                return a * b;
            }
        );

        int r1 = memo.apply(3, 7);
        int r2 = memo.apply(3, 7);
        int r3 = memo.apply(3, 7);

        assertEquals(21, r1);
        assertEquals(r1, r2);
        assertEquals(r2, r3);
        assertEquals(1, callCount.get());
    }
}
