package com.bvanseg.just.functional.result;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class OkTest {

    @Test
    void and_DelegatesToOtherResult() {
        // Arrange
        Result<Integer, String> ok = Result.ok(1);
        Result<String, String> other = Result.ok("next");
        // Act
        var result = ok.and(other);
        // Assert
        assertEquals(other, result);
    }

    @Test
    void andThen_MapsToNewResult() {
        // Arrange
        Result<Integer, String> ok = Result.ok(2);
        // Act
        var result = ok.andThen(v -> Result.ok(v * 10));
        // Assert
        assertEquals(Result.ok(20), result);
    }

    @Test
    void err_ReturnsNone() {
        // Arrange
        Result<Integer, String> ok = Result.ok(123);
        // Act
        var err = ok.err();
        // Assert
        assertTrue(err.isNone());
    }

    @Test
    void expect_ReturnsValue() {
        // Arrange
        Result<String, String> ok = Result.ok("success");
        // Act
        var value = ok.expect("should not fail");
        // Assert
        assertEquals("success", value);
    }

    @Test
    void expectErr_ThrowsException() {
        // Arrange
        Result<String, String> ok = Result.ok("fine");
        // Act & Assert
        var ex = assertThrows(NoSuchElementException.class, () -> ok.expectErr("boom"));
        assertEquals("No error present.", ex.getMessage());
    }

    @Test
    void ifOk_RunsAction() {
        // Arrange
        Result<String, String> ok = Result.ok("done");
        AtomicBoolean called = new AtomicBoolean(false);
        // Act
        ok.ifOk(v -> called.set(true));
        // Assert
        assertTrue(called.get());
    }

    @Test
    void ifErr_DoesNotRunAction() {
        // Arrange
        Result<String, String> ok = Result.ok("nope");
        AtomicBoolean called = new AtomicBoolean(false);
        // Act
        ok.ifErr(e -> called.set(true));
        // Assert
        assertFalse(called.get());
    }

    @Test
    void inspect_RunsConsumerAndReturnsSelf() {
        // Arrange
        Result<String, String> ok = Result.ok("ping");
        AtomicBoolean inspected = new AtomicBoolean(false);
        // Act
        var result = ok.inspect(v -> inspected.set(true));
        // Assert
        assertTrue(inspected.get());
        assertEquals(ok, result);
    }

    @Test
    void inspectErr_DoesNothing() {
        // Arrange
        Result<String, String> ok = Result.ok("pong");
        // Act
        var result = ok.inspectErr(e -> fail("Should not be called"));
        // Assert
        assertEquals(ok, result);
    }

    @Test
    void isErr_ReturnsFalse() {
        // Arrange & Act & Assert
        assertFalse(Result.ok("value").isErr());
    }

    @Test
    void isOk_ReturnsTrue() {
        // Arrange & Act & Assert
        assertTrue(Result.ok("value").isOk());
    }

    @Test
    void isOkAnd_PredicatePasses() {
        // Arrange
        var ok = Result.ok("pass");
        // Act & Assert
        assertTrue(ok.isOkAnd(v -> v.equals("pass")));
    }

    @Test
    void isOkAnd_PredicateFails() {
        // Arrange
        var ok = Result.ok("fail");
        // Act & Assert
        assertFalse(ok.isOkAnd(v -> v.equals("other")));
    }

    @Test
    void isErrAnd_AlwaysReturnsFalse() {
        // Arrange
        var ok = Result.ok("ignored");
        // Act & Assert
        assertFalse(ok.isErrAnd(e -> true));
    }

    @Test
    void map_TransformsValue() {
        // Arrange
        var ok = Result.ok("abc");
        // Act
        var mapped = ok.map(String::length);
        // Assert
        assertEquals(Result.ok(3), mapped);
    }

    @Test
    void mapErr_IgnoredAndReturnsSelf() {
        // Arrange
        var ok = Result.ok("original");
        // Act
        var mapped = ok.mapErr(err -> err + "mapped");
        // Assert
        assertEquals(Result.ok("original"), mapped);
    }

    @Test
    void match_UsesIsOkBranch() {
        // Arrange
        var ok = Result.ok(100);
        // Act
        var result = ok.match(v -> v + 1, _ -> -1);
        // Assert
        assertEquals(101, result);
    }

    @Test
    void ok_ReturnsSome() {
        // Arrange
        var ok = Result.ok("value");
        // Act
        var option = ok.ok();
        // Assert
        assertTrue(option.isSome());
        assertEquals("value", option.unwrap());
    }

    @Test
    void or_ReturnsSelf() {
        // Arrange
        var ok = Result.ok("original");
        var fallback = Result.ok("fallback");
        // Act
        var result = ok.or(fallback);
        // Assert
        assertEquals(ok, result);
    }

    @Test
    void orElse_SkipsSupplierAndReturnsSelf() {
        // Arrange
        var ok = Result.ok("final");
        // Act
        var result = ok.orElse(_ -> Result.ok("should not be used"));
        // Assert
        assertEquals(ok, result);
    }

    @Test
    void toOptional_ReturnsPresent() {
        // Arrange
        var ok = Result.ok("opt");
        // Act
        Optional<String> optional = ok.toOptional();
        // Assert
        assertTrue(optional.isPresent());
        assertEquals("opt", optional.get());
    }

    @Test
    void unwrap_ReturnsValue() {
        // Arrange & Act & Assert
        assertEquals(42, Result.ok(42).unwrap());
    }

    @Test
    void unwrapErr_ThrowsException() {
        // Arrange
        var ok = Result.ok("safe");
        // Act & Assert
        var ex = assertThrows(NoSuchElementException.class, ok::unwrapErr);
        assertEquals("No error present.", ex.getMessage());
    }

    @Test
    void equals_EqualIfSameValue() {
        // Arrange
        var a = Result.ok("test");
        var b = Result.ok("test");
        // Act & Assert
        assertEquals(a, b);
    }

    @Test
    void equals_NotEqualIfDifferentValue() {
        // Arrange
        var a = Result.ok("a");
        var b = Result.ok("b");
        // Act & Assert
        assertNotEquals(a, b);
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        // Arrange
        var ok = Result.ok("value");
        // Act & Assert
        assertEquals(ok, ok);
    }

    @Test
    void equals_Null_ReturnsFalse() {
        // Arrange & Act
        var ok = Result.ok("value");
        // Assert
        assertNotEquals(null, ok);
    }

    @Test
    void equals_DifferentType_ReturnsFalse() {
        // Arrange & Act
        var ok = Result.ok("value");
        // Assert
        assertNotEquals("value", ok);
    }

    @Test
    void hashCode_SameForEqualInstances() {
        // Arrange
        var a = Result.ok("value");
        var b = Result.ok("value");
        // Act & Assert
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toString_FormatsWithValue() {
        // Arrange
        var ok = Result.ok("data");
        // Act
        var str = ok.toString();
        // Assert
        assertEquals("Ok(data)", str);
    }
}
