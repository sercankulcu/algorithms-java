
public class LinearSearchExample {

	// Function to perform linear search
	public static int linearSearch(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i; // Return the index if the target is found
			}
		}
		return -1; // Return -1 if the target is not found in the array
	}

	public static void main(String[] args) {
		int[] array = {10, 5, 7, 2, 8, 4, 1};
		int target = 8;

		// Perform linear search
		int index = linearSearch(array, target);

		// Display the result
		if (index != -1) {
			System.out.println("Target " + target + " found at index " + index);
		} else {
			System.out.println("Target " + target + " not found in the array");
		}
	}
}
