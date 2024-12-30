import java.util.Arrays;

public class Knapsack {

    // Function to solve the Knapsack problem using dynamic programming
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;  // Number of items

        // Create a 2D array dp to store the maximum value that can be obtained for each weight and capacity combination
        int[][] dp = new int[n + 1][capacity + 1];

        // Create a 2D array to track the included items
        boolean[][] include = new boolean[n + 1][capacity + 1];

        // Fill the dp array using dynamic programming
        for (int i = 1; i <= n; i++) {  // Loop through all items
            for (int j = 1; j <= capacity; j++) {  // Loop through all capacities from 1 to max capacity
                if (weights[i - 1] <= j) {  // If the current item can be included
                    // Choose the maximum of including or excluding the current item
                    if (values[i - 1] + dp[i - 1][j - weights[i - 1]] > dp[i - 1][j]) {
                        dp[i][j] = values[i - 1] + dp[i - 1][j - weights[i - 1]];
                        include[i][j] = true;  // Mark the item as included
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            // Print the current state of the dp array after processing the ith item
            System.out.println("DP state after including item " + i + ": " + Arrays.toString(dp[i]));
        }

        // Backtrack to find the items included in the knapsack
        int remainingCapacity = capacity;
        System.out.println("Items included:");
        for (int i = n; i > 0; i--) {
            if (include[i][remainingCapacity]) {  // If the item was included
                System.out.println("Item " + i + " (Weight: " + weights[i - 1] + ", Value: " + values[i - 1] + ")");
                remainingCapacity -= weights[i - 1];  // Reduce the remaining capacity
            }
        }

        // Return the maximum value that can be obtained with the given capacity
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        // Item weights and values
        int[] weights = {2, 3, 4, 5,  6,  7,  8};
        int[] values =  {3, 5, 8, 6, 10, 11, 15};
        int capacity = 10;  // Maximum capacity of the knapsack

        // Solve the Knapsack problem and store the result
        int maxValue = knapsack(weights, values, capacity);

        // Print the maximum value that can be obtained with the given capacity
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
