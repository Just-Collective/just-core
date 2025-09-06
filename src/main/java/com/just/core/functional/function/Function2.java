package com.just.core.functional.function;

import java.util.function.BiFunction;

import com.just.core.functional.function.memo.Memo2;
import com.just.core.functional.tuple.Tuple2;

@FunctionalInterface
public interface Function2<A1, A2, R> extends BiFunction<A1, A2, R> {

    @Override
    R apply(A1 a1, A2 a2);

    default <Z> Function2<A1, A2, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1, a2) -> after.apply(this.apply(a1, a2));
    }

    default Memo2<A1, A2, R> memoize() {
        return new Memo2<>(this);
    }

    default Function<A1, Function<A2, R>> curried() {
        return a1 -> a2 -> this.apply(a1, a2);
    }

    default Function<Tuple2<A1, A2>, R> tupled() {
        return tuple -> this.apply(tuple.v1(), tuple.v2());
    }

    default Function<A2, R> partialFirst(A1 fixed) {
        return (a2) -> this.apply(fixed, a2);
    }

    default Function<A1, R> partialLast(A2 fixed) {
        return (a1) -> this.apply(a1, fixed);
    }

    static <A1, A2, R> Function2<A1, A2, R> from(
        Function<? super A1, ? extends Function<? super A2, ? extends R>> curried
    ) {
        return (A1 a1, A2 a2) -> curried.apply(a1).apply(a2);
    }
}
