package com.bvanseg.just.functional.option;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.bvanseg.just.functional.function.TriFunction;

public sealed abstract class Option<T> permits Some, None {

    public static <T> Option<T> ofNullable(T value) {
        return value != null ? Option.some(value) : Option.none();
    }

    public static <T> Some<T> some(T value) {
        return new Some<>(value);
    }

    public static <T> Option<T> none() {
        return None.instance();
    }

    public static <T> Option<T> flatten(Option<Option<T>> nested) {
        return nested.match(Function.identity(), Option::none);
    }

    public static <T> Option<T> guard(boolean condition, T value) {
        return condition ? Option.some(value) : Option.none();
    }

    public static <T> Option<T> when(boolean condition, Supplier<Option<T>> supplier) {
        return condition ? supplier.get() : Option.none();
    }

    public static <A, B, R> Option<R> map2(
        Option<A> a,
        Option<B> b,
        BiFunction<A, B, R> combiner
    ) {
        return a.flatMap(
            va -> b.map(
                vb -> combiner.apply(va, vb)
            )
        );
    }

    public static <A, B, C, R> Option<R> map3(
        Option<A> a,
        Option<B> b,
        Option<C> c,
        TriFunction<A, B, C, R> combiner
    ) {
        return flatten(
            map2(
                a,
                b,
                (av, bv) -> c.map(cv -> combiner.apply(av, bv, cv))
            )
        );
    }

    public abstract <R> Option<R> and(Option<R> other);

    public abstract <R> Option<R> andThen(Function<T, Option<R>> other);

    public abstract boolean contains(T value);

    public abstract T expect(String errorMessage);

    public abstract Option<T> filter(Predicate<? super T> predicate);

    public abstract <R> Option<R> flatMap(Function<? super T, ? extends Option<R>> f);

    public abstract void ifSome(Consumer<? super T> action);

    public abstract void ifNone(Runnable action);

    public abstract Option<T> inspect(Consumer<? super T> action);

    public abstract boolean isNone();

    public abstract boolean isNoneOr(Predicate<T> predicate);

    public abstract boolean isSome();

    public abstract boolean isSomeAnd(Predicate<? super T> predicate);

    public abstract <R> Option<R> map(Function<? super T, ? extends R> f);

    public abstract <R> R mapOr(Function<? super T, ? extends R> f, R fallbackValue);

    public abstract <R> R mapOrElse(Function<? super T, ? extends R> f, Supplier<R> supplier);

    public abstract <R> R match(Function<? super T, ? extends R> ifSome, Supplier<? extends R> ifNone);

    public abstract Optional<T> toOptional();

    public abstract Stream<T> toStream();

    public abstract T unwrap();

    public abstract T unwrapOr(T other);

    public abstract T unwrapOrElse(Supplier<? extends T> supplier);

    public abstract <X extends Throwable> T unwrapOrThrow(Supplier<? extends X> exceptionSupplier) throws X;

    public abstract <U, R> Option<R> zip(Option<U> other, BiFunction<? super T, ? super U, ? extends R> combiner);

    public <R> R reduce(Function<? super T, ? extends R> f, R identity) {
        return match(f, () -> identity);
    }
}
