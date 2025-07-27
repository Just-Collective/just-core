package com.bvanseg.just.functional.either;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.bvanseg.just.functional.option.Option;

public final class Left<L, R> extends Either<L, R> {

    private final L value;

    public Left(L value) {
        this.value = value;
    }

    @Override
    public @NotNull L expectLeft(@NotNull String errorMessage) throws NoSuchElementException {
        return value;
    }

    @Override
    public @NotNull R expectRight(@NotNull String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public @NotNull Either<R, L> flip() {
        return Either.right(value);
    }

    @Override
    public void ifLeft(@NotNull Consumer<? super @NotNull L> action) {
        action.accept(value);
    }

    @Override
    public void ifRight(@NotNull Consumer<? super @NotNull R> action) { /* NO-OP */ }

    @Override
    public @NotNull Either<L, R> inspectLeft(@NotNull Consumer<? super @NotNull L> action) {
        action.accept(value);
        return this;
    }

    @Override
    public @NotNull Either<L, R> inspectRight(@NotNull Consumer<? super @NotNull R> action) {
        return this;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isLeftAnd(@NotNull Predicate<? super @NotNull L> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isLeftOr(@NotNull Predicate<? super @NotNull R> predicate) {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public boolean isRightAnd(@NotNull Predicate<? super @NotNull R> predicate) {
        return false;
    }

    @Override
    public boolean isRightOr(@NotNull Predicate<? super @NotNull L> predicate) {
        return predicate.test(value);
    }

    @Override
    public @NotNull Option<L> left() {
        return Option.some(value);
    }

    @Override
    public @NotNull <U> Either<U, R> mapLeft(@NotNull Function<? super @NotNull L, ? extends U> f) {
        return Either.left(f.apply(value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NotNull <U> Either<L, U> mapRight(@NotNull Function<? super @NotNull R, ? extends U> f) {
        return (Either<L, U>) this;
    }

    @Override
    public @NotNull Option<R> right() {
        return Option.none();
    }

    @Override
    public @NotNull L unwrapLeft() throws NoSuchElementException {
        return value;
    }

    @Override
    public L leftOr(L other) {
        return value;
    }

    @Override
    public L leftOrElse(@NotNull Supplier<? extends L> supplier) {
        return value;
    }

    @Override
    public <U> @NotNull U map(
        @NotNull Function<? super @NotNull L, ? extends U> f,
        @NotNull Function<? super @NotNull R, ? extends U> g
    ) {
        return f.apply(value);
    }

    @Override
    public @NotNull R unwrapRight() throws NoSuchElementException {
        throw new NoSuchElementException("No right value present.");
    }

    @Override
    public R rightOr(R other) {
        return other;
    }

    @Override
    public R rightOrElse(@NotNull Supplier<? extends R> supplier) {
        return supplier.get();
    }
}
