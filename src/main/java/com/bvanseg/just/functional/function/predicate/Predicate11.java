package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function11;

@FunctionalInterface
public interface Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> extends Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
    }

    default Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> and(
        Predicate11<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)
            && other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
    }

    default Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> or(
        Predicate11<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)
            || other.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
    }

    default Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) -> !this.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> lift(
        Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, _) -> predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> not(
        Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) -> !predicate.test(
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

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> from(
        java.util.function.Function<A1, ? extends java.util.function.Function<A2, ? extends java.util.function.Function<A3, ? extends java.util.function.Function<A4, ? extends java.util.function.Function<A5, ? extends java.util.function.Function<A6, ? extends java.util.function.Function<A7, ? extends java.util.function.Function<A8, ? extends java.util.function.Function<A9, ? extends java.util.function.Function<A10, ? extends java.util.function.Function<A11, Boolean>>>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11) -> fn.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8)
            .apply(a9)
            .apply(a10)
            .apply(a11);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> from(
        Function11<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, ? super A11, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> named(
        String name,
        Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> delegate
    ) {
        return new Predicate11<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
            }

            @Override
            public String toString() {
                return "Predicate11." + name;
            }
        };
    }

}
