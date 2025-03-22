package com.bvanseg.just;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Exception;
}
