package ue3;

import de.htwberlin.fiw.profiler.Profiler;

public class Main {

    public static void main(String[] args) {

        Profiler profiler = new Profiler(ProfilerHashMap.class);
        profiler.start();
        profiler.printResults();

        /*
        FIWHashMap hashmap = new FIWHashMap();
        hashmap.put("dozent", "Michael Witt");
        hashmap.put("kanzler", "Claas Cordes");
        hashmap.put("fiw", "Juliane Siegeris");
        System.out.println("Der Dozent ist: " + hashmap.get("dozent"));
        */
    }

}
