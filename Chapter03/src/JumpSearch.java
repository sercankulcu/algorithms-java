/*
 * 
 * The main optimization in the Jump Search algorithm is the way it reduces the number of comparisons by dividing the array into smaller 
 * blocks and "jumping" over multiple elements in each step, rather than performing a linear search for each element. 
 * 
 * */

public class JumpSearch {

    // Function to perform Jump Search
    // Jump Search is an algorithm that works by dividing the list into smaller blocks and jumping
    // through the list in steps of block size. Once the block is found, it performs a linear search
    // within that block.
    public static int jumpSearch(int[] arr, int x) {
        
        int n = arr.length; // Get the length of the array
        int blockSize = (int) Math.sqrt(n); // Calculate the block size as the square root of the array length

        int step = blockSize; // Initialize the step size to the block size

        // Find the block where the key may reside
        int prev = 0; // Start with the first block
        while (arr[Math.min(step, n) - 1] < x) {
            // If the element at the end of the block is smaller than the target value, jump to the next block
            prev = step;
            step += blockSize; // Move to the next block
            if (prev >= n) // If we have reached the end of the array without finding the element, return -1
                return -1; 
        }

        // Perform linear search within the identified block
        while (arr[prev] < x) {
            System.out.println("offset = " + prev); // Debugging output to see the current index being checked
            prev++; // Move to the next element within the block
            if (prev == Math.min(step, n)) // If the end of the block is reached, the element is not present
                return -1; 
        }

        // If the element is found, return its index
        if (arr[prev] == x)
            return prev; // Return the index where the element is found
        else
            return -1; // If the element is not found, return -1
    }

    // Function to print the search result
    // If the index is found, print the index of the element
    // Otherwise, print that the element is not found in the array
    public static void printSearchResult(int index, int key) {
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index);
        else
            System.out.println("Element " + key + " not found in the array");
    }

    // Main method to test the JumpSearch class
    public static void main(String[] args) {
        // Define a sorted array for searching
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Print the original sorted array
        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        // Perform Jump search for the target key
        int index = jumpSearch(arr, key);
        
        // Print the result of the search (whether the element was found or not)
        printSearchResult(index, key);
    }
}
