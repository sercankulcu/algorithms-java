import java.util.Arrays;
import java.util.Random;

/*
 *  It works by iteratively taking one element (key) from the unsorted portion of the array and inserting it 
 *  into its correct position in the sorted portion of the array. The process continues until all elements are sorted.
 *  
 *  */

public class InsertionSort {

    public static void main(String[] args) {
        // Generate a random array of integers for testing
        int[] array = generateRandomArray(16, 1000); 
        System.out.println("Original Array: " + Arrays.toString(array));

        // Sort the array using the Insertion Sort algorithm
        insertionSort(array);

        // Print the sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    /**
     * Function to perform the Insertion Sort algorithm.
     * Insertion Sort works by building a sorted portion of the array element by element.
     * Each new element is "inserted" into its correct position within the sorted portion.
     *
     * @param array The array to be sorted.
     */
    private static void insertionSort(int[] array) {
        int n = array.length; // Length of the input array.

        // Iterate through the array starting from the second element (index 1).
        for (int i = 1; i < n; i++) {
            int key = array[i]; // Store the current element as the "key".
            int j = i - 1; // Initialize `j` as the index of the previous element.

            // Shift elements of the sorted portion (to the left of `i`) that are greater
            // than the key one position to the right to make space for the key.
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]; // Move the element one position to the right.
                j--; // Move to the next element in the sorted portion.
            }

            // Insert the key at its correct position in the sorted portion.
            array[j + 1] = key;

            // Print the array after each iteration for visualization/debugging purposes.
            System.out.println("After iteration " + i + ": " + Arrays.toString(array));
        }
    }

    /**
     * Function to generate a random array of integers for testing purposes.
     * 
     * @param size  The size of the array.
     * @param bound The upper limit for the random integers (exclusive).
     * @return The generated random array.
     */
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random(); // Create a Random object for generating random numbers.
        int[] array = new int[size]; // Initialize the array with the specified size.
        
        // Populate the array with random integers in the range [0, bound).
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound); 
        }

        return array; // Return the generated array.
    }
}
