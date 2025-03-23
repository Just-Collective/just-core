package com.bvanseg.just.functional.option;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.bvanseg.just.functional.result.Result;

public final class Some<T> extends Option<T> {

    private final T value;

    Some(T value) {
        this.value = value;
    }

    @Override
    public <R> Option<R> and(Option<R> other) {
        return other;
    }

    @Override
    public <R> Option<R> andThen(Function<? super T, ? extends Option<R>> other) {
        return other.apply(value);
    }

    @Override
    public boolean contains(T value) {
        return Objects.equals(this.value, value);
    }

    @Override
    public T expect(String errorMessage) {
        return value;
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return predicate.test(value) ? this : Option.none();
    }

    @Override
    public <R> Option<R> flatMap(Function<? super T, ? extends Option<R>> f) {
        return f.apply(value);
    }

    @Override
    public void ifSome(Consumer<? super T> action) {
        action.accept(value);
    }

    @Override
    public void ifNone(Runnable action) { /* NO-OP */ }

    @Override
    public Option<T> inspect(Consumer<? super T> action) {
        action.accept(value);
        return this;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public boolean isNoneOr(Predicate<? super T> predicate) {
        return predicate.test(value);
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isSomeAnd(Predicate<? super T> predicate) {
        return predicate.test(value);
    }

    @Override
    public <R> Option<R> map(Function<? super T, ? extends R> f) {
        return Option.some(f.apply(value));
    }

    @Override
    public <R> R mapOr(Function<? super T, ? extends R> f, R fallbackValue) {
        return f.apply(value);
    }

    @Override
    public <R> R mapOrElse(Function<? super T, ? extends R> f, Supplier<? extends R> supplier) {
        return f.apply(value);
    }

    @Override
    public <R> R match(Function<? super T, ? extends R> ifSome, Supplier<? extends R> ifNone) {
        return ifSome.apply(value);
    }

    @Override
    public <R> Result<T, R> okOr(R fallbackValue) {
        return Result.ok(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Option<R> or(Option<R> other) {
        return (Option<R>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Option<R> orElse(Supplier<? extends Option<R>> other) {
        return (Option<R>) this;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.of(value);
    }

    @Override
    public Stream<T> toStream() {
        return Stream.of(value);
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public T unwrapOr(T other) {
        return value;
    }

    @Override
    public T unwrapOrElse(Supplier<? extends T> supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> T unwrapOrThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <U, R> Option<R> zip(Option<U> other, BiFunction<? super T, ? super U, ? extends R> combiner) {
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
