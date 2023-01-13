package test;
import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// Class BloomFilter is used to test whether a word is definitely not in the dictionary or maybe in the dictionary
public class BloomFilter {
	int size; // Bit array size
    private final BitSet bitset; // An array of bits
    MessageDigest md; // Hash functions
    List<MessageDigest> digestList= new ArrayList<>(); // List of MessageDigest

    // Constructor
    public BloomFilter(int Size, String ... hashFunctions) {
        this.size = Size;
        this.bitset = new BitSet();
        for(int i=0;i<hashFunctions.length;i++) {
            try {
                // Create a new MessageDigest instance
                this.md = MessageDigest.getInstance(hashFunctions[i]); 
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("no such hash function hashFunctions[" + i + "]");
            }
            this.digestList.add(md);
        }
    }

    // Method to add a word to the bloom filter
    void add(String word) {
        // A loop to activate hash functions on the given word
        for (MessageDigest md : digestList) { 
            // Calculate the hash function and returns bytes array
            byte[] hash = md.digest(word.getBytes()); 
            // Creates a new BigInteger from bytes array
            BigInteger bigInt = new BigInteger(1, hash);
            // Returns an absolute int
            int index = Math.abs(bigInt.abs().intValue()) % size; 
            bitset.set(index, true); // Turns on the bit in the field bits
        }
    }

    // Method to check if a word is in the bloom filter
    boolean contains(String word) {
        // A loop to activate hash functions on the given word
        for (MessageDigest md : digestList) { 
            // Calculate the hash function and returns bytes array
            byte[] hash = md.digest(word.getBytes());
            // Creates a new BigInteger from bytes array
            BigInteger bigInt = new BigInteger(1, hash); 
            // Returns an absolute int
            int index = Math.abs(bigInt.abs().intValue()) % size; 
            // If the bit is not set, return false
            if(!bitset.get(index)) 
                return false;
        }
        return true;
    }

    // Override the toString() method to return a string representation of the bit array,
    // with each set bit represented by a '1' and each unset bit represented by a '0'
    @Override
    public String toString() {
        // Create a StringBuilder object
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitset.length(); i++)
            builder.append(bitset.get(i) ? "1" : "0");
        return builder.toString();
    }
}