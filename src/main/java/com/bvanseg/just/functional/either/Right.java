package com.bvanseg.just.functional.either;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.bvanseg.just.functional.option.Option;

public final class Right<L, R> extends Either<L, R> {

    private final R value;

    public Right(R value) {
        this.value = value;
    }

    @Override
    public @NotNull L expectLeft(@NotNull String errorMessage) throws NoSuchElementException {
        throw new NoSuchElementException(errorMessage);
    }

    @Override
    public @NotNull R expectRight(@NotNull String errorMessage) throws NoSuchElementException {
        return value;
    }

    @Override
    public @NotNull Either<R, L> flip() {
        return Either.left(value);
    }

    @Override
    public void ifLeft(@NotNull Consumer<? super @NotNull L> action) { /* NO-OP */ }

    @Override
    public void ifRight(@NotNull Consumer<? super @NotNull R> action) {
        action.accept(value);
    }

    @Override
    public @NotNull Either<L, R> inspectLeft(@NotNull Consumer<? super @NotNull L> action) {
        return this;
    }

    @Override
    public @NotNull Either<L, R> inspectRight(@NotNull Consumer<? super @NotNull R> action) {
        action.accept(value);
        return this;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isLeftAnd(@NotNull Predicate<? super @NotNull L> predicate) {
        return false;
    }

    @Override
    public boolean isLeftOr(@NotNull Predicate<? super @NotNull R> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public boolean isRightAnd(@NotNull Predicate<? super @NotNull R> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isRightOr(@NotNull Predicate<? super @NotNull L> predicate) {
        return true;
    }

    @Override
    public @NotNull Option<L> left() {
        return Option.none();
    }

    @Override
    public <U> @NotNull U map(
        @NotNull Function<? super @NotNull L, ? extends U> f,
        @NotNull Function<? super @NotNull R, ? extends U> g
    ) {
        return g.apply(value);
    }

    @Override
    public @NotNull <U, V> Either<U, V> mapEither(
        @NotNull Function<? super @NotNull L, ? extends U> f,
        @NotNull Function<? super @NotNull R, ? extends V> g
    ) {
        return Either.right(g.apply(value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NotNull <U> Either<U, R> mapLeft(@NotNull Function<? super @NotNull L, ? extends U> f) {
        return (Either<U, R>) this;
    }

    @Override
    public @NotNull <U> Either<L, U> mapRight(@NotNull Function<? super @NotNull R, ? extends U> f) {
        return Either.right(f.apply(value));
    }

    @Override
    public @NotNull Option<R> right() {
        return Option.some(value);
    }

    @Override
    public @NotNull L unwrapLeft() throws NoSuchElementException {
        throw new NoSuchElementException("No left value present.");
    }

    @Override
    public L leftOr(L other) {
        return other;
    }

    @Override
    public L leftOrElse(@NotNull Supplier<? extends L> supplier) {
        return supplier.get();
    }

    @Override
    public @NotNull R unwrapRight() throws NoSuchElementException {
        return value;
    }

    @Override
    public R rightOr(R other) {
        return value;
    }

    @Override
    public R rightOrElse(@NotNull Supplier<? extends R> supplier) {
        return value;
    }

    @Override
    public String toString() {
        return "Right(" + value + ")";
    }
}
