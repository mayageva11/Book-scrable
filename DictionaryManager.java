package test;

import java.util.HashMap;



public class DictionaryManager {
    HashMap<String, Dictionary> dictionaryMap= new HashMap<>();
    private static DictionaryManager instance = null;

    //singleton
    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    //method  to activate the query method in the dictionary class 
    public boolean query(String ... books) {
        boolean result = false;
        String w = books[books.length-1];
        //this loop go all over the books
        for(int i=0; i<books.length-1; i++) {
            //if the book is not in the dictionary map
            if(!dictionaryMap.containsKey(books[i])) {
                //add the book to the dictionary map
                dictionaryMap.put(books[i], new Dictionary(books[i]));
            }
            //if the word is in the dictionary and also if the isnt in the dictionary
            if(dictionaryMap.get(books[i]).query(w)) {
                result = true;
            }
        }
        return result;
        
    }

    //method to get the size of the dictionary map
    int getSize() {
        return dictionaryMap.size();
    }

    //this method call the challenge method in the dictionary class
    public boolean challenge(String ... books) {
        boolean result=false;
        String w = books[books.length-1];
        //this loop go all over the books
        for(int i=0; i<books.length-1; i++) {
            //if the book is not in the dictionary map
            if(!dictionaryMap.containsKey(books[i])) {
                //add the book to the dictionary map
                dictionaryMap.put(books[i], new Dictionary(books[i]));
            }
            //if the word is in the dictionary and also if the isnt in the dictionary
            if(dictionaryMap.get(books[i]).challenge(w)) {
                result = true;
            }
        }
        return result;
       
    }
   

   

}
