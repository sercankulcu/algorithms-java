
import java.util.Arrays;

public class KnapsackApproximation {

	static class Item implements Comparable<Item> {
		int value;
		int weight;

		public Item(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}

		// Compare items based on value-to-weight ratio in non-increasing order
		public int compareTo(Item other) {
			double ratio1 = (double) this.value / this.weight;
			double ratio2 = (double) other.value / other.weight;
			return Double.compare(ratio2, ratio1);
		}
	}

	// Greedy algorithm for the knapsack problem
	public static int knapsackApproximation(Item[] items, int capacity) {
		Arrays.sort(items); // Sort items based on value-to-weight ratio
		int totalValue = 0;
		int currentWeight = 0;

		for (Item item : items) {
			if (currentWeight + item.weight <= capacity) {
				// Include the entire item
				totalValue += item.value;
				currentWeight += item.weight;
				System.out.println("Added item" + item.weight);
			} else {
        // Knapsack is full, break the loop
				
				// Include a fraction of the item
				//int remainingCapacity = capacity - currentWeight;
				//totalValue += (int) (item.value * ((double) remainingCapacity / item.weight));
				//break;
			}
		}

		return totalValue;
	}

	public static void main(String[] args) {
		
		Item[] items = {
				new Item(3, 2),
				new Item(5, 3),
				new Item(8, 4),
				new Item(6, 5),
				new Item(10, 6),
				new Item(11, 7),
				new Item(15, 8)
		};

		int capacity = 10;
		int maxValue = knapsackApproximation(items, capacity);
		System.out.println("Maximum value that can be obtained: " + maxValue);
	}
}
