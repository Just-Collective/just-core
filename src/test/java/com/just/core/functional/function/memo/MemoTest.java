package com.just.core.functional.function.memo;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class MemoTest {

    @Test
    void testReferenceEqualityMemoization() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Function<String, Integer> lengthFunc = s -> {
            callCount.incrementAndGet();
            return s.length();
        };

        var memo = new Memo<>(lengthFunc);

        var str1 = "hello";
        // Different reference, same value.
        var str2 = new String("hello");

        // Act
        var result1 = memo.apply(str1);
        var result2 = memo.apply(str1);

        // Assert
        // Second call uses cache.
        assertEquals(result1, result2);
        assertEquals(1, callCount.get());

        var result3 = memo.apply(str2);

        // New reference, should trigger re-evaluation.
        assertEquals(result1, result3);
        assertEquals(2, callCount.get());
    }

    @Test
    void testValueEqualityMemoization() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Function<String, Integer> lengthFunc = s -> {
            callCount.incrementAndGet();
            return s.length();
        };

        var memo = new Memo<>(lengthFunc, Objects::equals);

        var str1 = "world";
        var str2 = new String("world");

        // Act
        var result1 = memo.apply(str1);
        var result2 = memo.apply(str2);

        // Assert
        // Second call uses cached result due to .equals() comparison.
        assertEquals(result1, result2);
        assertEquals(1, callCount.get());
    }

    @Test
    void testDistinctInputsInvalidateCache() {
        // Arrange
        var callCount = new AtomicInteger(0);

        Function<Integer, String> toStringFunc = i -> {
            callCount.incrementAndGet();
            return "#" + i;
        };

        var memo = new Memo<>(toStringFunc);

        // Act
        var result1 = memo.apply(1);
        var result2 = memo.apply(2);

        // Assert
        assertNotEquals(result1, result2);
        assertEquals(2, callCount.get());
    }

    @Test
    void testRepeatedCallWithSameReference() {
        // Arrange
        var callCount = new AtomicInteger(0);

        var input = "memoize";

        Memo<String, Integer> memo = new Memo<>(s -> {
            callCount.incrementAndGet();
            return s.length();
        });

        // Act
        var first = memo.apply(input);
        var second = memo.apply(input);
        var third = memo.apply(input);

        // Assert
        assertEquals(first, second);
        assertEquals(second, third);
        assertEquals(1, callCount.get());
    }
}
