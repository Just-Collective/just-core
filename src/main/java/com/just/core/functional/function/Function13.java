package com.just.core.functional.function;

import com.just.core.functional.function.memo.Memo13;
import com.just.core.functional.tuple.Tuple13;

@FunctionalInterface
public interface Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13);

    default <Z> Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, Z> andThen(
        Function<? super R, ? extends Z> after
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> after.apply(
            this.apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13)
        );
    }

    default Memo13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> memoize() {
        return new Memo13<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Function<A8, Function<A9, Function<A10, Function<A11, Function<A12, Function<A13, R>>>>>>>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> a8 -> a9 -> a10 -> a11 -> a12 -> a13 -> this.apply(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8,
            a9,
            a10,
            a11,
            a12,
            a13
        );
    }

    default Function<Tuple13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13>, R> tupled() {
        return tuple -> this.apply(
            tuple.v1(),
            tuple.v2(),
            tuple.v3(),
            tuple.v4(),
            tuple.v5(),
            tuple.v6(),
            tuple.v7(),
            tuple.v8(),
            tuple.v9(),
            tuple.v10(),
            tuple.v11(),
            tuple.v12(),
            tuple.v13()
        );
    }

    default Function12<A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> this.apply(
            fixed,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8,
            a9,
            a10,
            a11,
            a12,
            a13
        );
    }

    default Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> partialLast(A13 fixed) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) -> this.apply(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8,
            a9,
            a10,
            a11,
            a12,
            fixed
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends Function<? super A6, ? extends Function<? super A7, ? extends Function<? super A8, ? extends Function<? super A9, ? extends Function<? super A10, ? extends Function<? super A11, ? extends Function<? super A12, ? extends Function<? super A13, ? extends R>>>>>>>>>>>>> curried
    ) {
        return (
            A1 a1,
            A2 a2,
            A3 a3,
            A4 a4,
            A5 a5,
            A6 a6,
            A7 a7,
            A8 a8,
            A9 a9,
            A10 a10,
            A11 a11,
            A12 a12,
            A13 a13
        ) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8)
            .apply(a9)
            .apply(a10)
            .apply(a11)
            .apply(a12)
            .apply(a13);
    }
}
