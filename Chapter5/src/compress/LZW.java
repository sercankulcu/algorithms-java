package compress;

import java.util.*;

public class LZW {
    // Define the dictionary globally for LZW compression and decompression
    private static Map<String, Integer> dictionary = new HashMap<>();
    private static Map<Integer, String> reverseDictionary = new HashMap<>();
    private static int dictSize = 0; // Variable to track the current size of the dictionary

    // Initialize the dictionary with unique characters from the input string
    private static void initializeDictionary(String input) {
        dictionary.clear(); // Clear the dictionary at the start
        reverseDictionary.clear(); // Clear the reverse dictionary
        dictSize = 0; // Reset dictionary size
        // Loop through each character in the input string and add it to the dictionary if not already present
        for (char c : input.toCharArray()) {
            if (!dictionary.containsKey("" + c)) {
                dictionary.put("" + c, dictSize); // Add character as key with its corresponding index as value
                System.out.println(c + " is added to dictionary with key " + dictSize);
                reverseDictionary.put(dictSize, "" + c); // Reverse mapping for decompression
                dictSize++; // Increment dictionary size
            }
        }
    }

    // Compress a string using LZW algorithm and return the list of dictionary indices
    public static List<Integer> compress(String input) {
        // Initialize the dictionary with unique characters from input
        initializeDictionary(input);

        String w = ""; // Initialize a string w to hold the current sequence
        List<Integer> result = new ArrayList<>(); // List to store the indices for the compressed output
        // Loop through each character of the input string
        for (char c : input.toCharArray()) {
            String wc = w + c; // Concatenate w with the current character
            // If wc is in the dictionary, update w to wc
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w)); // Add the index of w to the result list
                // Add wc to the dictionary
                dictionary.put(wc, dictSize);
                System.out.println(wc + " is added to dictionary with key " + dictSize);
                reverseDictionary.put(dictSize, wc); // Update the reverse dictionary
                dictSize++; // Increment dictionary size
                w = "" + c; // Start a new sequence with the current character
            }
        }

        // Add the final value of w to the result list if it's non-empty
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }

    // Decompress a list of output keys to reconstruct the original string
    public static String decompress(List<Integer> compressed) {
        if (compressed == null || compressed.isEmpty()) {
            return ""; // Return an empty string if the compressed list is empty
        }

        String w = reverseDictionary.get(compressed.remove(0)); // Get the first string from the reverse dictionary
        StringBuilder result = new StringBuilder(w); // Initialize result with the first string
        // Iterate through the compressed list of keys
        for (int k : compressed) {
            String entry;
            if (reverseDictionary.containsKey(k)) {
                entry = reverseDictionary.get(k); // Get the string corresponding to the key k
            } else if (k == dictSize) {
                // Special case: if k equals dictSize, the new entry is w + w[0] (the first character of w)
                entry = w + w.charAt(0);
            } else {
                // If k is not a valid key, throw an error
                throw new IllegalArgumentException("Bad compressed k: " + k);
            }

            result.append(entry); // Append the entry to the result

            // Add w + entry[0] (the first character of entry) to the dictionary for future use
            reverseDictionary.put(dictSize, w + entry.charAt(0));
            dictionary.put(w + entry.charAt(0), dictSize); // Update both dictionaries
            dictSize++; // Increment dictionary size

            w = entry; // Update w to the current entry
        }
        return result.toString(); // Return the decompressed string
    }

    public static void main(String[] args) {
        // Input string for compression and decompression
        String input = "aaaaaaaaaaaaaabbbbbbececececececdddddddddecbaaaaaaaaaa";
        
        // Compress the input string using LZW
        List<Integer> compressed = compress(input);
        System.out.println("Input: \t\t" + input);
        System.out.println("Compressed: \t" + compressed);

        // Decompress the list of compressed output symbols using LZW
        String decompressed = decompress(new ArrayList<>(compressed)); // Use a copy of the compressed list for decompression
        System.out.println("Decompressed: \t" + decompressed);
    }
}
