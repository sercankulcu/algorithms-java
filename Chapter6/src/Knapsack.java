import java.util.Arrays;

public class Knapsack {

	// Function to solve the Knapsack problem using dynamic programming
	public static int knapsack(int[] weights, int[] values, int capacity) {
		int n = weights.length;

		// Create a 2D array to store the maximum value that can be obtained with different weights and capacities
		int[][] dp = new int[n + 1][capacity + 1];

		// Fill the array using dynamic programming
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= capacity; j++) {
				// If the current item can be included in the knapsack
				if (weights[i - 1] <= j) {
					// Choose the maximum of including or excluding the current item
					dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
				} else {
					// If the current item cannot be included, copy the value from the previous row
					dp[i][j] = dp[i - 1][j];
				}
			}
			System.out.println(Arrays.toString(dp[i]));
		}

		// Return the maximum value that can be obtained with the given capacity
		return dp[n][capacity];
	}

	public static void main(String[] args) {
		int[] weights = {2, 3, 4, 5, 6, 7, 8};
		int[] values = {3, 5, 8, 6, 10, 11, 15};
		int capacity = 10;

		// Solve the Knapsack problem
		int maxValue = knapsack(weights, values, capacity);

		// Print the maximum value that can be obtained
		System.out.println("Maximum value that can be obtained: " + maxValue);
	}
}
