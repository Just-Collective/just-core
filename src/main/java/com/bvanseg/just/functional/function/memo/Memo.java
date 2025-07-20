package com.bvanseg.just.functional.function.memo;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Memo<A, R> implements Function<A, R> {

    private A aRef;

    private R cachedResult;

    private final Function<A, R> function;

    private final BiPredicate<A, A> aEq;

    public Memo(Function<A, R> function) {
        this(function, (oldRef, newRef) -> newRef == oldRef);
    }

    public Memo(Function<A, R> function, BiPredicate<A, A> aEq) {
        this.function = function;
        this.aEq = aEq;
    }

    @Override
    public R apply(A a) {
        if (aEq.test(aRef, a)) {
            return cachedResult;
        }

        this.cachedResult = function.apply(a);
        this.aRef = a;

        return cachedResult;
    }
}
