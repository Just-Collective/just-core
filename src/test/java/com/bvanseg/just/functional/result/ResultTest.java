package com.bvanseg.just.functional.result;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    @Test
    void tryCatch_NoException() {
        // Arrange
        Supplier<Integer> dangerousTask = () -> Integer.parseInt("2");
        // Act
        var result = Result.tryCatch(dangerousTask);
        // Assert
        assertTrue(result.isOk());
        assertEquals(2, result.unwrap());
    }

    @Test
    void tryCatch_ThrowsNumberFormatException() {
        // Arrange
        Supplier<Integer> dangerousTask = () -> Integer.parseInt("a");
        // Act
        var result = Result.tryCatch(dangerousTask);
        // Assert
        assertTrue(result.isErr());
        assertInstanceOf(NumberFormatException.class, result.unwrapErr());
    }
}
