package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo6;

@FunctionalInterface
public interface Function6<A1, A2, A3, A4, A5, A6, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6);

    default <Z> Function6<A1, A2, A3, A4, A5, A6, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5, a6) -> after.apply(this.apply(a1, a2, a3, a4, a5, a6));
    }

    default Memo6<A1, A2, A3, A4, A5, A6, R> memoize() {
        return new Memo6<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, R>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> this.apply(a1, a2, a3, a4, a5, a6);
    }

    default Function5<A2, A3, A4, A5, A6, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5, a6) -> this.apply(fixed, a2, a3, a4, a5, a6);
    }

    default Function5<A1, A2, A3, A4, A5, R> partialLast(A6 fixed) {
        return (a1, a2, a3, a4, a5) -> this.apply(a1, a2, a3, a4, a5, fixed);
    }

    static <A1, A2, A3, A4, A5, A6, R> Function6<A1, A2, A3, A4, A5, A6, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends Function<? super A6, ? extends R>>>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6);
    }
}
