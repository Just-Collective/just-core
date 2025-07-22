package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function5;

@FunctionalInterface
public interface Predicate5<A1, A2, A3, A4, A5> extends Function5<A1, A2, A3, A4, A5, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) {
        return test(a1, a2, a3, a4, a5);
    }

    default Predicate5<A1, A2, A3, A4, A5> and(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> other
    ) {
        return (a1, a2, a3, a4, a5) -> this.test(a1, a2, a3, a4, a5) && other.test(a1, a2, a3, a4, a5);
    }

    default Predicate5<A1, A2, A3, A4, A5> or(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> other
    ) {
        return (a1, a2, a3, a4, a5) -> this.test(a1, a2, a3, a4, a5) || other.test(a1, a2, a3, a4, a5);
    }

    default Predicate5<A1, A2, A3, A4, A5> xor(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> other
    ) {
        return (a1, a2, a3, a4, a5) -> this.test(a1, a2, a3, a4, a5) ^ other.test(a1, a2, a3, a4, a5);
    }

    default Predicate5<A1, A2, A3, A4, A5> implies(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> other
    ) {
        return (a1, a2, a3, a4, a5) -> !this.test(a1, a2, a3, a4, a5) || other.test(a1, a2, a3, a4, a5);
    }

    default Predicate5<A1, A2, A3, A4, A5> negate() {
        return (a1, a2, a3, a4, a5) -> !this.test(a1, a2, a3, a4, a5);
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> lift(
        Predicate4<? super A1, ? super A2, ? super A3, ? super A4> predicate
    ) {
        return (a1, a2, a3, a4, _) -> predicate.test(a1, a2, a3, a4);
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> alwaysTrue() {
        return (_, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> alwaysFalse() {
        return (_, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> not(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> predicate
    ) {
        return (a1, a2, a3, a4, a5) -> !predicate.test(a1, a2, a3, a4, a5);
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, ? extends java.util.function.Function<? super A5, Boolean>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5) -> fn.apply(a1).apply(a2).apply(a3).apply(a4).apply(a5);
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> from(
        Function5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5> Predicate5<A1, A2, A3, A4, A5> named(
        String name,
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> delegate
    ) {
        return new Predicate5<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) {
                return delegate.test(a1, a2, a3, a4, a5);
            }

            @Override
            public String toString() {
                return "Predicate5." + name;
            }
        };
    }

}
