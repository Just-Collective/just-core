package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function14;

@FunctionalInterface
public interface Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> extends Function14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, Boolean> {

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

    @Override
    default Boolean apply(
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
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
    }

    @Override
    default Predicate13<A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> partialFirst(A1 fixed) {
        return Function14.super.partialFirst(fixed)::apply;
    }

    @Override
    default Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> partialLast(A14 fixed) {
        return Function14.super.partialLast(fixed)::apply;
    }

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

    default Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> xor(
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
        ) ^ other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
    }

    default Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> implies(
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> other
    ) {
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> lift(
        Predicate13<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, $14) -> predicate.test(
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
        return ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> alwaysFalse() {
        return ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> not(
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> predicate
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, ? extends java.util.function.Function<? super A5, ? extends java.util.function.Function<? super A6, ? extends java.util.function.Function<? super A7, ? extends java.util.function.Function<? super A8, ? extends java.util.function.Function<? super A9, ? extends java.util.function.Function<? super A10, ? extends java.util.function.Function<? super A11, ? extends java.util.function.Function<? super A12, ? extends java.util.function.Function<? super A13, ? extends java.util.function.Function<? super A14, Boolean>>>>>>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14) -> fn.apply(a1)
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
            .apply(a13)
            .apply(a14);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> from(
        Function14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Predicate14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> named(
        String name,
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> delegate
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
                return "Predicate14." + name;
            }
        };
    }

}
