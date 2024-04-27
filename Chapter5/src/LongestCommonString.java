
public class LongestCommonString {

	// Function to find the length of the Longest Common Substring (LCS)
	public static String longestCommonString(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();

		// Create a 2D array to store the lengths of common substrings
		int[][] dp = new int[m + 1][n + 1];
		int maxLength = 0; // To keep track of the length of the longest common substring
		int endIndex = 0; // To keep track of the ending index of the longest common substring

		// Fill the array using dynamic programming
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1; // If characters match, increment length
					if (dp[i][j] > maxLength) { // Update maxLength and endIndex
						maxLength = dp[i][j];
						endIndex = i - 1; // Update endIndex to the index of the last character of the common substring
					}
				}
			}
		}

		// Extract the longest common substring using the endIndex
		String longestCommonSubstring = str1.substring(endIndex - maxLength + 1, endIndex + 1);

		return longestCommonSubstring;
	}

	public static void main(String[] args) {
		
		// Find the Longest Common Subsequence (LCS)
		String lcs = longestCommonString("AGGTAB", "GXTXAYB");
		// Print the length of LCS and the LCS itself
		System.out.println("LCS: " +  lcs);

		lcs = longestCommonString("ercan", "sercan");
		System.out.println("LCS: " +  lcs);

		lcs = longestCommonString("esercan", "sercan");
		System.out.println("LCS: " +  lcs);

		lcs = longestCommonString("ertan", "sercan");
		System.out.println("LCS: " +  lcs);
	}
}
