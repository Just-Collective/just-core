package com.just.core.functional.result;

import org.junit.jupiter.api.Test;

import com.just.core.functional.function.CheckedSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    @Test
    void tryCatch_NoException() {
        // Arrange
        CheckedSupplier<Integer> dangerousTask = () -> Integer.parseInt("2");
        // Act
        var result = Result.trySupply(dangerousTask);
        // Assert
        assertTrue(result.isOk());
        assertEquals(2, result.unwrap());
    }

    @Test
    void tryCatch_ThrowsNumberFormatException() {
        // Arrange
        CheckedSupplier<Integer> dangerousTask = () -> Integer.parseInt("a");
        // Act
        var result = Result.trySupply(dangerousTask);
        // Assert
        assertTrue(result.isErr());
        assertInstanceOf(NumberFormatException.class, result.unwrapErr());
    }
}
