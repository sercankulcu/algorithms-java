package distance;

public class LongestCommonString {

    // Function to find the length of the Longest Common Substring (LCS)
    public static String longestCommonString(String str1, String str2) {
        int m = str1.length(); // Length of the first string
        int n = str2.length(); // Length of the second string

        // Create a 2D array to store the lengths of common substrings
        int[][] dp = new int[m + 1][n + 1]; // dp[i][j] will store the length of the common substring ending at str1[i-1] and str2[j-1]
        int maxLength = 0; // Variable to keep track of the length of the longest common substring found so far
        int endIndex = 0; // Variable to keep track of the ending index of the longest common substring in str1

        // Fill the array using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, extend the common substring length from the previous characters
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Increment length of common substring ending at this pair of characters
                    // If the length of the current common substring is the longest seen so far, update maxLength and endIndex
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j]; // Update the maximum length of common substring
                        endIndex = i - 1; // Update the endIndex to the last character of the longest common substring
                    }
                } else {
                    dp[i][j] = 0; // If characters do not match, reset the length of the common substring to 0
                }
            }
        }

        // Extract the longest common substring using the endIndex and maxLength
        String longestCommonSubstring = str1.substring(endIndex - maxLength + 1, endIndex + 1); // Get the substring from str1

        return longestCommonSubstring; // Return the longest common substring
    }

    public static void main(String[] args) {
        // Test the Longest Common Substring method with different inputs
        String lcs = longestCommonString("AGGTAB", "GXTXAYB");
        // Print the longest common substring for the first pair of strings
        System.out.println("LCS: " + lcs); // Expected output: "A"

        lcs = longestCommonString("ercan", "sercan");
        // Print the longest common substring for the second pair of strings
        System.out.println("LCS: " + lcs); // Expected output: "ercan"

        lcs = longestCommonString("esercan", "sercan");
        // Print the longest common substring for the third pair of strings
        System.out.println("LCS: " + lcs); // Expected output: "sercan"

        lcs = longestCommonString("ertan", "sercan");
        // Print the longest common substring for the fourth pair of strings
        System.out.println("LCS: " + lcs); // Expected output: "er"
    }
}
