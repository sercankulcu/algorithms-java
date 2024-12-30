package match;

import java.util.*;

public class BoyerMoore {

    // Preprocess the pattern to compute the bad character shift table
    private static int[] computeBadCharacterShift(String pattern) {
        int m = pattern.length(); // Length of the pattern
        int[] badCharShift = new int[256]; // Assuming ASCII characters (256 possible values)

        // Initialize all entries as -1
        Arrays.fill(badCharShift, -1);

        // Fill the table with the last occurrence of each character in the pattern
        for (int i = 0; i < m; i++) {
            badCharShift[pattern.charAt(i)] = i; // Store the last index of each character in the pattern
        }

        // Debugging: Print the bad character shift table
        System.out.println(Arrays.toString(badCharShift));
        for (int i = 0; i < 256; i++) {
            if (badCharShift[i] != -1)
                System.out.print(badCharShift[i] + " "); // Print non-negative shifts
        }
        System.out.println();

        return badCharShift; // Return the completed bad character shift table
    }

    // Search for the pattern in the text using the Boyer-Moore algorithm
    public static int boyerMooreStringMatch(String text, String pattern) {
        int n = text.length(); // Length of the text
        int m = pattern.length(); // Length of the pattern
        int[] badCharShift = computeBadCharacterShift(pattern); // Compute the bad character shift table
        int s = 0; // Start index of the current substring in the text

        // Loop through the text with a step size of pattern length
        while (s <= n - m) { // Ensure we don't go out of bounds
            int j = m - 1; // Start matching from the last character of the pattern

            // Debugging: Print the current comparison
            System.out.println("text[" + (s + j) + 
                    "] != pattern[" + j + "]\t" + text.charAt(s + j) + "?" + pattern.charAt(j));

            // Start matching from the end of the pattern
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                // Print the comparison at each step for debugging
                System.out.println("text[" + (s + j) + 
                        "] == pattern[" + j + "]\t" + text.charAt(s + j) + "?" + pattern.charAt(j));
                j--; // Move to the previous character of the pattern
            }

            // If we reached the start of the pattern, it means a match was found
            if (j < 0) {
                return s; // Return the starting index of the match
            } else {
                // Shift the pattern based on the bad character rule
                s += Math.max(1, j - badCharShift[text.charAt(s + j)]);
                System.out.println("shift " + s + " characters.");
            }
        }
        return -1; // Return -1 if no match is found
    }

    public static void main(String[] args) {
        String text = "merhaba naber"; // Sample text to search within
        String pattern = "abe"; // Pattern to search for

        // Perform Boyer-Moore string matching
        int matchIndex = boyerMooreStringMatch(text, pattern);

        // Output the result of the search
        if (matchIndex != -1) {
            System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
        } else {
            System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
        }
    }
}
