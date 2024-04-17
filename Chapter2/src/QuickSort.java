
public class QuickSort {

	public static void main(String[] args) {
		// Test the QuickSort algorithm with an example array
		int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
		System.out.println("Original Array: " + arrayToString(array));

		quickSort(array, 0, array.length - 1);

		System.out.println("Sorted Array: " + arrayToString(array));
	}

	// Function to perform the QuickSort algorithm
	private static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			// Find the pivot index such that elements smaller than the pivot are on the left,
			// and elements greater than the pivot are on the right
			int pivotIndex = partition(array, low, high);

			// Recursively sort the sub-arrays
			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}

	// Function to partition the array and find the pivot index
	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, high);
		return i + 1;
	}

	// Function to swap two elements in the array
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
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
