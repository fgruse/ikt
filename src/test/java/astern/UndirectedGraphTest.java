package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest {
/*
    @Test
    public void testConstructor() {
        UndirectedGraph graph = new UndirectedGraph();
        assertEquals(10000, graph.getNodes().length);
        assertNull(graph.getNodes()[0]);
        final boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        assertEquals(10000, adjacencyMatrix.length);
        assertEquals(10000, adjacencyMatrix[0].length);
        assertFalse(adjacencyMatrix[0][1]);
    }*/

    @Test
    public void testGraphFromFile() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo.csv");
        final boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        assertEquals(10000, graph.getNodes().length);
        assertEquals(10000, adjacencyMatrix.length);
        assertEquals(10000, adjacencyMatrix[0].length);
        assertNotNull(graph.getNodes()[6789]);
        assertTrue(adjacencyMatrix[4755][7885]);
        assertTrue(adjacencyMatrix[7885][4755]);
        assertTrue(adjacencyMatrix[7105][8837]);
        assertTrue(adjacencyMatrix[9990][9992]);
        assertFalse(adjacencyMatrix[9990][1]);
    }
}
