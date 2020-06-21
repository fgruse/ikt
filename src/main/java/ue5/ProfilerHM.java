package ue5;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ProfilerHM extends ProfiledClass {

    @Override
    public void run() {
        HashMap<String, String> map = new HashMap<>(2048);
        URL url = ProfilerHM.class.getResource("artikel.csv");
        File csvFile = new File(url.getPath());
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] artikel = line.split(cvsSplitBy);
                if(artikel.length==2) {
                    map.put(artikel[0], artikel[1]);
                }
                else {
                    map.put(artikel[0], "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(map.size());
        System.out.println(map.get("10002165"));
        System.out.println(map.get("10002870"));

        /*
        //Analyse der Verteilung der Einträge auf die Buckets
        ArrayList<Integer> a = map.verteilung();
        int sum = 0;
        for (Integer integer : a) {
            sum = sum + integer;
        }
        int durchschnitt = sum/a.size(); // durschnittliche Anzahl der Einträge pro Bucket
        System.out.println(durchschnitt);
        */
    }
}
