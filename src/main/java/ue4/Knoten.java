package ue4;

import ue4.versuch1.NodeDG;

public class Knoten {

    private GerichteterGraph graph;
    private String name;

    public Knoten(final GerichteterGraph graph, final String name) {
        this.graph = graph;
        this.name = name;
    }

    /**
     * gibt eine Liste mit den Kindknoten des Knoten zurück
     * @return Liste mit den Kindknoten des Knoten
     */
    public Liste<Knoten> getChildren() {
        Knoten[] graphKnoten = this.graph.getNodes();
        Liste<Knoten> children = new Liste<>();
        int index = -1;
        for(int i = 0; i< graphKnoten.length; i++) {
            if(graphKnoten[i].getName().equals(this.name)) {
                index = i;
                break;
            }
        }
        if(index>=0) {
            final float[] neighbors = this.graph.getAdjazenzListe()[index];
            for(int i=0; i<neighbors.length; i++) {
                if(neighbors[i]!=Float.MIN_VALUE) {
                    children.prepend(graphKnoten[i]);
                }
            }
        }
        return children;
    }

    /**
     * gibt den Ausgangsgrad des Knoten zurück (Anzahl ausgehender Kanten)
     * @return Ausgangsgrad
     */
    public int getOutputDegree() {
        return this.getChildren().getSize();
    }

    /**
     * gibt den Eingangsgrad des Knoten zurück (Anzahl eingehender Kanten)
     * @return Eingangsgrad
     */
    public int getInputDegree() {
        Knoten[] graphKnoten = this.graph.getNodes();
        Liste<Knoten> parents = new Liste<>();
        int index = -1;
        for(int i = 0; i< graphKnoten.length; i++) {
            if(graphKnoten[i].getName().equals(this.name)) {
                index = i;
                break;
            }
        }
        if(index>=0) {
            float[] column = new float[this.graph.getAdjazenzListe()[0].length]; // Here I assume a rectangular 2D array!
            for(int i=0; i<column.length; i++){
                column[i] = this.graph.getAdjazenzListe()[i][index];
            }
            for(int i=0; i<column.length; i++) {
                if(column[i]!=Float.MIN_VALUE) {
                    parents.prepend(graphKnoten[i]);
                }
            }
        }
        return parents.getSize();
    }

    /**
     * gibt den Grad des Knoten zurück (Anzahl ein- und ausgehender Kanten)
     * @return Grad
     */
    public int getDegree() {
        return this.getInputDegree()+this.getOutputDegree();
    }

    /**
     * gibt zurück, ob der Knoten ein Blatt ist (keine ausgehenden Kanten)
     * @return true wenn Blatt, false wenn nicht
     */
    public boolean isLeaf(){
        return getOutputDegree()==0;
    }

    /**
     * gibt zurück, ob der Knoten die Wurzel ist (keine eingehenden Kanten)
     * @return true wenn Wurzelb, false wenn nicht
     */
    public boolean isRoot() {
        return getInputDegree()==0;
    }

    /**
     * gibt einen String zurück, welcher als Identifikator für den Knoten verwendet wird
     * @return Name des Knoten
     */
    public String getName() {
        return this.name;
    }
}
