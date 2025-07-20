package com.bvanseg.just.functional.option;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.bvanseg.just.functional.function.Function3;
import com.bvanseg.just.functional.result.Result;

public sealed abstract class Option<T> permits Some, None {

    public static <T> @NotNull Option<T> ofNullable(@Nullable T value) {
        return value != null ? Option.some(value) : Option.none();
    }

    public static <T> @NotNull Some<T> some(@NotNull T value) {
        return new Some<>(value);
    }

    public static <T> @NotNull None<T> none() {
        return None.instance();
    }

    public static <T> @NotNull Option<T> flatten(@NotNull Option<? extends @NotNull Option<T>> nested) {
        return nested.match(Function.identity(), Option::none);
    }

    public static <T> @NotNull Option<T> guard(boolean condition, @NotNull T value) {
        return condition ? Option.some(value) : Option.none();
    }

    public static <T> @NotNull Option<T> when(
        boolean condition,
        @NotNull Supplier<? extends @NotNull Option<T>> supplier
    ) {
        return condition ? supplier.get() : Option.none();
    }

    public static <A, B, R> @NotNull Option<R> map2(
        @NotNull Option<A> a,
        @NotNull Option<B> b,
        @NotNull BiFunction<A, B, R> combiner
    ) {
        return a.andThen(
            va -> b.map(
                vb -> combiner.apply(va, vb)
            )
        );
    }

    public static <A, B, C, R> @NotNull Option<R> map3(
        @NotNull Option<A> a,
        @NotNull Option<B> b,
        @NotNull Option<C> c,
        @NotNull Function3<A, B, C, R> combiner
    ) {
        return flatten(
            map2(
                a,
                b,
                (av, bv) -> c.map(cv -> combiner.apply(av, bv, cv))
            )
        );
    }

    public abstract <R> @NotNull Option<R> and(@NotNull Option<R> other);

    public abstract <R> @NotNull Option<R> andThen(
        @NotNull Function<? super @NotNull T, ? extends @NotNull Option<R>> other
    );

    public abstract boolean contains(@NotNull T value);

    public abstract @NotNull T expect(@NotNull String errorMessage);

    public abstract @NotNull Option<T> filter(@NotNull Predicate<? super @NotNull T> predicate);

    public abstract void ifSome(@NotNull Consumer<? super @NotNull T> action);

    public abstract void ifNone(@NotNull Runnable action);

    public abstract @NotNull Option<T> inspect(@NotNull Consumer<? super @NotNull T> action);

    public abstract boolean isNone();

    public abstract boolean isNoneOr(@NotNull Predicate<? super @NotNull T> predicate);

    public abstract boolean isSome();

    public abstract boolean isSomeAnd(@NotNull Predicate<? super @NotNull T> predicate);

    public abstract <R> @NotNull Option<R> map(@NotNull Function<? super @NotNull T, ? extends R> f);

    public abstract <R> R mapOr(@NotNull Function<? super @NotNull T, ? extends R> f, R fallbackValue);

    public abstract <R> R mapOrElse(
        @NotNull Function<? super @NotNull T, ? extends R> f,
        @NotNull Supplier<? extends R> supplier
    );

    public abstract <R> R match(
        @NotNull Function<? super T, ? extends R> ifSome,
        @NotNull Supplier<? extends R> ifNone
    );

    public abstract <R> @NotNull Result<T, R> okOr(R fallbackValue);

    public abstract <R> @NotNull Option<R> or(@NotNull Option<R> other);

    public abstract <R> @NotNull Option<R> orElse(@NotNull Supplier<? extends @NotNull Option<R>> other);

    public abstract @NotNull Optional<T> toOptional();

    public abstract @NotNull Stream<T> toStream();

    public abstract @NotNull T unwrap();

    public abstract T unwrapOr(T other);

    public abstract T unwrapOrElse(@NotNull Supplier<? extends T> supplier);

    public abstract <X extends Throwable> T unwrapOrThrow(
        @NotNull Supplier<? extends @NotNull X> exceptionSupplier
    ) throws X;

    public abstract <U, R> @NotNull Option<R> zip(
        @NotNull Option<U> other,
        @NotNull BiFunction<? super @NotNull T, ? super @NotNull U, ? extends R> combiner
    );

    public <R> R reduce(@NotNull Function<? super @NotNull T, ? extends R> f, R identity) {
        return match(f, () -> identity);
    }
}
