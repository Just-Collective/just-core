package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function4;

@FunctionalInterface
public interface Predicate4<A1, A2, A3, A4> extends Function4<A1, A2, A3, A4, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4) {
        return test(a1, a2, a3, a4);
    }

    default Predicate4<A1, A2, A3, A4> and(Predicate4<? super A1, ? super A2, ? super A3, ? super A4> other) {
        return (a1, a2, a3, a4) -> this.test(a1, a2, a3, a4) && other.test(a1, a2, a3, a4);
    }

    default Predicate4<A1, A2, A3, A4> or(Predicate4<? super A1, ? super A2, ? super A3, ? super A4> other) {
        return (a1, a2, a3, a4) -> this.test(a1, a2, a3, a4) || other.test(a1, a2, a3, a4);
    }

    default Predicate4<A1, A2, A3, A4> negate() {
        return (a1, a2, a3, a4) -> !this.test(a1, a2, a3, a4);
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> lift(Predicate3<? super A1, ? super A2, ? super A3> predicate) {
        return (a1, a2, a3, _) -> predicate.test(a1, a2, a3);
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> alwaysTrue() {
        return (_, _, _, _) -> true;
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> alwaysFalse() {
        return (_, _, _, _) -> false;
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> not(
        Predicate4<? super A1, ? super A2, ? super A3, ? super A4> predicate
    ) {
        return (a1, a2, a3, a4) -> !predicate.test(a1, a2, a3, a4);
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, Boolean>>>> fn
    ) {
        return (a1, a2, a3, a4) -> fn.apply(a1).apply(a2).apply(a3).apply(a4);
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> from(
        Function4<? super A1, ? super A2, ? super A3, ? super A4, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4> Predicate4<A1, A2, A3, A4> named(
        String name,
        Predicate4<? super A1, ? super A2, ? super A3, ? super A4> delegate
    ) {
        return new Predicate4<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4) {
                return delegate.test(a1, a2, a3, a4);
            }

            @Override
            public String toString() {
                return "Predicate4." + name;
            }
        };
    }

}
