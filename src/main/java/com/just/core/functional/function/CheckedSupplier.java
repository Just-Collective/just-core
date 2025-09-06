package com.just.core.functional.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface CheckedSupplier<T> {

    static <T> CheckedSupplier<T> of(Supplier<T> supplier) {
        return supplier::get;
    }

    T get() throws Throwable;
}
