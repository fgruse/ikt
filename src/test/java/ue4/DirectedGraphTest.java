package ue4;

import org.junit.Test;
import ue4.directed.DirectedGraph;
import ue4.directed.EdgeDG;
import ue4.directed.NodeDG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DirectedGraphTest {

    @Test
    public void testInsertAndGetNode() {
        DirectedGraph d = new DirectedGraph();
        assertEquals(0, d.getNodes().getSize());
        assertEquals(0, d.getEdges().getSize());

        NodeDG n1 = new NodeDG("Knoten 1");
        NodeDG n2 = new NodeDG("Knoten 2");

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
        DirectedGraph d = new DirectedGraph();

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
        assertTrue(d.getEdges().contains(new EdgeDG(5.9, new NodeDG("Knoten 1"), new NodeDG("Knoten 2"))));

        // Anpassung der Grade/ Eltern/ Kinder der Nodes
        assertEquals(1, d.getNode("Knoten 1").getOutputDegree());
        assertEquals(1, d.getNode("Knoten 2").getInputDegree());

        // Weight des Graphen
        assertEquals(5.9, d.getWeight(), 0.000001);
        d.makeEdge("Knoten 2", "Knoten 1", 4.1);
        assertEquals(10.0, d.getWeight(), 0.000001);
    }
}
