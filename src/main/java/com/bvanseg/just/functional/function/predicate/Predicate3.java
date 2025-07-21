package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function;
import com.bvanseg.just.functional.function.Function3;

@FunctionalInterface
public interface Predicate3<A1, A2, A3> {

    boolean test(A1 a1, A2 a2, A3 a3);

    default Predicate3<A1, A2, A3> and(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> this.test(a1, a2, a3) && other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> or(Predicate3<? super A1, ? super A2, ? super A3> other) {
        return (a1, a2, a3) -> this.test(a1, a2, a3) || other.test(a1, a2, a3);
    }

    default Predicate3<A1, A2, A3> negate() {
        return (a1, a2, a3) -> !this.test(a1, a2, a3);
    }

    default Function3<A1, A2, A3, Boolean> toFunction() {
        return this::test;
    }

    default Function<A1, Function<A2, Function<A3, Boolean>>> curried() {
        return a1 -> a2 -> a3 -> this.test(a1, a2, a3);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> lift(Predicate2<A1, A2> predicate) {
        return (a1, a2, _) -> predicate.test(a1, a2);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> alwaysTrue() {
        return (_, _, _) -> true;
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> alwaysFalse() {
        return (_, _, _) -> false;
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> not(Predicate3<A1, A2, A3> predicate) {
        return (a1, a2, a3) -> !predicate.test(a1, a2, a3);
    }

    static <A1, A2, A3> Predicate3<A1, A2, A3> named(String name, Predicate3<A1, A2, A3> delegate) {
        return new Predicate3<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3) {
                return delegate.test(a1, a2, a3);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }
}
