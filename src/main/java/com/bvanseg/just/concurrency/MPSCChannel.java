package com.bvanseg.just.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.bvanseg.just.functional.option.Option;

public class MPSCChannel<T> {

    private final BlockingQueue<T> queue;

    public MPSCChannel() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public T receive() throws InterruptedException {
        return queue.take();
    }

    public Option<T> tryReceive() {
        var value = queue.poll();
        return Option.ofNullable(value);
    }

    public void send(T value) throws InterruptedException {
        queue.put(value);
    }

    public boolean trySend(T value) {
        return queue.offer(value);
    }
}
