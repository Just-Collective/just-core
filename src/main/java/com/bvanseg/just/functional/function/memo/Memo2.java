package com.bvanseg.just.functional.function.memo;

import java.util.function.BiPredicate;

import com.bvanseg.just.functional.function.Function2;

public class Memo2<A1, A2, R> implements Function2<A1, A2, R> {

    private A1 a1Ref;

    private A2 a2Ref;

    private R cachedResult;

    private final Function2<A1, A2, R> fn;

    private final BiPredicate<A1, A1> eq1;

    private final BiPredicate<A2, A2> eq2;

    public Memo2(Function2<A1, A2, R> fn) {
        this(
            fn,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public Memo2(Function2<A1, A2, R> fn, BiPredicate<A1, A1> eq1, BiPredicate<A2, A2> eq2) {
        this.fn = fn;
        this.eq1 = eq1;
        this.eq2 = eq2;
    }

    @Override
    public R apply(A1 a1, A2 a2) {
        if (eq1.test(a1Ref, a1) && eq2.test(a2Ref, a2)) {
            return cachedResult;
        }

        this.cachedResult = fn.apply(a1, a2);
        this.a1Ref = a1;
        this.a2Ref = a2;
        return cachedResult;
    }
}
