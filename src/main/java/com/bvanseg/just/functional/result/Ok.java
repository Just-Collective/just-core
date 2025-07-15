package com.bvanseg.just.functional.result;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bvanseg.just.functional.either.Either;
import com.bvanseg.just.functional.option.Option;

public final class Ok<T, E> extends Result<T, E> {

    private final T value;

    Ok(T value) {
        this.value = value;
    }

    @Override
    public <U> @NotNull Result<U, E> and(@NotNull Result<U, E> other) {
        return other;
    }

    @Override
    public <U> @NotNull Result<U, E> andThen(@NotNull Function<? super @NotNull T, ? extends Result<U, E>> f) {
        return f.apply(value);
    }

    @Override
    public Either<T, E> either() {
        return Either.left(value);
    }

    @Override
    public @NotNull Option<E> err() {
        return Option.none();
    }

    @Override
    public @NotNull T expect(@NotNull String errorMessage) {
        return value;
    }

    @Override
    public @NotNull E expectErr(@NotNull String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public <U> Result<T, U> filterOrElse(
        @NotNull Predicate<? super @NotNull T> predicate,
        @NotNull Function<? super @NotNull T, ? extends U> invalidValueMapper,
        @NotNull Function<? super @NotNull E, ? extends U> originalErrorMapper
    ) {
        if (predicate.test(value)) {
            @SuppressWarnings("unchecked")
            var self = (Result<T, U>) this;
            return self;
        }

        return Result.err(invalidValueMapper.apply(value));
    }

    @Override
    public void ifOk(@NotNull Consumer<? super @NotNull T> action) {
        action.accept(value);
    }

    @Override
    public void ifErr(@NotNull Consumer<? super @NotNull E> action) { /* NO-OP */ }

    @Override
    public @NotNull Result<T, E> inspect(@NotNull Consumer<? super @NotNull T> action) {
        action.accept(value);
        return this;
    }

    @Override
    public @NotNull Result<T, E> inspectErr(@NotNull Consumer<? super @NotNull E> action) {
        return this;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public boolean isErrAnd(@NotNull Predicate<? super @NotNull E> predicate) {
        return false;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isOkAnd(@NotNull Predicate<? super @NotNull T> predicate) {
        return predicate.test(value);
    }

    @Override
    public <U> @NotNull Result<U, E> map(@NotNull Function<? super @NotNull T, ? extends U> f) {
        return Result.ok(f.apply(value));
    }

    @Override
    public <U> @NotNull Result<T, U> mapErr(@NotNull Function<? super @NotNull E, ? extends U> f) {
        return Result.ok(value);
    }

    @Override
    public <R> R match(
        @NotNull Function<? super @NotNull T, ? extends R> isOk,
        @NotNull Function<? super @NotNull E, ? extends R> isErr
    ) {
        return isOk.apply(value);
    }

    @Override
    public @NotNull Option<T> ok() {
        return Option.some(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> @NotNull Result<T, U> or(@NotNull Result<T, U> other) {
        return (Result<T, U>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> @NotNull Result<T, U> orElse(@NotNull Function<? super @NotNull E, ? extends Result<T, U>> f) {
        return (Result<T, U>) this;
    }

    @Override
    public @NotNull Optional<T> toOptional() {
        return Optional.of(value);
    }

    @Override
    public @NotNull T unwrap() {
        return value;
    }

    @Override
    public @NotNull E unwrapErr() {
        throw new NoSuchElementException("No error present.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Ok<?, ?> other)) {
            return false;
        }

        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Ok(" + value + ")";
    }
}
