package sort;

import java.util.Arrays;

public class LexicographicOrder {

    /**
     * Sort an array of strings in lexicographic (dictionary) order using Bubble Sort.
     * @param arr The array of strings to be sorted.
     */
    public static void lexicographicSort(String[] arr) {
        int n = arr.length;

        // Step 1: Bubble Sort algorithm to sort strings in lexicographic order.
        for (int i = 0; i < n - 1; i++) {
            // Step 2: Compare each pair of adjacent strings.
            for (int j = i + 1; j < n; j++) {
                // Step 3: Compare the strings lexicographically.
                if (arr[i].compareTo(arr[j]) > 0) {
                    // Step 4: Swap the strings if the current string is greater than the next one.
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    // Debug: Print the array after each swap to show intermediate results.
                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test case 1: Array of strings to be sorted lexicographically.
        // String[] strings = {"banana", "orange", "grape", "apple", "kiwi"};

        // Test case 2: Array of strings with more complex lexicographic order.
        String[] strings = {"eabcdf", "abcde", "bcdea", "cdeab", "deabcg"};

        // Step 5: Print the original array before sorting.
        System.out.println("Before sorting: " + Arrays.toString(strings));
        
        // Step 6: Sort the array in lexicographic order.
        lexicographicSort(strings);

        // Step 7: Print the sorted array after applying lexicographic sort.
        System.out.println("After sorting: " + Arrays.toString(strings));
    }
}
