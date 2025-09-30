package com.just.core.functional.range;

import java.util.List;

import com.just.core.functional.function.Lazy;

public final class RangeSet<T extends Comparable<T>> {

    private final List<Range<T>> ranges;

    private final Lazy<String> toStringLazy;

    public RangeSet(List<Range<T>> ranges) {
        this.ranges = List.copyOf(ranges);
        this.toStringLazy = Lazy.of(this::describe);
    }

    @SafeVarargs
    public static <T extends Comparable<T>> RangeSet<T> of(Range<T>... ranges) {
        return new RangeSet<>(List.of(ranges));
    }

    public boolean contains(T value) {
        for (var range : ranges) {
            if (range.contains(value)) {
                return true;
            }
        }

        return false;
    }

    public List<Range<T>> ranges() {
        return ranges;
    }

    @Override
    public String toString() {
        return toStringLazy.get();
    }

    private String describe() {
        if (ranges.isEmpty()) {
            return "{}";
        }

        var sb = new StringBuilder();
        sb.append("in any of ");

        for (var i = 0; i < ranges.size(); i++) {
            sb.append(ranges.get(i).toString());

            if (i < ranges.size() - 1) {
                sb.append(" ∪ ");
            }
        }

        return sb.toString();
    }
}
