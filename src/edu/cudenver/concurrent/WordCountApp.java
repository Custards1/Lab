package edu.cudenver.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class WordCountApp {
    /* Set this variable to the folder containing the data files. Do not use ~/ on mac/lx, as it is not recognized
     * Examples:
     *          /Users/student/Downloads/word-count/1.Individual.Files/
     *          C:\word-count\1.Individual.Files\
     * */

    public static final String PATH_TO_FILES = "/home/blake/Music/words/word-count/1.Individual.Files";

    /**
     * Display information about the word count map.
     * @param wordCounts map to display
     */
    private static void displayMap(Map<String, Integer> wordCounts) {

        // sorting the Map. Refer to CHP about streams and lambdas if you want more info.
        Map<String, Integer> sortedCountsByValue = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        long totalNumberOfWords = 0;
        for (Integer i : wordCounts.values()) {
            totalNumberOfWords += i;
        }

        System.out.printf("%n%n%n%n");
        System.out.printf("Number of Distinct Words: %,10d%n", wordCounts.size());
        System.out.printf("Total Number of Words:    %,10d%n", totalNumberOfWords);
        System.out.printf("%n              Map contains:%n");
        System.out.printf("%2s : %-20s%20s%n","#","Word", "Quantity");
        System.out.printf("============================================================%n");

        int displayCount = 0;
        for (Map.Entry<String,Integer> entry : sortedCountsByValue.entrySet()) {
            if (++displayCount > 20)
                break;

            System.out.printf("%2d : %-20s%,20d%n", displayCount, entry.getKey(), entry.getValue());
        }
    }


    /**
     * Main Sequential Program
     * @param args ignored
     */
    public static void main(String[] args) {
        // Dataset Source: http://ai.stanford.edu/~amaas/data/sentiment/

        Instant start, end;
        Counter counter = new Counter(PATH_TO_FILES);

        start = Instant.now(); //timer
        counter.processFiles();
        end = Instant.now();
        displayMap(counter.getTotalWordCount());

        System.out.printf("%n%nSequential Word Count ran in %s milliseconds %n",
                Duration.between(start, end).toMillis());

    }

}
