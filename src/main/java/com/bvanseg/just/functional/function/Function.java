package com.bvanseg.just.functional.function;

import com.bvanseg.just.functional.function.memo.Memo;

@FunctionalInterface
public interface Function<A1, R> extends java.util.function.Function<A1, R> {

    @Override
    R apply(A1 a1);

    default <Z> Function<A1, Z> andThen(Function<? super R, ? extends Z> after) {
        return (a1) -> after.apply(this.apply(a1));
    }

    default Memo<A1, R> memoize() {
        return new Memo<>(this);
    }
}
