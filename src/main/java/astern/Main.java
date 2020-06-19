package astern;

import de.htwberlin.fiw.profiler.Profiler;

public class Main {
    public static void main(String[] args) {
        Profiler profiler = new Profiler(UndirectedGraph.class, "src/main/java/astern/ortschaften-demo.csv", 10000);
        profiler.start();
        profiler.printResults();
    }
}
