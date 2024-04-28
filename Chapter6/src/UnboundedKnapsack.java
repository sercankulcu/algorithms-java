import java.util.Arrays;

public class UnboundedKnapsack {

	// Function to solve the Unbounded Knapsack problem using dynamic programming
	public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
		int n = weights.length;

		// Create an array to store the maximum value that can be obtained with different capacities
		int[] dp = new int[capacity + 1];

		// Fill the array using dynamic programming
		for (int i = 0; i <= capacity; i++) {
			for (int j = 0; j < n; j++) {
				// If the current item can be included in the knapsack
				if (weights[j] <= i) {
					// Update the maximum value that can be obtained with the current capacity
					dp[i] = Math.max(dp[i], dp[i - weights[j]] + values[j]);
				}
			}
			System.out.println(Arrays.toString(dp));
		}

		// Return the maximum value that can be obtained with the given capacity
		return dp[capacity];
	}

	public static void main(String[] args) {
		int[] weights = {2, 3, 4, 5, 6};
		int[] values = {3, 4, 5, 6, 7};
		int capacity = 10;

		// Solve the Unbounded Knapsack problem
		int maxValue = unboundedKnapsack(weights, values, capacity);

		// Print the maximum value that can be obtained
		System.out.println("Maximum value that can be obtained: " + maxValue);
	}
}
