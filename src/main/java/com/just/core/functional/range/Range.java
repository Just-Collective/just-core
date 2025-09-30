package com.just.core.functional.range;

import com.just.core.functional.function.Lazy;
import com.just.core.functional.option.Option;

public final class Range<T extends Comparable<T>> {

    public static <T extends Comparable<T>> Range<T> closed(T min, T max) {
        return new Range<>(Option.some(min), BoundType.INCLUSIVE, Option.some(max), BoundType.INCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> open(T min, T max) {
        return new Range<>(Option.some(min), BoundType.EXCLUSIVE, Option.some(max), BoundType.EXCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> openClosed(T min, T max) {
        return new Range<>(Option.some(min), BoundType.EXCLUSIVE, Option.some(max), BoundType.INCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> closedOpen(T min, T max) {
        return new Range<>(Option.some(min), BoundType.INCLUSIVE, Option.some(max), BoundType.EXCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> atLeast(T min) {
        return new Range<>(Option.some(min), BoundType.INCLUSIVE, Option.none(), BoundType.INCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> greaterThan(T min) {
        return new Range<>(Option.some(min), BoundType.EXCLUSIVE, Option.none(), BoundType.INCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> atMost(T max) {
        return new Range<>(Option.none(), BoundType.INCLUSIVE, Option.some(max), BoundType.INCLUSIVE);
    }

    public static <T extends Comparable<T>> Range<T> lessThan(T max) {
        return new Range<>(Option.none(), BoundType.INCLUSIVE, Option.some(max), BoundType.EXCLUSIVE);
    }

    private final Option<T> lowerBound;

    private final Option<T> upperBound;

    private final BoundType lowerType;

    private final BoundType upperType;

    private final Lazy<String> toStringLazy;

    private Range(
        Option<T> lowerBound,
        BoundType lowerType,
        Option<T> upperBound,
        BoundType upperType
    ) {
        this.lowerBound = lowerBound;
        this.lowerType = lowerType;
        this.upperBound = upperBound;
        this.upperType = upperType;
        this.toStringLazy = Lazy.of(this::describe);
    }

    public boolean contains(T value) {
        if (lowerBound.isSome()) {
            var cmp = value.compareTo(lowerBound.unwrap());

            if (lowerType == BoundType.EXCLUSIVE && cmp <= 0) {
                return false;
            }

            if (lowerType == BoundType.INCLUSIVE && cmp < 0) {
                return false;
            }
        }

        if (upperBound.isSome()) {
            var cmp = value.compareTo(upperBound.unwrap());

            if (upperType == BoundType.EXCLUSIVE && cmp >= 0) {
                return false;
            }

            if (upperType == BoundType.INCLUSIVE && cmp > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return toStringLazy.get();
    }

    private String describe() {
        var lb = lowerBound.map(Object::toString).unwrapOr("-∞");
        var ub = upperBound.map(Object::toString).unwrapOr("+∞");
        var left = lowerType == BoundType.INCLUSIVE ? "[" : "(";
        var right = upperType == BoundType.INCLUSIVE ? "]" : ")";
        return left + lb + ", " + ub + right;
    }
}
