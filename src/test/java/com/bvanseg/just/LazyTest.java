package com.bvanseg.just;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class LazyTest {

    @Test
    void get_EvaluatesSupplierOnce() {
        // Arrange
        var counter = new AtomicInteger(0);

        // Act
        var lazy = Lazy.of(() -> {
            counter.incrementAndGet();
            return 42;
        });

        // Assert
        // First call: should evaluate.
        int value1 = lazy.get();
        assertEquals(42, value1);

        // Second call: should reuse cached value.
        int value2 = lazy.get();
        assertEquals(42, value2);

        // Supplier should only have been invoked once.
        assertEquals(1, counter.get());
    }

    @Test
    void isPresent_IsFalseBeforeEvaluation() {
        // Arrange & Act
        Lazy<String> lazy = Lazy.of(() -> "Hello");
        // Assert
        assertFalse(lazy.isPresent());
    }

    @Test
    void isPresent_IsTrueAfterEvaluation() {
        // Arrange
        Lazy<String> lazy = Lazy.of(() -> "World");
        // Act
        lazy.get();
        // Assert
        assertTrue(lazy.isPresent());
    }

    @Test
    void isEmpty_IsTrueBeforeEvaluation() {
        // Arrange & Act
        Lazy<String> lazy = Lazy.of(() -> "Test");
        // Assert
        assertTrue(lazy.isEmpty());
    }

    @Test
    void isEmpty_IsFalseAfterEvaluation() {
        // Arrange
        Lazy<String> lazy = Lazy.of(() -> "Now evaluated");
        // Act
        lazy.get();
        // Assert
        assertFalse(lazy.isEmpty());
    }
}
