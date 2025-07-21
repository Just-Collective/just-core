package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function8;

@FunctionalInterface
public interface Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> extends Function8<A1, A2, A3, A4, A5, A6, A7, A8, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8);
    }

    default Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> and(
        Predicate8<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8) && other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8
        );
    }

    default Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> or(
        Predicate8<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8) || other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7,
            a8
        );
    }

    default Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> !this.test(a1, a2, a3, a4, a5, a6, a7, a8);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8> Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> lift(
        Predicate7<A1, A2, A3, A4, A5, A6, A7> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, _) -> predicate.test(a1, a2, a3, a4, a5, a6, a7);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8> Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> alwaysTrue() {
        return (_, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8> Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> alwaysFalse() {
        return (_, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8> Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> not(
        Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8) -> !predicate.test(a1, a2, a3, a4, a5, a6, a7, a8);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8> Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> named(
        String name,
        Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> delegate
    ) {
        return new Predicate8<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

}
