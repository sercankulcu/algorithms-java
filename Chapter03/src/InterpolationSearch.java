/*
 * 
 * The main optimization of the Interpolation Search algorithm is its use of a heuristic approach to estimate the most likely 
 * position of the target element based on its value, rather than simply dividing the array in half as Binary Search does.
 * 
 * */

public class InterpolationSearch {

    // Function to perform Interpolation Search
    // Interpolation Search is an improvement over Binary Search for instances where
    // the values in the array are uniformly distributed.
    // The algorithm uses a heuristic approach to estimate the probable position of the element
    // based on the values of the elements at the low and high ends of the search range.
    public static int interpolationSearch(int[] arr, int x) {
        
        int n = arr.length;  // Length of the array
        int low = 0;         // Lower bound of the search range
        int high = n - 1;    // Upper bound of the search range

        // Interpolation formula to determine the probable position of the element
        // The formula estimates the position (pos) based on the value of the target element (x)
        // and the values at the low and high ends of the search range.
        while (low <= high && x >= arr[low] && x <= arr[high]) {
            // Position formula: pos = low + ( (x - arr[low]) * (high - low) ) / (arr[high] - arr[low])
            // It estimates the position of 'x' based on the relative position between arr[low] and arr[high]
            int pos = low + ((x - arr[low]) * (high - low)) / (arr[high] - arr[low]);
            
            // Print the current estimated position for debugging purposes
            System.out.println("offset = " + pos);

            // If the element is found at pos, return pos
            if (arr[pos] == x)
                return pos;

            // If x is less than the element at pos, search in the left half (adjust high)
            if (arr[pos] > x)
                high = pos - 1;

            // If x is greater than the element at pos, search in the right half (adjust low)
            else
                low = pos + 1;
        }

        // If the element is not found, return -1 indicating the element is absent
        return -1;
    }

    // Function to print the search result
    // If index is found, print the index of the element
    // Otherwise, print that the element is not found in the array
    public static void printSearchResult(int index, int key) {
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index);
        else
            System.out.println("Element " + key + " not found in the array");
    }

    // Main method to test the InterpolationSearch class
    public static void main(String[] args) {
        // Define a sorted array for searching
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Print the original sorted array
        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        // Perform Interpolation search for the target key
        int index = interpolationSearch(arr, key);
        
        // Print the result of the search (whether the element was found or not)
        printSearchResult(index, key);
    }
}
