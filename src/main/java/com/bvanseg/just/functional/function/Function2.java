package com.bvanseg.just.functional.function;

import java.util.function.BiFunction;

import com.bvanseg.just.functional.function.memo.Memo2;

@FunctionalInterface
public interface Function2<A1, A2, R> extends BiFunction<A1, A2, R> {

    @Override
    R apply(A1 a1, A2 a2);

    default <Z> Function2<A1, A2, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2) -> after.apply(this.apply(a1, a2));
    }

    default Memo2<A1, A2, R> memoize() {
        return new Memo2<>(this);
    }

    default Function<A1, Function<A2, R>> curried() {
        return a1 -> a2 -> this.apply(a1, a2);
    }

    static <A1, A2, R> Function2<A1, A2, R> fromCurried(Function<A1, Function<A2, R>> curried) {
        return (a1, a2) -> curried.apply(a1).apply(a2);
    }
}
