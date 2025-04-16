package com.bvanseg.just.functional.result;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bvanseg.just.functional.function.CheckedRunnable;
import com.bvanseg.just.functional.function.CheckedSupplier;
import com.bvanseg.just.functional.option.Option;

public sealed abstract class Result<T, E> permits Ok, Err {

    public static <T, E> @NotNull Result<T, E> ok(T value) {
        return new Ok<>(value);
    }

    public static <T, E> @NotNull Result<T, E> err(E error) {
        return new Err<>(error);
    }

    public static <E extends Throwable> @NotNull Result<Void, E> tryRun(@NotNull CheckedRunnable runnable) {
        try {
            runnable.run();
            return ok(null);
        } catch (Throwable e) {
            @SuppressWarnings("unchecked")
            var err = Result.<Void, E>err((E) e);
            return err;
        }
    }

    public static <T, E extends Throwable> @NotNull Result<T, E> trySupply(
        @NotNull CheckedSupplier<? extends T> supplier
    ) {
        try {
            return ok(supplier.get());
        } catch (Throwable throwable) {
            @SuppressWarnings("unchecked")
            var err = (Result<T, E>) Result.err((E) throwable);
            return err;
        }
    }

    public abstract <U> @NotNull Result<U, E> and(@NotNull Result<U, E> other);

    public abstract <U> @NotNull Result<U, E> andThen(@NotNull Function<? super @NotNull T, ? extends Result<U, E>> f);

    public abstract @NotNull Option<E> err();

    public abstract @NotNull T expect(@NotNull String errorMessage) throws NoSuchElementException;

    public abstract @NotNull E expectErr(@NotNull String errorMessage);

    public abstract <U> Result<T, U> filterOrElse(
        @NotNull Predicate<? super @NotNull T> predicate,
        @NotNull Function<? super @NotNull T, ? extends U> invalidValueMapper,
        @NotNull Function<? super @NotNull E, ? extends U> originalErrorMapper
    );

    public abstract void ifOk(@NotNull Consumer<? super @NotNull T> action);

    public abstract void ifErr(@NotNull Consumer<? super @NotNull E> action);

    public abstract @NotNull Result<T, E> inspect(@NotNull Consumer<? super @NotNull T> action);

    public abstract @NotNull Result<T, E> inspectErr(@NotNull Consumer<? super @NotNull E> action);

    public abstract boolean isErr();

    public abstract boolean isErrAnd(@NotNull Predicate<? super @NotNull E> predicate);

    public abstract boolean isOk();

    public abstract boolean isOkAnd(@NotNull Predicate<? super @NotNull T> predicate);

    public abstract <U> @NotNull Result<U, E> map(@NotNull Function<? super @NotNull T, ? extends U> f);

    public abstract <U> @NotNull Result<T, U> mapErr(@NotNull Function<? super @NotNull E, ? extends U> f);

    public abstract <R> R match(
        @NotNull Function<? super @NotNull T, ? extends R> isOk,
        @NotNull Function<? super @NotNull E, ? extends R> isErr
    );

    public abstract @NotNull Option<T> ok();

    public abstract <U> @NotNull Result<T, U> or(@NotNull Result<T, U> other);

    public abstract <U> @NotNull Result<T, U> orElse(@NotNull Function<? super @NotNull E, ? extends Result<T, U>> f);

    public abstract @NotNull Optional<T> toOptional();

    public abstract @NotNull T unwrap() throws NoSuchElementException;

    public abstract @NotNull E unwrapErr();
}
