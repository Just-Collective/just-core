package com.bvanseg.just;

@FunctionalInterface
public interface CheckedSupplier<T> {

    T get() throws Throwable;
}
