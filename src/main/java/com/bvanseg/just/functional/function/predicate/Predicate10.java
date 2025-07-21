package com.bvanseg.just.functional.function.predicate;

import com.bvanseg.just.functional.function.Function10;

@FunctionalInterface
public interface Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> extends Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, Boolean> {

    boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10);

    @Override
    default Boolean apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) {
        return test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
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

    default Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> negate() {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> !this.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> lift(
        Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, _) -> predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> alwaysTrue() {
        return (_, _, _, _, _, _, _, _, _, _) -> true;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> alwaysFalse() {
        return (_, _, _, _, _, _, _, _, _, _) -> false;
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> not(
        Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> predicate
    ) {
        return (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) -> !predicate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

    static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> named(
        String name,
        Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> delegate
    ) {
        return new Predicate10<>() {

            @Override
            public boolean test(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) {
                return delegate.test(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

}
