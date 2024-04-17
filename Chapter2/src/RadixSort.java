
public class RadixSort {

	// Function to perform counting sort based on the digit represented by exp
	private static void countingSort(int[] arr, int exp) {
		int n = arr.length;
		int[] output = new int[n]; // Output array
		int[] count = new int[10]; // Count array to store the count of each digit

		// Store count of occurrences in count[]
		for (int i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;

		// Modify count[] to store the actual position of each digit in output[]
		for (int i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (int i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		// Copy the output array to arr[] so that arr[] now contains sorted numbers based on digit at exp
		for (int i = 0; i < n; i++)
			arr[i] = output[i];
	}

	// Function to perform radix sort
	public static void radixSort(int[] arr) {
		int max = getMax(arr); // Find the maximum number in the array

		// Perform counting sort for every digit. Note that instead of passing digit number, exp is passed.
		// exp is 10^i where i is the current digit being considered
		for (int exp = 1; max / exp > 0; exp *= 10)
			countingSort(arr, exp);
	}

	// Function to find the maximum element in the array
	private static int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}

	// Function to print the array
	public static void printArray(int[] arr) {
		for (int value : arr)
			System.out.print(value + " ");
		System.out.println();
	}

	// Main method to test RadixSort class
	public static void main(String[] args) {
		int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
		System.out.println("Original array:");
		printArray(arr);

		radixSort(arr);

		System.out.println("Sorted array:");
		printArray(arr);
	}
}
