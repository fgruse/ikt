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

    public KnotenListe computeShortestPath() {
        Knoten[] alleOrte = g.getOrte();
        KnotenQueue queue = new KnotenQueue();
        final Knoten start = alleOrte[startIndex];
        final Knoten ziel = alleOrte[zielIndex];
        start.setKostenStartBisZuMir(0.0);
        start.setKostenBisZielGeschaetzt(0.0);
        start.setParent(start);

        queue.insert(start);
        KnotenListe weg = new KnotenListe();

        while(queue.size()>0) {
            // knoten mit höchster priorität = geringste gesamtkosten wird aus queue entfernt
            Knoten minimumKostenInQueue = queue.remove();
            // final Knoten currentKnoten = alle.get(minimumKostenInQueue.getIndex());
            if(minimumKostenInQueue.isNotAbgearbeitet()) {
                minimumKostenInQueue.setAbgearbeitet(true);

                // ziel erreicht, weg abgeschlossen
                if(minimumKostenInQueue.equals(ziel)) {
                    while(!minimumKostenInQueue.getParent().equals(minimumKostenInQueue)){
                        weg.prepend(minimumKostenInQueue);
                        minimumKostenInQueue = minimumKostenInQueue.getParent();
                    }
                    weg.prepend(start);
                    break;
                }

                // nachbarn zur queue hinzufügen
                final boolean[] istNachbar = g.getAdjazenzmatrix()[minimumKostenInQueue.getIndex()];
                for (int i=0; i<istNachbar.length; i++) {
                    if(istNachbar[i]) {
                        Knoten k = alleOrte[i];
                        if(k.isNotAbgearbeitet()) {
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
        KnotenListe weg = this.computeShortestPath();
        int knotenInWeg = weg.size();
        if(knotenInWeg>0) {
            StringBuilder wegString = new StringBuilder();
            for (int i = 0; i< knotenInWeg; i++) {
                wegString.append(weg.get(i).getIndex()).append(" ");
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
