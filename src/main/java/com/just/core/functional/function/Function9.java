package com.just.core.functional.function;

import com.just.core.functional.function.memo.Memo9;
import com.just.core.functional.tuple.Tuple9;

@FunctionalInterface
public interface Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9);

    default <Z> Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> after.apply(this.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9));
    }

    default Memo9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> memoize() {
        return new Memo9<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Function<A8, Function<A9, R>>>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> a8 -> a9 -> this.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    default Function<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>, R> tupled() {
        return tuple -> this.apply(
            tuple.v1(),
            tuple.v2(),
            tuple.v3(),
            tuple.v4(),
            tuple.v5(),
            tuple.v6(),
            tuple.v7(),
            tuple.v8(),
            tuple.v9()
        );
    }

    default Function8<A2, A3, A4, A5, A6, A7, A8, A9, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5, a6, a7, a8, a9) -> this.apply(fixed, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    default Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> partialLast(A9 fixed) {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> this.apply(a1, a2, a3, a4, a5, a6, a7, a8, fixed);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, R> Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends Function<? super A6, ? extends Function<? super A7, ? extends Function<? super A8, ? extends Function<? super A9, ? extends R>>>>>>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8)
            .apply(a9);
    }
}
