package com.bvanseg.just.traversal;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DFS {

    public static <T> void traverse(
        T start,
        Function<T, Collection<T>> neighborProvider,
        Consumer<T> onVisit,
        Predicate<T> stopCondition
    ) {
        var visited = new HashSet<T>();
        var queue = new ArrayDeque<T>();

        queue.push(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            var current = queue.pop();

            onVisit.accept(current);

            if (stopCondition.test(current)) {
                break;
            }

            for (var neighbor : neighborProvider.apply(current)) {
                if (visited.add(neighbor)) {
                    queue.push(neighbor);
                }
            }
        }
    }

    public static <T> void traverse(T start, Function<T, Collection<T>> neighborProvider, Consumer<T> onVisit) {
        traverse(start, neighborProvider, onVisit, $1 -> false);
    }

    DFS() {
        throw new UnsupportedOperationException();
    }
}
