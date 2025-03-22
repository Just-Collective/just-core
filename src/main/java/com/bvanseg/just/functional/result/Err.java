package com.bvanseg.just.functional.result;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bvanseg.just.functional.option.Option;

public final class Err<T, E> extends Result<T, E> {

    private final E error;

    Err(E error) {
        this.error = error;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> and(Result<U, E> other) {
        return (Result<U, E>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> andThen(Function<T, Result<U, E>> f) {
        return (Result<U, E>) this;
    }

    @Override
    public Option<E> err() {
        return Option.some(error);
    }

    @Override
    public T expect(String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException("No value present.");
    }

    @Override
    public E expectErr(String errorMessage) {
        return error;
    }

    @Override
    public void ifOk(Consumer<? super T> action) { /* NO-OP */ }

    @Override
    public void ifErr(Consumer<? super E> action) {
        action.accept(error);
    }

    @Override
    public Result<T, E> inspect(Consumer<? super T> action) {
        return this;
    }

    @Override
    public Result<T, E> inspectErr(Consumer<? super E> action) {
        action.accept(error);
        return this;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public boolean isErrAnd(Predicate<E> predicate) {
        return predicate.test(error);
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isOkAnd(Predicate<T> predicate) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Result<U, E> map(Function<T, U> f) {
        return (Result<U, E>) this;
    }

    @Override
    public <U> Result<T, U> mapErr(Function<E, U> f) {
        return Result.err(f.apply(error));
    }

    @Override
    public <R> R match(Function<? super T, ? extends R> isOk, Function<? super E, ? extends R> isErr) {
        return isErr.apply(error);
    }

    @Override
    public Option<T> ok() {
        return Option.none();
    }

    @Override
    public <U> Result<T, U> or(Result<T, U> other) {
        return other;
    }

    @Override
    public <U> Result<T, U> orElse(Function<E, Result<T, U>> f) {
        return f.apply(error);
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public T unwrap() throws NoSuchElementException {
        throw new NoSuchElementException("No value present.");
    }

    @Override
    public E unwrapErr() {
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
