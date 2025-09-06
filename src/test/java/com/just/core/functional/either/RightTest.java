package com.just.core.functional.either;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.just.core.functional.option.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class RightTest {

    @Test
    void testIsLeftAndIsRight() {
        Either<String, Integer> either = new Right<>(42);

        assertFalse(either.isLeft());
        assertTrue(either.isRight());
    }

    @Test
    void testExpectLeftThrows() {
        Either<String, Integer> either = new Right<>(999);

        assertThrows(NoSuchElementException.class, () -> either.expectLeft("no left"));
    }

    @Test
    void testExpectRightReturnsValue() {
        Either<String, Integer> either = new Right<>(123);

        assertEquals(123, either.expectRight("should not throw"));
    }

    @Test
    void testFlipConvertsToLeft() {
        Either<String, Integer> either = new Right<>(777);
        Either<Integer, String> flipped = either.flip();

        assertTrue(flipped.isLeft());
        assertEquals(777, flipped.expectLeft("should be left"));
    }

    @Test
    void testIfLeftDoesNothing() {
        Either<String, Integer> either = new Right<>(888);
        AtomicBoolean called = new AtomicBoolean(false);

        either.ifLeft(l -> called.set(true));

        assertFalse(called.get());
    }

    @Test
    void testIfRightInvokesConsumer() {
        Either<String, Integer> either = new Right<>(1234);
        AtomicReference<Integer> ref = new AtomicReference<>();

        either.ifRight(ref::set);

        assertEquals(1234, ref.get());
    }

    @Test
    void testInspectLeftReturnsSelfOnly() {
        Either<String, Integer> either = new Right<>(1000);
        Either<String, Integer> result = either.inspectLeft(l -> fail("Should not be called"));

        assertSame(either, result);
    }

    @Test
    void testInspectRightInvokesConsumerAndReturnsSelf() {
        Either<String, Integer> either = new Right<>(2000);
        AtomicReference<Integer> ref = new AtomicReference<>();

        Either<String, Integer> result = either.inspectRight(ref::set);

        assertEquals(2000, ref.get());
        assertSame(either, result);
    }

    @Test
    void testIsRightAndMatches() {
        Either<String, Integer> either = new Right<>(99);

        assertTrue(either.isRightAnd(r -> r == 99));
    }

    @Test
    void testIsLeftAndAlwaysFalse() {
        Either<String, Integer> either = new Right<>(50);

        assertFalse(either.isLeftAnd(l -> true));
    }

    @Test
    void testIsLeftOrUsesRightPredicate() {
        Either<String, Integer> either = new Right<>(42);

        assertTrue(either.isLeftOr(r -> r == 42));
        assertFalse(either.isLeftOr(r -> r == 0));
    }

    @Test
    void testIsRightOrAlwaysTrue() {
        Either<String, Integer> either = new Right<>(55);

        assertTrue(either.isRightOr(l -> false));
    }

    @Test
    void testLeftReturnsNone() {
        Either<String, Integer> either = new Right<>(1);

        assertEquals(Option.none(), either.left());
    }

    @Test
    void testRightReturnsSome() {
        Either<String, Integer> either = new Right<>(5);

        assertEquals(Option.some(5), either.right());
    }

    @Test
    void testMapLeftIsNoop() {
        Either<String, Integer> either = new Right<>(77);
        Either<Integer, Integer> result = either.mapLeft(l -> 999); // should not apply

        assertTrue(result.isRight());
        assertEquals(77, result.expectRight("still same right value"));
    }

    @Test
    void testMapRightAppliesFunction() {
        Either<String, Integer> either = new Right<>(10);
        Either<String, String> mapped = either.mapRight(i -> "num:" + i);

        assertTrue(mapped.isRight());
        assertEquals("num:10", mapped.expectRight("mapped string"));
    }

    @Test
    void testUnwrapLeftThrows() {
        Either<String, Integer> either = new Right<>(66);

        assertThrows(NoSuchElementException.class, either::unwrapLeft);
    }

    @Test
    void testUnwrapRightReturnsValue() {
        Either<String, Integer> either = new Right<>(456);

        assertEquals(456, either.unwrapRight());
    }

    @Test
    void testLeftOrReturnsFallback() {
        Either<String, Integer> either = new Right<>(321);

        assertEquals("default", either.leftOr("default"));
    }

    @Test
    void testLeftOrElseUsesSupplier() {
        Either<String, Integer> either = new Right<>(999);

        assertEquals("supplied", either.leftOrElse(() -> "supplied"));
    }

    @Test
    void testRightOrReturnsRight() {
        Either<String, Integer> either = new Right<>(5);

        assertEquals(5, either.rightOr(0));
    }

    @Test
    void testRightOrElseReturnsRight() {
        Either<String, Integer> either = new Right<>(6);

        assertEquals(6, either.rightOrElse(() -> 999));
    }
}
