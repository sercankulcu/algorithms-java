/*
 * 
 * The main optimization in the sentinel search is the avoidance of the boundary check (i < n - 1) on every iteration of the loop. 
 * By replacing the last element with the sentinel value, the search stops only when the sentinel value is found. 
 * This eliminates one comparison per iteration, particularly for large arrays.
 * 
 * */

public class SentinelLinearSearch {

    // Function to perform Sentinel Linear Search
    // This search algorithm uses a sentinel value (x) to reduce the number of comparisons during the search.
    public static int sentinelLinearSearch(int[] arr, int x) {

        int n = arr.length; // Get the length of the array

        // Save the last element and replace it with the sentinel value (x)
        int lastElement = arr[n - 1]; // Store the last element of the array temporarily
        arr[n - 1] = x; // Replace the last element with the sentinel value (x)

        int i = 0; // Initialize the index to 0 to start from the beginning of the array

        // Search from the beginning until the sentinel value (x) is found
        while (arr[i] != x) {
            System.out.println("offset = " + i); // Debugging: print the current index being checked
            i++; // Increment the index to check the next element
        }

        // Restore the last element of the array (restore the original array)
        arr[n - 1] = lastElement;

        // If the sentinel value (x) is found at index less than n-1, return its index
        // This ensures we don't mistakenly return the index of the sentinel element itself
        if (i < n - 1 || arr[n - 1] == x)
            return i; // Return the index where the element is found
        else
            return -1; // Return -1 if the element is not found in the array
    }

    // Function to print the result of the search
    // This function displays whether the element is found or not in the array.
    public static void printSearchResult(int index, int x) {
        if (index != -1)
            System.out.println("Element " + x + " found at index " + index); // If found, print the index
        else
            System.out.println("Element " + x + " not found in the array"); // If not found, print the message
    }

    // Main method to test the SentinelLinearSearch class
    // This function tests the search functionality with a given sorted array and target element.
    public static void main(String[] args) {
        // Define a sorted array to search
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 21; // Define the element to search for

        // Print the original array for the user
        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " "); // Print all elements in the array
        System.out.println();

        // Perform Sentinel Linear Search and print the result
        int index = sentinelLinearSearch(arr, key);
        printSearchResult(index, key); // Print the index or not-found message
    }
}
