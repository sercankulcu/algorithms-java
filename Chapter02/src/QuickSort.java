import java.util.Arrays;
import java.util.Random;

/*
 * Breaks the array into smaller subarrays based on the pivot element, sorts them, and combines them.
 * 
 * */

public class QuickSort {

    public static void main(String[] args) {
        // Generate an array of 16 random integers with values ranging up to 1000
        int[] array = generateRandomArray(16, 1000);

        // Print the original (unsorted) array
        System.out.println("Original Array: " + Arrays.toString(array));

        // Perform quick sort on the array
        quickSort(array, 0, array.length - 1);

        // Print the sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    // Recursive function to perform the QuickSort algorithm
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            // All elements smaller than the pivot are moved to its left,
            // and all greater elements are moved to its right
            int pivotIndex = partition(array, low, high);
            System.out.println("Pivot: " + array[pivotIndex]);

            // Recursively sort the subarray to the left of the pivot
            quickSort(array, low, pivotIndex - 1);

            // Recursively sort the subarray to the right of the pivot
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Function to partition the array and return the pivot index
    private static int partition(int[] array, int low, int high) {
        // Choose the last element as the pivot
        int pivot = array[high];

        // Index of the smaller element
        int i = low - 1;

        // Iterate through the array from 'low' to 'high - 1'
        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (array[j] <= pivot) {
                // Increment the index of the smaller element
                i++;

                // Swap the current element with the element at index 'i'
                swap(array, i, j);

                // Print the array state after each swap for debugging/visualization
                System.out.println("Array after swapping: " + Arrays.toString(array));
            }
        }

        // Swap the pivot element with the element at index 'i + 1'
        swap(array, i + 1, high);

        // Print the array state after placing the pivot in its correct position
        System.out.println("Array after placing pivot: " + Arrays.toString(array));

        // Return the index of the pivot
        return i + 1;
    }

    // Function to swap two elements in the array
    private static void swap(int[] array, int i, int j) {
        // Temporary variable to hold one of the elements during the swap
        int temp = array[i];

        // Swap the elements
        array[i] = array[j];
        array[j] = temp;
    }

    // Helper function to generate an array of random integers
    public static int[] generateRandomArray(int size, int bound) {
        // Create an instance of the Random class for random number generation
        Random random = new Random();

        // Create an array of the specified size
        int[] array = new int[size];

        // Fill the array with random integers between 0 and the specified bound
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);
        }

        // Return the generated array
        return array;
    }
}
