package astern;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AStern extends ProfiledClass {

    private final Graph g;
    private final int startIndex;
    private final int zielIndex;

    public AStern(final String file, final Integer startIndex, final Integer zielIndex) {
        this.g = readFile(file);
        this.startIndex = startIndex;
        this.zielIndex = zielIndex;
    }

    public Knoten[] computeShortestPath() {
        Knoten[] alleOrte = g.getOrte();
        final Knoten start = alleOrte[startIndex];
        final Knoten ziel = alleOrte[zielIndex];
        start.setKostenStartBisZuMir(0.0);
        start.setKostenBisZielGeschaetzt(0.0);
        start.setParent(start);
        KnotenQueue queue = new KnotenQueue();
        queue.insert(start);

        while(queue.size()>0) {
            Knoten minimumKostenInQueue = queue.remove();
            if(!minimumKostenInQueue.isAbgearbeitet()) {
                minimumKostenInQueue.setAbgearbeitet(true);

                if(minimumKostenInQueue.equals(ziel)) {
                    return getPath(start, ziel);
                }

                final boolean[] istNachbar = g.getAdjazenzmatrix()[minimumKostenInQueue.getIndex()];
                for (int i=0; i<istNachbar.length; i++) {
                    if(istNachbar[i]) {
                        Knoten k = alleOrte[i];
                        if(!k.isAbgearbeitet()) {
                            double predictionBisZiel = Graph.getDistanz(k, ziel);
                            double distanzNachbarParent = Graph.getDistanz(k, minimumKostenInQueue);
                            double startBisK = minimumKostenInQueue.getKostenStartBisZuMir() + distanzNachbarParent;
                            double gesamtkosten = startBisK + predictionBisZiel;
                            if(gesamtkosten < k.getGesamtkosten()) {
                                k.setParent(minimumKostenInQueue);
                                k.setKostenStartBisZuMir(startBisK);
                                k.setKostenBisZielGeschaetzt(predictionBisZiel);
                                queue.insert(k);
                            }
                        }
                    }
                }
            }
        }
        return new Knoten[0];
    }

    static public Knoten[] getPath(Knoten start, Knoten ziel) {
        int length = ziel.getNumOfSuccessors();
        Knoten[] weg = new Knoten[length];
        int counter = length-1;
        while(!ziel.getParent().equals(ziel)){
            weg[counter] = ziel;
            ziel = ziel.getParent();
            counter--;
        }
        weg[0] = start;
        return weg;
    }

    static public Graph readFile(final String file) {
        Graph graph = new Graph(10000);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            for(String line = br.readLine(); line != null; line = br.readLine()) {
                String[] eintrag = line.split(",");
                if(eintrag[0].equals("O")) {
                    graph.insertOrt(
                            Integer.parseInt(eintrag[1]),
                            Integer.parseInt(eintrag[2]),
                            Integer.parseInt(eintrag[3])
                    );
                }
                else {
                    graph.insertStrasse(
                            Integer.parseInt(eintrag[1]),
                            Integer.parseInt(eintrag[2])
                    );
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    @Override
    public void run() {
        Knoten[] weg = this.computeShortestPath();
        int knotenInWeg = weg.length;
        if(knotenInWeg>0) {
            StringBuilder wegString = new StringBuilder();
            for(final Knoten knoten : weg) {
                wegString.append(knoten.getIndex()).append(" ");
            }
            System.out.println("Weg gefunden der Laenge " + g.getOrte()[zielIndex].getKostenStartBisZuMir());
            System.out.println();
            System.out.println("Weg: ( " + wegString + ")");
        }
        else {
            System.out.println("Leider kein Weg gefunden!");
        }
    }
}
