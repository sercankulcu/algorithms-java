
import java.util.Arrays;

public class FibonacciSearch {

	// Function to perform Fibonacci Search
	public static int fibonacciSearch(int[] arr, int x) {
		int n = arr.length;

		// Initialize Fibonacci numbers
		int fib2 = 0; // (m-2)'th Fibonacci Number
		int fib1 = 1; // (m-1)'th Fibonacci Number
		int fib = fib1 + fib2; // m'th Fibonacci Number

		// Find the smallest Fibonacci Number greater than or equal to n
		while (fib < n) {
			fib2 = fib1;
			fib1 = fib;
			fib = fib1 + fib2;
		}

		// Marks the eliminated range from front
		int offset = -1;

		// while there are elements to be inspected
		// Note that we compare arr[fib2] with x.
		// When fib becomes 1, fib2 becomes 0
		while (fib > 1) {
			// Check if fib2 is a valid index
			int i = Math.min(offset + fib2, n - 1);

			// If x is greater than the value at index fib2, cut the subarray from offset to i
			if (arr[i] < x) {
				fib = fib1;
				fib1 = fib2;
				fib2 = fib - fib1;
				offset = i;
			}

			// If x is less than the value at index fib2, cut the subarray after i+1
			else if (arr[i] > x) {
				fib = fib2;
				fib1 = fib1 - fib2;
				fib2 = fib - fib1;
			}

			// If x is found at index i
			else
				return i;
		}

		// Compare the last element with x
		if (fib1 == 1 && arr[offset + 1] == x)
			return offset + 1;

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

	// Main method to test FibonacciSearch class
	public static void main(String[] args) {
		int[] arr = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
		int key = 85;

		System.out.println("Original array:");
		System.out.println(Arrays.toString(arr));

		int index = fibonacciSearch(arr, key);
		printSearchResult(index, key);
	}
}
