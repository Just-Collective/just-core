package com.bvanseg.just.functional.function.memo;

import java.util.function.BiPredicate;

import com.bvanseg.just.functional.function.TriFunction;

public class TriMemo<A, B, C, R> implements TriFunction<A, B, C, R> {

    private A aRef;

    private B bRef;

    private C cRef;

    private R cachedResult;

    private final TriFunction<A, B, C, R> triFunction;

    private final BiPredicate<A, A> aEq;

    private final BiPredicate<B, B> bEq;

    private final BiPredicate<C, C> cEq;

    public TriMemo(TriFunction<A, B, C, R> triFunction) {
        this(
            triFunction,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public TriMemo(
        TriFunction<A, B, C, R> triFunction,
        BiPredicate<A, A> aEq,
        BiPredicate<B, B> bEq,
        BiPredicate<C, C> cEq
    ) {
        this.triFunction = triFunction;
        this.aEq = aEq;
        this.bEq = bEq;
        this.cEq = cEq;
    }

    @Override
    public R apply(A a, B b, C c) {
        if (
            aEq.test(aRef, a) && bEq.test(bRef, b) && cEq.test(cRef, c)
        ) {
            return cachedResult;
        }

        cachedResult = triFunction.apply(a, b, c);
        aRef = a;
        bRef = b;
        cRef = c;

        return cachedResult;
    }
}
