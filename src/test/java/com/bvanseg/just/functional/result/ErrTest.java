package com.bvanseg.just.functional.result;

import com.bvanseg.just.functional.option.Option;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ErrTest {

    @Test
    void and_ReturnsSelf() {
        Result<Integer, String> err = Result.err("error");
        Result<String, String> other = Result.ok("value");

        var result = err.and(other);

        assertEquals(err, result);
    }

    @Test
    void andThen_ReturnsSelf() {
        Result<Integer, String> err = Result.err("error");

        var result = err.andThen(v -> Result.ok("ignored"));

        assertEquals(err, result);
    }

    @Test
    void err_ReturnsSomeWithError() {
        Result<Integer, String> err = Result.err("bad");

        var result = err.err();

        assertTrue(result.isSome());
        assertEquals("bad", result.unwrap());
    }

    @Test
    void expect_ThrowsException() {
        Result<String, String> err = Result.err("nope");

        var ex = assertThrows(NoSuchElementException.class, () -> err.expect("boom"));
        assertEquals("No value present.", ex.getMessage());
    }

    @Test
    void expectErr_ReturnsError() {
        Result<String, String> err = Result.err("important");

        assertEquals("important", err.expectErr("unused"));
    }

    @Test
    void ifOk_DoesNothing() {
        Result<String, String> err = Result.err("fail");
        AtomicBoolean called = new AtomicBoolean(false);

        err.ifOk(v -> called.set(true));

        assertFalse(called.get());
    }

    @Test
    void ifErr_CallsAction() {
        Result<String, String> err = Result.err("whoops");
        AtomicBoolean called = new AtomicBoolean(false);

        err.ifErr(e -> {
            assertEquals("whoops", e);
            called.set(true);
        });

        assertTrue(called.get());
    }

    @Test
    void inspect_ReturnsSelf() {
        Result<String, String> err = Result.err("bad");

        var result = err.inspect(v -> fail("Should not be called"));

        assertEquals(err, result);
    }

    @Test
    void inspectErr_RunsAndReturnsSelf() {
        Result<String, String> err = Result.err("err");
        AtomicBoolean called = new AtomicBoolean(false);

        var result = err.inspectErr(e -> called.set(true));

        assertTrue(called.get());
        assertEquals(err, result);
    }

    @Test
    void isErr_ReturnsTrue() {
        assertTrue(Result.err("e").isErr());
    }

    @Test
    void isOk_ReturnsFalse() {
        assertFalse(Result.err("e").isOk());
    }

    @Test
    void isErrAnd_PredicateMatches_ReturnsTrue() {
        var err = Result.err("match");

        assertTrue(err.isErrAnd(e -> e.equals("match")));
    }

    @Test
    void isErrAnd_PredicateFails_ReturnsFalse() {
        var err = Result.err("match");

        assertFalse(err.isErrAnd(e -> e.equals("wrong")));
    }

    @Test
    void isOkAnd_AlwaysFalse() {
        var err = Result.err("nope");

        assertFalse(err.isOkAnd(v -> true));
    }

    @Test
    void map_ReturnsSelf() {
        var err = Result.<String, String>err("error");

        var mapped = err.map(v -> v.length());

        assertEquals(err, mapped);
    }

    @Test
    void mapErr_TransformsError() {
        var err = Result.<String, String>err("fail");

        var mapped = err.mapErr(String::toUpperCase);

        assertEquals(Result.err("FAIL"), mapped);
    }

    @Test
    void match_CallsErrBranch() {
        var err = Result.err("boom");

        var result = err.match(v -> "ok", e -> "err: " + e);

        assertEquals("err: boom", result);
    }

    @Test
    void ok_ReturnsNone() {
        var err = Result.err("fail");

        assertTrue(err.ok().isNone());
    }

    @Test
    void or_ReturnsOther() {
        Result<String, String> err = Result.err("error");
        Result<String, String> fallback = Result.ok("default");

        var result = err.or(fallback);

        assertEquals(fallback, result);
    }

    @Test
    void orElse_AppliesFunction() {
        var err = Result.err("missing");

        var result = err.orElse(e -> Result.ok(e + "-handled"));

        assertEquals(Result.ok("missing-handled"), result);
    }

    @Test
    void toOptional_ReturnsEmpty() {
        var err = Result.err("fail");

        assertTrue(err.toOptional().isEmpty());
    }

    @Test
    void unwrap_ThrowsException() {
        var err = Result.err("fail");

        var ex = assertThrows(NoSuchElementException.class, err::unwrap);
        assertEquals("No value present.", ex.getMessage());
    }

    @Test
    void unwrapErr_ReturnsError() {
        var err = Result.err("boom");

        assertEquals("boom", err.unwrapErr());
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        var err = Result.err("e");

        assertEquals(err, err);
    }

    @Test
    void equals_SameValue_ReturnsTrue() {
        var a = Result.err("x");
        var b = Result.err("x");

        assertEquals(a, b);
    }

    @Test
    void equals_DifferentValues_ReturnsFalse() {
        var a = Result.err("a");
        var b = Result.err("b");

        assertNotEquals(a, b);
    }

    @Test
    void equals_Null_ReturnsFalse() {
        var err = Result.err("something");

        assertNotEquals(null, err);
    }

    @Test
    void equals_OtherType_ReturnsFalse() {
        var err = Result.err("something");

        assertNotEquals("something", err);
    }

    @Test
    void hashCode_SameForEqualErrors() {
        var a = Result.err("fail");
        var b = Result.err("fail");

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toString_FormatsCorrectly() {
        var err = Result.err("boom");

        assertEquals("Err(boom)", err.toString());
    }
}
