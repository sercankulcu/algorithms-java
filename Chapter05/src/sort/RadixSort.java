package sort;

import java.util.Arrays;

public class RadixSort {

    /**
     * Radix Sort algorithm for sorting an array of strings lexicographically.
     * @param arr The array of strings to be sorted.
     */
    public static void radixSort(String[] arr) {
        // Step 1: Find the maximum length of strings in the array.
        int maxLength = maxLength(arr);

        // Step 2: Apply counting sort for each character from right to left.
        // This ensures that strings are sorted based on their individual characters.
        for (int i = maxLength - 1; i >= 0; i--) {
            countingSort(arr, i);  // Sort the strings based on character at position i.
            // Debug: Print the array after each pass to see intermediate results.
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Function to find the maximum length of strings in the array.
     * @param arr The array of strings.
     * @return The maximum length of the strings in the array.
     */
    private static int maxLength(String[] arr) {
        int maxLength = 0;
        // Iterate through the array to find the maximum string length.
        for (String str : arr) {
            maxLength = Math.max(maxLength, str.length());  // Update max length if a longer string is found.
        }
        return maxLength;
    }

    /**
     * Counting Sort algorithm for sorting strings based on a specific character position.
     * @param arr The array of strings to be sorted.
     * @param pos The position (index) of the character to sort by.
     */
    private static void countingSort(String[] arr, int pos) {
        int n = arr.length;
        int[] count = new int[256];  // Assuming ASCII characters (256 possible values).

        // Step 1: Count occurrences of characters at the specified position for all strings.
        for (String str : arr) {
            int index = (pos < str.length()) ? (int) str.charAt(pos) : 0;  // Default to 0 if pos exceeds string length.
            count[index]++;  // Increment the count of the character at the given position.
        }

        // Step 2: Update the count array to store the actual positions of characters.
        // The count array will now store the cumulative count of characters.
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];  // Accumulate the counts to get the final positions.
        }

        // Step 3: Create a temporary array to store the sorted strings.
        String[] temp = new String[n];
        // Traverse the array from right to left to maintain stability of the sort.
        for (int i = n - 1; i >= 0; i--) {
            String str = arr[i];
            int index = (pos < str.length()) ? (int) str.charAt(pos) : 0;  // Get the character at position pos.
            temp[--count[index]] = str;  // Place the string in its sorted position.
        }

        // Step 4: Copy the sorted strings back to the original array.
        System.arraycopy(temp, 0, arr, 0, n);  // Copy the sorted strings from temp back to arr.
    }

    public static void main(String[] args) {
        // Test case: Array of strings to be sorted.
        String[] strings = {"banana", "apple", "orange", "grape", "kiwi"};
        // Alternative test case with different strings.
        // String[] strings = {"abcde", "bcdea", "cdeab", "deabcg", "eabcdf"};

        // Print the original array before sorting.
        System.out.println("Before sorting: " + Arrays.toString(strings));
        
        // Step 5: Sort the array using Radix Sort.
        radixSort(strings);

        // Print the sorted array after applying Radix Sort.
        System.out.println("After sorting: " + Arrays.toString(strings));
    }
}
