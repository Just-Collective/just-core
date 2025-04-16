package com.bvanseg.just.functional.function.predicate;

@FunctionalInterface
public interface TriPredicate<A, B, C> {

    boolean test(A a, B b, C c);
}
