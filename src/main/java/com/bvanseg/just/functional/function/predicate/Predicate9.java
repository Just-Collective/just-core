package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function9;

@FunctionalInterface
public interface Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> extends Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    default Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> and(
        Predicate9<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9) && other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8,
            a9
        );
    }

    default Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> or(
        Predicate9<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9) || other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8,
            a9
        );
    }

    default Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> !this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> lift(
        Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, _) -> predicate.test(a1, a2, a3, a4, a5, a6, a7, a8);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> not(
        Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> !predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> from(
        java.util.function.Function<A1, ? extends java.util.function.Function<A2, ? extends java.util.function.Function<A3, ? extends java.util.function.Function<A4, ? extends java.util.function.Function<A5, ? extends java.util.function.Function<A6, ? extends java.util.function.Function<A7, ? extends java.util.function.Function<A8, ? extends java.util.function.Function<A9, Boolean>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9) -> fn.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8)
            .apply(a9);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> from(
        Function9<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> named(
        String name,
        Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> delegate
    ) {
        return new Predicate9<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
            }

            @Override
            public String toString() {
                return "Predicate9." + name;
            }
        };
    }

}
