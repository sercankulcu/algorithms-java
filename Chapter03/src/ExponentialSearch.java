/*
 * 
 * The main optimization of the Exponential Search algorithm lies in its efficient approach to narrowing down the 
 * search range by exponentially increasing the index value before performing a binary search within that range.
 * 
 * */

public class ExponentialSearch {

    // Function to perform Exponential Search
    // Exponential Search is efficient for searching in a sorted array
    // It first finds the range using exponential steps and then performs binary search in the range
    public static int exponentialSearch(int[] arr, int x) {
        
        int n = arr.length; // Length of the array

        // If the element is found at index 0, return 0 directly
        if (arr[0] == x)
            return 0;

        // Find the range for binary search by doubling the index (exponentially increasing) 
        // until arr[i] is greater than or equal to x
        int i = 1;
        while (i < n && arr[i] <= x) {
            i *= 2; // Exponentially increase i: 1, 2, 4, 8, 16, ...
            System.out.println("offset = " + i);
        }

        // Print the starting index for binary search (range start)
        System.out.println("start index = " + i / 2);

        // Perform binary search on the range [i/2, min(i, n-1)]
        return binarySearch(arr, x, i / 2, Math.min(i, n - 1));
    }

    // Function to perform Binary Search within a given range [low, high]
    // This is used after finding a suitable range using Exponential Search
    public static int binarySearch(int[] arr, int x, int low, int high) {
        // While the low index is less than or equal to the high index
        while (low <= high) {
            // Calculate the middle index of the current range
            int mid = low + (high - low) / 2;

            // If the element is found at mid, return mid
            if (arr[mid] == x)
                return mid;

            // If x is smaller than the element at mid, search the left half of the array
            if (arr[mid] > x)
                high = mid - 1;

            // If x is larger than the element at mid, search the right half of the array
            else
                low = mid + 1;
        }

        // If the element is not found, return -1
        return -1;
    }

    // Function to print the search result (either index or not found message)
    public static void printSearchResult(int index, int key) {
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index);
        else
            System.out.println("Element " + key + " not found in the array");
    }

    // Main method to test the ExponentialSearch class
    public static void main(String[] args) {
        // Define a sorted array for searching
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Print the original sorted array
        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        // Perform exponential search for the target key
        int index = exponentialSearch(arr, key);
        // Print the result of the search (found index or not found message)
        printSearchResult(index, key);
    }
}
