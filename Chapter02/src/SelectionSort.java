import java.util.Arrays;
import java.util.Random;

/*
 * 
 * The Selection Sort algorithm sorts an array by repeatedly finding the smallest element from the unsorted part and moving it to the sorted part.
 * 
 * */

public class SelectionSort {

    // Function to perform selection sort
    public static void selectionSort(int[] arr) {
        int n = arr.length; // Get the length of the array

        // Traverse through the entire array
        for (int i = 0; i < n - 1; i++) {
            // Initialize the index of the minimum element
            int minIndex = i;

            // Find the smallest element in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) { // Compare current element with the minimum
                    minIndex = j; // Update the index of the minimum element
                }
            }

            // Swap the found minimum element with the first element of the unsorted part
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            // Print the array state after each iteration for debugging or analysis
            System.out.println("Array after pass " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    // Helper function to generate a random array of integers
    // This function creates an array of the specified size filled with random integers within a given range
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random(); // Random number generator
        int[] array = new int[size]; // Create an array of the specified size
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound); // Generate random integers between 0 and the bound
        }
        return array; // Return the generated array
    }

    // Main method to test the SelectionSort class
    public static void main(String[] args) {
        // Generate a random array of 16 integers with values ranging from 0 to 999
        int[] array = generateRandomArray(16, 1000);

        // Print the original unsorted array
        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        // Call the selectionSort method to sort the array
        selectionSort(array);

        // Print the sorted array
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}
