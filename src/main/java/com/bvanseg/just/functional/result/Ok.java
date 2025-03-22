package com.bvanseg.just.functional.result;

import com.bvanseg.just.functional.option.Option;
import com.bvanseg.just.functional.option.Some;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Ok<T, E> extends Result<T, E> {

    private final T value;

    Ok(T value) {
        this.value = value;
    }

    @Override
    public <U> Result<U, E> and(Result<U, E> other) {
        return other;
    }

    @Override
    public <U> Result<U, E> andThen(Function<T, Result<U, E>> f) {
        return f.apply(value);
    }

    @Override
    public Option<E> err() {
        return Option.none();
    }

    @Override
    public T expect(String errorMessage) {
        return value;
    }

    @Override
    public E expectErr(String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException("No error present.");
    }

    @Override
    public void ifOk(Consumer<? super T> action) {
        action.accept(value);
    }

    @Override
    public void ifErr(Consumer<? super E> action) { /* NO-OP */ }

    @Override
    public Result<T, E> inspect(Consumer<? super T> action) {
        action.accept(value);
        return this;
    }

    @Override
    public Result<T, E> inspectErr(Consumer<? super E> action) {
        return this;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public boolean isErrAnd(Predicate<E> predicate) {
        return false;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isOkAnd(Predicate<T> predicate) {
        return predicate.test(value);
    }

    @Override
    public <U> Result<U, E> map(Function<T, U> f) {
        return Result.ok(f.apply(value));
    }

    @Override
    public <U> Result<T, U> mapErr(Function<E, U> f) {
        return Result.ok(value);
    }

    @Override
    public <R> R match(Function<? super T, ? extends R> isOk, Function<? super E, ? extends R> isErr) {
        return isOk.apply(value);
    }

    @Override
    public Option<T> ok() {
        return Option.some(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<T, U> or(Result<T, U> other) {
        return (Result<T, U>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<T, U> orElse(Function<E, Result<T, U>> f) {
        return (Result<T, U>) this;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.of(value);
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public E unwrapErr() {
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