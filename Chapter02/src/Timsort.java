import java.util.Arrays;
import java.util.Random;

public class Timsort {
	// Minimum run size for Timsort
	private static final int MIN_MERGE = 32;

	// Main method to sort the array
	public static void timSort(int[] arr) {
		int n = arr.length;

		// Step 1: Divide array into runs and sort them using insertion sort
		for (int i = 0; i < n; i += MIN_MERGE) {
			insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
		}

		// Step 2: Merge runs
		for (int size = MIN_MERGE; size < n; size = 2 * size) {
			for (int left = 0; left < n; left += 2 * size) {
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1), (n - 1));

				// Merge only if mid is less than right
				if (mid < right) {
					merge(arr, left, mid, right);
				}
			}
		}
	}

	// Insertion sort implementation for small runs
	private static void insertionSort(int[] arr, int left, int right) {
		// Start from second element in the run
		for (int i = left + 1; i <= right; i++) {
			int key = arr[i];
			int j = i - 1;

			// Move elements that are greater than key
			// to one position ahead of their current position
			while (j >= left && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	// Merge two sorted runs
	private static void merge(int[] arr, int left, int mid, int right) {
		// Calculate lengths of two runs to be merged
		int len1 = mid - left + 1;
		int len2 = right - mid;

		// Create temporary arrays
		int[] leftArr = new int[len1];
		int[] rightArr = new int[len2];

		// Copy data to temporary arrays
		for (int i = 0; i < len1; i++) {
			leftArr[i] = arr[left + i];
		}
		for (int i = 0; i < len2; i++) {
			rightArr[i] = arr[mid + 1 + i];
		}

		// Merge the temporary arrays back into arr
		int i = 0, j = 0, k = left;

		// Compare and merge elements from both arrays
		while (i < len1 && j < len2) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			} else {
				arr[k] = rightArr[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of leftArr[] if any
		while (i < len1) {
			arr[k] = leftArr[i];
			i++;
			k++;
		}

		// Copy remaining elements of rightArr[] if any
		while (j < len2) {
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}
	
	/**
     * Function to generate a random array of integers for testing purposes.
     * 
     * @param size  The size of the array.
     * @param bound The upper limit for the random integers (exclusive).
     * @return The generated random array.
     */
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random(); // Create a Random object for generating random numbers.
        int[] array = new int[size]; // Initialize the array with the specified size.
        
        // Populate the array with random integers in the range [0, bound).
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound); 
        }

        return array; // Return the generated array.
    }

	// Main method to test the implementation
	public static void main(String[] args) {

		// Generate a random array of integers for testing
		int[] array = generateRandomArray(16, 1000); 
		System.out.println("Original Array: " + Arrays.toString(array));

		timSort(array);

		// Print the sorted array
		System.out.println("Sorted Array: " + Arrays.toString(array));
	}
}