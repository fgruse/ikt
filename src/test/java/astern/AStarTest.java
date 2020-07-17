package astern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    }

    @Test
    public void testCantFindPath() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo-less-edges.csv");
        AStar aStar = new AStar(graph);
        final Path path = aStar.computeShortestPath(0, 3611);
        assertNull(path);
    }

    @Test
    public void testComputeShortestPathAnotherGraph() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo-less-edges.csv");
        AStar aStar = new AStar(graph);

        final Path path = aStar.computeShortestPath(0, 1);
        final String expected = "[0, 2830, 21, 7855, 77, 7617, 96, 49, 6948, 40, 6759, 1]";
        assertEquals(expected, path.toString());
        assertEquals(85576, path.getLength(), 0.99999999999);

        final Path path2 = aStar.computeShortestPath(0, 9999);
        final String expected2 = "[0, 2830, 21, 13, 1745, 4, 7753, 55, 4487, 99, 5821, 93, 4595, 63, 9999]";
        assertEquals(expected2, path2.toString());
        assertEquals(111555, path2.getLength(), 0.99999999999);

        final Path path3 = aStar.computeShortestPath(2000, 3000);
        final String expected3 = "[2000, 32, 5467, 25, 4736, 66, 7255, 54, 1010, 47, 2149, 38, 3000]";
        assertEquals(expected3, path3.toString());
        assertEquals(90655, path3.getLength(), 0.99999999999);

        final Path path4 = aStar.computeShortestPath(4461, 9211);
        final String expected4 = "[4461, 17, 54, 3791, 95, 97, 9211]";
        assertEquals(expected4, path4.toString());
        assertEquals(51586, path4.getLength(), 0.99999999999);
    }
}
