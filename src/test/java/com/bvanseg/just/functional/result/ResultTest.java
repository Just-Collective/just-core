package com.bvanseg.just.functional.result;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultTest {

    @Test
    void and_Ok() {
        // Arrange
        Result<Integer, Integer> ok = Result.ok(1);
        Result<Integer, Integer> other = Result.ok(2);

        // Act
        var result = ok.and(other);

        // Assert
        assertTrue(result.isOk());
        assertEquals(2, result.unwrap());
    }

    @Test
    void and_Err() {
        // Arrange
        Result<Integer, Integer> ok = Result.ok(1);
        Result<Integer, Integer> other = Result.err(2);

        // Act
        var result = ok.and(other);

        // Assert
        assertTrue(result.isErr());
    }

    @Test
    void inspect_Ok() {
        // Arrange
        Result<Integer, Integer> ok = Result.ok(1);

        // Act
        var atom = new AtomicInteger(0);
        ok.inspect(atom::set);

        // Assert
        assertEquals(1, atom.get());
    }

    @Test
    void inspect_Err() {
        // Arrange
        Result<Integer, Integer> err = Result.err(2);

        // Act
        var atom = new AtomicInteger(0);
        err.inspect(atom::set);

        // Assert
        assertEquals(0, atom.get());
    }

    @Test
    void map_Ok() {
        // Arrange
        Result<Integer, Integer> ok = Result.ok(1);

        // Act
        var other = ok.map(x -> x + 1);

        // Assert
        assertTrue(other.isOk());
        assertEquals(2, other.unwrap());
    }

    @Test
    void map_Err() {
        // Arrange
        Result<Integer, Integer> err = Result.err(1);

        // Act
        var other = err.map(x -> x + 1);

        // Assert
        assertTrue(other.isErr());
        assertEquals(1, other.unwrapErr());
    }
}
