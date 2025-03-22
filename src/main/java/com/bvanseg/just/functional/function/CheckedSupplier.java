package com.bvanseg.just.functional.function;

@FunctionalInterface
public interface CheckedSupplier<T> {

    T get() throws Throwable;
}
