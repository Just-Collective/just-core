package com.bvanseg.just.functional.either;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.bvanseg.just.functional.option.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LeftTest {

    @Test
    void testIsLeftAndIsRight() {
        Either<String, Integer> either = new Left<>("error");

        assertTrue(either.isLeft());
        assertFalse(either.isRight());
    }

    @Test
    void testExpectLeftReturnsValue() {
        Either<String, Integer> either = new Left<>("left");

        assertEquals("left", either.expectLeft("should not throw"));
    }

    @Test
    void testExpectRightThrows() {
        Either<String, Integer> either = new Left<>("oops");

        assertThrows(NoSuchElementException.class, () -> either.expectRight("right not present"));
    }

    @Test
    void testFlipConvertsToRight() {
        Either<String, Integer> either = new Left<>("fail");
        Either<Integer, String> flipped = either.flip();

        assertTrue(flipped.isRight());
        assertEquals("fail", flipped.expectRight("should be right"));
    }

    @Test
    void testIfLeftInvokesConsumer() {
        Either<String, Integer> either = new Left<>("present");
        AtomicReference<String> result = new AtomicReference<>();

        either.ifLeft(result::set);

        assertEquals("present", result.get());
    }

    @Test
    void testIfRightDoesNothing() {
        Either<String, Integer> either = new Left<>("ignored");
        AtomicBoolean called = new AtomicBoolean(false);

        either.ifRight(val -> called.set(true));

        assertFalse(called.get());
    }

    @Test
    void testInspectLeftReturnsSelfAndInvokesConsumer() {
        Either<String, Integer> either = new Left<>("check");
        AtomicReference<String> result = new AtomicReference<>();

        Either<String, Integer> returned = either.inspectLeft(result::set);

        assertEquals("check", result.get());
        assertSame(either, returned);
    }

    @Test
    void testInspectRightReturnsSelfOnly() {
        Either<String, Integer> either = new Left<>("left");
        Either<String, Integer> returned = either.inspectRight(val -> fail("Should not be called"));

        assertSame(either, returned);
    }

    @Test
    void testIsLeftAndWithMatchingPredicate() {
        Either<String, Integer> either = new Left<>("match");

        assertTrue(either.isLeftAnd(val -> val.equals("match")));
    }

    @Test
    void testIsLeftOrAlwaysTrue() {
        Either<String, Integer> either = new Left<>("whatever");

        assertTrue(either.isLeftOr(val -> false)); // left always returns true
    }

    @Test
    void testIsRightAndAlwaysFalse() {
        Either<String, Integer> either = new Left<>("nope");

        assertFalse(either.isRightAnd(val -> true));
    }

    @Test
    void testIsRightOrPredicateUsed() {
        Either<String, Integer> either = new Left<>("check");

        assertTrue(either.isRightOr(val -> val.startsWith("ch")));
        assertFalse(either.isRightOr(val -> val.startsWith("x")));
    }

    @Test
    void testLeftReturnsOptionSome() {
        Either<String, Integer> either = new Left<>("x");

        assertEquals(Option.some("x"), either.left());
    }

    @Test
    void testRightReturnsOptionNone() {
        Either<String, Integer> either = new Left<>("x");

        assertEquals(Option.none(), either.right());
    }

    @Test
    void testMapLeftTransformsLeftValue() {
        Either<String, Integer> either = new Left<>("fail");

        Either<Integer, Integer> mapped = either.mapLeft(String::length);

        assertTrue(mapped.isLeft());
        assertEquals(4, mapped.expectLeft("should be length of 'fail'"));
    }

    @Test
    void testMapRightIsNoop() {
        Either<String, Integer> either = new Left<>("unchanged");

        Either<String, String> mapped = either.mapRight(i -> "mapped");

        assertTrue(mapped.isLeft());
        assertEquals("unchanged", mapped.expectLeft("should be same as original"));
    }

    @Test
    void testUnwrapLeftReturnsValue() {
        Either<String, Integer> either = new Left<>("unwrap");

        assertEquals("unwrap", either.unwrapLeft());
    }

    @Test
    void testUnwrapRightThrows() {
        Either<String, Integer> either = new Left<>("fail");

        assertThrows(NoSuchElementException.class, either::unwrapRight);
    }

    @Test
    void testLeftOrReturnsSelfValue() {
        Either<String, Integer> either = new Left<>("original");

        assertEquals("original", either.leftOr("fallback"));
    }

    @Test
    void testLeftOrElseReturnsSelfValue() {
        Either<String, Integer> either = new Left<>("val");

        assertEquals("val", either.leftOrElse(() -> "other"));
    }

    @Test
    void testRightOrReturnsFallback() {
        Either<String, Integer> either = new Left<>("fail");

        assertEquals(42, either.rightOr(42));
    }

    @Test
    void testRightOrElseUsesSupplier() {
        Either<String, Integer> either = new Left<>("missing");

        assertEquals(100, either.rightOrElse(() -> 100));
    }
}
