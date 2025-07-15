package com.bvanseg.just.functional.result;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ErrTest {

    @Test
    void and_ReturnsSelf() {
        // Arrange
        Result<Integer, String> err = Result.err("error");
        Result<String, String> other = Result.ok("value");
        // Act
        var result = err.and(other);
        // Assert
        assertEquals(err, result);
    }

    @Test
    void andThen_ReturnsSelf() {
        // Arrange
        Result<Integer, String> err = Result.err("error");
        // Act
        var result = err.andThen(v -> Result.ok("ignored"));
        // Assert
        assertEquals(err, result);
    }

    @Test
    void err_ReturnsSomeWithError() {
        // Arrange
        Result<Integer, String> err = Result.err("bad");
        // Act
        var result = err.err();
        // Assert
        assertTrue(result.isSome());
        assertEquals("bad", result.unwrap());
    }

    @Test
    void expect_ThrowsException() {
        // Arrange
        Result<String, String> err = Result.err("nope");
        // Act & Assert
        var ex = assertThrows(NoSuchElementException.class, () -> err.expect("boom"));
        assertEquals("boom", ex.getMessage());
    }

    @Test
    void expectErr_ReturnsError() {
        // Arrange
        Result<String, String> err = Result.err("important");
        // Act & Assert
        assertEquals("important", err.expectErr("unused"));
    }

    @Test
    void ifOk_DoesNothing() {
        // Arrange
        Result<String, String> err = Result.err("fail");
        AtomicBoolean called = new AtomicBoolean(false);
        // Act
        err.ifOk(v -> called.set(true));
        // Assert
        assertFalse(called.get());
    }

    @Test
    void ifErr_CallsAction() {
        // Arrange
        Result<String, String> err = Result.err("whoops");
        AtomicBoolean called = new AtomicBoolean(false);
        // Act
        err.ifErr(e -> {
            assertEquals("whoops", e);
            called.set(true);
        });
        // Assert
        assertTrue(called.get());
    }

    @Test
    void inspect_ReturnsSelf() {
        // Arrange
        Result<String, String> err = Result.err("bad");
        // Act
        var result = err.inspect(v -> fail("Should not be called"));
        // Assert
        assertEquals(err, result);
    }

    @Test
    void inspectErr_RunsAndReturnsSelf() {
        // Arrange
        Result<String, String> err = Result.err("err");
        AtomicBoolean called = new AtomicBoolean(false);
        // Act
        var result = err.inspectErr(e -> called.set(true));
        // Assert
        assertTrue(called.get());
        assertEquals(err, result);
    }

    @Test
    void isErr_ReturnsTrue() {
        // Arrange & Act & Assert
        assertTrue(Result.err("e").isErr());
    }

    @Test
    void isOk_ReturnsFalse() {
        // Arrange & Act & Assert
        assertFalse(Result.err("e").isOk());
    }

    @Test
    void isErrAnd_PredicateMatches_ReturnsTrue() {
        // Arrange
        var err = Result.err("match");
        // Act & Assert
        assertTrue(err.isErrAnd(e -> e.equals("match")));
    }

    @Test
    void isErrAnd_PredicateFails_ReturnsFalse() {
        // Arrange
        var err = Result.err("match");
        // Act & Assert
        assertFalse(err.isErrAnd(e -> e.equals("wrong")));
    }

    @Test
    void isOkAnd_AlwaysFalse() {
        // Arrange
        var err = Result.err("nope");
        // Act & Assert
        assertFalse(err.isOkAnd(v -> true));
    }

    @Test
    void map_ReturnsSelf() {
        // Arrange
        var err = Result.<String, String>err("error");
        // Act
        var mapped = err.map(String::length);
        // Assert
        assertEquals(err, mapped);
    }

    @Test
    void mapErr_TransformsError() {
        // Arrange
        var err = Result.<String, String>err("fail");
        // Act
        var mapped = err.mapErr(String::toUpperCase);
        // Assert
        assertEquals(Result.err("FAIL"), mapped);
    }

    @Test
    void match_CallsErrBranch() {
        // Arrange
        var err = Result.err("boom");
        // Act
        var result = err.match(v -> "ok", e -> "err: " + e);
        // Assert
        assertEquals("err: boom", result);
    }

    @Test
    void ok_ReturnsNone() {
        // Arrange
        var err = Result.err("fail");
        // Act & Assert
        assertTrue(err.ok().isNone());
    }

    @Test
    void or_ReturnsOther() {
        // Arrange
        Result<String, String> err = Result.err("error");
        Result<String, String> fallback = Result.ok("default");
        // Act
        var result = err.or(fallback);
        // Assert
        assertEquals(fallback, result);
    }

    @Test
    void orElse_AppliesFunction() {
        // Arrange
        var err = Result.err("missing");
        // Act
        var result = err.orElse(e -> Result.ok(e + "-handled"));
        // Assert
        assertEquals(Result.ok("missing-handled"), result);
    }

    @Test
    void toOptional_ReturnsEmpty() {
        // Arrange
        var err = Result.err("fail");
        // Act & Assert
        assertTrue(err.toOptional().isEmpty());
    }

    @Test
    void unwrap_ThrowsException() {
        // Arrange
        var err = Result.err("fail");
        // Act & Assert
        var ex = assertThrows(NoSuchElementException.class, err::unwrap);
        assertEquals("No value present.", ex.getMessage());
    }

    @Test
    void unwrapErr_ReturnsError() {
        // Arrange
        var err = Result.err("boom");
        // Act & Assert
        assertEquals("boom", err.unwrapErr());
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        // Arrange
        var err = Result.err("e");
        // Act & Assert
        assertEquals(err, err);
    }

    @Test
    void equals_SameValue_ReturnsTrue() {
        // Arrange
        var a = Result.err("x");
        var b = Result.err("x");
        // Act & Assert
        assertEquals(a, b);
    }

    @Test
    void equals_DifferentValues_ReturnsFalse() {
        // Arrange
        var a = Result.err("a");
        var b = Result.err("b");
        // Act & Assert
        assertNotEquals(a, b);
    }

    @Test
    void equals_Null_ReturnsFalse() {
        // Arrange
        var err = Result.err("something");
        // Act & Assert
        assertNotEquals(null, err);
    }

    @Test
    void equals_OtherType_ReturnsFalse() {
        // Arrange
        var err = Result.err("something");
        // Act & Assert
        assertNotEquals("something", err);
    }

    @Test
    void hashCode_SameForEqualErrors() {
        // Arrange
        var a = Result.err("fail");
        var b = Result.err("fail");
        // Act & Assert
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toString_FormatsCorrectly() {
        // Arrange
        var err = Result.err("boom");
        // Act & Assert
        assertEquals("Err(boom)", err.toString());
    }
}
