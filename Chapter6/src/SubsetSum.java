import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    /**
     * Function to solve the Subset Sum problem using dynamic programming.
     * @param nums An array of integers representing the set of numbers.
     * @param target The target sum to be achieved.
     * @return A list of integers forming a subset whose sum equals the target, or an empty list if no subset exists.
     */
    public static List<Integer> subsetSum(int[] nums, int target) {
        int n = nums.length;

        // Create a 2D boolean array (dp table) where dp[i][j] indicates whether a sum 'j' is possible
        // using the first 'i' elements of the array.
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base case: It's always possible to form the sum '0' by selecting no elements.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the dp table row by row.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // If the current element is greater than the current target sum, we cannot include it.
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // Carry forward the value from the previous row.
                } else {
                    // Otherwise, we can either include or exclude the current element.
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        // Create a list to store the subset elements if a valid subset exists.
        List<Integer> subset = new ArrayList<>();

        // If dp[n][target] is true, it means a subset with the given sum exists.
        if (dp[n][target]) {
            int i = n;
            int j = target;

            // Trace back through the dp table to determine which elements were included in the subset.
            while (i > 0 && j > 0) {
                // Check if the current element was included in the subset.
                if (dp[i][j] && !dp[i - 1][j]) {
                    subset.add(nums[i - 1]); // Add the current element to the subset.
                    j -= nums[i - 1]; // Reduce the target sum by the included element's value.
                }
                i--; // Move to the previous element.
            }
        }

        return subset; // Return the subset with the given sum, or an empty list if no subset exists.
    }

    public static void main(String[] args) {
        // Input array and target sum
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 21;

        // Solve the Subset Sum problem and retrieve the subset elements
        System.out.println("Target Sum " + target + ": " + subsetSum(nums, target));
    }
}
