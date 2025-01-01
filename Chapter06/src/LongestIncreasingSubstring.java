public class LongestIncreasingSubstring {

	// Function to find the Longest Increasing Substring (LIS) in a string
	public static String longestIncreasingSubstring(String s) {

		int n = s.length(); // Length of the input string

		// Variables to track the longest increasing substring
		int maxLength = 0; // Maximum length of the longest increasing substring found so far
		int startIndex = 0; // Starting index of the longest increasing substring
		int endIndex = 0; // Ending index of the longest increasing substring

		// Variables to track the current increasing substring
		int currLength = 1; // Length of the current increasing substring (initially 1 for the first character)
		int currStartIndex = 0; // Starting index of the current increasing substring

		// Iterate through the string starting from the second character
		for (int i = 1; i < n; i++) {
			// Check if the current character is greater than the previous character
			if (s.charAt(i) > s.charAt(i - 1)) {
				currLength++; // Increment the length of the current increasing substring

				// If the current substring is longer than the previously recorded longest substring
				if (currLength > maxLength) {
					maxLength = currLength; // Update the maximum length
					startIndex = currStartIndex; // Update the starting index of the longest substring
					endIndex = i; // Update the ending index of the longest substring
					
					// Print the current longest increasing substring for debugging
					System.out.println("Current Longest Increasing Substring: " + s.substring(startIndex, endIndex + 1));
				}
			} else {
				// Reset the current increasing substring
				currLength = 1; // Restart the length count for the new substring
				currStartIndex = i; // Update the starting index of the new substring
			}
		}

		// Return the longest increasing substring
		return s.substring(startIndex, endIndex + 1);
	}

	public static void main(String[] args) {
		String s = "abcfdeedfdefghcba"; // Input string

		// Find and print the Longest Increasing Substring
		System.out.println("Longest Increasing Substring: " + longestIncreasingSubstring(s));
	}
}
