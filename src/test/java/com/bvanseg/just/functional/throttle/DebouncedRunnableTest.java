package com.bvanseg.just.functional.throttle;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class DebouncedRunnableTest {

    @Test
    void runsTaskAfterDelay() throws InterruptedException {
        var latch = new CountDownLatch(1);
        var debounced = new DebouncedRunnable(latch::countDown, Duration.ofMillis(100));

        debounced.run();

        assertTrue(
            latch.await(200, java.util.concurrent.TimeUnit.MILLISECONDS),
            "Task did not run after delay."
        );
    }

    @Test
    void runsOnlyOnceDespiteMultipleCalls() throws InterruptedException {
        var counter = new AtomicInteger();
        var debounced = new DebouncedRunnable(counter::incrementAndGet, Duration.ofMillis(100));

        for (var i = 0; i < 5; i++) {
            debounced.run();
            Thread.sleep(20);
        }

        Thread.sleep(200);

        assertEquals(1, counter.get(), "Task should run only once after debounce delay.");
    }

    @Test
    void resetsAndRunsAgainOnNewSequence() throws InterruptedException {
        var counter = new AtomicInteger();
        var debounced = new DebouncedRunnable(counter::incrementAndGet, Duration.ofMillis(100));

        // First debounce burst.
        debounced.run();
        Thread.sleep(50);
        debounced.run();
        // Wait for debounce to resolve.
        Thread.sleep(150);

        // Second debounce burst.
        debounced.run();
        Thread.sleep(150);

        assertEquals(2, counter.get(), "Task should run once per debounce sequence.");
    }

    @Test
    void doesNotRunImmediately() throws InterruptedException {
        var counter = new AtomicInteger();
        var debounced = new DebouncedRunnable(counter::incrementAndGet, Duration.ofMillis(100));

        debounced.run();
        Thread.sleep(50);

        assertEquals(0, counter.get(), "Task should not run before delay.");

        Thread.sleep(100);

        assertEquals(1, counter.get(), "Task should run after delay.");
    }
}
