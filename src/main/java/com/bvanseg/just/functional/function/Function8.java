package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo8;

@FunctionalInterface
public interface Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8);

    default <Z> Function8<A1, A2, A3, A4, A5, A6, A7, A8, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> after.apply(this.apply(a1, a2, a3, a4, a5, a6, a7, a8));
    }

    default Memo8<A1, A2, A3, A4, A5, A6, A7, A8, R> memoize() {
        return new Memo8<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Function<A8, R>>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> a8 -> this.apply(a1, a2, a3, a4, a5, a6, a7, a8);
    }

    default Function7<A2, A3, A4, A5, A6, A7, A8, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5, a6, a7, a8) -> this.apply(fixed, a2, a3, a4, a5, a6, a7, a8);
    }

    default Function7<A1, A2, A3, A4, A5, A6, A7, R> partialLast(A8 fixed) {
        return (a1, a2, a3, a4, a5, a6, a7) -> this.apply(a1, a2, a3, a4, a5, a6, a7, fixed);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, R> Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends Function<? super A6, ? extends Function<? super A7, ? extends Function<? super A8, ? extends R>>>>>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8);
    }
}
