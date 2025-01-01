import java.util.Arrays;
import java.util.Random;

/*
 * 
 * Counting Sort is a non-comparison-based sorting algorithm that works efficiently for small ranges of integers. 
 * It uses a count array to store the frequency of each value, modifies it to store cumulative counts, and uses 
 * this information to place elements in their correct positions.
 * 
 * */

public class CountingSort {

    /**
     * Function to perform the Counting Sort algorithm.
     * Counting Sort is a non-comparison-based sorting algorithm that works
     * efficiently for small ranges of integers. It counts the occurrences of each element
     * in the input array and uses this information to place elements in the correct order.
     *
     * @param arr The input array to be sorted.
     * @return The sorted array.
     */
    public static int[] countingSort(int[] arr) {

        int n = arr.length;  // Length of the input array.

        // Find the maximum element in the array to determine the size of the count array.
        int max = findMax(arr);

        // Create a count array to store the occurrences of each element.
        int[] count = new int[max + 1];

        // Store the count of each element in the count array.
        countElements(arr, count);

        // Modify the count array to store the cumulative count, which helps determine
        // the position of each element in the sorted array.
        modifyCountArray(count);

        // Create an output array to store the sorted result.
        int[] output = new int[n];

        // Build the sorted output array using the count array.
        buildOutputArray(arr, count, output);

        return output;  // Return the sorted array.
    }

    /**
     * Function to find the maximum element in the input array.
     * This is used to determine the range of values for the count array.
     *
     * @param arr The input array.
     * @return The maximum value in the array.
     */
    private static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;  // Initialize to the smallest possible integer.
        for (int value : arr) {
            if (value > max) {  // Update max if a larger value is found.
                max = value;
            }
        }
        return max;
    }

    /**
     * Function to count the occurrences of each element in the input array.
     * The count array keeps track of how many times each value appears.
     *
     * @param arr   The input array.
     * @param count The count array to store occurrences of each element.
     */
    private static void countElements(int[] arr, int[] count) {
        for (int value : arr) {
            count[value]++;  // Increment the count for the corresponding value.
        }
    }

    /**
     * Function to modify the count array to store cumulative counts.
     * This allows the count array to reflect the actual positions of elements
     * in the sorted array.
     *
     * @param count The count array to modify.
     */
    private static void modifyCountArray(int[] count) {
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i - 1];  // Add the previous count to the current one.
        }
    }

    /**
     * Function to build the output array using the modified count array.
     * This places each element of the input array in its correct sorted position.
     *
     * @param arr    The input array.
     * @param count  The modified count array.
     * @param output The output array to store the sorted elements.
     */
    private static void buildOutputArray(int[] arr, int[] count, int[] output) {
        for (int i = arr.length - 1; i >= 0; --i) {  // Traverse the input array in reverse.
            output[count[arr[i]] - 1] = arr[i];  // Place the element in its correct position.
            count[arr[i]]--;  // Decrement the count for the placed element.
        }
    }

    /**
     * Function to generate a random array for testing.
     * This creates an array of the specified size, filled with random integers
     * ranging from 0 to (bound - 1).
     *
     * @param size  The size of the array to generate.
     * @param bound The upper limit for the random integers (exclusive).
     * @return The generated random array.
     */
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random();  // Create a Random object to generate random numbers.
        int[] array = new int[size];  // Initialize the array with the given size.
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);  // Generate a random integer between 0 and bound.
        }
        return array;  // Return the generated array.
    }

    /**
     * Main method to test the CountingSort class.
     * It generates a random array, sorts it using Counting Sort, and prints
     * the original and sorted arrays.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Generate a random array with 16 elements, values between 0 and 999.
        int[] array = generateRandomArray(16, 1000);

        // Print the original unsorted array.
        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        // Sort the array using Counting Sort.
        array = countingSort(array);

        // Print the sorted array.
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}
