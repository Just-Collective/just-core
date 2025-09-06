package com.just.core.functional.result;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.just.core.functional.either.Either;
import com.just.core.functional.option.Option;

public final class Err<T, E> extends Result<T, E> {

    private final E error;

    Err(E error) {
        this.error = error;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> @NotNull Result<U, E> and(@NotNull Result<U, E> other) {
        return (Result<U, E>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> @NotNull Result<U, E> andThen(@NotNull Function<? super @NotNull T, ? extends Result<U, E>> f) {
        return (Result<U, E>) this;
    }

    @Override
    public Either<T, E> either() {
        return Either.right(error);
    }

    @Override
    public @NotNull Option<E> err() {
        return Option.some(error);
    }

    @Override
    public @NotNull T expect(@NotNull String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public @NotNull E expectErr(@NotNull String errorMessage) {
        return error;
    }

    @Override
    public <U> Result<T, U> filterOrElse(
        @NotNull Predicate<? super @NotNull T> predicate,
        @NotNull Function<? super @NotNull T, ? extends U> invalidValueMapper,
        @NotNull Function<? super @NotNull E, ? extends U> originalErrorMapper
    ) {
        return Result.err(originalErrorMapper.apply(error));
    }

    @Override
    public void ifOk(@NotNull Consumer<? super @NotNull T> action) { /* NO-OP */ }

    @Override
    public void ifErr(@NotNull Consumer<? super @NotNull E> action) {
        action.accept(error);
    }

    @Override
    public @NotNull Result<T, E> inspect(@NotNull Consumer<? super @NotNull T> action) {
        return this;
    }

    @Override
    public @NotNull Result<T, E> inspectErr(@NotNull Consumer<? super @NotNull E> action) {
        action.accept(error);
        return this;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public boolean isErrAnd(@NotNull Predicate<? super @NotNull E> predicate) {
        return predicate.test(error);
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isOkAnd(@NotNull Predicate<? super @NotNull T> predicate) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> @NotNull Result<U, E> map(@NotNull Function<? super @NotNull T, ? extends U> f) {
        return (Result<U, E>) this;
    }

    @Override
    public <U> @NotNull Result<T, U> mapErr(@NotNull Function<? super @NotNull E, ? extends U> f) {
        return Result.err(f.apply(error));
    }

    @Override
    public <R> R match(
        @NotNull Function<? super @NotNull T, ? extends R> isOk,
        @NotNull Function<? super @NotNull E, ? extends R> isErr
    ) {
        return isErr.apply(error);
    }

    @Override
    public @NotNull Option<T> ok() {
        return Option.none();
    }

    @Override
    public <U> @NotNull Result<T, U> or(@NotNull Result<T, U> other) {
        return other;
    }

    @Override
    public <U> @NotNull Result<T, U> orElse(@NotNull Function<? super @NotNull E, ? extends Result<T, U>> f) {
        return f.apply(error);
    }

    @Override
    public @NotNull Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public @NotNull T unwrap() throws NoSuchElementException {
        throw new NoSuchElementException("No value present.");
    }

    @Override
    public @NotNull E unwrapErr() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Err<?, ?> other)) {
            return false;
        }

        return Objects.equals(this.error, other.error);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(error);
    }

    @Override
    public String toString() {
        return "Err(" + error + ")";
    }
}
