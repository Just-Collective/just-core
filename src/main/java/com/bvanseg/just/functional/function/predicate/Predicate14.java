package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function14;

@FunctionalInterface
public interface Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> {

    boolean test(
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
        A13 a13,
        A14 a14
    );

    default Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> and(
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) -> this.test(
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
            a13,
            a14
        ) && other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
    }

    default Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> or(
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) -> this.test(
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
            a13,
            a14
        ) || other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
    }

    default Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) -> !this.test(
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
            a13,
            a14
        );
    }

    default Function14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Function<A8, Function<A9, Function<A10, Function<A11, Function<A12, Function<A13, Function<A14, Boolean>>>>>>>>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> a8 -> a9 -> a10 -> a11 -> a12 -> a13 -> a14 -> this.test(
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
            a13,
            a14
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> lift(
        Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, _) -> predicate.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> not(
        Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) -> !predicate.test(
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
            a13,
            a14
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> named(
        String name,
        Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> delegate
    ) {
        return new Predicate14<>() {

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
                A12 a12,
                A13 a13,
                A14 a14
            ) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }
}
