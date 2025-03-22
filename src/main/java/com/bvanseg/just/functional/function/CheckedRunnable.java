package com.bvanseg.just.functional.function;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Exception;
}
