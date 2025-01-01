import java.util.Arrays;

public class Knapsack {

    // Define the Item class with id, value, and weight
    static class Item implements Comparable<Item> {
        int id;        // Unique identifier for the item
        int value;     // Value of the item
        int weight;    // Weight of the item

        // Constructor to initialize id, value, and weight
        public Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
        }

        // Compare items based on value-to-weight ratio in non-increasing order
        public int compareTo(Item other) {
            double ratio1 = (double) this.value / this.weight;
            double ratio2 = (double) other.value / other.weight;
            return Double.compare(ratio2, ratio1); // Higher ratio comes first
        }
    }

    // Greedy algorithm for the knapsack problem (Approximation)
    public static int knapsackApproximation(Item[] items, int capacity) {
        Arrays.sort(items); // Sort items based on value-to-weight ratio
        int totalValue = 0;
        int currentWeight = 0;

        // Iterate through sorted items
        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Include the entire item if it fits in the knapsack
                totalValue += item.value;
                currentWeight += item.weight;
                System.out.println("Added item with ID: " + item.id + ", weight: " + item.weight + ", value: " + item.value);
            } else {
               
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        // Initialize items with id, value, and weight
        Item[] items = {
            new Item(1, 3, 2),   // Item 1: value 3, weight 2
            new Item(2, 5, 3),   // Item 2: value 5, weight 3
            new Item(3, 8, 4),   // Item 3: value 8, weight 4
            new Item(4, 6, 5),   // Item 4: value 6, weight 5
            new Item(5, 10, 6),  // Item 5: value 10, weight 6
            new Item(6, 20, 7),  // Item 6: value 11, weight 7
            new Item(7, 15, 8)   // Item 7: value 15, weight 8
        };

        int capacity = 10;  // Maximum weight capacity of the knapsack
        int maxValue = knapsackApproximation(items, capacity);  // Get the maximum value
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
