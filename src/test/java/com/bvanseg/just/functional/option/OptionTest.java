package com.bvanseg.just.functional.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionTest {

    @Test
    void filter_PredicatePasses() {
        // Arrange
        var some = Option.some(1);

        // Act
        var someFiltered = some.filter(x -> x == 1);

        // Assert
        assertTrue(someFiltered.isSome());
    }

    @Test
    void filter_PredicateFails() {
        // Arrange
        var some = Option.some(1);

        // Act
        var someFiltered = some.filter(x -> x != 1);

        // Assert
        assertTrue(someFiltered.isNone());
    }

    @Test
    void map_Success() {
        // Arrange
        var some = Option.some(1);

        // Act
        var someString = some.map(Object::toString);

        // Assert
        assertEquals("1", someString.unwrap());
    }

    @Test
    void match_Some_Success() {
        // Arrange
        var some = Option.some(1);

        // Act
        var someString = some.match(Object::toString, () -> "2");

        // Assert
        assertEquals("1", someString);
    }

    @Test
    void match_None_Success() {
        // Arrange
        var some = Option.none();

        // Act
        var someString = some.match(Object::toString, () -> "2");

        // Assert
        assertEquals("2", someString);
    }

    @Test
    void zip_Some_Success() {
        // Arrange
        var some = Option.some(1);
        var someOther = Option.some(2);

        // Act
        var someSum = some.zip(someOther, Integer::sum);

        // Assert
        assertTrue(someSum.isSome());
        assertEquals(3, someSum.unwrap());
    }

    @Test
    void zip_None_Success() {
        // Arrange
        var some = Option.some(1);
        var someOther = Option.<Integer>none();

        // Act
        var someSum = some.zip(someOther, Integer::sum);

        // Assert
        assertTrue(someSum.isNone());
    }
}
