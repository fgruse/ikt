package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest {

    @Test
    public void testFirstConstructor() {
        UndirectedGraph graph = new UndirectedGraph(5);
        assertEquals(5, graph.getNodes().length);
        assertNull(graph.getNodes()[0]);
        final boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        assertEquals(5, adjacencyMatrix.length);
        assertEquals(5, adjacencyMatrix[0].length);
        assertFalse(adjacencyMatrix[0][1]);
    }

    @Test
    public void testInsertNodes() {
        UndirectedGraph graph = new UndirectedGraph(5);

        // node is inserted
        assertTrue(graph.insertNode(0, 1000, 400));
        Node node = graph.getNodes()[0];
        assertNotNull(node);
        assertEquals(1000, node.getxCoordinate());
        assertEquals(400, node.getyCoordinate());

        // existing node at index is not overwritten
        assertFalse(graph.insertNode(0, 2000, 300));
        Node node2 = graph.getNodes()[0];
        assertEquals(node, node2);
    }

    @Test
    public void testInsertEdges() {
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.insertNode(0, 1000, 400);

        // edge isn't inserted if one or both nodes don't exist
        assertFalse(graph.insertEdge(0, 4));
        assertFalse(graph.insertEdge(1, 2));

        graph.insertNode(1, 300, 470);
        graph.insertNode(2, 190, 788);
        graph.insertNode(3, 25480, 2569);
        graph.insertNode(4, 12589, 8559);

        // edge is inserted
        assertFalse(graph.getAdjacencyMatrix()[0][4]);
        assertTrue(graph.insertEdge(0, 4));
        assertTrue(graph.getAdjacencyMatrix()[0][4]);
        assertTrue(graph.getAdjacencyMatrix()[4][0]);

    }

    @Test
    public void testSecondConstructor() {
        UndirectedGraph graph = new UndirectedGraph(
                "src/main/java/astern/ortschaften-demo-klein.csv", 5
        );
        final boolean[][] adjacencyMatrix = graph.getAdjacencyMatrix();

        assertEquals(5, graph.getNodes().length);
        assertNotNull(graph.getNodes()[0]);
        assertEquals(5, adjacencyMatrix.length);
        assertEquals(5, adjacencyMatrix[0].length);
        assertTrue(adjacencyMatrix[0][4]);
        assertTrue(adjacencyMatrix[4][0]);
        assertTrue(adjacencyMatrix[2][1]);
        assertTrue(adjacencyMatrix[1][3]);
        assertFalse(adjacencyMatrix[0][1]);
    }

    @Test
    public void testComputeShortestPath() {
        UndirectedGraph graph = new UndirectedGraph(
                "src/main/java/astern/ortschaften-demo.csv", 10000
        );
        final Path path = graph.computeShortestPath(0, 1);
        final String expected = "Nodes in path: [0, 4404, 8365, 1847, 9602, 2089, 8179, " +
                "6431, 8810, 6289, 1176, 1]";
        assertEquals(expected, path.toString());
        assertEquals(84681, path.getPathLength(), 0.99999999999);

        final Path path2 = graph.computeShortestPath(0, 9999);
        final String expected2 = "Nodes in path: [0, 5960, 6126, 7464, 7300, 5089, 8024, 9107," +
                " 8968, 3302, 8124, 5214, 9999]";
        assertEquals(expected2, path2.toString());
        assertEquals(94180, path2.getPathLength(), 0.99999999999);

        final Path path3 = graph.computeShortestPath(2000, 3000);
        final String expected3 = "Nodes in path: [2000, 3237, 9051, 2443, 9281, 4593, 8110, " +
                "7902, 5944, 3482, 3000]";
        assertEquals(expected3, path3.toString());
        assertEquals(72351, path3.getPathLength(), 0.99999999999);

        final Path path4 = graph.computeShortestPath(3361, 9261);
        final String expected4 = "Nodes in path: [3361, 380, 5767, 4120, 8134, 3836, 3000, 3482, " +
                "8039, 9083, 8901, 4400, 3204, 1088, 4848, 6146, 832, 1489, 4203, 9790, 9261]";
        assertEquals(expected4, path4.toString());
        assertEquals(139166, path4.getPathLength(), 0.99999999999);



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
       UndirectedGraph graph = new UndirectedGraph(
               "src/main/java/astern/ortschaften-demo-klein.csv", 5
       );
       final Path path = graph.computeShortestPath(0, 1);
       assertEquals("Nodes in path: []", path.toString());
       assertEquals(0, path.getPathLength(), 0.0);
   }
}
