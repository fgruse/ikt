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
        assertEquals(5, g.getOrte().length);
        assertNull(g.getOrte()[0]);
        final boolean[][] adjazenzmatrix = g.getAdjazenzmatrix();
        assertEquals(5, adjazenzmatrix.length);
        assertEquals(5, adjazenzmatrix[0].length);
        assertFalse(adjazenzmatrix[0][0]);

        // knoten wird eingefügt
        assertTrue(g.insertOrt(0, 1000, 400));
        Knoten knoten = g.getOrte()[0];
        assertNotNull(knoten);
        assertEquals(1000, knoten.getX());
        assertEquals(400, knoten.getY());

        // knoten wird nicht überschrieben
        assertFalse(g.insertOrt(0, 2000, 300));
        Knoten knoten2 = g.getOrte()[0];
        assertEquals(knoten, knoten2);

        // strasse wird nicht eingefügt, weil ort 4 (noch) nicht existiert
        assertFalse(g.insertStrasse(0, 4));

        // mehr knoten einfügen
        assertTrue(g.insertOrt(1, 300, 470));
        assertTrue(g.insertOrt(2, 190, 788));
        assertTrue(g.insertOrt(3, 25480, 2569));
        assertTrue(g.insertOrt(4, 12589, 8559));

        // strasse einfügen
        assertFalse(g.getAdjazenzmatrix()[0][4]);
        assertTrue(g.insertStrasse(0, 4));
        assertTrue(g.getAdjazenzmatrix()[0][4]);

        // mehr strassen einfügen
        assertTrue(g.insertStrasse(2, 1));
        assertTrue(g.insertStrasse(1, 3));
        assertTrue(g.insertStrasse(1, 2));

        // nachbarn von ort finden
        final Knoten[] nachbarorte = g.getNachbarorte(1);
        final Knoten[] erwarteteNachbarorte = {g.getOrte()[2], g.getOrte()[3]};
        assertNotNull(nachbarorte);
        assertNotNull(erwarteteNachbarorte);
        assertEquals(erwarteteNachbarorte, nachbarorte);

        final Knoten[] nachbarorte2 = g.getNachbarorte(4);
        final Knoten[] erwarteteNachbarorte2 = {g.getOrte()[0]};
        assertNotNull(nachbarorte2);
        assertNotNull(erwarteteNachbarorte2);
        assertEquals(erwarteteNachbarorte2, nachbarorte2);

        final Knoten[] nachbarorte3 = g.getNachbarorte(0);
        final Knoten[] erwarteteNachbarorte3 = {g.getOrte()[4]};
        assertNotNull(nachbarorte3);
        assertNotNull(erwarteteNachbarorte3);
        assertEquals(erwarteteNachbarorte3, nachbarorte3);

        // distanz zwischen zwei orten
        final double distanz = Graph.getDistanz(g.getOrte()[0], g.getOrte()[1]);
        assertEquals(703.4912934784624, distanz, 0.00000000001);
    }
}
