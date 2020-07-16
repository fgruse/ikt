package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import de.htwberlin.fiw.profiler.Profiler;

/**
 * Diese Klasse stellt den hauptsächlichen Eingangspunkt für das Programm dar und implementiert den Profiler.
 */
public class Main extends ProfiledClass {

    /**
     * Main-Methode, ruft Profiler auf und gibt Ergebnisse in der Konsole aus
     * @param args ungenutzt
     */
    public static void main(final String[] args) {
        final Profiler profiler = new Profiler(Main.class);
        profiler.start();
        profiler.printResults();
    }

    /**
     * Implementiert run-Methode des Profilers, erzeugt Graph aus Eingabedatei, berechnet kürzesten Weg zwischen zwei Knoten
     * und gibt diesen und dessen Länge in der Konsole aus, falls er gefunden wurde
     */
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





