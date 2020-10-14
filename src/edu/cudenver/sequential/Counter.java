package edu.cudenver.sequential;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Counter {
    private final String filePath;
    private final Map<String, Integer> totalWordCount;

    public Counter(String filePath){
        this.filePath = filePath;
        this.totalWordCount = new HashMap<>(10_000);
    }

    /**
     * Process all files in the filepath.
     */
    public void processFiles() {
        File f = new File(this.filePath);
        String[] filenames = f.list(); // Populates the array with names of files and directories

        if (filenames != null) {  //successfully retrieved filenames
            for (String filename : filenames) {
                System.out.print(".");
                WordCount wordCount = new WordCount(this.filePath + "/" + filename);
                wordCount.processFile();
                updateMap(this.totalWordCount, wordCount.getWordCount());
            }
        }
        else{
            System.out.printf("Invalid File Path. %n");
        }
    }

    /**
     * Returns the total Word Count map
     * @return returns the total word count map
     */
    public Map<String, Integer> getTotalWordCount() {
        return totalWordCount;
    }


    /**
     * Appends the counts from Map 2 into Map 1.
     * @param map1 first main map. This map object will be updated with map2
     * @param map2 map to add to map1
     */
    private void updateMap(Map<String, Integer> map1,Map<String, Integer> map2 ){
        for (Map.Entry<String,Integer> entry : map2.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                int count = map1.get(entry.getKey());
                map1.put(entry.getKey(), count + entry.getValue());
            }
            else {
                map1.put(entry.getKey(), entry.getValue());
            }
        }
    }

}
