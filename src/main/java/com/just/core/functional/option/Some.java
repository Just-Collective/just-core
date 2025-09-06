package com.just.core.functional.option;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.just.core.functional.result.Result;

public final class Some<T> extends Option<T> {

    private final T value;

    Some(@NotNull T value) {
        this.value = value;
    }

    @Override
    public <R> @NotNull Option<R> and(@NotNull Option<R> other) {
        return other;
    }

    @Override
    public <R> @NotNull Option<R> andThen(@NotNull Function<? super @NotNull T, ? extends @NotNull Option<R>> other) {
        return other.apply(value);
    }

    @Override
    public boolean contains(@NotNull T value) {
        return Objects.equals(this.value, value);
    }

    @Override
    public @NotNull T expect(@NotNull String errorMessage) {
        return value;
    }

    @Override
    public @NotNull Option<T> filter(@NotNull Predicate<? super @NotNull T> predicate) {
        return predicate.test(value) ? this : Option.none();
    }

    @Override
    public void ifSome(@NotNull Consumer<? super @NotNull T> action) {
        action.accept(value);
    }

    @Override
    public void ifNone(@NotNull Runnable action) { /* NO-OP */ }

    @Override
    public @NotNull Option<T> inspect(@NotNull Consumer<? super @NotNull T> action) {
        action.accept(value);
        return this;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public boolean isNoneOr(@NotNull Predicate<? super @NotNull T> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isSomeAnd(@NotNull Predicate<? super @NotNull T> predicate) {
        return predicate.test(value);
    }

    @Override
    public <R> @NotNull Option<R> map(@NotNull Function<? super @NotNull T, ? extends R> f) {
        return Option.some(f.apply(value));
    }

    @Override
    public <R> R mapOr(@NotNull Function<? super @NotNull T, ? extends R> f, R fallbackValue) {
        return f.apply(value);
    }

    @Override
    public <R> R mapOrElse(
        @NotNull Function<? super @NotNull T, ? extends R> f,
        @NotNull Supplier<? extends R> supplier
    ) {
        return f.apply(value);
    }

    @Override
    public <R> R match(
        @NotNull Function<? super @NotNull T, ? extends R> ifSome,
        @NotNull Supplier<? extends R> ifNone
    ) {
        return ifSome.apply(value);
    }

    @Override
    public <R> @NotNull Result<T, R> okOr(R fallbackValue) {
        return Result.ok(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> @NotNull Option<R> or(@NotNull Option<R> other) {
        return (Option<R>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> @NotNull Option<R> orElse(@NotNull Supplier<? extends @NotNull Option<R>> other) {
        return (Option<R>) this;
    }

    @Override
    public @NotNull Optional<T> toOptional() {
        return Optional.of(value);
    }

    @Override
    public @NotNull Stream<T> toStream() {
        return Stream.of(value);
    }

    @Override
    public @NotNull T unwrap() {
        return value;
    }

    @Override
    public T unwrapOr(T other) {
        return value;
    }

    @Override
    public T unwrapOrElse(@NotNull Supplier<? extends T> supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> T unwrapOrThrow(@NotNull Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <U, R> @NotNull Option<R> zip(
        @NotNull Option<U> other,
        @NotNull BiFunction<? super @NotNull T, ? super U, ? extends R> combiner
    ) {
        return other.match(
            u -> Option.some(combiner.apply(value, u)),
            Option::none
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Some<?> other)) {
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
        return "Some(" + value + ")";
    }
}
