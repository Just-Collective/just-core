package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo3;

@FunctionalInterface
public interface Function3<A1, A2, A3, R> {

    R apply(A1 a1, A2 a2, A3 a3);

    default <Z> Function3<A1, A2, A3, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
    }

    default Memo3<A1, A2, A3, R> memoize() {
        return new Memo3<>(this);
    }

    default Function<A1, Function<A2, Function<A3, R>>> curried() {
        return a1 -> a2 -> a3 -> this.apply(a1, a2, a3);
    }

    default Function2<A2, A3, R> partialFirst(A1 fixed) {
        return (a2, a3) -> this.apply(fixed, a2, a3);
    }

    default Function2<A1, A2, R> partialLast(A3 fixed) {
        return (a1, a2) -> this.apply(a1, a2, fixed);
    }

    static <A1, A2, A3, R> Function3<A1, A2, A3, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends R>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3) -> curried.apply(a1).apply(a2).apply(a3);
    }
}
