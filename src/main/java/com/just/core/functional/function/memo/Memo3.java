package com.just.core.functional.function.memo;

import java.util.function.BiPredicate;

import com.just.core.functional.function.Function3;

public class Memo3<A1, A2, A3, R> implements Function3<A1, A2, A3, R> {

    private A1 a1Ref;

    private A2 a2Ref;

    private A3 a3Ref;

    private R cachedResult;

    private final Function3<A1, A2, A3, R> fn;

    private final BiPredicate<A1, A1> eq1;

    private final BiPredicate<A2, A2> eq2;

    private final BiPredicate<A3, A3> eq3;

    public Memo3(Function3<A1, A2, A3, R> fn) {
        this(
            fn,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public Memo3(
        Function3<A1, A2, A3, R> fn,
        BiPredicate<A1, A1> eq1,
        BiPredicate<A2, A2> eq2,
        BiPredicate<A3, A3> eq3
    ) {
        this.fn = fn;
        this.eq1 = eq1;
        this.eq2 = eq2;
        this.eq3 = eq3;
    }

    @Override
    public R apply(A1 a1, A2 a2, A3 a3) {
        if (eq1.test(a1Ref, a1) && eq2.test(a2Ref, a2) && eq3.test(a3Ref, a3)) {
            return cachedResult;
        }

        this.cachedResult = fn.apply(a1, a2, a3);
        this.a1Ref = a1;
        this.a2Ref = a2;
        this.a3Ref = a3;
        return cachedResult;
    }
}
