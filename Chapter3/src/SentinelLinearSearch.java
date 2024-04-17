
public class SentinelLinearSearch {

	// Function to perform sentinel linear search
	public static int sentinelLinearSearch(int[] arr, int x) {
		int n = arr.length;

		// Save the last element and replace it with the sentinel value (x)
		int lastElement = arr[n - 1];
		arr[n - 1] = x;

		int i = 0;
		// Search from the beginning until the sentinel value (x) is found
		while (arr[i] != x)
			i++;

		// Restore the last element of the array
		arr[n - 1] = lastElement;

		// If the sentinel value (x) is found at index less than n-1, return its index
		if (i < n - 1 || arr[n - 1] == x)
			return i;
		else
			return -1; // Element not found
	}

	// Function to print the result of the search
	public static void printSearchResult(int index, int x) {
		if (index != -1)
			System.out.println("Element " + x + " found at index " + index);
		else
			System.out.println("Element " + x + " not found in the array");
	}

	// Main method to test SentinelLinearSearch class
	public static void main(String[] args) {
		int[] arr = {10, 20, 80, 30, 60, 50, 110, 100, 130, 170};
		int x = 110;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = sentinelLinearSearch(arr, x);
		printSearchResult(index, x);
	}
}
