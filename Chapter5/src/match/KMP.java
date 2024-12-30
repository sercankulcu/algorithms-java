package match;
import java.util.Arrays;

public class KMP {

    // Preprocess the pattern to compute the prefix function
    // The prefix function stores the length of the longest proper prefix of the substring that is also a suffix.
    private static int[] computePrefixFunction(String pattern) {
        int m = pattern.length(); // Length of the pattern
        int[] pi = new int[m]; // Array to store the prefix function values
        int k = 0; // Length of the current matching prefix-suffix

        // Compute the prefix function for each character in the pattern
        for (int q = 1; q < m; q++) {
            // Backtrack using the prefix function array to find the longest proper prefix that matches the suffix
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = pi[k - 1]; // Backtrack to the previous longest matching prefix-suffix
            }

            // If there's a match, increase the length of the current prefix-suffix
            if (pattern.charAt(k) == pattern.charAt(q)) {
                System.out.println("pattern[" + k + "] == pattern[" + q + "]\t" + 
                        pattern.charAt(k) + "?" + pattern.charAt(q) + " k=" + (k+1));
                k++; // Increase the length of the prefix-suffix
            }

            pi[q] = k; // Store the length of the longest matching prefix-suffix for pattern[q]
        }
        
        // Print the complete prefix function for debugging purposes
        System.out.println(Arrays.toString(pi));
        return pi; // Return the prefix function array
    }

    // Search for the pattern in the text using the KMP algorithm
    // The Knuth-Morris-Pratt algorithm uses the prefix function to efficiently search the pattern in the text
    public static int kmpStringMatch(String text, String pattern) {
        int n = text.length(); // Length of the text
        int m = pattern.length(); // Length of the pattern
        int[] pi = computePrefixFunction(pattern); // Compute the prefix function for the pattern
        int q = 0; // Current position in the pattern

        // Iterate through the characters of the text
        for (int i = 0; i < n; i++) {
            // While there is a mismatch and we have a non-zero prefix-suffix length, backtrack using the prefix function
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = pi[q - 1]; // Backtrack to the previous longest matching prefix-suffix
            }

            // Print the current comparison between the pattern and the text for debugging purposes
            System.out.println("pattern[" + (q) + 
                    "] != text[" + i + "]\t" + text.charAt(i) + "?" + pattern.charAt(q));
            
            // If there's a match, move forward in the pattern
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }

            // If we've matched the entire pattern, return the starting index of the match
            if (q == m) {
                return i - m + 1; // Match found, return the starting index of the match
            }
        }

        // If no match is found after scanning the entire text
        return -1; // No match found
    }

    public static void main(String[] args) {
        // Sample input text and pattern to search
        String text = "abracacbacacbacbacabra";
        String pattern = "acbacb";

        // Perform the KMP string matching
        int matchIndex = kmpStringMatch(text, pattern);

        // Output the result
        if (matchIndex != -1) {
            System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
        } else {
            System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
        }
    }
}
