package com.bvanseg.just.traversal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BFSTest {

    @Test
    void constructor_Throws_UnsupportedOperationException() {
        // Act & Assert
        assertThrows(UnsupportedOperationException.class, BFS::new);
    }

    @Test
    void testSimpleTraversal() {
        // Arrange
        Map<String, List<String>> graph = Map.of(
            "A",
            List.of("B", "C"),
            "B",
            List.of("D"),
            "C",
            List.of("E"),
            "D",
            List.of(),
            "E",
            List.of()
        );

        List<String> visitedOrder = new ArrayList<>();

        // Act
        BFS.traverse(
            "A",
            node -> graph.getOrDefault(node, List.of()),
            visitedOrder::add
        );

        // Assert
        // BFS traversal order
        assertEquals(List.of("A", "B", "C", "D", "E"), visitedOrder);
    }

    @Test
    void testTraversalWithStopCondition() {
        // Arrange
        Map<Integer, List<Integer>> graph = Map.of(
            1,
            List.of(2, 3),
            2,
            List.of(4),
            3,
            List.of(5),
            4,
            List.of(),
            5,
            List.of()
        );

        List<Integer> visited = new ArrayList<>();

        // Act
        BFS.traverse(
            1,
            node -> graph.getOrDefault(node, List.of()),
            visited::add,
            // stop when reaching node 3.
            node -> node == 3
        );

        // Assert
        // Stops after visiting node 3.
        assertEquals(List.of(1, 2, 3), visited);
    }

    @Test
    void testTraversalWithCycle() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("C"));
        // cycle A -> B -> C -> A.
        graph.put("C", List.of("A"));

        Set<String> visited = new HashSet<>();

        // Act & Assert
        assertDoesNotThrow(() -> {
            BFS.traverse(
                "A",
                node -> graph.getOrDefault(node, List.of()),
                visited::add
            );
        });

        assertEquals(Set.of("A", "B", "C"), visited);
    }

    @Test
    void testSingleNode() {
        // Arrange
        List<String> visited = new ArrayList<>();

        // Act
        BFS.traverse(
            "X",
            // no neighbors
            _ -> List.of(),
            visited::add
        );

        // Assert
        assertEquals(List.of("X"), visited);
    }
}
