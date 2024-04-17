
public class InterpolationSearch {

	// Function to perform Interpolation Search
	public static int interpolationSearch(int[] arr, int x) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;

		// Interpolation formula to determine the probable position of the element
		while (low <= high && x >= arr[low] && x <= arr[high]) {
			int pos = low + ((x - arr[low]) * (high - low)) / (arr[high] - arr[low]);

			// If the element is found at pos, return pos
			if (arr[pos] == x)
				return pos;

			// If x is less than the element at pos, search in the left half
			if (arr[pos] > x)
				high = pos - 1;

			// If x is greater than the element at pos, search in the right half
			else
				low = pos + 1;
		}

		// If the element is not found, return -1
		return -1;
	}

	// Function to print the search result
	public static void printSearchResult(int index, int key) {
		if (index != -1)
			System.out.println("Element " + key + " found at index " + index);
		else
			System.out.println("Element " + key + " not found in the array");
	}

	// Main method to test InterpolationSearch class
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		int key = 50;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = interpolationSearch(arr, key);
		printSearchResult(index, key);
	}
}
