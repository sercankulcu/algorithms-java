import java.util.Arrays;

public class PermutationsAllSizesExample {
	
	static int stepCount = 0;
	
	static int factorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static void main(String[] args) {
		// Example array
		int[] array = {1, 2, 3, 4};
		int[] array2 = {1, 2, 3, 4, 5};
		int[] array3 = {1, 2, 3, 4, 5, 6};
		int[] array4 = {1, 2, 3, 4, 5, 6, 7, 8};
		
		stepCount = 0;
		// Generate and print all permutations for different sizes
		for (int size = 1; size <= array.length; size++) {
			generateAllPermutations(array, size, 0);
		}
		System.out.println(stepCount + "/" + factorial(4) + ":" + ((double)stepCount / factorial(4)));
		
		stepCount = 0;
		// Generate and print all permutations for different sizes
		for (int size = 1; size <= array2.length; size++) {
			generateAllPermutations(array2, size, 0);
		}
		System.out.println(stepCount + "/" + factorial(5) + ":" + ((double)stepCount / factorial(5)));
		
		stepCount = 0;
		// Generate and print all permutations for different sizes
		for (int size = 1; size <= array3.length; size++) {
			generateAllPermutations(array3, size, 0);
		}
		System.out.println(stepCount + "/" + factorial(6) + ":" + ((double)stepCount / factorial(6)));
		
		stepCount = 0;
		// Generate and print all permutations for different sizes
		for (int size = 1; size <= array4.length; size++) {
			generateAllPermutations(array4, size, 0);
		}
		System.out.println(stepCount + "/" + factorial(8) + ":" + ((double)stepCount / factorial(8)));	}

	// Function to generate all permutations of a specific size for an array
	private static void generateAllPermutations(int[] array, int size, int currentIndex) {
		if (currentIndex == size) {
			// If currentIndex reaches the specified size, print the permutation
			//System.out.println(Arrays.toString(Arrays.copyOf(array, size)));
			stepCount++;
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
