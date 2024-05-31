package compress;

import java.util.*;

public class LZWCompression {
    // Define the dictionary globally
    private static Map<String, Integer> dictionary = new HashMap<>();
    private static Map<Integer, String> reverseDictionary = new HashMap<>();
    private static int dictSize = 0;

    // Initialize dictionary with unique characters from the input
    private static void initializeDictionary(String input) {
        dictionary.clear();
        reverseDictionary.clear();
        dictSize = 0;
        for (char c : input.toCharArray()) {
            if (!dictionary.containsKey("" + c)) {
                dictionary.put("" + c, dictSize);
                System.out.println(c + " is added to dictionary with key " + dictSize);
                reverseDictionary.put(dictSize, "" + c);
                dictSize++;
            }
        }
    }

    // Compress a string to a list of output symbols.
    public static List<Integer> compress(String input) {
        // Initialize the dictionary
        initializeDictionary(input);

        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char c : input.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize);
                System.out.println(wc + " is added to dictionary with key " + dictSize);
                reverseDictionary.put(dictSize, wc);
                dictSize++;
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }

    // Decompress a list of output ks to a string.
    public static String decompress(List<Integer> compressed) {
        if (compressed == null || compressed.isEmpty()) {
            return "";
        }

        String w = reverseDictionary.get(compressed.remove(0));
        StringBuilder result = new StringBuilder(w);
        for (int k : compressed) {
            String entry;
            if (reverseDictionary.containsKey(k))
                entry = reverseDictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            // Add w+entry[0] to the dictionary.
            reverseDictionary.put(dictSize, w + entry.charAt(0));
            dictionary.put(w + entry.charAt(0), dictSize);
            dictSize++;

            w = entry;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        
    	String input = "aaaaaaaaaaaaaabbbbbbececececececdddddddddecbaaaaaaaaaa";
        
        List<Integer> compressed = compress(input);
        System.out.println("Input: \t\t" + input);
        System.out.println("Compressed: \t" + compressed);

        String decompressed = decompress(new ArrayList<>(compressed)); // Use a copy for decompression
        System.out.println("Decompressed: \t" + decompressed);
    }
}
