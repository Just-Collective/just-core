package com.just.core.functional.function;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    private final Supplier<T> supplier;

    private T value;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (value == null) {
            value = supplier.get();
        }

        return value;
    }

    public boolean isEmpty() {
        return !isPresent();
    }

    public boolean isPresent() {
        return value != null;
    }
}
