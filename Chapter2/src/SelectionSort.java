
public class SelectionSort {

	// Function to perform selection sort
	public static void selectionSort(int[] arr) {
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++) {
			// Find the minimum element in unsorted array
			int minIndex = i;
			for (int j = i+1; j < n; j++) {
				if (arr[j] < arr[minIndex])
					minIndex = j;
			}

			// Swap the found minimum element with the first element
			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
			printArray(arr);

		}
	}

	// Function to print the array
	public static void printArray(int[] arr) {
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Main method to test SelectionSort class
	public static void main(String[] args) {
		int[] arr = {64, 25, 12, 22, 11, 7, 6, 8};
		System.out.println("Original array:");
		printArray(arr);

		selectionSort(arr);

		System.out.println("Sorted array:");
		printArray(arr);
	}
}
