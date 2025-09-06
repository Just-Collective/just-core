package com.just.core.functional.option;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class SomeTest {

    @Test
    void and_ReturnsOtherOption() {
        // Arrange
        var some = Option.some(1);
        var other = Option.some("value");
        // Act
        var result = some.and(other);
        // Assert
        assertEquals(other, result);
    }

    @Test
    void contains_ReturnsTrueIfValueMatches() {
        // Arrange
        var some = Option.some("hello");
        // Act
        var result = some.contains("hello");
        // Assert
        assertTrue(result);
    }

    @Test
    void contains_ReturnsFalseIfValueDoesNotMatch() {
        // Arrange
        var some = Option.some("hello");
        // Act
        var result = some.contains("world");
        // Assert
        assertFalse(result);
    }

    @Test
    void expect_ReturnsValue() {
        // Arrange
        var some = Option.some(42);
        // Act
        var result = some.expect("should not fail");
        // Assert
        assertEquals(42, result);
    }

    @Test
    void filter_PredicatePasses() {
        // Arrange
        var some = Option.some(1);
        // Act
        var filtered = some.filter(x -> x == 1);
        // Assert
        assertTrue(filtered.isSome());
    }

    @Test
    void filter_PredicateFails() {
        // Arrange
        var some = Option.some(1);
        // Act
        var filtered = some.filter(x -> x != 1);
        // Assert
        assertTrue(filtered.isNone());
    }

    @Test
    void ifSome_RunsConsumer() {
        // Arrange
        var some = Option.some("value");
        var flag = new AtomicBoolean(false);
        // Act
        some.ifSome(x -> flag.set(true));
        // Assert
        assertTrue(flag.get());
    }

    @Test
    void ifNone_DoesNothing() {
        // Arrange
        var some = Option.some("value");
        var flag = new AtomicBoolean(false);
        // Act
        some.ifNone(() -> flag.set(true));
        // Assert
        assertFalse(flag.get());
    }

    @Test
    void inspect_RunsConsumerAndReturnsSelf() {
        // Arrange
        var some = Option.some(42);
        var flag = new AtomicBoolean(false);
        // Act
        var result = some.inspect(x -> flag.set(true));
        // Assert
        assertTrue(flag.get());
        assertEquals(some, result);
    }

    @Test
    void isNone_ReturnsFalse() {
        // Arrange
        var some = Option.some("value");
        // Act & Assert
        assertFalse(some.isNone());
    }

    @Test
    void isSome_ReturnsTrue() {
        // Arrange
        var some = Option.some("value");
        // Act & Assert
        assertTrue(some.isSome());
    }

    @Test
    void isSomeAnd_PredicatePasses() {
        // Arrange
        var some = Option.some(10);
        // Act
        var result = some.isSomeAnd(x -> x > 5);
        // Assert
        assertTrue(result);
    }

    @Test
    void isSomeAnd_PredicateFails() {
        // Arrange
        var some = Option.some(2);
        // Act
        var result = some.isSomeAnd(x -> x > 5);
        // Assert
        assertFalse(result);
    }

    @Test
    void map_TransformsValue() {
        // Arrange
        var some = Option.some("hello");
        // Act
        var mapped = some.map(String::length);
        // Assert
        assertEquals(Option.some(5), mapped);
    }

    @Test
    void match_InvokesIfSomeBranch() {
        // Arrange
        var some = Option.some(100);
        // Act
        var result = some.match(Object::toString, () -> "none");
        // Assert
        assertEquals("100", result);
    }

    @Test
    void toOptional_ReturnsPresent() {
        // Arrange
        var some = Option.some("x");
        // Act
        Optional<String> optional = some.toOptional();
        // Assert
        assertTrue(optional.isPresent());
        assertEquals("x", optional.get());
    }

    @Test
    void toStream_ReturnsSingleElementStream() {
        // Arrange
        var some = Option.some("element");
        // Act
        var count = some.toStream().count();
        // Assert
        assertEquals(1, count);
    }

    @Test
    void unwrap_ReturnsValue() {
        // Arrange
        var some = Option.some("data");
        // Act & Assert
        assertEquals("data", some.unwrap());
    }

    @Test
    void unwrapOr_IgnoresOther() {
        // Arrange
        var some = Option.some("x");
        // Act
        var value = some.unwrapOr("y");
        // Assert
        assertEquals("x", value);
    }

    @Test
    void unwrapOrElse_IgnoresSupplier() {
        // Arrange
        var some = Option.some("real");
        // Act
        var value = some.unwrapOrElse(() -> "fallback");
        // Assert
        assertEquals("real", value);
    }

    @Test
    void unwrapOrThrow_DoesNotThrow() {
        // Arrange
        var some = Option.some("value");
        // Act
        var result = some.unwrapOrThrow(() -> new RuntimeException("should not happen"));
        // Assert
        assertEquals("value", result);
    }

    @Test
    void zip_WithOtherSome_CombinesValues() {
        // Arrange
        var a = Option.some(2);
        var b = Option.some(3);
        // Act
        var zipped = a.zip(b, Integer::sum);
        // Assert
        assertEquals(Option.some(5), zipped);
    }

    @Test
    void zip_WithNone_ReturnsNone() {
        // Arrange
        var some = Option.some(1);
        var none = Option.<Integer>none();
        // Act
        var result = some.zip(none, Integer::sum);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void equals_EqualIfSameValue() {
        // Arrange
        var a = Option.some("test");
        var b = Option.some("test");
        // Act & Assert
        assertEquals(a, b);
    }

    @Test
    void equals_NotEqualIfDifferentValue() {
        // Arrange
        var a = Option.some("a");
        var b = Option.some("b");
        // Act & Assert
        assertNotEquals(a, b);
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        // Arrange
        var some = Option.some("value");
        // Act & Assert
        assertEquals(some, some);
    }

    @Test
    void equals_Null_ReturnsFalse() {
        // Arrange & Act
        var some = Option.some("value");
        // Assert
        assertNotEquals(null, some);
    }

    @Test
    void equals_DifferentType_ReturnsFalse() {
        // Arrange & Act
        var some = Option.some("value");
        // Assert
        assertNotEquals("value", some);
    }

    @Test
    void hashCode_SameForEqualInstances() {
        // Arrange
        var a = Option.some("value");
        var b = Option.some("value");
        // Act & Assert
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toString_FormatsWithValue() {
        // Arrange
        var some = Option.some("data");
        // Act
        var str = some.toString();
        // Assert
        assertEquals("Some(data)", str);
    }
}
