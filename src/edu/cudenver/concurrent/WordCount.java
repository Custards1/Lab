package edu.cudenver.concurrent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

//TODO: Make this a thread.
public class WordCount implements Runnable{
    private final String filename;
    private final Map<String,Integer> wordCount;

    /**
     * Initializes the map to store the word count.
     */
    public WordCount(String filename){
        this.filename = filename;
        this.wordCount = new HashMap<>();

    }

    /**
     * Given a line counts the words and update the map
     * @param line line to inspect
     */
    private void countWords(String line){
       if(line == null || line.isEmpty()) {
        return;
       }
       String[] words = line.split("\\s+");
        for(int i=0; i < words.length; i++) {
            if(this.wordCount.containsKey(words[i])){
                this.wordCount.put(words[i],this.wordCount.get(words[i])+1);
            }
            else {
                this.wordCount.put(words[i],1);
            }
        }

    }


    public Map<String,Integer> getWordCount(){
        return this.wordCount;
    }

    public void readFile() throws IOException {
            Stream<String> stream =Files.lines(Paths.get(this.filename));
            Consumer<String> counter = s -> {
               this.countWords(s);
            };
            stream.forEach(counter);
    }
    @Override
    public void run() {
        try {
            this.readFile();
        }
        catch (IOException e ) {
            //has to return void,
            //wait can it throw an execption too?
            System.out.println(String.format("Failed to read %s ",this.filename));
        }
    }
    /*
    * TODO: Implement the methods to execute in the thread.
    *  In summary: open the file, read the lines, and for each line count the words.
    *
    * */


}
