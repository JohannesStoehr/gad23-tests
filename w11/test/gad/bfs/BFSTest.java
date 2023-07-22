package gad.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BFSTest {
    @Test
    public void testBFSOnSingleGraphNode() {
        Graph g = new Graph();
        g.addNode();
        BFS bfs = new BFS();
        bfs.sssp(g, 0);
        assertEquals(0, bfs.getDepth(0));
        assertEquals(-1, bfs.getParent(0));
        assertTrue(bfs.visitedNode(0));
    }

    @Test
    public void testBFSOnDisconnectedGraph() {
        Graph g = new Graph();
        int node1 = g.addNode();
        int node2 = g.addNode();
        BFS bfs = new BFS();
        bfs.sssp(g, node1);
        assertEquals(0, bfs.getDepth(node1));
        assertEquals(-1, bfs.getDepth(node2));
        assertTrue(bfs.visitedNode(node1));
        assertFalse(bfs.visitedNode(node2));
    }

    @Test
    public void testBFSOnConnectedGraph() {
        Graph g = new Graph();
        int node1 = g.addNode();
        int node2 = g.addNode();
        g.addEdge(node1, node2);
        BFS bfs = new BFS();
        bfs.sssp(g, node1);
        assertEquals(0, bfs.getDepth(node1));
        assertEquals(1, bfs.getDepth(node2));
        assertTrue(bfs.visitedNode(node1));
        assertTrue(bfs.visitedNode(node2));
    }

    @Test
    public void testBFSOnGraphWithCycle() {
        Graph g = new Graph();
        int node1 = g.addNode();
        int node2 = g.addNode();
        int node3 = g.addNode();
        g.addEdge(node1, node2);
        g.addEdge(node2, node3);
        g.addEdge(node3, node1);
        BFS bfs = new BFS();
        bfs.sssp(g, node1);
        assertTrue(bfs.visitedNode(node1));
        assertTrue(bfs.visitedNode(node2));
        assertTrue(bfs.visitedNode(node3));
        assertEquals(0, bfs.getDepth(node1));
        assertEquals(1, bfs.getDepth(node2));
        assertEquals(1, bfs.getDepth(node3));
    }

    @Test
    public void testBFSOnGraphWithMultipleConnectedComponents() {
        Graph g = new Graph();
        int node1 = g.addNode();
        int node2 = g.addNode();
        int node3 = g.addNode();
        int node4 = g.addNode();
        g.addEdge(node1, node2);
        g.addEdge(node3, node4);
        BFS bfs = new BFS();
        bfs.sssp(g, node1);
        assertTrue(bfs.visitedNode(node1));
        assertTrue(bfs.visitedNode(node2));
        assertFalse(bfs.visitedNode(node3));
        assertFalse(bfs.visitedNode(node4));
        assertEquals(0, bfs.getDepth(node1));
        assertEquals(1, bfs.getDepth(node2));
        assertEquals(-1, bfs.getDepth(node3));
        assertEquals(-1, bfs.getDepth(node4));
    }

    @Test
    public void testBFSOnGraphWithReflexivity() {
        Graph g = new Graph();
        int node1 = g.addNode();
        g.addEdge(node1, node1);
        BFS bfs = new BFS();
        bfs.sssp(g, node1);
        assertTrue(bfs.visitedNode(node1));
        assertEquals(0, bfs.getDepth(node1));
    }
}
