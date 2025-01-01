import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedKnapsack {

    /**
     * Function to solve the Unbounded Knapsack problem using dynamic programming.
     * @param weights Array of weights of the items.
     * @param values Array of values of the items.
     * @param capacity The total capacity of the knapsack.
     * @return A list of items that are added to achieve the maximum value.
     */
    public static List<Integer> unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Create a DP array where dp[i] represents the maximum value that can be obtained with capacity 'i'.
        int[] dp = new int[capacity + 1];

        // To track which items are added to achieve the maximum value for each capacity.
        int[] itemAdded = new int[capacity + 1];

        // Fill the DP array using the unbounded knapsack logic.
        for (int i = 0; i <= capacity; i++) { // Iterate over all capacities from 0 to the given capacity.
            for (int j = 0; j < n; j++) { // Iterate over all items.
                // If the current item's weight is less than or equal to the current capacity 'i'.
                if (weights[j] <= i) {
                    // Update the maximum value for capacity 'i' by considering the current item.
                    if (dp[i - weights[j]] + values[j] > dp[i]) {
                        dp[i] = dp[i - weights[j]] + values[j];
                        itemAdded[i] = j; // Track the index of the item added.
                    }
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        // Reconstruct the list of items added.
        List<Integer> selectedItems = new ArrayList<>();
        int remainingCapacity = capacity;
        while (remainingCapacity > 0) {
            int selectedItemIndex = itemAdded[remainingCapacity];
            selectedItems.add(selectedItemIndex); // Add the index of the item to the list.
            remainingCapacity -= weights[selectedItemIndex]; // Reduce the remaining capacity.
        }

        // Print the DP array for debugging purposes (optional).
        System.out.println("DP Array: " + Arrays.toString(dp));

        return selectedItems;
    }

    public static void main(String[] args) {
        // Weights and values of the items.
        int[] weights = {2, 3, 4, 5, 6, 7, 8};
        int[] values = {3, 5, 8, 6, 10, 11, 15};
        
        // Total capacity of the knapsack.
        int capacity = 10;

        // Solve the Unbounded Knapsack problem.
        List<Integer> selectedItems = unboundedKnapsack(weights, values, capacity);

        // Print the maximum value that can be obtained.
        int maxValue = selectedItems.stream()
                                    .mapToInt(index -> values[index])
                                    .sum();
        System.out.println("Maximum value that can be obtained: " + maxValue);

        // Print the items that were added to the knapsack.
        System.out.println("Items added to the knapsack:");
        for (int index : selectedItems) {
            System.out.println("Weight: " + weights[index] + ", Value: " + values[index]);
        }
    }
}
