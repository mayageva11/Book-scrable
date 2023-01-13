package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Class used to search a word in the given files by reading it
public class IOSearcher {
    static boolean search(String word, String...fileName) throws IOException {
      List<String> fileNames = new ArrayList<>();
      Collections.addAll(fileNames, fileName);

        // For each file name in the list
        for (String file : fileNames) {
            // Read the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line= br.readLine();
            // For each line in the file
            while (line!= null) {
                // Check if the line contains the word
                if (line.contains(word)) {
                    br.close();
                    return true;
                }
                // Read the next line
                line= br.readLine();
            }
            br.close();
        }
        // If the word is not found in any of the files
        return false;
        
    }
}
