package ue2;

import de.htwberlin.fiw.profiler.Profiler;
import ue1.ProfilerEinfachVerketteteListe;

public class Main {

    public static void main(String[] args) {
        // einfach verkettete
        Profiler profilerEinfach = new Profiler(ProfilerEinfachVerketteteListe.class);
        profilerEinfach.start();
        // doppelt verkettete
        Profiler profilerDoppelt = new Profiler(ProfilerDoppeltVerketteteListe.class);
        profilerDoppelt.start();
        // set
        Profiler profilerSet = new Profiler(ProfilerSet.class);
        profilerSet.start();
        // optimiertes set
        Profiler profilerOptimiertesSet = new Profiler(ProfilerOptimiertesSet.class);
        profilerOptimiertesSet.start();

        //profilerEinfach.printResults();
        //profilerDoppelt.printResults();
        //profilerSet.printResults();
        profilerOptimiertesSet.printResults();
    }
}