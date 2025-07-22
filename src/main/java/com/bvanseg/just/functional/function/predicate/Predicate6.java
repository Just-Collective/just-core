package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function6;

@FunctionalInterface
public interface Predicate6<A1, A2, A3, A4, A5, A6> extends Function6<A1, A2, A3, A4, A5, A6, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) {
        return test(a1, a2, a3, a4, a5, a6);
    }

    default Predicate6<A1, A2, A3, A4, A5, A6> and(
        Predicate6<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6> other
    ) {
        return (a1, a2, a3, a4, a5, a6) -> this.test(a1, a2, a3, a4, a5, a6) && other.test(a1, a2, a3, a4, a5, a6);
    }

    default Predicate6<A1, A2, A3, A4, A5, A6> or(
        Predicate6<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6> other
    ) {
        return (a1, a2, a3, a4, a5, a6) -> this.test(a1, a2, a3, a4, a5, a6) || other.test(a1, a2, a3, a4, a5, a6);
    }

    default Predicate6<A1, A2, A3, A4, A5, A6> negate() {
        return (a1, a2, a3, a4, a5, a6) -> !this.test(a1, a2, a3, a4, a5, a6);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> lift(
        Predicate5<? super A1, ? super A2, ? super A3, ? super A4, ? super A5> predicate
    ) {
        return (a1, a2, a3, a4, a5, _) -> predicate.test(a1, a2, a3, a4, a5);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> alwaysTrue() {
        return (_, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> alwaysFalse() {
        return (_, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> not(
        Predicate6<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6) -> !predicate.test(a1, a2, a3, a4, a5, a6);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, ? extends java.util.function.Function<? super A5, ? extends java.util.function.Function<? super A6, Boolean>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6) -> fn.apply(a1).apply(a2).apply(a3).apply(a4).apply(a5).apply(a6);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> from(
        Function6<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> named(
        String name,
        Predicate6<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6> delegate
    ) {
        return new Predicate6<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) {
                return delegate.test(a1, a2, a3, a4, a5, a6);
            }

            @Override
            public String toString() {
                return "Predicate6." + name;
            }
        };
    }

}
