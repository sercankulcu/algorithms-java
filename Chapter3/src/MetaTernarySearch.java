/*
 * 
 * The main optimization of the MetaTernarySearch algorithm is the division of the array into three parts 
 * (instead of two, as in binary search) to potentially reduce the search space more efficiently with each iteration.
 * 
 * */

public class MetaTernarySearch {

    // Function to perform Meta Ternary Search
    // This function divides the array into three parts and applies ternary search recursively.
    public static int metaTernarySearch(int[] arr, int key) {
        int n = arr.length;
        int start = 0; // Start index of the array
        int end = n - 1; // End index of the array

        // Perform the search until the start index is less than or equal to the end index
        while (start <= end) {
            // Divide the array into three parts using two mid-points (mid1 and mid2)
            int mid1 = start + (end - start) / 3; // First third of the array
            int mid2 = end - (end - start) / 3; // Second third of the array

            // Print mid1 index for debugging purposes
            System.out.println("offset = " + mid1);
            // Check if the key is found at mid1
            if (arr[mid1] == key)
                return mid1; // If found, return the index

            // Print mid2 index for debugging purposes
            System.out.println("offset = " + mid2);
            // Check if the key is found at mid2
            if (arr[mid2] == key)
                return mid2; // If found, return the index

            // If the key is less than the element at mid1, search the left part of the array
            if (key < arr[mid1])
                end = mid1 - 1; // Adjust the end index to the left part

            // If the key is greater than the element at mid2, search the right part of the array
            else if (key > arr[mid2])
                start = mid2 + 1; // Adjust the start index to the right part

            // If the key lies between mid1 and mid2, search the middle part of the array
            else {
                start = mid1 + 1; // Adjust the start index to search between mid1 and mid2
                end = mid2 - 1; // Adjust the end index to search between mid1 and mid2
            }
        }

        // If the key is not found, return -1
        return -1;
    }

    // Function to print the search result
    // This function displays whether the element is found or not in the array.
    public static void printSearchResult(int index, int key) {
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index); // If found, print the index
        else
            System.out.println("Element " + key + " not found in the array"); // If not found, print the message
    }

    // Main method to test the MetaTernarySearch class
    // This function tests the search functionality with a given sorted array and target element.
    public static void main(String[] args) {
        // Define a sorted array to search
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Print the original array for the user
        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        // Perform Meta Ternary Search and print the result
        int index = metaTernarySearch(arr, key);
        printSearchResult(index, key); // Print the index or not-found message
    }
}
