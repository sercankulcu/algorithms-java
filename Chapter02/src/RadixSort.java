import java.util.Arrays;
import java.util.Random;

/*
 * 
 * The Radix Sort algorithm sorts an array of non-negative integers by processing individual digits, 
 * starting from the least significant digit (units place) to the most significant digit.
 * 
 * */

public class RadixSort {

    // Function to perform counting sort based on the digit represented by exp
    private static int[] countingSort(int[] arr, int exp) {
        int n = arr.length; // Length of the array
        int[] output = new int[n]; // Output array to store sorted elements
        int[] count = new int[10]; // Count array to store occurrences of digits (0-9)

        // Store count of occurrences of each digit in count[]
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10; // Extract the digit at the current position (exp)
            count[digit]++; // Increment the count of the digit
        }

        // Modify count[] so that it contains the actual position of each digit in the output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1]; // Cumulative count
        }

        // Build the output array by placing elements in the correct position
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10; // Extract the digit
            output[count[digit] - 1] = arr[i]; // Place the element in the correct position
            count[digit]--; // Decrement the count for the digit
        }

        // Return the sorted array for the current digit
        return output;
    }

    // Function to perform radix sort
    public static int[] radixSort(int[] arr) {
        // Find the maximum number in the array to determine the number of digits
        int max = getMax(arr);

        // Perform counting sort for each digit, starting from the least significant digit
        // exp represents the current digit position (1 for units, 10 for tens, etc.)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            arr = countingSort(arr, exp); // Sort based on the current digit
            System.out.println("After sorting by digit position (exp = " + exp + "):");
            System.out.println(Arrays.toString(arr)); // Print the array state after each pass
        }

        // Return the fully sorted array
        return arr;
    }

    // Function to find the maximum element in the array
    private static int getMax(int[] arr) {
        int max = arr[0]; // Assume the first element is the maximum
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) { // Update max if a larger element is found
                max = arr[i];
            }
        }
        return max; // Return the maximum value
    }

    // Helper function to generate an array of random integers
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random(); // Random number generator
        int[] array = new int[size]; // Create an array of the specified size
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound); // Generate random integers within the specified range
        }
        return array; // Return the generated array
    }

    // Main method to test the RadixSort class
    public static void main(String[] args) {
        // Generate a random array of 16 integers with values ranging from 0 to 999
        int[] array = generateRandomArray(16, 1000);
        
        // Print the original (unsorted) array
        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        // Perform radix sort on the array
        array = radixSort(array);

        // Print the fully sorted array
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}
