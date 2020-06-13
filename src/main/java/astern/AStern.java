package astern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AStern {

    public ArrayList<Knoten> astern(final String file, final int startIndex, final int zielIndex) {

        Graph g = readFile(file);
        ArrayList<Knoten> queue = new ArrayList<>();
        ArrayList<Knoten> abgearbeitet = new ArrayList<>();
        final Knoten start = g.getOrte()[startIndex];
        final Knoten ziel = g.getOrte()[zielIndex];
        start.setParent(start);

        queue.add(start);
        ArrayList<Knoten> weg = new ArrayList<>();
        while(!queue.isEmpty()) {
            int index = 0;
            Knoten min = queue.get(index);
            if(queue.size()>1) {
                for(int i=1; i<queue.size(); i++) {
                    Knoten k = queue.get(i);
                    double kostenWert = k.getKostenStartBisZuMir() + k.getKostenBisZielGeschaetzt();
                    if(kostenWert < (min.getKostenStartBisZuMir() + min.getKostenBisZielGeschaetzt())) {
                        min = k;
                        index = i;
                    }
                }
            }

            queue.remove(index);
            abgearbeitet.add(min);
            if(min.equals(ziel)) {
                while(!min.getParent().equals(min)){
                    weg.add(min);
                    min = min.getParent();
                }
                weg.add(start);
                break;
            }
            Knoten[] nachbarn = g.getNachbarorte(min.getIndex());
            for(Knoten k: nachbarn) {
                if(!queue.contains(k) && !abgearbeitet.contains(k)) {
                    // länge vom weg zwischen elternknoten und start + länge weg k bis elternknoten
                    k.setParent(min);
                    k.setKostenStartBisZuMir(k.getParent().getKostenStartBisZuMir() + Graph.getDistanz(k, k.getParent()));
                    // geschätzte länge vom weg von k bis ziel
                    k.setKostenBisZielGeschaetzt(Graph.getDistanz(k, ziel));
                    queue.add(k);
                }
            }
        }
        return weg;
    }

    static public Graph readFile(final String file) {
        Graph graph = new Graph(10000);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
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

    public double getWeglaenge(final Knoten[] weg) {
        // TODO
        return 1F;
    }

}
