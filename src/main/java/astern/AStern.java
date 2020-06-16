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
        KnotenListe queue = new KnotenListe();
        final Knoten start = g.getOrte()[startIndex];
        final Knoten ziel = g.getOrte()[zielIndex];
        start.setKostenStartBisZuMir(0.0);
        start.setKostenBisZielGeschaetzt(0.0);
        start.setParent(start);

        queue.append(start);
        KnotenListe weg = new KnotenListe();

        while(queue.size()>0) {
            // suche nach knoten mit höchster priority --> geringste gesamtkosten
            int index = 0;
            Knoten minimumKostenInQueue = queue.get(index);
            if(queue.size()>1) {
                for(int i=1; i<queue.size(); i++) {
                    Knoten k = queue.get(i);
                    if(k.getGesamtkosten() < minimumKostenInQueue.getGesamtkosten()) {
                        minimumKostenInQueue = k;
                        index = i;
                    }
                }
            }

            // knoten mit priority gefunden, wird aus queue entfernt & bearbeitet
            queue.remove(index);
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
                Knoten[] nachbarn = g.getNachbarorte(minimumKostenInQueue.getIndex());
                for(Knoten k: nachbarn) {
                    if(k.isNotAbgearbeitet()) {
                        double predictionBisZiel = Graph.getDistanz(k, ziel);
                        double distanzNachbarParent = Graph.getDistanz(k, minimumKostenInQueue);
                        double startBisK = minimumKostenInQueue.getKostenStartBisZuMir() + distanzNachbarParent;
                        if((startBisK + predictionBisZiel) < k.getGesamtkosten()) {
                            k.setParent(minimumKostenInQueue);
                            k.setKostenStartBisZuMir(startBisK);
                            k.setKostenBisZielGeschaetzt(predictionBisZiel);
                            queue.append(k);
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
