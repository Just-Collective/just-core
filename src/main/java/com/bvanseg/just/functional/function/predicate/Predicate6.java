package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function6;

@FunctionalInterface
public interface Predicate6<A1, A2, A3, A4, A5, A6> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6);

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

    default Function6<A1, A2, A3, A4, A5, A6, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Boolean>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> this.test(a1, a2, a3, a4, a5, a6);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> lift(Predicate5<A1, A2, A3, A4, A5> predicate) {
        return (a1, a2, a3, a4, a5, _) -> predicate.test(a1, a2, a3, a4, a5);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> alwaysTrue() {
        return (_, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> alwaysFalse() {
        return (_, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> not(
        Predicate6<A1, A2, A3, A4, A5, A6> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6) -> !predicate.test(a1, a2, a3, a4, a5, a6);
    }

    static <A1, A2, A3, A4, A5, A6> Predicate6<A1, A2, A3, A4, A5, A6> named(
        String name,
        Predicate6<A1, A2, A3, A4, A5, A6> delegate
    ) {
        return new Predicate6<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) {
                return delegate.test(a1, a2, a3, a4, a5, a6);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }
}
