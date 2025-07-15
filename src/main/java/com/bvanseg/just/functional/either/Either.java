package com.bvanseg.just.functional.either;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.bvanseg.just.functional.option.Option;

public sealed abstract class Either<L, R> permits Left, Right {

    public static <L, R> Left<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Right<L, R> right(R value) {
        return new Right<>(value);
    }

    public abstract @NotNull L expectLeft(@NotNull String errorMessage) throws NoSuchElementException;

    public abstract @NotNull R expectRight(@NotNull String errorMessage) throws NoSuchElementException;

    public abstract @NotNull Either<R, L> flip();

    public abstract void ifLeft(@NotNull Consumer<? super @NotNull L> action);

    public abstract void ifRight(@NotNull Consumer<? super @NotNull R> action);

    public abstract @NotNull Either<L, R> inspectLeft(@NotNull Consumer<? super @NotNull L> action);

    public abstract @NotNull Either<L, R> inspectRight(@NotNull Consumer<? super @NotNull R> action);

    public abstract boolean isLeft();

    public abstract boolean isLeftAnd(@NotNull Predicate<? super @NotNull L> predicate);

    public abstract boolean isLeftOr(@NotNull Predicate<? super @NotNull R> predicate);

    public abstract boolean isRight();

    public abstract boolean isRightAnd(@NotNull Predicate<? super @NotNull R> predicate);

    public abstract boolean isRightOr(@NotNull Predicate<? super @NotNull L> predicate);

    public abstract @NotNull Option<L> left();

    public abstract L leftOr(L other);

    public abstract L leftOrElse(@NotNull Supplier<? extends L> supplier);

    public abstract <U> @NotNull Either<U, R> mapLeft(@NotNull Function<? super @NotNull L, ? extends U> f);

    public abstract <U> @NotNull Either<L, U> mapRight(@NotNull Function<? super @NotNull R, ? extends U> f);

    public abstract @NotNull Option<R> right();

    public abstract R rightOr(R other);

    public abstract R rightOrElse(@NotNull Supplier<? extends R> supplier);

    public abstract @NotNull L unwrapLeft() throws NoSuchElementException;

    public abstract @NotNull R unwrapRight() throws NoSuchElementException;
}
