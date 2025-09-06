package com.just.core.functional.option;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.just.core.functional.result.Result;

public final class None<T> extends Option<T> {

    private static final None<?> INSTANCE = new None<>();

    @SuppressWarnings("unchecked")
    public static <T> None<T> instance() {
        return (None<T>) INSTANCE;
    }

    @Override
    public <R> @NotNull Option<R> and(@NotNull Option<R> other) {
        return Option.none();
    }

    @Override
    public <R> @NotNull Option<R> andThen(@NotNull Function<? super @NotNull T, ? extends @NotNull Option<R>> other) {
        return Option.none();
    }

    @Override
    public boolean contains(@NotNull T value) {
        return false;
    }

    @Override
    public @NotNull T expect(@NotNull String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public @NotNull Option<T> filter(@NotNull Predicate<? super @NotNull T> predicate) {
        return Option.none();
    }

    @Override
    public void ifSome(@NotNull Consumer<? super @NotNull T> action) { /* NO-OP */ }

    @Override
    public void ifNone(@NotNull Runnable action) {
        action.run();
    }

    @Override
    public @NotNull Option<T> inspect(@NotNull Consumer<? super @NotNull T> action) {
        return Option.none();
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public boolean isNoneOr(@NotNull Predicate<? super @NotNull T> predicate) {
        return true;
    }

    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isSomeAnd(@NotNull Predicate<? super @NotNull T> predicate) {
        return false;
    }

    @Override
    public <R> @NotNull Option<R> map(@NotNull Function<? super @NotNull T, ? extends R> f) {
        return Option.none();
    }

    @Override
    public <R> R mapOr(@NotNull Function<? super @NotNull T, ? extends R> f, R fallbackValue) {
        return fallbackValue;
    }

    @Override
    public <R> R mapOrElse(
        @NotNull Function<? super @NotNull T, ? extends R> f,
        @NotNull Supplier<? extends R> supplier
    ) {
        return supplier.get();
    }

    @Override
    public <R> R match(
        @NotNull Function<? super @NotNull T, ? extends R> ifSome,
        @NotNull Supplier<? extends R> ifNone
    ) {
        return ifNone.get();
    }

    @Override
    public <R> @NotNull Result<T, R> okOr(R fallbackValue) {
        return Result.err(fallbackValue);
    }

    @Override
    public <R> @NotNull Option<R> or(@NotNull Option<R> other) {
        return other;
    }

    @Override
    public <R> @NotNull Option<R> orElse(@NotNull Supplier<? extends @NotNull Option<R>> other) {
        return other.get();
    }

    @Override
    public @NotNull Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public @NotNull Stream<T> toStream() {
        return Stream.empty();
    }

    @Override
    public @NotNull T unwrap() throws NoSuchElementException {
        throw new NoSuchElementException("No value present.");
    }

    @Override
    public T unwrapOr(T other) {
        return other;
    }

    @Override
    public T unwrapOrElse(@NotNull Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T unwrapOrThrow(@NotNull Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <U, R> @NotNull Option<R> zip(
        @NotNull Option<U> other,
        @NotNull BiFunction<? super @NotNull T, ? super @NotNull U, ? extends R> combiner
    ) {
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
