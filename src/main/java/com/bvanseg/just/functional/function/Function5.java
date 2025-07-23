package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo5;
import com.bvanseg.just.functional.tuple.Tuple5;

@FunctionalInterface
public interface Function5<A1, A2, A3, A4, A5, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5);

    default <Z> Function5<A1, A2, A3, A4, A5, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5) -> after.apply(this.apply(a1, a2, a3, a4, a5));
    }

    default Memo5<A1, A2, A3, A4, A5, R> memoize() {
        return new Memo5<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, R>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> this.apply(a1, a2, a3, a4, a5);
    }

    default Function<Tuple5<A1, A2, A3, A4, A5>, R> tupled() {
        return tuple -> this.apply(tuple.v1(), tuple.v2(), tuple.v3(), tuple.v4(), tuple.v5());
    }

    default Function4<A2, A3, A4, A5, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5) -> this.apply(fixed, a2, a3, a4, a5);
    }

    default Function4<A1, A2, A3, A4, R> partialLast(A5 fixed) {
        return (a1, a2, a3, a4) -> this.apply(a1, a2, a3, a4, fixed);
    }

    static <A1, A2, A3, A4, A5, R> Function5<A1, A2, A3, A4, A5, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends R>>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) -> curried.apply(a1).apply(a2).apply(a3).apply(a4).apply(a5);
    }
}
