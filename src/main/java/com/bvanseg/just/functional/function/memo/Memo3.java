package com.bvanseg.just.functional.function.memo;

import java.util.function.BiPredicate;

import com.bvanseg.just.functional.function.Function3;

public class Memo3<A, B, C, R> implements Function3<A, B, C, R> {

    private A aRef;

    private B bRef;

    private C cRef;

    private R cachedResult;

    private final Function3<A, B, C, R> function3;

    private final BiPredicate<A, A> aEq;

    private final BiPredicate<B, B> bEq;

    private final BiPredicate<C, C> cEq;

    public Memo3(Function3<A, B, C, R> function3) {
        this(
            function3,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public Memo3(
        Function3<A, B, C, R> function3,
        BiPredicate<A, A> aEq,
        BiPredicate<B, B> bEq,
        BiPredicate<C, C> cEq
    ) {
        this.function3 = function3;
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

        cachedResult = function3.apply(a, b, c);
        aRef = a;
        bRef = b;
        cRef = c;

        return cachedResult;
    }
}
