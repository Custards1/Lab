# LAB \#3

The lab focuses on the use of multi-threading to improve (maybe) 
the performance of an application that focus on word counting. 

## 1. Data
The data to process contains the imdb movie reviews from users. 
This dataset is extensively used for data science pipelines. 

You were provided with files split into three folders: 

1. `1.Individual.Files`: where each file contains an individual review.
1. `2.Collapsed.Files.Simple`: where reviews were consolidated in three 7 files (a1.txt to a7.txt)
1. `3.Collapsed.Files.Double`: the same reviews but the files were duplicated.

## 2.Implementation
There are two packages, a `sequential` and a `concurrent`. 
The sequential version was implemented for you. 

### 2.1.Sequential Implementation
All the following classes belong to the package `edu.cudenver.sequential`.
#### 2.1.1.WordCountApp Class

- Creates an instance of Counter, setting the path where the files are located.
- Implements a method to display a summary from a word count map (Word -> number of appearances)

#### 2.1.2.Counter Class

- Process all the files in the given path.
- For each file to process, creates a WordCount object to count the words and obtain a MAP <Word -> qty>
- Stores a summarized map with the quantities of all processed files, and updates that map once a new file is processed.

#### 2.1.3.WordCount Class  

- Counts the words for a single file. 
- Stores the counts as an instance MAP

### 2.2.Concurrent Implementation. 
All the following classes belong to the package `edu.cudenver.concurrent`.

#### 2.2.1.WordCountApp Class
This class implementation is identical to the sequential implementation. 

- Creates an instance of Counter, setting the path where the files are located.
- Implements a method to display a summary from a word count map (Word -> number of appearances)

#### 2.2.2.Counter Class

- Process all the files in the given path.
- For each file to process, creates a WordCount thread and starts that thread. 
 - The thread object will count the words and generate a MAP <Word -> qty> for the given file.
- Stores a summarized map with the quantities of all processed files, and updates that map once a new file is processed.
 - Updates should be thread safe!

#### 2.2.3.WordCount Class  

- Implemented as a single thread.
- Counts the words for a single file. 
- Stores the counts as an instance MAP


# 3.Analysis
Once your implementation is complete, run the following experiments to analyze the effect of multithreading programming:

## 3.1.RUNTIME

| Dataset                | Sequential | Concurrent | Concurrent_2 |
|------------------------|------------|------------|--------------|
|1.Individual.Files      | ?          | ?          | ?            |
|2.Collapsed.Files.Simple| ?          | ?          | ?            |
|3.Collapsed.Files.Double| ?          | ?          | ?            |

- **Sequential**: Run the sequential program for each ds.
- **Concurrent**: Run the concurrent program initializing the initial capacity of lists and maps to 10,000
- **Concurrent_2**: Run the concurrent program initializing the initial capacity of lists and maps to 20,000

Remeber that the initial capacity has impact when the list/map needs to grow.

## 3.2.Discussion:
Based on the runtimes you got, what are your team's conclusions?
