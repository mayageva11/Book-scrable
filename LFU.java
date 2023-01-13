package test;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class LFU implements CacheReplacementPolicy {
    
    PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(this::applyAsInt)); // To prioritize the words by the amount of uses
    HashMap<String, Integer> timesCalled = new HashMap<>(); // To count the number of times a word is used

    // Method to get the count for a string
    public int getCount(String s, HashMap<String, Integer> timesCalled) {
        return timesCalled.getOrDefault(s, 0);
    }
    // Method to return the count for a string
    private int applyAsInt(String s) {return getCount(s, timesCalled);}

    // Method to update the count for a string and prioritize it in the queue
    @Override
    public void add(String word) {
        int count = timesCalled.getOrDefault(word, 0) + 1;
        timesCalled.put(word, count);
        if(!queue.isEmpty())
            queue.remove(word); // Remove the string (if needed) from the queue and re-add it to update its position
        queue.add(word);
    }

    // Method to return the least frequently used word
    @Override
    public String remove() {
        if(queue.isEmpty())
            return null;
        else {
            String word = queue.poll();
            timesCalled.remove(word);
            return word;
        }
    }
};


