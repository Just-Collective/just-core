package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo4;

@FunctionalInterface
public interface Function4<A1, A2, A3, A4, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4);

    default <Z> Function4<A1, A2, A3, A4, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4) -> after.apply(this.apply(a1, a2, a3, a4));
    }

    default Memo4<A1, A2, A3, A4, R> memoize() {
        return new Memo4<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, R>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> this.apply(a1, a2, a3, a4);
    }

    default Function3<A2, A3, A4, R> partialFirst(A1 fixed) {
        return (a2, a3, a4) -> this.apply(fixed, a2, a3, a4);
    }

    default Function3<A1, A2, A3, R> partialLast(A4 fixed) {
        return (a1, a2, a3) -> this.apply(a1, a2, a3, fixed);
    }

    static <A1, A2, A3, A4, R> Function4<A1, A2, A3, A4, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends R>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4) -> curried.apply(a1).apply(a2).apply(a3).apply(a4);
    }
}
