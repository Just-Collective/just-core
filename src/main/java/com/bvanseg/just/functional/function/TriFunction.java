package com.bvanseg.just.functional.function;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {

    public R apply(A a, B b, C c);
}
