
public class BinarySearch {

	// Function to perform binary search
	public static int binarySearch(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			System.out.println("mid: " + array[mid]);

			if (array[mid] == target) {
				return mid; // Return the index if the target is found
			} else if (array[mid] < target) {
				left = mid + 1; // Search in the right half
			} else {
				right = mid - 1; // Search in the left half
			}
		}

		return -1; // Return -1 if the target is not found in the array
	}

	public static void main(String[] args) {
		// Sorted array for binary search
		int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		// Target value to search for
		int target = 7;

		// Perform binary search
		int index = binarySearch(sortedArray, target);

		// Display the result
		if (index != -1) {
			System.out.println("Target " + target + " found at index " + index);
		} else {
			System.out.println("Target " + target + " not found in the array");
		}
	}
}
