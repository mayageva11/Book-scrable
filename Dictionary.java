package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class to manage the entire process of searching a word in the files
public class Dictionary {
    //files names
    String[] fileNames;
    //cache managers for existing and fake words 
    CacheManager existingWords, fakeWords; 
    BloomFilter bloomFilterr; // Instance of BloomFilter

    // Ctor
    public Dictionary(String...fileName) {
        // Initializing the file names
        this.fileNames= new String[fileName.length];
        
        // Adding the file names to the list
       System.arraycopy(fileName, 0, this.fileNames, 0, fileName.length);
        // Initializing the cache managers
        this.existingWords = new CacheManager(400, new LRU()); 
        this.fakeWords = new CacheManager(100, new LFU()); 
        // Initializing the BloomFilter
        this.bloomFilterr = new BloomFilter(256, "MD5", "SHA1"); 
        // Adding the words in the files to the BloomFilter
        try {
        for (String file: fileNames) {
            // Reading the file
            BufferedReader r = new BufferedReader(new FileReader(file)); 
            // Reading the first line
            String line = r.readLine();
            // For each line in the file
            String[] words; 
            words = line.split(" "); // Splitting the array to words
            {
                // Adding the words to the BloomFilter
                for (String word : words) 
                    bloomFilterr.add(word);
            }
            // Reading the next line
            r.close();
        }
        }catch (Exception e){ // Catching any exception
            System.out.println("Problem in reading dictionary file");
        }
    }

    // Method to see if a word is in the dictionary by the filters and if needed, 
    //update the CacheManagers accordingly
    Boolean query(String word) {
        // If the word is in the existing words cache manager
        if(existingWords.query(word))
            return true;
        // If the word is in the fake words cache manager
        else if(fakeWords.query(word))
            return false;
        else if(bloomFilterr.contains(word)) {
            existingWords.add(word);
            return true;
        }
        else {
            fakeWords.add(word);
            return false;
        }
    }

    // Method to activate the IO searcher and update the CacheManagers accordingly
    Boolean challenge(String word) {
        try {
            if(IOSearcher.search(word, fileNames)) {
                existingWords.add(word);
                return true;
            }
            else {
                fakeWords.add(word);
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
