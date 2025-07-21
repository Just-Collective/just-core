package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function9;

@FunctionalInterface
public interface Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9);

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

    default Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Function<A8, Function<A9, Boolean>>>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> a8 -> a9 -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
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
                return name;
            }
        };
    }
}
