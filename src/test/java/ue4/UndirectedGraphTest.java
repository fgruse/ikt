package ue4;

import org.junit.Test;
import ue4.directed.DirectedGraph;
import ue4.undirected.EdgeUG;
import ue4.undirected.NodeUG;
import ue4.undirected.UndirectedGraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest {

    @Test
    public void testInsertAndGetNode() {
        UndirectedGraph d = new UndirectedGraph();
        assertEquals(0, d.getNodes().getSize());
        assertEquals(0, d.getEdges().getSize());

        NodeUG n1 = new NodeUG("Knoten 1");
        NodeUG n2 = new NodeUG("Knoten 2");

        d.insertNode("Knoten 1");
        d.insertNode("Knoten 2");
        assertEquals(2, d.getNodes().getSize());
        assertEquals(0, d.getEdges().getSize());
        assertEquals(n1, d.getNode("Knoten 1"));
        assertEquals(n2, d.getNode("Knoten 2"));
        assertNotEquals(n1, d.getNode("Knoten 2"));
        assertTrue(d.getNodes().contains(n1));
        assertTrue(d.getNodes().contains(n2));
        assertNull(d.getNode("Nicht vorhanden"));
    }

    @Test
    public void testMakeAndGetEdgeAndGetWeight() {
        UndirectedGraph d = new UndirectedGraph();

        // keine Kante einfügen, wenn Knoten noch nicht vorhanden sind
        d.makeEdge("Knoten 1", "Knoten 2", 5.9);
        assertEquals(0, d.getEdges().getSize());
        assertEquals(0.0, d.getWeight(), 0.000001);

        // Kante einfügen
        d.insertNode("Knoten 1");
        d.insertNode("Knoten 2");
        d.makeEdge("Knoten 1", "Knoten 2", 5.9);
        assertEquals(1, d.getEdges().getSize());
        assertEquals(2, d.getNodes().getSize());
        assertTrue(d.getEdges().contains(new EdgeUG(5.9, new NodeUG("Knoten 1"), new NodeUG("Knoten 2"))));

        // Anpassung der Grade/ Eltern/ Kinder der Nodes
        assertEquals(1, d.getNode("Knoten 1").getDegree());
        assertEquals(1, d.getNode("Knoten 2").getDegree());

        // Weight des Graphen
        assertEquals(5.9, d.getWeight(), 0.000001);
        d.insertNode("Knoten 3");
        d.makeEdge("Knoten 3", "Knoten 1", 4.1);
        assertEquals(10.0, d.getWeight(), 0.000001);

        // Reihenfolge der Knoten ist egal --> Kante wird nur einmal eingefügt
        d.makeEdge("Knoten 2", "Knoten 1", 17.1);
        assertEquals(10.0, d.getWeight(), 0.000001);
    }

    @Test
    public void spanningTreeTest() {
        UndirectedGraph g = new UndirectedGraph();
        g.insertNode("A");
        g.insertNode("B");
        g.insertNode("C");
        g.insertNode("D");
        g.insertNode("E");
        g.insertNode("F");
        g.makeEdge("E", "A", 1.0);
        g.makeEdge("A","C", 9.0);
        g.makeEdge("A", "D", 5.0);
        g.makeEdge("D", "B", 10.0);
        g.makeEdge("C", "B", 3.0);
        g.makeEdge("B", "F", 7.0);

        assertEquals(6, g.getNodes().getSize());
        assertEquals(6, g.getEdges().getSize());

        DirectedGraph minimalSpanningTree = g.getMinimalSpanningTree();

        assertEquals(6, minimalSpanningTree.getNodes().getSize());
        assertEquals(5, minimalSpanningTree.getEdges().getSize());

        assertTrue(minimalSpanningTree.getNode("F").isRoot());
        assertTrue(minimalSpanningTree.getNode("E").isLeaf());
        assertTrue(minimalSpanningTree.getNode("D").isLeaf());

        assertFalse(minimalSpanningTree.getNode("A").isLeaf());
        assertFalse(minimalSpanningTree.getNode("C").isLeaf());
        assertFalse(minimalSpanningTree.getNode("B").isLeaf());
        assertFalse(minimalSpanningTree.getNode("F").isLeaf());

        assertFalse(minimalSpanningTree.getNode("A").isRoot());
        assertFalse(minimalSpanningTree.getNode("B").isRoot());
        assertFalse(minimalSpanningTree.getNode("C").isRoot());
        assertFalse(minimalSpanningTree.getNode("D").isRoot());
        assertFalse(minimalSpanningTree.getNode("E").isRoot());

        assertEquals(1, minimalSpanningTree.getNode("F").getDegree());
        assertEquals(2, minimalSpanningTree.getNode("B").getDegree());
        assertEquals(2, minimalSpanningTree.getNode("C").getDegree());
        assertEquals(3, minimalSpanningTree.getNode("A").getDegree());
        assertEquals(2, minimalSpanningTree.getNode("A").getOutputDegree());
        assertEquals(1, minimalSpanningTree.getNode("F").getDegree());
    }
}
