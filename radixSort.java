// imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

// create class
public class RadixSort {

    // main method
    public static void main(String[] args) {

        // display name and email address and message
        System.out.println("Submitted by Aarnav Singh - chhabraa@csp.edu");
        System.out.println("I certify that this is my own work");

        // load the list of words from words.txt
        ArrayList<String> words = loadWordsFromFile("words.txt");

        // display the list of words
        System.out.println("\nOriginal List of Words:");
        displayWords(words);

        // create buckets using a linked list as the collection object
        LinkedList<String>[] buckets = new LinkedList[27]; // 26 for letters + 1 for spaces

        // initialize the buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        // process the words using the radix sorting algorithm
        for (int i = 19; i >= 0; i--) {
            System.out.println("\nIterating on index: " + i);
            distributeWords(words, buckets, i);
            collectWords(words, buckets);
            displayWords(words);
        }

        // display the sorted list of words
        System.out.println("\nDone...");
    }

    // load the words from a file into an ArrayList
    private static ArrayList<String> loadWordsFromFile(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.toLowerCase()); // convert to lowercase
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    // display a list of words
    private static void displayWords(ArrayList<String> words) {
        for (String word : words) {
            System.out.println(word);
        }
    }

    // distribute words into buckets based on the current index
    private static void distributeWords(ArrayList<String> words, LinkedList<String>[] buckets, int index) {
        for (String word : words) {
            int bucketIndex = getBucketIndex(word, index);
            buckets[bucketIndex].add(word);
        }
    }

    // collect words from buckets back into the original list
    private static void collectWords(ArrayList<String> words, LinkedList<String>[] buckets) {
        words.clear();
        for (LinkedList<String> bucket : buckets) {
            words.addAll(bucket);
            bucket.clear(); // clear the bucket for the next iteration
        }
    }

    // get the bucket index for a word based on the current index
    private static int getBucketIndex(String word, int index) {
        if (index < word.length()) {
            char ch = word.charAt(index);
            if (ch == ' ') {
                return 26; // bucket for spaces
            } else {
                return ch - 'a'; // buckets for letters (a: 0, b: 1, ..., z: 25)
            }
        } else {
            return 26; // if the word is shorter than the current index, consider it in the space bucket
        }
    }
}
