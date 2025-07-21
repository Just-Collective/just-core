package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo7;

@FunctionalInterface
public interface Function7<A1, A2, A3, A4, A5, A6, A7, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7);

    default <Z> Function7<A1, A2, A3, A4, A5, A6, A7, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5, a6, a7) -> after.apply(this.apply(a1, a2, a3, a4, a5, a6, a7));
    }

    default Memo7<A1, A2, A3, A4, A5, A6, A7, R> memoize() {
        return new Memo7<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, R>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> this.apply(a1, a2, a3, a4, a5, a6, a7);
    }

    static <A1, A2, A3, A4, A5, A6, A7, R> Function7<A1, A2, A3, A4, A5, A6, A7, R> fromCurried(
        Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, R>>>>>>> curried
    ) {
        return (a1, a2, a3, a4, a5, a6, a7) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7);
    }
}
