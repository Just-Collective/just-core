package com.bvanseg.just.functional.function.predicate;

import java.util.function.Predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function2;

@FunctionalInterface
public interface Predicate2<A1, A2> {

    boolean test(A1 a1, A2 a2);

    default Predicate2<A1, A2> and(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> this.test(a1, a2) && other.test(a1, a2);
    }

    default Predicate2<A1, A2> or(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> this.test(a1, a2) || other.test(a1, a2);
    }

    default Predicate2<A1, A2> negate() {
        return (a1, a2) -> !this.test(a1, a2);
    }

    default Function2<A1, A2, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Boolean>> curried() {
        return a1 -> a2 -> this.test(a1, a2);
    }

    static <A1, A2> Predicate2<A1, A2> lift(Predicate<A1> predicate) {
        return (a1, _) -> predicate.test(a1);
    }

    static <A1, A2> Predicate2<A1, A2> alwaysTrue() {
        return (_, _) -> true;
    }

    static <A1, A2> Predicate2<A1, A2> alwaysFalse() {
        return (_, _) -> false;
    }

    static <A1, A2> Predicate2<A1, A2> not(Predicate2<A1, A2> predicate) {
        return (a1, a2) -> !predicate.test(a1, a2);
    }

    static <A1, A2> Predicate2<A1, A2> named(String name, Predicate2<A1, A2> delegate) {
        return new Predicate2<>() {

            @Override
            public boolean test(A1 a1, A2 a2) {
                return delegate.test(a1, a2);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }
}
