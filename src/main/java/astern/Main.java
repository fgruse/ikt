package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;

public class Main extends ProfiledClass {
    public static void main(String[] args) {
        Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }

    @Override
    public void run() {
        UndirectedGraph g = UndirectedGraph.fromFile("src/main/java/astern/ortschaften-demo.csv");
        AStar a = new AStar(g);
        Path shortestPath = a.computeShortestPath(0, 1);
        int numberOfNodesInPath = shortestPath.getNumberOfNodes();
        if(numberOfNodesInPath>0) {
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
