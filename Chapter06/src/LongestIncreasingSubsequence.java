import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

	// Function to find the Longest Increasing Subsequence (LIS) using dynamic programming
	public static List<Integer> longestIncreasingSubsequence(int[] nums) {
		List<Integer> elements = new ArrayList<>(); // List to store the LIS elements
		int n = nums.length; // Length of the input array

		// Create an array to store the length of the LIS ending at each index
		int[] dp = new int[n];
		Arrays.fill(dp, 1); // Initialize LIS length to 1 for each index

		// Calculate the LIS length for each element
		for (int i = 1; i < n; i++) { // Iterate through the array
			for (int j = 0; j < i; j++) { // Compare the current element with previous elements
				if (nums[i] > nums[j] && dp[i] < dp[j] + 1) { 
					// If nums[i] > nums[j], update dp[i] if a longer subsequence can be formed
					dp[i] = dp[j] + 1;
				}
			}
		}

		// Find the maximum LIS length in the dp array
		int maxLength = 0; // Initialize maximum LIS length
		for (int len : dp) {
			maxLength = Math.max(maxLength, len);
		}

		// Backtrack to retrieve the elements of the LIS
		for (int i = n - 1; i >= 0 && maxLength > 0; i--) {
			if (dp[i] == maxLength) { 
				// If the dp value matches the current LIS length, add the element to the result
				elements.add(0, nums[i]); // Insert at the beginning to maintain order
				maxLength--; // Decrease the required LIS length
			}
		}

		return elements; // Return the LIS as a list of elements
	}

	public static void main(String[] args) {
		int[] nums = {10, 22, 9, 33, 21, 50, 41, 60}; // Input array

		// Find and print the Longest Increasing Subsequence
		System.out.println("Longest Increasing Subsequence: " + 
					longestIncreasingSubsequence(nums));
	}
}
