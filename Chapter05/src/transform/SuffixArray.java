package transform;

import java.util.Arrays;

public class SuffixArray {

    /**
     * Function to construct the Suffix Array for a given string.
     * @param text The input string for which the suffix array is to be built.
     * @return The suffix array, where each entry represents the starting index of a suffix in the lexicographically sorted order.
     */
    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        // Step 1: Generate all suffixes of the input string and store them in an array.
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(i, text.substring(i)); // Store the suffix starting at index 'i'.
        }

        // Step 2: Sort the array of suffixes lexicographically using Arrays.sort().
        Arrays.sort(suffixes);

        // Step 3: Extract the indices of the sorted suffixes to create the suffix array.
        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index; // Store the starting index of each sorted suffix.
        }

        // Optional: Print the sorted suffixes for debugging purposes.
        System.out.println("Sorted Suffixes:");
        System.out.println(Arrays.toString(suffixes));
        
        return suffixArray; // Return the constructed suffix array.
    }

    /**
     * A helper class to represent a suffix with its starting index and its value (substring).
     */
    static class Suffix implements Comparable<Suffix> {
        int index; // The starting index of the suffix in the original string.
        String suffixValue; // The string representation of the suffix.

        Suffix(int index, String suffixValue) {
            this.index = index;
            this.suffixValue = suffixValue;
        }

        /**
         * Compare two suffixes lexicographically based on their string values.
         * @param other The suffix to compare this suffix with.
         * @return A negative value if this suffix is lexicographically smaller, 0 if equal, and a positive value if larger.
         */
        @Override
        public int compareTo(Suffix other) {
            return this.suffixValue.compareTo(other.suffixValue);
        }
        
        /**
         * Override toString to provide a string representation of the suffix for debugging.
         * @return The index and value of the suffix as a string.
         */
        @Override
        public String toString() {
            return index + "-" + suffixValue;
        }
    }

    public static void main(String[] args) {
        String text = "alabama"; // Input text for which the suffix array is to be built.

        // Step 4: Build the suffix array for the given text.
        int[] suffixArray = buildSuffixArray(text);

        // Step 5: Print the constructed suffix array.
        System.out.println("Suffix Array for the text \"" + text + "\":");
        System.out.println(Arrays.toString(suffixArray));
    }
}
