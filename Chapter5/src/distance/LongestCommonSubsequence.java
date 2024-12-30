package distance;

public class LongestCommonSubsequence {

    // Find the Longest Common Subsequence (LCS) of two strings
    public static String longestCommonSubsequence(String str1, String str2) {
        int m = str1.length(); // Length of the first string
        int n = str2.length(); // Length of the second string

        // Create a 2D array to store the lengths of LCS for each prefix
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j] will hold the LCS length of str1[0..i-1] and str2[0..j-1]

        // Fill the array using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, increment the LCS length
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Take the value from diagonal cell and add 1
                } else {
                    // If characters do not match, take the maximum of the top or left cell
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Trace back to find the Longest Common Subsequence from the dp table
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder(); // StringBuilder to build the LCS string
        while (i > 0 && j > 0) {
            // If characters match, it's part of the LCS
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.insert(0, str1.charAt(i - 1)); // Prepend the matching character to the LCS
                i--; // Move diagonally in the dp table
                j--; // Move diagonally in the dp table
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // If the value above is greater, move up in the table
                i--;
            } else {
                // If the value to the left is greater, move left in the table
                j--;
            }
        }

        // Return the Longest Common Subsequence as a string
        return lcs.toString();
    }

    public static void main(String[] args) {

        // Test the Longest Common Subsequence method with different inputs
        String lcs = longestCommonSubsequence("AGGTAB", "GXTXAYB");
        // Print the LCS found for the first pair of strings
        System.out.println("LCS: " +  lcs); // Expected LCS: "GTAB"
        
        lcs = longestCommonSubsequence("ercan", "sercan");
        // Print the LCS found for the second pair of strings
        System.out.println("LCS: " +  lcs); // Expected LCS: "ercan"
        
        lcs = longestCommonSubsequence("esercan", "sercan");
        // Print the LCS found for the third pair of strings
        System.out.println("LCS: " +  lcs); // Expected LCS: "sercan"
        
        lcs = longestCommonSubsequence("ertan", "sercan");
        // Print the LCS found for the fourth pair of strings
        System.out.println("LCS: " +  lcs); // Expected LCS: "ran"
    }
}
