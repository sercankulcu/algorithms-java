
public class InterpolationSearch {

	public static int interpolationSearch(int[] arr, int x) {
		
		int n = arr.length;
		int low = 0;
		int high = n - 1;

		// Interpolation formula to determine the probable position of the element
		while (low <= high && x >= arr[low] && x <= arr[high]) {
			int pos = low + ((x - arr[low]) * (high - low)) / (arr[high] - arr[low]);
			System.out.println("offset = " + pos);

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
		int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40};
		int key = 33;

		System.out.println("Original array:");
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();

		int index = interpolationSearch(arr, key);
		printSearchResult(index, key);
	}
}
