
public class MetaTernarySearch {

	// Function to perform Meta Ternary Search
	public static int metaTernarySearch(int[] arr, int key) {
		int n = arr.length;
		int start = 0;
		int end = n - 1;

		while (start <= end) {
			// Divide the array into three parts
			int mid1 = start + (end - start) / 3;
			int mid2 = end - (end - start) / 3;

			// If key is found at mid1, return mid1
			if (arr[mid1] == key)
				return mid1;

			// If key is found at mid2, return mid2
			if (arr[mid2] == key)
				return mid2;

			// If key is less than mid1, search the left part
			if (key < arr[mid1])
				end = mid1 - 1;

			// If key is greater than mid2, search the right part
			else if (key > arr[mid2])
				start = mid2 + 1;

			// If key is between mid1 and mid2, search the middle part
			else {
				start = mid1 + 1;
				end = mid2 - 1;
			}
		}

		// If key is not found, return -1
		return -1;
	}

	// Function to print the search result
	public static void printSearchResult(int index, int key) {
		if (index != -1)
			System.out.println("Element " + key + " found at index " + index);
		else
			System.out.println("Element " + key + " not found in the array");
	}

	// Main method to test MetaTernarySearch class
	public static void main(String[] args) {
    int[] arr = {2, 3, 4, 10, 20, 21, 22, 23, 24, 25, 26, 40};
		int key = 10;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = metaTernarySearch(arr, key);
		printSearchResult(index, key);
	}
}
