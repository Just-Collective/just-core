package com.bvanseg.just.functional.function.predicate;

import org.jetbrains.annotations.NotNull;

import com.bvanseg.just.functional.function.Function;

@FunctionalInterface
public interface Predicate<A1> extends Function<A1, Boolean>, java.util.function.Predicate<A1> {

    @Override
    boolean test(A1 a1);

    @Override
    default Boolean apply(A1 a1) {
        return test(a1);
    }

    default Predicate<A1> and(Predicate<? super A1> other) {
        return (a1) -> this.test(a1) && other.test(a1);
    }

    default Predicate<A1> or(Predicate<? super A1> other) {
        return (a1) -> this.test(a1) || other.test(a1);
    }

    @Override
    default @NotNull Predicate<A1> negate() {
        return (a1) -> !this.test(a1);
    }

    static <A1> Predicate<A1> alwaysTrue() {
        return (_) -> true;
    }

    static <A1> Predicate<A1> alwaysFalse() {
        return (_) -> false;
    }

    static <A1> Predicate<A1> not(Predicate<A1> predicate) {
        return (a1) -> !predicate.test(a1);
    }

    static <A1> Predicate<A1> from(java.util.function.Function<A1, Boolean> fn) {
        return fn::apply;
    }

    static <A1> Predicate<A1> from(Function<? super A1, Boolean> fn) {
        return fn::apply;
    }

    static <A1> Predicate<A1> named(String name, Predicate<A1> delegate) {
        return new Predicate<>() {

            @Override
            public boolean test(A1 a1) {
                return delegate.test(a1);
            }

            @Override
            public String toString() {
                return "Predicate." + name;
            }
        };
    }

}
