import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

	// Function to find the length of the Longest Increasing Subsequence (LIS) using dynamic programming
	public static List<Integer> longestIncreasingSubsequence(int[] nums) {
		
		List<Integer> elements = new ArrayList<>();
		int n = nums.length;

		// Create an array to store the length of LIS ending at each index
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		// Initialize all LIS lengths to 1, as each element is a subsequence of length 1
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				// If the current element is greater than the element at index j,
				// update the LIS length ending at index i
				if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}

		// Find the maximum LIS length
		int maxLength = 0;
		for (int len : dp) {
			maxLength = Math.max(maxLength, len);
		}

		// Retrieve the elements of the Longest Increasing Subsequence
		for (int i = n - 1; i >= 0 && maxLength > 0; i--) {
			if (dp[i] == maxLength) {
				elements.add(nums[i]);
				maxLength--;
			}
		}

		return elements;
	}

	public static void main(String[] args) {
		int[] nums = {10, 22, 9, 33, 21, 50, 41, 60};

		// Print the length of the Longest Increasing Subsequence
		System.out.println("Longest Increasing Subsequence: " + 
					longestIncreasingSubsequence(nums));
	}
}
