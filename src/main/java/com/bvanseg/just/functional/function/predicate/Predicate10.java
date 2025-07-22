package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function10;

@FunctionalInterface
public interface Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> extends Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    @Override
    default Predicate9<A2, A3, A4, A5, A6, A7, A8, A9, A10> partialFirst(A1 fixed) {
        return Function10.super.partialFirst(fixed)::apply;
    }

    @Override
    default Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> partialLast(A10 fixed) {
        return Function10.super.partialLast(fixed)::apply;
    }

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> and(
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) && other
            .test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> or(
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) || other
            .test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> xor(
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) ^ other
            .test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> implies(
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> other
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> !this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) || other
            .test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> !this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> lift(
        Predicate9<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, $10) -> predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> alwaysTrue() {
        return ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> alwaysFalse() {
        return ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> not(
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> !predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> from(
        java.util.function.Function<? super A1, ? extends java.util.function.Function<? super A2, ? extends java.util.function.Function<? super A3, ? extends java.util.function.Function<? super A4, ? extends java.util.function.Function<? super A5, ? extends java.util.function.Function<? super A6, ? extends java.util.function.Function<? super A7, ? extends java.util.function.Function<? super A8, ? extends java.util.function.Function<? super A9, ? extends java.util.function.Function<? super A10, Boolean>>>>>>>>>> fn
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> fn.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7)
            .apply(a8)
            .apply(a9)
            .apply(a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> from(
        Function10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10, Boolean> fn
    ) {
        return fn::apply;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> named(
        String name,
        Predicate10<? super A1, ? super A2, ? super A3, ? super A4, ? super A5, ? super A6, ? super A7, ? super A8, ? super A9, ? super A10> delegate
    ) {
        return new Predicate10<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
            }

            @Override
            public String toString() {
                return "Predicate10." + name;
            }
        };
    }

}
