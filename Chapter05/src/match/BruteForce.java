package match;

public class BruteForce {

    // Brute force algorithm for string matching
    // This function attempts to find the pattern in the text by checking all possible substrings.
    public static int bruteForceStringMatch(String text, String pattern) {
        int n = text.length(); // Length of the text
        int m = pattern.length(); // Length of the pattern
        int count = 0; // Counter to track the number of character comparisons

        // Loop through each character in the text, starting from the beginning
        // The outer loop checks all possible positions in the text where the pattern can fit
        for (int i = 0; i <= n - m; i++) {
            int j;

            // Inner loop checks if the substring of the text starting at i matches the pattern
            for (j = 0; j < m; j++) {
                // Print the comparison result for debugging purposes
                System.out.println("text[" + (i + j) + 
                        "] != pattern[" + j + "]\t" + text.charAt(i + j) + "?" + pattern.charAt(j));
                count++; // Increment comparison counter
                
                // If characters don't match, break out of the inner loop
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            // If the inner loop completes without breaking, it means the whole pattern matched
            if (j == m) {
                // Output the total number of comparisons made before finding the match
            	System.out.println("Total number of comparisons made " + count);
                return i; // Return the starting index of the match
            }
        }

        // Output the total number of comparisons made during the search
        System.out.println("Total number of comparisons made " + count);
        return -1; // Return -1 if no match is found
    }

    public static void main(String[] args) {
        // Test case: search for the pattern in the text
        String text = "acaddbracaddeabra"; // Sample text
        String pattern = "cadde"; // Sample pattern to search for

        // Update test case
        text = "abracacadabra"; // New text
        pattern = "cad"; // New pattern

        // Call the brute force string match function
        int matchIndex = bruteForceStringMatch(text, pattern);

        // Output the result of the match attempt
        if (matchIndex != -1) {
            System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
        } else {
            System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
        }
    }
}
