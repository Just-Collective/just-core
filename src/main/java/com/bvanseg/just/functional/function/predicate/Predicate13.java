package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function13;

@FunctionalInterface
public interface Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> extends Function13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13);

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
        A13 a13
    ) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
    }

    default Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> and(
        Predicate13<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> this.test(
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
        ) && other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
    }

    default Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> or(
        Predicate13<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> this.test(
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
        ) || other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
    }

    default Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> !this.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> lift(
        Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, _) -> predicate.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> not(
        Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> !predicate.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> from(
        java.util.function.Function<A1, ? extends java.util.function.Function<A2, ? extends java.util.function.Function<A3, ? extends java.util.function.Function<A4, ? extends java.util.function.Function<A5, ? extends java.util.function.Function<A6, ? extends java.util.function.Function<A7, ? extends java.util.function.Function<A8, ? extends java.util.function.Function<A9, ? extends java.util.function.Function<A10, ? extends java.util.function.Function<A11, ? extends java.util.function.Function<A12, ? extends java.util.function.Function<A13, Boolean>>>>>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) -> fn.apply(a1)
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> from(
        Function13<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, ? super A12, ? super A13, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> named(
        String name,
        Predicate13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> delegate
    ) {
        return new Predicate13<>() {

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
                A13 a13
            ) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
            }

            @Override
            public String toString() {
                return "Predicate13." + name;
            }
        };
    }

}
