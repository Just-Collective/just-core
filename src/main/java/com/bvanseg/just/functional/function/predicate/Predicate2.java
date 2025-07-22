package com.bvanseg.just.functional.function.predicate;

import org.jetbrains.annotations.NotNull;

import com.bvanseg.just.functional.function.Function2;

@FunctionalInterface
public interface Predicate2<A1, A2> extends Function2<A1, A2, Boolean>, java.util.function.BiPredicate<A1, A2> {

    @Override
    boolean test(A1 a1, A2 a2);

    @Override
    default Boolean apply(A1 a1, A2 a2) {
        return test(a1, a2);
    }

    default Predicate2<A1, A2> and(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> this.test(a1, a2) && other.test(a1, a2);
    }

    default Predicate2<A1, A2> or(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> this.test(a1, a2) || other.test(a1, a2);
    }

    default Predicate2<A1, A2> xor(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> this.test(a1, a2) ^ other.test(a1, a2);
    }

    default Predicate2<A1, A2> implies(Predicate2<? super A1, ? super A2> other) {
        return (a1, a2) -> !this.test(a1, a2) || other.test(a1, a2);
    }

    @Override
    default @NotNull Predicate2<A1, A2> negate() {
        return (a1, a2) -> !this.test(a1, a2);
    }

    static <A1, A2> Predicate2<A1, A2> lift(Predicate<? super A1> predicate) {
        return (a1, _) -> predicate.test(a1);
    }

    static <A1, A2> Predicate2<A1, A2> alwaysTrue() {
        return (_, _) -> true;
    }

    static <A1, A2> Predicate2<A1, A2> alwaysFalse() {
        return (_, _) -> false;
    }

    static <A1, A2> Predicate2<A1, A2> not(Predicate2<? super A1, ? super A2> predicate) {
        return (a1, a2) -> !predicate.test(a1, a2);
    }

    static <A1, A2> Predicate2<A1, A2> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, Boolean>> fn
    ) {
        return (a1, a2) -> fn.apply(a1).apply(a2);
    }

    static <A1, A2> Predicate2<A1, A2> from(Function2<? super A1, ? super A2, Boolean> fn) {
        return fn::apply;
    }

    static <A1, A2> Predicate2<A1, A2> named(String name, Predicate2<? super A1, ? super A2> delegate) {
        return new Predicate2<>() {

            @Override
            public boolean test(A1 a1, A2 a2) {
                return delegate.test(a1, a2);
            }

            @Override
            public String toString() {
                return "Predicate2." + name;
            }
        };
    }

}
