package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function12;

@FunctionalInterface
public interface Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> extends Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }

    default Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> and(
        Predicate12<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) -> this.test(
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
            a12
        ) && other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }

    default Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> or(
        Predicate12<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) -> this.test(
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
            a12
        ) || other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }

    default Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) -> !this.test(
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
            a12
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> lift(
        Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, _) -> predicate.test(
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
            a11
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> not(
        Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12) -> !predicate.test(
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
            a12
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> named(
        String name,
        Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> delegate
    ) {
        return new Predicate12<>() {

            @Override
            public boolean test(
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
                A12 a12
            ) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

}
