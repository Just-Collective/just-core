package com.just.core.functional.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class OptionTest {

    @Test
    void ofNullable_WithNonNull_ReturnsSome() {
        // Arrange & Act
        var result = Option.ofNullable("hello");
        // Assert
        assertTrue(result.isSome());
        assertEquals("hello", result.unwrap());
    }

    @Test
    void ofNullable_WithNull_ReturnsNone() {
        // Arrange & Act
        var result = Option.ofNullable(null);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void flatten_SomeSome_ReturnsInner() {
        // Arrange
        Option<Option<String>> nested = Option.some(Option.some("value"));
        // Act
        var flat = Option.flatten(nested);
        // Assert
        assertEquals(Option.some("value"), flat);
    }

    @Test
    void flatten_SomeNone_ReturnsNone() {
        // Arrange
        var nested = Option.some(Option.<String>none());
        // Act
        var flat = Option.flatten(nested);
        // Assert
        assertTrue(flat.isNone());
    }

    @Test
    void flatten_None_ReturnsNone() {
        // Arrange
        var nested = Option.<Option<String>>none();
        // Act
        var flat = Option.flatten(nested);
        // Assert
        assertTrue(flat.isNone());
    }

    @Test
    void guard_ConditionTrue_ReturnsSome() {
        // Arrange & Act
        var result = Option.guard(true, 123);
        // Assert
        assertTrue(result.isSome());
        assertEquals(123, result.unwrap());
    }

    @Test
    void guard_ConditionFalse_ReturnsNone() {
        // Arrange & Act
        var result = Option.guard(false, 123);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void when_ConditionTrue_InvokesSupplier() {
        // Arrange & Act
        var result = Option.when(true, () -> Option.some("value"));
        // Assert
        assertEquals(Option.some("value"), result);
    }

    @Test
    void when_ConditionFalse_SkipsSupplier() {
        // Arrange
        var result = Option.when(false, () -> {
            fail("Supplier should not be invoked.");
            return Option.none();
        });
        // Act & Assert
        assertTrue(result.isNone());
    }

    @Test
    void map2_BothSome_Combines() {
        // Arrange
        var a = Option.some(2);
        var b = Option.some(3);
        // Act
        var result = Option.map2(a, b, Integer::sum);
        // Assert
        assertEquals(Option.some(5), result);
    }

    @Test
    void map2_FirstNone_ReturnsNone() {
        // Arrange
        var a = Option.<Integer>none();
        var b = Option.some(3);
        // Act
        var result = Option.map2(a, b, Integer::sum);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void map2_SecondNone_ReturnsNone() {
        // Arrange
        var a = Option.some(2);
        var b = Option.<Integer>none();
        // Act
        var result = Option.map2(a, b, Integer::sum);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void map3_AllSome_Combines() {
        // Arrange
        var a = Option.some(1);
        var b = Option.some(2);
        var c = Option.some(3);
        // Act
        var result = Option.map3(a, b, c, (x, y, z) -> x + y + z);
        // Assert
        assertEquals(Option.some(6), result);
    }

    @Test
    void map3_OneNone_ReturnsNone() {
        // Arrange
        var a = Option.some(1);
        var b = Option.<Integer>none();
        var c = Option.some(3);
        // Act
        var result = Option.map3(a, b, c, (x, y, z) -> x + y + z);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void reduce_OnSome_AppliesFunction() {
        // Arrange
        var some = Option.some(10);
        // Act
        var result = some.reduce(x -> x + 5, 0);
        // Assert
        assertEquals(15, result);
    }

    @Test
    void reduce_OnNone_ReturnsIdentity() {
        // Arrange
        var none = Option.<Integer>none();
        // Act
        var result = none.reduce(x -> x + 5, 0);
        // Assert
        assertEquals(0, result);
    }
}
