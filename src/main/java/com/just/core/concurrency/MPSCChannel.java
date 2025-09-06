package com.just.core.concurrency;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.just.core.functional.option.Option;
import com.just.core.functional.result.Result;

public class MPSCChannel<T> implements AutoCloseable {

    public static <T> MPSCChannel<T> unbounded() {
        return new MPSCChannel<>(Integer.MAX_VALUE);
    }

    public static <T> MPSCChannel<T> bounded(int capacity) {
        return new MPSCChannel<>(capacity);
    }

    private final BlockingQueue<T> queue;

    private final AtomicBoolean closed;

    private MPSCChannel(int capacity) {
        this.queue = capacity == 0 ? new SynchronousQueue<>() : new LinkedBlockingQueue<>(capacity);
        this.closed = new AtomicBoolean(false);
    }

    @Override
    public void close() {
        closed.set(true);
    }

    public Result<T, ReceiveError> receive() {
        return Result.<T, InterruptedException>trySupply(queue::take)
            .mapErr(ReceiveError.Interrupted::new);
    }

    public Option<T> tryReceive() {
        var value = queue.poll();
        return Option.ofNullable(value);
    }

    public Result<T, ReceiveTimeoutError> receive(Duration timeout) {
        return Result.trySupply(() -> {
            var valueOrNull = queue.poll(timeout.toMillis(), TimeUnit.MILLISECONDS);

            if (valueOrNull == null) {
                throw new MPSCChannelTimeoutException(timeout);
            }

            return valueOrNull;
        }).mapErr(e -> {
            if (e instanceof MPSCChannel.MPSCChannelTimeoutException ste) {
                return new ReceiveTimeoutError.Timeout(ste.duration);
            }

            return new ReceiveTimeoutError.Interrupted((InterruptedException) e);
        });
    }

    public Result<Void, SendError> send(T value) {
        if (closed.get()) {
            return Result.err(new SendError.Closed());
        }

        return Result.<InterruptedException>tryRun(() -> queue.put(value))
            .mapErr(SendError.Interrupted::new);
    }

    public Result<Void, SendTimeoutError> send(T value, Duration timeout) {
        if (closed.get()) {
            return Result.err(new SendTimeoutError.Closed());
        }

        return Result.<Void, Throwable>trySupply(() -> {
            boolean success = queue.offer(value, timeout.toMillis(), TimeUnit.MILLISECONDS);

            if (!success) {
                throw new MPSCChannelTimeoutException(timeout);
            }

            return null;
        }).mapErr(e -> {
            if (e instanceof MPSCChannel.MPSCChannelTimeoutException ste) {
                return new SendTimeoutError.Timeout(ste.duration);
            }

            return new SendTimeoutError.Interrupted((InterruptedException) e);
        });
    }

    public boolean trySend(T value) {
        return !closed.get() && queue.offer(value);
    }

    public boolean isClosed() {
        return closed.get();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    private static class MPSCChannelTimeoutException extends Throwable {

        private final Duration duration;

        MPSCChannelTimeoutException(Duration duration) {
            this.duration = duration;
        }
    }

    public sealed interface ReceiveError {

        record Interrupted(InterruptedException e) implements ReceiveError {}
    }

    public sealed interface ReceiveTimeoutError {

        record Interrupted(InterruptedException e) implements ReceiveTimeoutError {}

        record Timeout(Duration timeout) implements ReceiveTimeoutError {}
    }

    public sealed interface SendError {

        record Closed() implements SendError {}

        record Interrupted(InterruptedException e) implements SendError {}
    }

    public sealed interface SendTimeoutError {

        record Closed() implements SendTimeoutError {}

        record Interrupted(InterruptedException e) implements SendTimeoutError {}

        record Timeout(Duration timeout) implements SendTimeoutError {}
    }
}
