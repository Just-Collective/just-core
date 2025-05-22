package com.bvanseg.just.functional.throttle;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class DebouncedRunnable implements Runnable {

    private final Runnable task;

    private final long delayMillis;

    private final AtomicLong generation = new AtomicLong();

    public DebouncedRunnable(Runnable task, Duration duration) {
        this(task, duration.toMillis());
    }

    public DebouncedRunnable(Runnable task, long delayMillis) {
        this.task = task;
        this.delayMillis = delayMillis;
    }

    @Override
    public void run() {
        long currentGeneration = generation.incrementAndGet();

        Thread.startVirtualThread(() -> {
            try {
                Thread.sleep(delayMillis);

                if (generation.get() == currentGeneration) {
                    task.run();
                }
            } catch (InterruptedException _) {
                // Shouldn't happen.
            }
        });
    }
}
