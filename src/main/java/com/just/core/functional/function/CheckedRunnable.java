package com.just.core.functional.function;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Throwable;
}
