package ue4.versuch1;

import org.junit.Test;
import ue4.GerichteterGraph;
import ue4.Knoten;
import ue4.Liste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GerichteterGraphTest {

    @Test
    public void testGraph() {
        GerichteterGraph g = new GerichteterGraph(10);

        g.insertNode("Knoten 1");
        g.insertNode("Knoten 2");
        g.insertNode("Knoten 3");
        g.insertNode("Knoten 4");
        g.insertNode("Knoten 5");
        g.insertNode("Knoten 6");
        g.insertNode("Knoten 7");
        g.insertNode("Knoten 8");
        g.insertNode("Knoten 9");
        g.insertNode("Knoten 10");

        g.makeEdge("Knoten 5", "Knoten 2", 4.5F);
        g.makeEdge("Knoten 2", "Knoten 4", 8.5F);
        g.makeEdge("Knoten 1", "Knoten 5", 12.5F);
        g.makeEdge("Knoten 4", "Knoten 5", 92.5F);
        g.makeEdge("Knoten 7", "Knoten 5", 92.5F);

        final Knoten node1 = g.getNode("Knoten 1");
        final Knoten node5 = g.getNode("Knoten 5");
        assertEquals(3, node5.getInputDegree());
        assertEquals(1, node5.getOutputDegree());
        assertEquals(4, node5.getDegree());
        assertFalse(node5.isLeaf());
        assertFalse(node5.isRoot());
        assertTrue(node1.isRoot());
        assertFalse(node1.isLeaf());
        assertTrue(g.getNode("Knoten 8").isLeaf());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testZuVieleKnoten() {
        GerichteterGraph g = new GerichteterGraph(2);

        g.insertNode("Knoten 1");
        g.insertNode("Knoten 2");
        g.insertNode("Knoten 3");
    }

}
