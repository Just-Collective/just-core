package com.bvanseg.just.functional.function.memo;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiMemo<A, B, R> implements BiFunction<A, B, R> {

    private A aRef;

    private B bRef;

    private R cachedResult;

    private final BiFunction<A, B, R> biFunction;

    private final BiPredicate<A, A> aEq;

    private final BiPredicate<B, B> bEq;

    public BiMemo(BiFunction<A, B, R> biFunction) {
        this(
            biFunction,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public BiMemo(BiFunction<A, B, R> biFunction, BiPredicate<A, A> aEq, BiPredicate<B, B> bEq) {
        this.biFunction = biFunction;
        this.aEq = aEq;
        this.bEq = bEq;
    }

    @Override
    public R apply(A a, B b) {
        if (aEq.test(aRef, a) && bEq.test(bRef, b)) {
            return cachedResult;
        }

        cachedResult = biFunction.apply(a, b);
        aRef = a;
        bRef = b;

        return cachedResult;
    }
}
