package com.just.core.functional.function.memo;

import java.util.function.BiPredicate;

import com.just.core.functional.function.Function4;

public class Memo4<A1, A2, A3, A4, R> implements Function4<A1, A2, A3, A4, R> {

    private A1 a1Ref;

    private A2 a2Ref;

    private A3 a3Ref;

    private A4 a4Ref;

    private R cachedResult;

    private final Function4<A1, A2, A3, A4, R> fn;

    private final BiPredicate<A1, A1> eq1;

    private final BiPredicate<A2, A2> eq2;

    private final BiPredicate<A3, A3> eq3;

    private final BiPredicate<A4, A4> eq4;

    public Memo4(Function4<A1, A2, A3, A4, R> fn) {
        this(
            fn,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public Memo4(
        Function4<A1, A2, A3, A4, R> fn,
        BiPredicate<A1, A1> eq1,
        BiPredicate<A2, A2> eq2,
        BiPredicate<A3, A3> eq3,
        BiPredicate<A4, A4> eq4
    ) {
        this.fn = fn;
        this.eq1 = eq1;
        this.eq2 = eq2;
        this.eq3 = eq3;
        this.eq4 = eq4;
    }

    @Override
    public R apply(A1 a1, A2 a2, A3 a3, A4 a4) {
        if (eq1.test(a1Ref, a1) && eq2.test(a2Ref, a2) && eq3.test(a3Ref, a3) && eq4.test(a4Ref, a4)) {
            return cachedResult;
        }

        this.cachedResult = fn.apply(a1, a2, a3, a4);
        this.a1Ref = a1;
        this.a2Ref = a2;
        this.a3Ref = a3;
        this.a4Ref = a4;
        return cachedResult;
    }
}
