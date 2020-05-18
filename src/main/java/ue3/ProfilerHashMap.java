package ue3;

import de.htwberlin.fiw.profiler.ProfiledClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ProfilerHashMap extends ProfiledClass {

    @Override
    public void run() {
        FIWHashMap map = new FIWHashMap(256);
        URL url = ProfilerHashMap.class.getResource("artikel.csv");
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
    }
}
