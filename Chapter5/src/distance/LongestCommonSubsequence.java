package distance;

public class LongestCommonSubsequence {

	// Find the Longest Common Subsequence (LCS) of two strings
	public static String longestCommonSubsequence(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();

		// Create a 2D array to store the lengths of LCS for each prefix
		int[][] dp = new int[m + 1][n + 1];

		// Fill the array using dynamic programming
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1; // If characters match, increment LCS length
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Take the maximum of two previous lengths
				}
			}
		}

		// Trace back to find the Longest Common Subsequence
		int i = m, j = n;
		StringBuilder lcs = new StringBuilder();
		while (i > 0 && j > 0) {
			if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
				lcs.insert(0, str1.charAt(i - 1)); // Append character to LCS
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}

		// Return the Longest Common Subsequence
		return lcs.toString();
	}

	public static void main(String[] args) {

		// Find the Longest Common Subsequence (LCS)
		String lcs = longestCommonSubsequence("AGGTAB", "GXTXAYB");
		// Print the length of LCS and the LCS itself
		System.out.println("LCS: " +  lcs);
		
		lcs = longestCommonSubsequence("ercan", "sercan");
		System.out.println("LCS: " +  lcs);
		
		lcs = longestCommonSubsequence("esercan", "sercan");
		System.out.println("LCS: " +  lcs);
		
		lcs = longestCommonSubsequence("ertan", "sercan");
		System.out.println("LCS: " +  lcs);
	}
}
