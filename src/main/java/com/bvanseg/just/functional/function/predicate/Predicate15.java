package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function15;

@FunctionalInterface
public interface Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> extends Function15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, Boolean> {

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
        A14 a14,
        A15 a15
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
        A14 a14,
        A15 a15
    ) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
    }

    default Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> and(
        Predicate15<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, ? super A15> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) -> this.test(
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
            a14,
            a15
        ) && other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
    }

    default Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> or(
        Predicate15<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, ? super A15> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) -> this.test(
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
            a14,
            a15
        ) || other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
    }

    default Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) -> !this.test(
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
            a14,
            a15
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> lift(
        Predicate14<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, _) -> predicate.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> not(
        Predicate15<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, ? super A15> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) -> !predicate.test(
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
            a14,
            a15
        );
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, ? extends java.util.function.Function<? super A5, ? extends java.util.function.Function<? super A6, ? extends java.util.function.Function<? super A7, ? extends java.util.function.Function<? super A8, ? extends java.util.function.Function<? super A9, ? extends java.util.function.Function<? super A10, ? extends java.util.function.Function<? super A11, ? extends java.util.function.Function<? super A12, ? extends java.util.function.Function<? super A13, ? extends java.util.function.Function<? super A14, ? extends java.util.function.Function<? super A15, Boolean>>>>>>>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15) -> fn.apply(a1)
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
            .apply(a14)
            .apply(a15);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> from(
        Function15<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, ? super A15, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Predicate15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> named(
        String name,
        Predicate15<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, ? super A14, ? super A15> delegate
    ) {
        return new Predicate15<>() {

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
                A14 a14,
                A15 a15
            ) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
            }

            @Override
            public String toString() {
                return "Predicate15." + name;
            }
        };
    }

}
