package distance;

public class LevenshteinDistance {

    // Calculate the Levenshtein Distance between two strings
    public static int levenshteinDistance(String str1, String str2) {
        // Create a 2D array to store the distances
        // dp[i][j] will hold the minimum number of operations required to convert
        // the first i characters of str1 to the first j characters of str2.
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // Initialize the first row and column of the array
        // The first row represents the case where str1 is empty, 
        // so the distance is just the number of insertions needed to convert str2 to str1.
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i; // To convert any prefix of str1 to an empty string, we need 'i' deletions.
        }
        // The first column represents the case where str2 is empty, 
        // so the distance is just the number of insertions needed to convert str1 to str2.
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j; // To convert an empty string to any prefix of str2, we need 'j' insertions.
        }

        // Fill the rest of the array using dynamic programming
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // If the characters are equal, no operation is needed (carry over the previous value)
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters differ, consider the three possible operations:
                    // 1. Deletion (dp[i-1][j] + 1)
                    // 2. Insertion (dp[i][j-1] + 1)
                    // 3. Replacement (dp[i-1][j-1] + 1)
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        // Print the DP table (for debugging purposes, not necessary in the final output)
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing one row of the table
        }
        System.out.println(); // Empty line for better readability

        // Return the edit distance between the two strings, located at the bottom-right corner of the DP table
        return dp[str1.length()][str2.length()];
    }

    public static void main(String[] args) {

        // Example 1: Calculate the Levenshtein Distance between "kitten" and "sitting"
        System.out.println("Distance is: " + levenshteinDistance("kitten", "sitting"));
        // Example 2 (commented out): Distance between "kitten" and "sittin"
        //System.out.println("Distance is: " + levenshteinDistance("kitten", "sittin"));
        // Example 3 (commented out): Distance between "ercan" and "sercan"
        //System.out.println("Distance is: " + levenshteinDistance("ercan", "sercan"));
        // Example 4 (commented out): Distance between "apple" and "banana"
        //System.out.println("Distance is: " + levenshteinDistance("apple", "banana"));
    }
}
