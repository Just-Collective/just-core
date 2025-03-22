package com.bvanseg.just.functional.option;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class None<T> extends Option<T> {

    private static final None<?> INSTANCE = new None<>();

    @SuppressWarnings("unchecked")
    public static <T> None<T> instance() {
        return (None<T>) INSTANCE;
    }

    @Override
    public <R> Option<R> and(Option<R> other) {
        return Option.none();
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public T expect(String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return Option.none();
    }

    @Override
    public <R> Option<R> flatMap(Function<? super T, ? extends Option<R>> f) {
        return Option.none();
    }

    @Override
    public void ifSome(Consumer<? super T> action) { /* NO-OP */ }

    @Override
    public void ifNone(Runnable action) {
        action.run();
    }

    @Override
    public Option<T> inspect(Consumer<? super T> action) {
        return Option.none();
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isSomeAnd(Predicate<? super T> predicate) {
        return false;
    }

    @Override
    public <R> Option<R> map(Function<? super T, ? extends R> f) {
        return Option.none();
    }

    @Override
    public <R> R match(Function<? super T, ? extends R> ifSome, Supplier<? extends R> ifNone) {
        return ifNone.get();
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public Stream<T> toStream() {
        return Stream.empty();
    }

    @Override
    public T unwrap() throws NoSuchElementException {
        throw new NoSuchElementException("No value present.");
    }

    @Override
    public T unwrapOr(T other) {
        return other;
    }

    @Override
    public T unwrapOrElse(Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T unwrapOrThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <U, R> Option<R> zip(Option<U> other, BiFunction<? super T, ? super U, ? extends R> combiner) {
        return Option.none();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof None;
    }

    @Override
    public int hashCode() {
        // constant for all None.
        return 0;
    }

    @Override
    public String toString() {
        return "None";
    }
}