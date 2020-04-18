package ue2;

public class doppeltVerketteteListe {

    public class Eintrag {

        @Override
        public String toString() {
            return  " Value: " +  inhalt;
        }

        private final Integer inhalt;
        private Eintrag vorher;
        private Eintrag nachher;

        public Eintrag(int inhalt) {
            this.inhalt=inhalt;
            this.vorher=null;
            this.nachher=null;
        }

        public Eintrag() {
            this.inhalt=null;
            this.vorher=null;
            this.nachher=null;
        }

        public Eintrag getVorher() {
            return vorher;
        }

        public void setVorher(Eintrag vorher) {
            this.vorher = vorher;
        }

        public Eintrag getNachher() {
            return nachher;
        }

        public void setNachher(Eintrag nachher) {
            this.nachher = nachher;
        }

        public int getInhalt() {
            return inhalt;
        }

    }

    private final Eintrag anfang;
    private final Eintrag ende;

    public Eintrag getAnfang() {
        return anfang;
    }

    public Eintrag getEnde() {
        return ende;
    }

    public doppeltVerketteteListe() {
        this.anfang = new Eintrag();
        this.ende = new Eintrag();
    }


    /**
     * Element am Anfang einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void prepend(int value) {

    }

    /**
     * Element am Ende einfügen
     * @param value - Element, welches eingefügt wird
     */
    public void append(int value) {
        Eintrag neu = new Eintrag(value);
        if(anfang.getNachher()==null && ende.getVorher()==null) {
            anfang.setNachher(neu);
            ende.setVorher(neu);
        }
        else {
            Eintrag altesLetztesElement = ende.getVorher();
            ende.setVorher(neu);
            neu.setVorher(altesLetztesElement);
            altesLetztesElement.setNachher(neu);
        }
    }

    /**
     * Element an einer angegebenen Position einfügen
     * @param value - Element, welches eingefügt wird
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException if the index is out of range (<0 or >size())
     */
    public void insert(int index, int value) throws IndexOutOfBoundsException {
        int size = this.size();
        if(index>size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            if(size==0) {
                this.append(value);
            }
            else {
                Eintrag neu = new Eintrag(value);
                Eintrag pointer = anfang.getNachher();

                for(int i=0; i<index; i++) {
                    pointer = pointer.getNachher();
                }

                if(index==0) {
                    this.getAnfang().setNachher(neu);
                }
                else {
                    Eintrag previous = pointer.getVorher();
                    neu.setVorher(previous);
                    previous.setNachher(neu);
                }

                neu.setNachher(pointer);
                pointer.setVorher(neu);

            }
        }
    }

    /**
     * Element an einer angegebenen Position abrufen
     * @param index - angegebene Position
     * @return value - abgerufenes Element
     * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
     */
    public int get(int index) throws IndexOutOfBoundsException {
        int size = this.size();
        int value;
        if(index>=size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            Eintrag pointer = anfang.getNachher();
            for(int i=0; i<index; i++) {
                pointer = pointer.getNachher();
            }
            value = pointer.getInhalt();
        }
        return value;
    }

    /**
     * Element an einer angegebenen Position löschen
     * @param index - angegebene Position
     * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        int size = this.size();
        if(index>=size || index<0) {
            throw new IndexOutOfBoundsException("Die Liste enthält kein Element an Index " + index + ".");
        }
        else {
            Eintrag pointer = anfang.getNachher();
            for(int i=0; i<index; i++) {
                pointer = pointer.getNachher();
            }

            Eintrag vorgaengerElement;
            Eintrag nachfolgerElement;

            if(index==(size-1)) {
                vorgaengerElement = pointer.getVorher();
                this.getEnde().setVorher(vorgaengerElement);
                vorgaengerElement.setNachher(null);
            }
            else if(index==0) {
                nachfolgerElement = pointer.getNachher();
                this.getAnfang().setNachher(nachfolgerElement);
                nachfolgerElement.setVorher(null);
            }
            else {
                vorgaengerElement = pointer.getVorher();
                nachfolgerElement = pointer.getNachher();
                vorgaengerElement.setNachher(nachfolgerElement);
                nachfolgerElement.setVorher(vorgaengerElement);
            }

            pointer.setVorher(null);
            pointer.setNachher(null);
        }

    }

    /**
     * prüft, ob das übergebene Element in der Liste enthalten ist
     * @param value - Element, welches übergeben wird
     * @throws IndexOutOfBoundsException if the index is out of range (<0 or >=size())
     */
    public boolean contains(int value) throws IndexOutOfBoundsException {
        int size = this.size();
        boolean contains = false;
        if(size>0) {
            Eintrag pointer = anfang.getNachher();
            for(int i=0; i<size; i++) {
                if(pointer.getInhalt()==value) {
                    contains = true;
                    break;
                }
                pointer = pointer.getNachher();
            }
        }
        return contains;
    }

    /**
     * ruft die Länge der Liste ab (Hilfsmethode)
     * @return - Länge der Liste
     */
    public int size() {
        Eintrag pointer = anfang.getNachher();
        if(pointer==null) {
            return 0;
        }
        else {
            int counter = 1;
            while(pointer.getNachher()!=null) {
                pointer = pointer.getNachher();
                counter++;
            }
            return counter;
        }
    }

}
