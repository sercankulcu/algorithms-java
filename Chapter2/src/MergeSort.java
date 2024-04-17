
public class MergeSort {

	public static void main(String[] args) {
		// Test the Merge Sort algorithm with an example array
		int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
		System.out.println("Original Array: " + arrayToString(array));

		mergeSort(array, 0, array.length - 1);

		System.out.println("Sorted Array: " + arrayToString(array));
	}

	// Function to perform the Merge Sort algorithm
	private static void mergeSort(int[] array, int left, int right) {
		if (left < right) {
			// Find the middle point
			int mid = left + (right - left) / 2;

			// Recursively sort the first and second halves
			mergeSort(array, left, mid);
			mergeSort(array, mid + 1, right);

			// Merge the sorted halves
			merge(array, left, mid, right);
		}
	}

	// Function to merge two subarrays of array[]
	private static void merge(int[] array, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Create temporary arrays
		int[] leftArray = new int[n1];
		int[] rightArray = new int[n2];

		// Copy data to temporary arrays
		for (int i = 0; i < n1; ++i) {
			leftArray[i] = array[left + i];
		}
		for (int j = 0; j < n2; ++j) {
			rightArray[j] = array[mid + 1 + j];
		}

		// Merge the temporary arrays

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray
		int k = left;

		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				array[k] = leftArray[i];
				i++;
			} else {
				array[k] = rightArray[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of leftArray[], if there are any
		while (i < n1) {
			array[k] = leftArray[i];
			i++;
			k++;
		}

		// Copy remaining elements of rightArray[], if there are any
		while (j < n2) {
			array[k] = rightArray[j];
			j++;
			k++;
		}
	}

	// Function to convert an array to a string for printing
	private static String arrayToString(int[] array) {
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i < array.length - 1) {
				result.append(", ");
			}
		}
		result.append("]");
		return result.toString();
	}
}
