import java.util.Arrays;

/*
 * 
 * The main optimization of Fibonacci Search lies in its use of Fibonacci numbers to determine the search range, 
 * as opposed to other algorithms like Binary Search that divide the array into two halves. 
 * Fibonacci Search reduces the search range by selecting intervals that are based on Fibonacci numbers, 
 * which provides a more efficient approach for certain types of problems.
 * 
 * */

public class FibonacciSearch {

    // Function to perform Fibonacci Search
    // Fibonacci Search is an efficient searching algorithm that uses Fibonacci numbers
    // to divide the search range into smaller intervals and reduces the range step-by-step.
    public static int fibonacciSearch(int[] arr, int x) {
        
        int n = arr.length; // Length of the array

        // Initialize Fibonacci numbers
        int fib2 = 0; // (m-2)'th Fibonacci Number
        int fib1 = 1; // (m-1)'th Fibonacci Number
        int fib = fib1 + fib2; // m'th Fibonacci Number (initial value of Fibonacci series)

        // Find the smallest Fibonacci Number greater than or equal to n
        // This loop calculates Fibonacci numbers greater than or equal to the length of the array
        while (fib < n) {
            fib2 = fib1;
            fib1 = fib;
            fib = fib1 + fib2;
        }

        // Marks the eliminated range from the front of the array
        int offset = -1;

        // While there are elements to be inspected, check each subarray using Fibonacci numbers
        while (fib > 1) {
            // Determine the index to compare by taking the minimum of offset + fib2 and n-1
            // This ensures that the index doesn't go out of bounds
            int i = Math.min(offset + fib2, n - 1);
            
            // Print the current offset for debugging
            System.out.println("offset = " + i);

            // If x is greater than the value at index fib2, eliminate the subarray from offset to i
            if (arr[i] < x) {
                fib = fib1;
                fib1 = fib2;
                fib2 = fib - fib1;
                offset = i;
            }

            // If x is less than the value at index fib2, eliminate the subarray after i
            else if (arr[i] > x) {
                fib = fib2;
                fib1 = fib1 - fib2;
                fib2 = fib - fib1;
            }

            // If x is found at index i, return the index
            else
                return i;
        }

        // Compare the last element with x if fib1 is 1
        // This is the final check to ensure that the element is found at the last index
        if (fib1 == 1 && arr[offset + 1] == x)
            return offset + 1;

        // If the element is not found, return -1
        return -1;
    }

    // Function to print the search result
    public static void printSearchResult(int index, int key) {
        // If index is found (not -1), print the index of the element
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index);
        else
            System.out.println("Element " + key + " not found in the array");
    }

    // Main method to test FibonacciSearch class
    public static void main(String[] args) {
        // Define a sorted array for searching
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Print the original sorted array
        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        // Perform Fibonacci search for the target key
        int index = fibonacciSearch(arr, key);
        // Print the result of the search (found index or not found message)
        printSearchResult(index, key);
    }
}
