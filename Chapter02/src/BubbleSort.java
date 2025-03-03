import java.util.Arrays;
import java.util.Random;

/*
 * 
 * Bubble Sort is a simple sorting algorithm that compares adjacent elements and swaps them if needed.
 * It continues until no more swaps are required, indicating that the array is sorted.
 * 
*/

public class BubbleSort {

    public static void main(String[] args) {
        // Generate a random array with 16 elements and values ranging from 0 to 999
        int[] array = generateRandomArray(16, 100);

        // Print the original unsorted array for reference
        System.out.println("Original Array: " + Arrays.toString(array));

        // Call the bubbleSort function to sort the array
        bubbleSort(array);

        // Print the sorted array to confirm the sorting was successful
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    // Function to perform the Bubble Sort algorithm
    // Bubble Sort works by repeatedly stepping through the list, comparing adjacent elements,
    // and swapping them if they are in the wrong order. This process continues until the array is sorted.
    private static void bubbleSort(int[] array) {
        int n = array.length;  // Get the length of the array
        boolean swapped;  // Boolean flag to check if any swaps occurred during a pass

        // Outer loop: Iterate over the array to ensure all elements are sorted
        for (int i = 0; i < n - 1; i++) {
            swapped = false;  // Reset the swapped flag at the beginning of each pass

            // Inner loop: Compare adjacent elements and swap if needed
            // Reduce the range of the inner loop by 'i' since the last 'i' elements are already sorted
            for (int j = 0; j < n - i - 1; j++) {
                // If the current element is greater than the next, swap them
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);  // Swap the elements at indices j and j+1
                    swapped = true;  // Mark that a swap has occurred
                    // Print the array state after each swap for debugging
                    System.out.println("Array after swap: " + Arrays.toString(array));
                }
            }

            // If no swaps occurred during this pass, the array is already sorted
            if (!swapped) {
                break;  // Exit the loop early to optimize performance
            }
        }
    }

    // Function to swap two elements in the array
    // This utility function exchanges the elements at indices i and j
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];  // Temporarily store the value at index i
        array[i] = array[j];  // Assign the value at index j to index i
        array[j] = temp;      // Assign the stored value to index j
    }

    // Function to generate a random array
    // This function creates an array of the specified size, filled with random integers
    // ranging from 0 to (bound - 1)
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random();  // Create an instance of the Random class
        int[] array = new int[size];  // Initialize an array with the given size
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);  // Generate a random integer between 0 and bound
        }
        return array;  // Return the generated array
    }
}
