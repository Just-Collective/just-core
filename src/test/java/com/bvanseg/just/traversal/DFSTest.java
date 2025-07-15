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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DFSTest {

    @Test
    void constructor_Throws_UnsupportedOperationException() {
        // Act & Assert
        assertThrows(UnsupportedOperationException.class, DFS::new);
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
        DFS.traverse(
            "A",
            node -> graph.getOrDefault(node, List.of()),
            visitedOrder::add
        );

        // Assert
        // DFS may visit in different valid orders depending on neighbor push order,
        // here's the expected order for left-to-right child push
        assertEquals(List.of("A", "C", "E", "B", "D"), visitedOrder);
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
        DFS.traverse(
            1,
            node -> graph.getOrDefault(node, List.of()),
            visited::add,
            node -> node == 4
        );

        // Assert
        assertTrue(visited.contains(4));
        // May be visited *before* 4 is encountered
        assertTrue(visited.contains(5));
    }

    @Test
    void testTraversalWithCycle() {
        // Arrange
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("C"));
        graph.put("C", List.of("A")); // cycle A -> B -> C -> A

        Set<String> visited = new HashSet<>();

        // Act & Assert
        assertDoesNotThrow(() -> {
            DFS.traverse(
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
        DFS.traverse(
            "X",
            _ -> List.of(),
            visited::add
        );

        // Assert
        assertEquals(List.of("X"), visited);
    }
}
