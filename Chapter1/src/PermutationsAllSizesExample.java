import java.util.Arrays;

public class PermutationsAllSizesExample {

	public static void main(String[] args) {
		// Example array
		int[] array = {1, 2, 3, 4};

		// Generate and print all permutations for different sizes
		for (int size = 1; size <= array.length; size++) {
			generateAllPermutations(array, size, 0);
		}
	}

	// Function to generate all permutations of a specific size for an array
	private static void generateAllPermutations(int[] array, int size, int currentIndex) {
		if (currentIndex == size) {
			// If currentIndex reaches the specified size, print the permutation
			System.out.println(Arrays.toString(Arrays.copyOf(array, size)));
		} else {
			for (int i = currentIndex; i < array.length; i++) {
				// Swap elements at currentIndex and i
				swap(array, currentIndex, i);

				// Recursively generate permutations for the remaining elements
				generateAllPermutations(array, size, currentIndex + 1);

				// Backtrack by swapping elements back to their original positions
				swap(array, currentIndex, i);
			}
		}
	}

	// Function to swap two elements in an array
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
