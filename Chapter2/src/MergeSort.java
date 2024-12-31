import java.util.Arrays;
import java.util.Random;

/*
 * 
 * A recursive method that divides the array into two halves until the base case (single-element array) is reached.
 * Combines the sorted halves using the merge method.
 * 
*/

public class MergeSort {

    public static void main(String[] args) {
        // Generate an array of 16 random integers with values ranging up to 1000
        int[] array = generateRandomArray(16, 1000);
        
        // Print the original (unsorted) array
        System.out.println("Original Array: " + Arrays.toString(array));

        // Perform merge sort on the array
        mergeSort(array, 0, array.length - 1);

        // Print the sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    // Recursive function to perform the Merge Sort algorithm
    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Calculate the middle index of the current segment
            int mid = left + (right - left) / 2;

            // Recursively sort the left half of the array
            mergeSort(array, left, mid);

            // Recursively sort the right half of the array
            mergeSort(array, mid + 1, right);

            // Merge the sorted halves back together
            merge(array, left, mid, right);

            // Print the array after each merge operation for debugging/visualization
            System.out.println("Array after merging: " + Arrays.toString(array));
        }
    }

    // Function to merge two sorted subarrays of the original array
    private static void merge(int[] array, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold the data from the two subarrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data from the original array into the temporary left and right arrays
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Merge the two subarrays back into the original array

        // Initialize indices for left, right, and merged subarrays
        int i = 0, j = 0, k = left;

        // Compare elements from leftArray and rightArray, and copy the smaller one into the original array
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from leftArray (if any) into the original array
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray (if any) into the original array
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
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
