package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo7;
import com.bvanseg.just.functional.tuple.Tuple7;

@FunctionalInterface
public interface Function7<A1, A2, A3, A4, A5, A6, A7, R> {

    R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7);

    default <Z> Function7<A1, A2, A3, A4, A5, A6, A7, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2, a3, a4, a5, a6, a7) -> after.apply(this.apply(a1, a2, a3, a4, a5, a6, a7));
    }

    default Memo7<A1, A2, A3, A4, A5, A6, A7, R> memoize() {
        return new Memo7<>(this);
    }

    default Function<A1, Function<A2, Function<A3, Function<A4, Function<A5, Function<A6, Function<A7, R>>>>>>> curried() {
        return a1 -> a2 -> a3 -> a4 -> a5 -> a6 -> a7 -> this.apply(a1, a2, a3, a4, a5, a6, a7);
    }

    default Function<Tuple7<A1, A2, A3, A4, A5, A6, A7>, R> tupled() {
        return tuple -> this.apply(tuple.v1(), tuple.v2(), tuple.v3(), tuple.v4(), tuple.v5(), tuple.v6(), tuple.v7());
    }

    default Function6<A2, A3, A4, A5, A6, A7, R> partialFirst(A1 fixed) {
        return (a2, a3, a4, a5, a6, a7) -> this.apply(fixed, a2, a3, a4, a5, a6, a7);
    }

    default Function6<A1, A2, A3, A4, A5, A6, R> partialLast(A7 fixed) {
        return (a1, a2, a3, a4, a5, a6) -> this.apply(a1, a2, a3, a4, a5, a6, fixed);
    }

    static <A1, A2, A3, A4, A5, A6, A7, R> Function7<A1, A2, A3, A4, A5, A6, A7, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends Function<? super A3, ? extends Function<? super A4, ? extends Function<? super A5, ? extends Function<? super A6, ? extends Function<? super A7, ? extends R>>>>>>> curried
    ) {
        return (A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7) -> curried.apply(a1)
            .apply(a2)
            .apply(a3)
            .apply(a4)
            .apply(a5)
            .apply(a6)
            .apply(a7);
    }
}
