package com.just.core.functional.option;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NoneTest {

    @Test
    void and_AlwaysReturnsNone() {
        // Arrange
        var none = Option.none();
        var some = Option.some(42);
        // Act
        var result = none.and(some);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void contains_AlwaysReturnsFalse() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.contains("value");
        // Assert
        assertFalse(result);
    }

    @Test
    void expect_ThrowsExceptionWithMessage() {
        // Arrange
        var none = Option.none();
        // Act & Assert
        var exception = assertThrows(NoSuchElementException.class, () -> none.expect("Custom error"));
        assertEquals("Custom error", exception.getMessage());
    }

    @Test
    void filter_AlwaysReturnsNone() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.filter(x -> true);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void ifSome_DoesNothing() {
        // Arrange
        var none = Option.none();
        var called = new AtomicBoolean(false);
        // Act
        none.ifSome(x -> called.set(true));
        // Assert
        assertFalse(called.get());
    }

    @Test
    void ifNone_RunsAction() {
        // Arrange
        var none = Option.none();
        var called = new AtomicBoolean(false);
        // Act
        none.ifNone(() -> called.set(true));
        // Assert
        assertTrue(called.get());
    }

    @Test
    void inspect_AlwaysReturnsNone() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.inspect(x -> fail("Should not be called"));
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void isNone_ReturnsTrue() {
        // Arrange & Act
        var none = Option.none();
        // Assert
        assertTrue(none.isNone());
    }

    @Test
    void isSome_ReturnsFalse() {
        // Arrange & Act
        var none = Option.none();
        // Assert
        assertFalse(none.isSome());
    }

    @Test
    void isSomeAnd_AlwaysReturnsFalse() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.isSomeAnd(x -> true);
        // Assert
        assertFalse(result);
    }

    @Test
    void map_AlwaysReturnsNone() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.map(x -> "mapped");
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void match_InvokesIfNoneBranch() {
        // Arrange
        var none = Option.none();
        // Act
        var result = none.match(x -> "some", () -> "none");
        // Assert
        assertEquals("none", result);
    }

    @Test
    void toOptional_ReturnsEmpty() {
        // Arrange
        var none = Option.none();
        // Act
        Optional<Object> opt = none.toOptional();
        // Assert
        assertTrue(opt.isEmpty());
    }

    @Test
    void toStream_ReturnsEmptyStream() {
        // Arrange
        var none = Option.none();
        // Act
        Stream<?> stream = none.toStream();
        // Assert
        assertEquals(0, stream.count());
    }

    @Test
    void unwrap_ThrowsException() {
        // Arrange
        var none = Option.none();
        // Act & Assert
        assertThrows(NoSuchElementException.class, none::unwrap);
    }

    @Test
    void unwrapOr_ReturnsOtherValue() {
        // Arrange
        var none = Option.none();
        // Act
        var value = none.unwrapOr("default");
        // Assert
        assertEquals("default", value);
    }

    @Test
    void unwrapOrElse_ReturnsSupplierValue() {
        // Arrange
        var none = Option.none();
        // Act
        var value = none.unwrapOrElse(() -> "generated");
        // Assert
        assertEquals("generated", value);
    }

    @Test
    void unwrapOrThrow_ThrowsSuppliedException() {
        // Arrange
        var none = Option.none();
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> none.unwrapOrThrow(() -> new IllegalStateException("boom")));
    }

    @Test
    void zip_AlwaysReturnsNone() {
        // Arrange
        var none = Option.none();
        var some = Option.some("data");
        // Act
        var result = none.zip(some, (a, b) -> a.toString() + b);
        // Assert
        assertTrue(result.isNone());
    }

    @Test
    void equals_EqualToOtherNone() {
        // Arrange & Act
        var none1 = Option.none();
        var none2 = Option.none();
        // Assert
        assertEquals(none1, none2);
    }

    @Test
    void hashCode_AlwaysZero() {
        // Arrange
        var none = Option.none();
        // Act & Assert
        assertEquals(0, none.hashCode());
    }

    @Test
    void toString_ReturnsNoneString() {
        // Arrange
        var none = Option.none();
        // Act & Assert
        assertEquals("None", none.toString());
    }
}
