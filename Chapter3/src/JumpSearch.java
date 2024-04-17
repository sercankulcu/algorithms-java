
public class JumpSearch {

	// Function to perform Jump Search
	public static int jumpSearch(int[] arr, int x) {
		int n = arr.length;
		int blockSize = (int) Math.sqrt(n); // Determine the block size

		int step = blockSize; // Initialize step size

		// Find the block where the key may reside
		int prev = 0;
		while (arr[Math.min(step, n) - 1] < x) {
			prev = step;
			step += blockSize;
			if (prev >= n)
				return -1; // Element not present in the array
		}

		// Perform linear search within the block
		while (arr[prev] < x) {
			prev++;
			if (prev == Math.min(step, n))
				return -1; // Element not present in the array
		}

		// If the element is found, return its index
		if (arr[prev] == x)
			return prev;
		else
			return -1; // Element not present in the array
	}

	// Function to print the search result
	public static void printSearchResult(int index, int key) {
		if (index != -1)
			System.out.println("Element " + key + " found at index " + index);
		else
			System.out.println("Element " + key + " not found in the array");
	}

	// Main method to test JumpSearch class
	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
		int key = 11;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = jumpSearch(arr, key);
		printSearchResult(index, key);
	}
}
