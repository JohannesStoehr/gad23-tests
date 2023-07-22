package gad.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {
    Graph graph;

    @BeforeEach
    void init() {
        graph = new Graph();
    }

    @Test
    void shouldStartWithZeroAsId() {
        assertEquals(0, graph.addNode());
    }

    @Test
    void shouldReturnAllNodes() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        assertEquals(3, graph.getAllNodes().size());
    }

    @Test
    void shouldReturnEmptyNeighbours() {
        graph.addNode();
        assertEquals(0, graph.getAllNeighbours(0).size());
    }

    @Test
    void shouldReturnEmptyAllNodes() {
        assertEquals(0, graph.getAllNodes().size());
    }

    @Test
    void connectionToItselfShouldBePossible() {
        graph.addNode();
        graph.addEdge(0, 0);
        assertEquals(1, graph.getAllNeighbours(0).size());
    }
}
