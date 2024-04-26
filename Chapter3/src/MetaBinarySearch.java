
public class MetaBinarySearch {

	// Function to perform Binary Search on blocks
	public static int binarySearchOnBlocks(int[] arr, int key, int blockSize) {
		int n = arr.length;
		int blockIndex = 0;

		// Find the block where the key may reside
		while (blockIndex * blockSize < n && arr[blockIndex * blockSize] <= key)
			blockIndex++;

		// Perform Binary Search on the block
		int start = (blockIndex - 1) * blockSize;
		int end = Math.min(blockIndex * blockSize - 1, n - 1);
		return metaBinarySearch(arr, key, start, end);
	}

	// Function to perform Meta Binary Search within a range
	public static int metaBinarySearch(int[] arr, int key, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			System.out.println("offset = " + mid);

			// If key is found at mid, return mid
			if (arr[mid] == key)
				return mid;

			// If key is less than mid, search the left half
			else if (arr[mid] > key)
				end = mid - 1;

			// If key is greater than mid, search the right half
			else
				start = mid + 1;
		}

		// If key is not found, return -1
		return -1;
	}

	// Function to perform Meta Binary Search
	public static int metaBinarySearch(int[] arr, int key) {
		int blockSize = (int) Math.sqrt(arr.length);
		return binarySearchOnBlocks(arr, key, blockSize);
	}

	// Function to print the search result
	public static void printSearchResult(int index, int key) {
		if (index != -1)
			System.out.println("Element " + key + " found at index " + index);
		else
			System.out.println("Element " + key + " not found in the array");
	}

	// Main method to test MetaBinarySearch class
	public static void main(String[] args) {
		int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40};
		int key = 33;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = metaBinarySearch(arr, key);
		printSearchResult(index, key);
	}
}
