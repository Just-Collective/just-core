package com.bvanseg.just.functional.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class OptionTest {

    @Test
    void ofNullable_WithNonNull_ReturnsSome() {
        var result = Option.ofNullable("hello");
        assertTrue(result.isSome());
        assertEquals("hello", result.unwrap());
    }

    @Test
    void ofNullable_WithNull_ReturnsNone() {
        var result = Option.ofNullable(null);
        assertTrue(result.isNone());
    }

    @Test
    void flatten_SomeSome_ReturnsInner() {
        Option<Option<String>> nested = Option.some(Option.some("value"));
        var flat = Option.flatten(nested);
        assertEquals(Option.some("value"), flat);
    }

    @Test
    void flatten_SomeNone_ReturnsNone() {
        var nested = Option.some(Option.<String>none());
        var flat = Option.flatten(nested);
        assertTrue(flat.isNone());
    }

    @Test
    void flatten_None_ReturnsNone() {
        var nested = Option.<Option<String>>none();
        var flat = Option.flatten(nested);
        assertTrue(flat.isNone());
    }

    @Test
    void guard_ConditionTrue_ReturnsSome() {
        var result = Option.guard(true, 123);
        assertTrue(result.isSome());
        assertEquals(123, result.unwrap());
    }

    @Test
    void guard_ConditionFalse_ReturnsNone() {
        var result = Option.guard(false, 123);
        assertTrue(result.isNone());
    }

    @Test
    void when_ConditionTrue_InvokesSupplier() {
        var result = Option.when(true, () -> Option.some("value"));
        assertEquals(Option.some("value"), result);
    }

    @Test
    void when_ConditionFalse_SkipsSupplier() {
        var result = Option.when(false, () -> {
            fail("Supplier should not be invoked.");
            return Option.none();
        });
        assertTrue(result.isNone());
    }

    @Test
    void map2_BothSome_Combines() {
        var a = Option.some(2);
        var b = Option.some(3);
        var result = Option.map2(a, b, (x, y) -> x + y);
        assertEquals(Option.some(5), result);
    }

    @Test
    void map2_FirstNone_ReturnsNone() {
        var a = Option.<Integer>none();
        var b = Option.some(3);
        var result = Option.map2(a, b, (x, y) -> x + y);
        assertTrue(result.isNone());
    }

    @Test
    void map2_SecondNone_ReturnsNone() {
        var a = Option.some(2);
        var b = Option.<Integer>none();
        var result = Option.map2(a, b, (x, y) -> x + y);
        assertTrue(result.isNone());
    }

    @Test
    void map3_AllSome_Combines() {
        var a = Option.some(1);
        var b = Option.some(2);
        var c = Option.some(3);
        var result = Option.map3(a, b, c, (x, y, z) -> x + y + z);
        assertEquals(Option.some(6), result);
    }

    @Test
    void map3_OneNone_ReturnsNone() {
        var a = Option.some(1);
        var b = Option.<Integer>none();
        var c = Option.some(3);
        var result = Option.map3(a, b, c, (x, y, z) -> x + y + z);
        assertTrue(result.isNone());
    }

    @Test
    void reduce_OnSome_AppliesFunction() {
        var some = Option.some(10);
        var result = some.reduce(x -> x + 5, 0);
        assertEquals(15, result);
    }

    @Test
    void reduce_OnNone_ReturnsIdentity() {
        var none = Option.<Integer>none();
        var result = none.reduce(x -> x + 5, 0);
        assertEquals(0, result);
    }
}
