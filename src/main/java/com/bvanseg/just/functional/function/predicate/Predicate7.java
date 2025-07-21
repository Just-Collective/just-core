package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function7;

@FunctionalInterface
public interface Predicate7<A1, A2, A3, A4, A5, A6, A7> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7);

    default Predicate7<A1, A2, A3, A4, A5, A6, A7> and(
        Predicate7<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7) -> this.test(a1, a2, a3, a4, a5, a6, a7) && other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7
        );
    }

    default Predicate7<A1, A2, A3, A4, A5, A6, A7> or(
        Predicate7<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7) -> this.test(a1, a2, a3, a4, a5, a6, a7) || other.test(
            a1,
            a2,
            a3,
            a4,
            a5,
            a6,
            a7
        );
    }

    default Predicate7<A1, A2, A3, A4, A5, A6, A7> negate() {
        return (a1, a2, a3, a4, a5, a6, a7) -> !this.test(a1, a2, a3, a4, a5, a6, a7);
    }

    default Function7<A1, A2, A3, A4, A5, A6, A7, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, Boolean>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> this.test(a1, a2, a3, a4, a5, a6, a7);
    }

    static <A1, A2, A3, A4, A5, A6, A7> Predicate7<A1, A2, A3, A4, A5, A6, A7> lift(
        Predicate6<A1, A2, A3, A4, A5, A6> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, _) -> predicate.test(a1, a2, a3, a4, a5, a6);
    }

    static <A1, A2, A3, A4, A5, A6, A7> Predicate7<A1, A2, A3, A4, A5, A6, A7> alwaysTrue() {
        return (_, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7> Predicate7<A1, A2, A3, A4, A5, A6, A7> alwaysFalse() {
        return (_, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7> Predicate7<A1, A2, A3, A4, A5, A6, A7> not(
        Predicate7<A1, A2, A3, A4, A5, A6, A7> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7) -> !predicate.test(a1, a2, a3, a4, a5, a6, a7);
    }

    static <A1, A2, A3, A4, A5, A6, A7> Predicate7<A1, A2, A3, A4, A5, A6, A7> named(
        String name,
        Predicate7<A1, A2, A3, A4, A5, A6, A7> delegate
    ) {
        return new Predicate7<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }
}
