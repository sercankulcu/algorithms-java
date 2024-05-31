package sort;

import java.util.Arrays;

public class RadixSort {

	// Radix Sort algorithm for sorting strings lexicographically
	public static void radixSort(String[] arr) {
		// Find the maximum length of strings in the array
		int maxLength = maxLength(arr);

		// Apply counting sort for each character from right to left
		for (int i = maxLength - 1; i >= 0; i--) {
			countingSort(arr, i);
			System.out.println(Arrays.toString(arr));
		}
	}

	// Find the maximum length of strings in the array
	private static int maxLength(String[] arr) {
		int maxLength = 0;
		for (String str : arr) {
			maxLength = Math.max(maxLength, str.length());
		}
		return maxLength;
	}

	// Counting Sort for a specific character position in the strings
	private static void countingSort(String[] arr, int pos) {
		int n = arr.length;
		int[] count = new int[256]; // Assuming ASCII characters

		// Count occurrences of characters at the specified position
		for (String str : arr) {
			int index = (pos < str.length()) ? (int) str.charAt(pos) : 0;
			count[index]++;
		}

		// Update count array to store the actual position of each character
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		// Create a temporary array to store sorted strings
		String[] temp = new String[n];
		for (int i = n - 1; i >= 0; i--) {
			String str = arr[i];
			int index = (pos < str.length()) ? (int) str.charAt(pos) : 0;
			temp[--count[index]] = str;
		}

		// Copy the sorted strings back to the original array
		System.arraycopy(temp, 0, arr, 0, n);
	}

	public static void main(String[] args) {
		//String[] strings = {"banana", "apple", "orange", "grape", "kiwi"};
		String[] strings = {"abcde", "bcdea", "cdeab", "deabcg", "eabcdf"};

		System.out.println(Arrays.toString(strings));
		// Sort the array using Radix Sort
		radixSort(strings);
	}
}
