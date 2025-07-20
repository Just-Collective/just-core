package com.bvanseg.just.functional.function;

@FunctionalInterface
public interface Function3<A, B, C, R> {

    R apply(A a, B b, C c);
}
