
public class LinearSearch {

	public static int linearSearch(int[] array, int target) {
		
		for (int i = 0; i < array.length; i++) {
			System.out.println("offset = " + i);
			if (array[i] == target) {
				return i; // Return the index if the target is found
			}
		}
		return -1; // Return -1 if the target is not found in the array
	}

	public static void main(String[] args) {
		int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40};
		int target = 33;

		// Perform linear search
		int index = linearSearch(arr, target);

		// Display the result
		if (index != -1) {
			System.out.println("Target " + target + " found at index " + index);
		} else {
			System.out.println("Target " + target + " not found in the array");
		}
	}
}
