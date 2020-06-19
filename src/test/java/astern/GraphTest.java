package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    @Test
    public void test() {
        // graph wird richtig initialisiert
        Graph g = new Graph(5);
        assertEquals(5, g.getNodes().length);
        assertNull(g.getNodes()[0]);
        final boolean[][] adjazenzmatrix = g.getAdjacencyMatrix();
        assertEquals(5, adjazenzmatrix.length);
        assertEquals(5, adjazenzmatrix[0].length);
        assertFalse(adjazenzmatrix[0][0]);

        // knoten wird eingefügt
        assertTrue(g.insertNode(0, 1000, 400));
        Node node = g.getNodes()[0];
        assertNotNull(node);
        assertEquals(1000, node.getxCoordinate());
        assertEquals(400, node.getyCoordinate());

        // knoten wird nicht überschrieben
        assertFalse(g.insertNode(0, 2000, 300));
        Node node2 = g.getNodes()[0];
        assertEquals(node, node2);

        // strasse wird nicht eingefügt, weil ort 4 (noch) nicht existiert
        assertFalse(g.insertEdge(0, 4));

        // mehr knoten einfügen
        assertTrue(g.insertNode(1, 300, 470));
        assertTrue(g.insertNode(2, 190, 788));
        assertTrue(g.insertNode(3, 25480, 2569));
        assertTrue(g.insertNode(4, 12589, 8559));

        // strasse einfügen
        assertFalse(g.getAdjacencyMatrix()[0][4]);
        assertTrue(g.insertEdge(0, 4));
        assertTrue(g.getAdjacencyMatrix()[0][4]);

        // mehr strassen einfügen
        assertTrue(g.insertEdge(2, 1));
        assertTrue(g.insertEdge(1, 3));
        assertTrue(g.insertEdge(1, 2));

        // nachbarn von ort finden
        final Node[] nachbarorte = g.getNeighbors(1);
        final Node[] erwarteteNachbarorte = {g.getNodes()[2], g.getNodes()[3]};
        assertNotNull(nachbarorte);
        assertNotNull(erwarteteNachbarorte);
        // TODO - deprecated ?? use something else ??
        assertEquals(erwarteteNachbarorte, nachbarorte);

        final Node[] nachbarorte2 = g.getNeighbors(4);
        final Node[] erwarteteNachbarorte2 = {g.getNodes()[0]};
        assertNotNull(nachbarorte2);
        assertNotNull(erwarteteNachbarorte2);
        assertEquals(erwarteteNachbarorte2, nachbarorte2);

        final Node[] nachbarorte3 = g.getNeighbors(0);
        final Node[] erwarteteNachbarorte3 = {g.getNodes()[4]};
        assertNotNull(nachbarorte3);
        assertNotNull(erwarteteNachbarorte3);
        assertEquals(erwarteteNachbarorte3, nachbarorte3);

        // distanz zwischen zwei orten
        final double distanz = Graph.getDistance(g.getNodes()[0], g.getNodes()[1]);
        assertEquals(703.4912934784624, distanz, 0.00000000001);
    }
}
