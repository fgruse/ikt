package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AStarTest {

    @Test
    public void testComputeShortestPath() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo.csv");
        AStar aStar = new AStar(graph);
        
        final Path path = aStar.computeShortestPath(0, 1);
        final String expected = "[0, 4404, 8365, 1847, 9602, 2089, 8179, " +
                "6431, 8810, 6289, 1176, 1]";
        assertEquals(expected, path.toString());
        assertEquals(84681, path.getLength(), 0.99999999999);

        final Path path2 = aStar.computeShortestPath(0, 9999);
        final String expected2 = "[0, 5960, 6126, 7464, 7300, 5089, 8024, 9107," +
                " 8968, 3302, 8124, 5214, 9999]";
        assertEquals(expected2, path2.toString());
        assertEquals(94180, path2.getLength(), 0.99999999999);

        final Path path3 = aStar.computeShortestPath(2000, 3000);
        final String expected3 = "[2000, 3237, 9051, 2443, 9281, 4593, 8110, " +
                "7902, 5944, 3482, 3000]";
        assertEquals(expected3, path3.toString());
        assertEquals(72351, path3.getLength(), 0.99999999999);

        final Path path4 = aStar.computeShortestPath(3361, 9261);
        final String expected4 = "[3361, 380, 5767, 4120, 8134, 3836, 3000, 3482, " +
                "8039, 9083, 8901, 4400, 3204, 1088, 4848, 6146, 832, 1489, 4203, 9790, 9261]";
        assertEquals(expected4, path4.toString());
        assertEquals(139166, path4.getLength(), 0.99999999999);



        /*
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
        final double distanz = UndirectedGraph.getDistance(g.getNodes()[0], g.getNodes()[1]);
        assertEquals(703.4912934784624, distanz, 0.00000000001);
        */
    }

    @Test
    public void testCantFindPath() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo-klein.csv");
        AStar aStar = new AStar(graph);

        final Path path = aStar.computeShortestPath(0, 1);
        assertEquals("[]", path.toString());
        assertEquals(0, path.getLength(), 0.0);
    }
}
