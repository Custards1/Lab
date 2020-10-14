package edu.cudenver.concurrent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Counter {
    private final String filePath;
    private final Map<String, Integer> totalWordCount;
    private final int poolSize;

    public Counter(String filePath){
        this.filePath = filePath;
        this.totalWordCount = new HashMap<String, Integer>();
        this.poolSize = 13000;
    }

    /**
     * Process all files in the filepath.
     */
    private ArrayList<WordCount> crawlDirectoryAndProcessFiles(File directory, Executor service) {
        ArrayList<WordCount> result = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
               ArrayList<WordCount> temp =  crawlDirectoryAndProcessFiles(file,service);
               for (WordCount words : temp) {
                   result.add(words);
               }
            } else {
                WordCount c = new WordCount(file.getName());
                service.execute(c);
                result.add(c);
            }
        }
        return result;
    }

    public void processFiles() {
        //so wait, are you saying he wants us to limit about of active threads?
        //so where is the thread pool variable coming from?, class method or function param?
        ExecutorService service = Executors.newFixedThreadPool(this.poolSize);
        //literally

        File directory = new File(this.filePath);
        ArrayList<WordCount> threads = new ArrayList<WordCount>();
        ArrayList<WordCount> counts =  this.crawlDirectoryAndProcessFiles(directory,service);
        service.shutdown();
        try {
            service.awaitTermination(5,TimeUnit.MINUTES);
        }
        catch (InterruptedException e){
            System.out.println();
        }
        for(WordCount word : counts) {
            this.updateMap(word.getWordCount(),this.totalWordCount);
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
    private synchronized void updateMap(Map<String, Integer> map1,Map<String, Integer> map2 ){

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
