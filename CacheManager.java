package test;

import java.util.HashSet;

public class CacheManager {
    int size; // max size of the cache
    CacheReplacementPolicy crp; // instace of cache replacement policy
    HashSet<String> cache= new HashSet<>(); // the cache

    // constructor
    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
    }

    //method that return if the word is in the cache
    public boolean query(String word) {
        return cache.contains(word);
    }

    //method that add a word to the cache
    public void add(String word) {
        //add the words to the cache and the cache replacement policy
        cache.add(word);
        crp.add(word);
        // if the cache is full, size is limited, remove the least recently used word
        if (cache.size() > size) {
            cache.remove(crp.remove());
        }   
    }
}
