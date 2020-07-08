package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;

public class Main extends ProfiledClass {
    public static void main(final String[] args) {
        final Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }

    @Override
    public void run() {
        final String FILE_NAME = "src/main/java/astern/ortschaften-demo.csv";
        final int startNodeIndex = 0;
        final int endNodeIndex = 1;

        final UndirectedGraph graph = UndirectedGraph.fromFile(FILE_NAME);
        final AStar aStar = new AStar(graph);
        final Path shortestPath = aStar.computeShortestPath(startNodeIndex, endNodeIndex);

        if(shortestPath != null) {
            System.out.println("Nodes in Path: " + shortestPath.toString());
            System.out.println("Length: " + shortestPath.getLength());
            System.out.println();
        }
        else {
            System.out.println("Couldn't find any path!");
            System.out.println();
        }
    }
}
