import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

	// Function to solve the Subset Sum problem using dynamic programming
	public static List<Integer> subsetSum(int[] nums, int target) {
		int n = nums.length;

		// Create a 2D array to store whether it's possible to obtain the sum 'i' using elements up to 'j'
		boolean[][] dp = new boolean[n + 1][target + 1];

		// Base case: It's possible to obtain sum 0 using any number of elements (by not selecting any)
		for (int i = 0; i <= n; i++) {
			dp[i][0] = true;
		}

		// Fill the array using dynamic programming
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= target; j++) {
				// If the current number is greater than the target, we cannot include it
				if (nums[i - 1] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					// Either include or exclude the current number
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
				}
			}
		}

		// Retrieve the selected elements that form the subset with the given sum
		List<Integer> subset = new ArrayList<>();
		if (dp[n][target]) {
			int i = n;
			int j = target;
			while (i > 0 && j > 0) {
				// If the current number was included
				if (dp[i][j] && !dp[i - 1][j]) {
					subset.add(nums[i - 1]);
					j -= nums[i - 1];
				}
				i--;
			}
		}

		return subset;
	}

	public static void main(String[] args) {
		int[] nums = {3, 34, 4, 12, 5, 2};
		int target = 21;

		// Solve the Subset Sum problem and retrieve the selected elements
		System.out.println(target + ":" + subsetSum(nums, target));
	}
}
