package test;

import java.util.LinkedList;

//this class implements the interface CacheReplacementPolicy
public class LRU implements CacheReplacementPolicy {
    //create a linked list of the words
    LinkedList<String> words= new LinkedList<String>();

    //update the list of the words
    @Override
    public void add(String w) {
       if(w!=null){
        words.remove(w);
        words.add(w);
       }
    }
    //return the least resently used word
    @Override
    public String remove(){
        if(words.isEmpty()){
            return null;
        }
        else{
            return words.poll();
        }
    }
}
