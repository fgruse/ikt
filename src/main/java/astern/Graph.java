package astern;

import java.util.Arrays;
import ue1.FeldListe;

public class Graph {

    private final int ortanzahl;
    private final boolean[][] adjazenzmatrix;
    private final Knoten[] orte;

    public Graph(final int ortanzahl) {
        this.ortanzahl = ortanzahl;
        this.adjazenzmatrix = new boolean[ortanzahl][ortanzahl];
        for (boolean[] row: this.adjazenzmatrix) {
            Arrays.fill(row, false);
        }
        this.orte = new Knoten[ortanzahl];
        Arrays.fill(this.orte, null);
    }

    /**
     * fügt einen neuen Ort hinzu, es passiert nicht, wenn am index schon ein Ort existiert
     * @param index Index vom  Ort
     * @param x x-Koordinate des Orts
     * @param y y-Koordinate des Orts
     * @return true, wenn Ort eingefügt wurde, false wenn nicht
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public boolean insertOrt(final int index, final int x, final int y) throws IndexOutOfBoundsException {
        if(index >= ortanzahl) {
            throw new IndexOutOfBoundsException("Index out of bounds. Erstelle einen neuen Graphen mit mehr Knoten!");
        }
        if(this.orte[index]==null) {
            Knoten k = new Knoten(this, index, x, y);
            this.orte[index] = k;
            return true;
        }
        return false;
    }

    /**
     * fügt eine neue Strasse zwischen zwei Orten ein
     * @param indexOrt1 Index vom ersten Ort
     * @param indexOrt2 Index vom zweiten Ort
     * @return true, wenn Strasse eingefügt wurde, false wenn nicht
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public boolean insertStrasse(final int indexOrt1, final int indexOrt2) throws IndexOutOfBoundsException {
        if(indexOrt1 >= ortanzahl || indexOrt2 >= ortanzahl) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds. Erstelle einen neuen Graphen mit mehr Knoten! Knoten gibt es nicht!"
            );
        }
        if(this.orte[indexOrt1]!=null && this.orte[indexOrt2]!=null) {
            this.adjazenzmatrix[indexOrt1][indexOrt2] = true;
            this.adjazenzmatrix[indexOrt2][indexOrt1] = true;
            return true;
        }
        return false;
    }

    /**
     * gibt alle Nachbarorte eines Ortes zurück
     * @param indexOrt Index vom  Ort
     * @return Array der Nachbarorte
     * @throws IndexOutOfBoundsException wenn Index vom Ort zu groß
     */
    public Knoten[] getNachbarorte(int indexOrt) throws IndexOutOfBoundsException {
        if(indexOrt >= ortanzahl) {
            throw new IndexOutOfBoundsException("Index out of bounds. Knoten gibt es nicht!");
        }
        FeldListe indizes = new FeldListe();
        for (int i=0; i<this.adjazenzmatrix[indexOrt].length; i++) {
            if(this.adjazenzmatrix[indexOrt][i]) {
                indizes.append(i);
            }
        }
        Knoten[] nachbarn = new Knoten[indizes.size()];
        for(int i=0; i<indizes.size(); i++) {
            nachbarn[i] = this.orte[indizes.get(i)];
        }
        return nachbarn;
    }

    // euklidisch
    static public double getDistanz(final Knoten knoten, final Knoten knoten2) {
        final int x1 = knoten.getX();
        final int x2 = knoten2.getX();
        final int y1 = knoten.getY();
        final int y2 = knoten2.getY();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public boolean[][] getAdjazenzmatrix() {
        return adjazenzmatrix;
    }

    public Knoten[] getOrte() {
        return orte;
    }
}
