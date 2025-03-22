package com.bvanseg.just.functional.result;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bvanseg.just.functional.function.CheckedRunnable;
import com.bvanseg.just.functional.function.CheckedSupplier;
import com.bvanseg.just.functional.option.Option;

public sealed abstract class Result<T, E> permits Ok, Err {

    public static <T, E> Result<T, E> ok(T value) {
        return new Ok<>(value);
    }

    public static <T, E> Result<T, E> err(E error) {
        return new Err<>(error);
    }

    public static <E extends Exception> Result<Void, E> tryRun(CheckedRunnable runnable) {
        try {
            runnable.run();
            return ok(null);
        } catch (Exception e) {
            @SuppressWarnings("unchecked")
            var err = Result.<Void, E>err((E) e);
            return err;
        }
    }

    public static <T, E extends Throwable> Result<T, E> trySupply(CheckedSupplier<T> supplier) {
        try {
            return ok(supplier.get());
        } catch (Throwable throwable) {
            @SuppressWarnings("unchecked")
            var err = (Result<T, E>) Result.err((E) throwable);
            return err;
        }
    }

    public abstract <U> Result<U, E> and(Result<U, E> other);

    public abstract <U> Result<U, E> andThen(Function<T, Result<U, E>> f);

    public abstract Option<E> err();

    public abstract T expect(String errorMessage) throws NoSuchElementException;

    public abstract E expectErr(String errorMessage);

    public abstract void ifOk(Consumer<? super T> action);

    public abstract void ifErr(Consumer<? super E> action);

    public abstract Result<T, E> inspect(Consumer<? super T> action);

    public abstract Result<T, E> inspectErr(Consumer<? super E> action);

    public abstract boolean isErr();

    public abstract boolean isErrAnd(Predicate<E> predicate);

    public abstract boolean isOk();

    public abstract boolean isOkAnd(Predicate<T> predicate);

    public abstract <U> Result<U, E> map(Function<T, U> f);

    public abstract <U> Result<T, U> mapErr(Function<E, U> f);

    public abstract <R> R match(Function<? super T, ? extends R> isOk, Function<? super E, ? extends R> isErr);

    public abstract Option<T> ok();

    public abstract <U> Result<T, U> or(Result<T, U> other);

    public abstract <U> Result<T, U> orElse(Function<E, Result<T, U>> f);

    public abstract Optional<T> toOptional();

    public abstract T unwrap() throws NoSuchElementException;

    public abstract E unwrapErr();
}
