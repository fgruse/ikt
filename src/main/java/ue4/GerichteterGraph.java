package ue4;

import java.util.Arrays;

public class GerichteterGraph {

    private int knotenCounter;
    private int kantenCounter;
    private final int knotenanzahl;
    private Knoten[] knoten;
    private Kante[] kanten;
    private float[][] adjazenzListe; // from, to - parent, child
    private float gesamtgewicht;

    public GerichteterGraph(int knotenanzahl) {
        this.knotenCounter = 0;
        this.kantenCounter = 0;
        this.knotenanzahl = knotenanzahl;
        this.knoten = new Knoten[knotenanzahl];
        Arrays.fill(knoten, null);
        this.kanten = new Kante[knotenanzahl*knotenanzahl];
        Arrays.fill(kanten, null);
        this.adjazenzListe = new float[knotenanzahl][knotenanzahl];
        for (float[] row: adjazenzListe) {
            Arrays.fill(row, Float.MIN_VALUE);
        }
        this.gesamtgewicht = 0;
    }

    /**
     * gibt eine Liste mit allen Knoten im Graphen zurück
     * @return Liste mit allen Knoten im Graphen
     */
    public Knoten[] getNodes() {
        return knoten;
    }

    /**
     * gibt eine Liste mit allen Kanten im Graphen zurück
     * @return Liste mit allen Kanten im Graphen
     */
    public Kante[] getEdges() {
        return kanten;
    }

    /**
     * gibt den Knoten mit dem angegebenen Namen zurück
     * @return Knoten mit dem angegebenen Namen, null wenn nicht existent
     */
    public Knoten getNode(String name) {
        for(final Knoten k : knoten) {
            if(k.getName().equals(name)) {
                return k;
            }
        }
        return null;
    }

    /**
     * fügt einen neuen Knoten in den Graphen ein, falls bereits ein
     * Knoten mit dem Namen vorhanden ist, passiert nichts
     * @param name - Name des Knoten
     */
    public void insertNode(String name) throws IndexOutOfBoundsException {
        if(knotenCounter>=knotenanzahl) {
            throw new IndexOutOfBoundsException("Graph ist schon voll!");
        }
        for(final Knoten k : knoten) {
            if(k!=null) {
                if(k.getName().equals(name)) {
                    return;
                }
            }
        }
        this.knoten[knotenCounter] = new Knoten(this, name);
        knotenCounter++;
    }

    /**
     * erzeugt eine Kante im Graphen, es passiert nichts, wenn die Knoten
     * noch nicht im Graphen vorhanden sind
     * @param from - Name des Ausgangsknoten
     * @param to - Name des Endknoten
     * @param weight - Kantengewicht
     */
    public void makeEdge(String from, String to, float weight) {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i=0; i<knoten.length; i++) {
            if(knoten[i].getName().equals(from)) {
                fromIndex = i;
            }
            if(knoten[i].getName().equals(to)) {
                toIndex = i;
            }
        }
        if(fromIndex>=0 || toIndex>=0) {
            Kante kante = new Kante(this, new Knoten(this, from), new Knoten(this, to), weight);
            this.kanten[kantenCounter] = kante;
            kantenCounter++;
            this.adjazenzListe[fromIndex][toIndex] = weight;
            gesamtgewicht = gesamtgewicht + weight;
        }
    }

    /**
     * gibt das Gesamtgewicht aller Kanten des Graphen zurück
     * @return Gesamtgewicht aller Knaten des Graphen
     */
    public double getWeight() {
        return this.gesamtgewicht;
    }

    public float[][] getAdjazenzListe() {
        return adjazenzListe;
    }
}
