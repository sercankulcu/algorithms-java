package sort;

import java.util.Arrays;

public class LexicographicOrder {

	// Sort an array of strings in lexicographic order
	public static void lexicographicSort(String[] arr) {
		int n = arr.length;

		// Use bubble sort algorithm
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				// Compare strings lexicographically
				if (arr[i].compareTo(arr[j]) > 0) {
					// Swap if the current string is greater than the next one
					String temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}

	public static void main(String[] args) {
		//String[] strings = {"banana", "orange", "grape", "apple", "kiwi"};
		String[] strings = {"eabcdf", "abcde", "bcdea", "cdeab", "deabcg"};

		System.out.println(Arrays.toString(strings));
		// Sort the array in lexicographic order
		lexicographicSort(strings);
	}
}
