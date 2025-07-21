package com.bvanseg.just.functional.function.memo;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Memo<A1, R> implements Function<A1, R> {

    private A1 a1Ref;

    private R cachedResult;

    private final Function<A1, R> fn;

    private final BiPredicate<A1, A1> eq1;

    public Memo(Function<A1, R> fn) {
        this(
            fn,
            (oldRef, newRef) -> newRef == oldRef
        );
    }

    public Memo(Function<A1, R> fn, BiPredicate<A1, A1> eq1) {
        this.fn = fn;
        this.eq1 = eq1;
    }

    @Override
    public R apply(A1 a1) {
        if (eq1.test(a1Ref, a1)) {
            return cachedResult;
        }

        this.cachedResult = fn.apply(a1);
        this.a1Ref = a1;
        return cachedResult;
    }
}
