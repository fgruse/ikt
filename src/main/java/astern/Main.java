package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;

public class Main extends ProfiledClass {
    public static void main(String[] args) {
        Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }


    // TODO - in der main lassen oder eigene klasse für profiler?
    @Override
    public void run() {
        UndirectedGraph graph = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo-less-edges.csv");
        AStar aStar = new AStar(graph);
        Path shortestPath = aStar.computeShortestPath(0, 3611);
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
