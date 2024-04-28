public class LongestIncreasingSubstring {

	// Function to find the length of the Longest Increasing Substring (LIS) using dynamic programming
	public static String longestIncreasingSubstring(String s) {

		StringBuilder substring = new StringBuilder();
		int n = s.length();

		// Initialize variables to track the longest increasing substring
		int maxLength = 0;
		int startIndex = 0;
		int endIndex = 0;
		int currLength = 1;
		int currStartIndex = 0;

		// Iterate through each character of the string
		for (int i = 1; i < n; i++) {
			// If the current character is greater than the previous character
			if (s.charAt(i) > s.charAt(i - 1)) {
				currLength++; // Increment the length of the current increasing substring
				// If the current increasing substring length is greater than the maximum length found so far
				if (currLength > maxLength) {
					maxLength = currLength; // Update the maximum length
					startIndex = currStartIndex; // Update the starting index of the longest increasing substring
					endIndex = i; // Update the ending index of the longest increasing substring
					System.out.println(s.substring(startIndex, endIndex + 1));
				}
			} else {
				currLength = 1; // Reset the length of the current increasing substring
				currStartIndex = i; // Update the starting index of the current increasing substring
			}
		}
		// Return the length of the longest increasing substring
		return s.substring(startIndex, endIndex + 1);
	}

	public static void main(String[] args) {
		String s = "abcfdeedfdefghcba";

		// Find the length of the Longest Increasing Substring and retrieve its elements
		// Print the length of the Longest Increasing Substring
		System.out.println("Length of Longest Increasing Substring: " + 
					longestIncreasingSubstring(s));

	}
}
