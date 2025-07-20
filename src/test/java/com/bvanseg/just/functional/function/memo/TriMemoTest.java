package com.bvanseg.just.functional.function.memo;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;

import com.bvanseg.just.functional.function.Function3;

import static org.junit.jupiter.api.Assertions.*;

class TriMemoTest {

    @Test
    void testReferenceEqualityMemoization() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Function3<String, Integer, Boolean, String> f = (s, i, b) -> {
            callCount.incrementAndGet();
            return s + "-" + i + "-" + b;
        };

        var memo = new Memo3<>(f);

        var s1 = "ref";
        var s2 = new String("ref");

        // Act
        var r1 = memo.apply(s1, 42, true);
        // same refs.
        var r2 = memo.apply(s1, 42, true);
        // different string ref.
        var r3 = memo.apply(s2, 42, true);

        // Assert
        assertEquals(r1, r2);
        assertEquals(r1, r3);
        assertEquals(2, callCount.get());
    }

    @Test
    void testValueEqualityMemoization() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Function3<String, Integer, Boolean, String> f = (s, i, b) -> {
            callCount.incrementAndGet();
            return s + "|" + i + "|" + b;
        };

        BiPredicate<String, String> aEq = Objects::equals;
        BiPredicate<Integer, Integer> bEq = Integer::equals;
        BiPredicate<Boolean, Boolean> cEq = Boolean::equals;

        var memo = new Memo3<>(f, aEq, bEq, cEq);

        var a1 = new String("key");
        var a2 = new String("key");

        // Act
        var r1 = memo.apply(a1, 99, false);
        var r2 = memo.apply(a2, 99, false);

        // Assert
        assertEquals(r1, r2);
        assertEquals(1, callCount.get());
    }

    @Test
    void testCacheInvalidatesWhenAChanges() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Memo3<String, Integer, Double, String> memo = new Memo3<>(
            (a, b, c) -> {
                callCount.incrementAndGet();
                return a + ":" + b + ":" + c;
            }
        );

        // Act
        var r1 = memo.apply("a", 10, 1.1);
        var r2 = memo.apply("b", 10, 1.1);

        // Assert
        assertNotEquals(r1, r2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testCacheInvalidatesWhenBChanges() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Memo3<String, Integer, Double, String> memo = new Memo3<>(
            (a, b, c) -> {
                callCount.incrementAndGet();
                return a + ":" + b + ":" + c;
            }
        );

        // Act
        var r1 = memo.apply("x", 10, 3.3);
        var r2 = memo.apply("x", 20, 3.3);

        // Assert
        assertNotEquals(r1, r2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testCacheInvalidatesWhenCChanges() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Memo3<String, Integer, Double, String> memo = new Memo3<>(
            (a, b, c) -> {
                callCount.incrementAndGet();
                return a + ":" + b + ":" + c;
            }
        );

        // Act
        var r1 = memo.apply("x", 10, 1.0);
        var r2 = memo.apply("x", 10, 2.0);

        // Assert
        assertNotEquals(r1, r2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testRepeatedCallWithSameInputsUsesCache() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Memo3<Integer, Integer, Integer, Integer> memo = new Memo3<>(
            (a, b, c) -> {
                callCount.incrementAndGet();
                return a + b + c;
            }
        );

        // Act
        var r1 = memo.apply(1, 2, 3);
        var r2 = memo.apply(1, 2, 3);
        var r3 = memo.apply(1, 2, 3);

        // Assert
        assertEquals(r1, r2);
        assertEquals(r2, r3);
        assertEquals(1, callCount.get());
    }
}
