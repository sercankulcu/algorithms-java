/*
 * 
 * The main optimization of the MetaBinarySearch algorithm is the block search strategy combined with binary search. 
 * This hybrid approach first narrows down the potential search range by dividing the array into blocks and then uses binary 
 * search within the identified block to find the target element. 
 * 
 * */

public class MetaBinarySearch {

	// Function to perform Binary Search on blocks
	// This function first identifies a block where the element might be located, and then performs binary search on that block.
	public static int binarySearchOnBlocks(int[] arr, int key, int blockSize) {
		int n = arr.length;
		int blockIndex = 0;

		// Find the block where the key may reside by checking the first element of each block
		// The block size is used to divide the array into smaller blocks.
		while (blockIndex * blockSize < n && arr[blockIndex * blockSize] <= key) {
			blockIndex++; // Increment the block index to move to the next block
			System.out.println("offset = " + blockIndex * blockSize); 
		}

		// Define the range (start and end) for binary search within the found block
		int start = (blockIndex - 1) * blockSize; // Start index of the block
		int end = Math.min(blockIndex * blockSize - 1, n - 1); // End index of the block (or the last index of the array if within bounds)
		return metaBinarySearch(arr, key, start, end); // Perform binary search on the identified block
	}

	// Function to perform Meta Binary Search within a specified range (start to end)
	// This function is a standard binary search implementation within the given range.
	public static int metaBinarySearch(int[] arr, int key, int start, int end) {
		while (start <= end) {
			// Calculate the middle index of the current range
			int mid = (start + end) / 2;
			System.out.println("offset = " + mid); // Print the current middle index for debugging purposes

			// Check if the middle element is the target key
			if (arr[mid] == key)
				return mid; // Return the index if the key is found at mid

			// If the key is smaller than the element at mid, search in the left half
			else if (arr[mid] > key)
				end = mid - 1; // Move the end pointer to the left half

			// If the key is larger than the element at mid, search in the right half
			else
				start = mid + 1; // Move the start pointer to the right half
		}

		// If the key is not found, return -1
		return -1;
	}

	// Function to perform Meta Binary Search on the entire array
	// This function calculates the block size as the square root of the array length
	// and then uses binary search on the appropriate block.
	public static int metaBinarySearch(int[] arr, int key) {
		int blockSize = (int) Math.sqrt(arr.length); // Calculate block size as the square root of the array length
		return binarySearchOnBlocks(arr, key, blockSize); // Perform binary search on the identified block
	}

	// Function to print the search result
	// This function prints the result of the search (either the index or a not-found message).
	public static void printSearchResult(int index, int key) {
		if (index != -1)
			System.out.println("Element " + key + " found at index " + index); // If found, print the index
		else
			System.out.println("Element " + key + " not found in the array"); // If not found, print a message
	}

	// Main method to test the MetaBinarySearch class
	// This function tests the search functionality on a given sorted array and a target element.
	public static void main(String[] args) {
		// Define a sorted array to search
		int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
		int key = 46; // Define the element to search for

		System.out.println("Original array:");
		// Print the elements of the array for the user to see
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		// Perform the Meta Binary Search and print the result
		int index = metaBinarySearch(arr, key);
		printSearchResult(index, key); // Print the index or not-found message
	}
}
