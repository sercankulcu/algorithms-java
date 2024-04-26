
public class InsertionSort {

	public static void main(String[] args) {

		int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
		System.out.println("Original Array: " + arrayToString(array));

		insertionSort(array);

		System.out.println("Sorted Array: " + arrayToString(array));
	}

	// Function to perform the Insertion Sort algorithm
	private static void insertionSort(int[] array) {
		int n = array.length;

		for (int i = 1; i < n; i++) {
			int key = array[i];
			int j = i - 1;

			// Move elements greater than key to one position ahead of their current position
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}

			// Place the key at its correct position
			array[j + 1] = key;
			System.out.println("Array: " + arrayToString(array));
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
