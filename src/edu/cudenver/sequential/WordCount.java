package edu.cudenver.sequential;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Counts the word in a single file.
 */
public class WordCount {
    private final String filename;
    private final Map<String,Integer> wordCount;

    /**
     * Initializes the map to store the word count.
     */
    public WordCount(String filename){
        this.filename = filename;
        this.wordCount = new HashMap<>(10_000);
    }

    /**
     * Given a file, opens the file, and count all the words in that file line by line
     */
    public void processFile (){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.filename));
            String line = reader.readLine();
            while (line != null) {
                this.countWords(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a line counts the words and update the map
     * @param line
     */
    private void countWords(String line){
        line = line.replaceAll("[^A-Za-z0-9 ]+", "");

        String[] tokens = line.split(" ");

        for (String token : tokens) {
            String word = token.toLowerCase();
            if (word.trim() == "")
                continue;
            if (this.wordCount.containsKey(word)) {
                int count = this.wordCount.get(word);
                this.wordCount.put(word, count + 1);
            }
            else {
                this.wordCount.put(word, 1);
            }
        }
    }


    public Map<String,Integer> getWordCount(){
        return this.wordCount;
    }

}
