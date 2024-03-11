
public class BubbleSortExample {

	public static void main(String[] args) {
		// Test the Bubble Sort algorithm with an example array
		int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
		System.out.println("Original Array: " + arrayToString(array));

		bubbleSort(array);

		System.out.println("Sorted Array: " + arrayToString(array));
	}

	// Function to perform the Bubble Sort algorithm
	private static void bubbleSort(int[] array) {
		int n = array.length;
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;

			// Last i elements are already in place
			for (int j = 0; j < n - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					// Swap elements if they are in the wrong order
					swap(array, j, j + 1);
					swapped = true;
				}
			}

			// If no two elements were swapped by inner loop, the array is already sorted
			if (!swapped) {
				break;
			}
		}
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
