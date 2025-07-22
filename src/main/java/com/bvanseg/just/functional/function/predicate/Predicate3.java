package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function3;

@FunctionalInterface
public interface Predicate3<A1, A2, A3> extends Function3<A1, A2, A3, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3) {
        return test(a1, a2, a3);
    }

    @Override
    default Predicate2<A2, A3> partialFirst(A1 fixed) {
        return Function3.super.partialFirst(fixed)::apply;
    }

    @Override
    default Predicate2<A1, A2> partialLast(A3 fixed) {
        return Function3.super.partialLast(fixed)::apply;
    }

    default Predicate3<A1, A2, A3> and(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> this.test(a1, a2, a3) && other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> or(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> this.test(a1, a2, a3) || other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> xor(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> this.test(a1, a2, a3) ^ other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> implies(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> !this.test(a1, a2, a3) || other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> negate() {
        return (a1, a2, a3) -> !this.test(a1, a2, a3);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> lift(Predicate2<? super A1, ? super A2> predicate) {
        return (a1, a2, $3) -> predicate.test(a1, a2);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> alwaysTrue() {
        return ($1, $2, $3) -> true;
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> alwaysFalse() {
        return ($1, $2, $3) -> false;
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> not(Predicate3<? super A1, ? super A2, ? super A3> predicate) {
        return (a1, a2, a3) -> !predicate.test(a1, a2, a3);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, Boolean>>> fn
    ) {
        return (a1, a2, a3) -> fn.apply(a1).apply(a2).apply(a3);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> from(Function3<? super A1, ? super A2, ? super A3, Boolean> fn) {
        return fn::apply;
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> named(
        String name,
        Predicate3<? super A1, ? super A2, ? super A3> delegate
    ) {
        return new Predicate3<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3) {
                return delegate.test(a1, a2, a3);
            }

            @Override
            public String toString() {
                return "Predicate3." + name;
            }
        };
    }

}
