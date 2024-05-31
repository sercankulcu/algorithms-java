package distance;

public class LevenshteinDistance {

	// Calculate the Levenshtein Distance between two strings
	public static int levenshteinDistance(String str1, String str2) {
		// Create a 2D array to store the distances
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		// Initialize the first row and column of the array
		for (int i = 0; i <= str1.length(); i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= str2.length(); j++) {
			dp[0][j] = j;
		}

		// Fill the rest of the array using dynamic programming
		for (int i = 1; i <= str1.length(); i++) {
			for (int j = 1; j <= str2.length(); j++) {
				// If the characters are equal, no operation needed
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// Minimum of insert, delete, or replace operation
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				}
			}
		}
		
		for(int i = 0; i <= str1.length(); i++) {
			for(int j = 0; j <= str2.length(); j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		// Return the edit distance between the two strings
		return dp[str1.length()][str2.length()];
	}

	public static void main(String[] args) {

		// Print the Levenshtein Distance
		System.out.println("Distance is: " + levenshteinDistance("kitten", "sitting"));
		//System.out.println("Distance is: " + levenshteinDistance("kitten", "sittin"));
		//System.out.println("Distance is: " + levenshteinDistance("ercan", "sercan"));
		//System.out.println("Distance is: " + levenshteinDistance("apple", "banana"));
	}
}
