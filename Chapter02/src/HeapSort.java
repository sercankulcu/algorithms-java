import java.util.Arrays;
import java.util.Random;

/*
 * 
 * Heap Sort is a comparison-based sorting algorithm that uses the heap data structure. The algorithm works in two phases:
 * Heap Construction:
 * The input array is converted into a Max-Heap where the largest element is at the root.
 * Sorting:
 * The largest element (root) is swapped with the last element in the array.
 * The heap is then reduced in size, and the process is repeated until the array is sorted.
 * 
*/

public class HeapSort {

    /**
     * Function to perform Heap Sort.
     * Heap Sort is a comparison-based sorting algorithm that uses a binary heap structure.
     * It works in two phases:
     * 1. Build a Max-Heap from the input array.
     * 2. Extract elements from the heap one by one and place them in the sorted order.
     *
     * @param arr The array to be sorted.
     */
    public static void heapSort(int[] arr) {

        int n = arr.length;  // Length of the input array.

        // Build a Max-Heap from the array.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);  // Heapify each non-leaf node starting from the last level.
        }

        // Extract elements from the heap one by one.
        for (int i = n - 1; i > 0; i--) {
            // Move the root (largest element) to the end of the array.
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Print the array after each extraction (for debugging purposes).
            System.out.println(Arrays.toString(arr));

            // Rebuild the heap with the reduced size.
            heapify(arr, i, 0);
        }
    }

    /**
     * Function to heapify a subtree rooted at node `i`.
     * The heap property ensures that the root is larger than its children.
     *
     * @param arr The input array representing the heap.
     * @param n   The size of the heap.
     * @param i   The index of the root node of the subtree.
     */
    static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Initialize the largest element as the root.
        int left = 2 * i + 1;  // Index of the left child.
        int right = 2 * i + 2;  // Index of the right child.

        // Check if the left child exists and is greater than the root.
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if the right child exists and is greater than the largest so far.
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If the largest is not the root, swap it with the root.
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree.
            heapify(arr, n, largest);
        }
    }

    /**
     * Function to generate a random array for testing purposes.
     * This creates an array of specified size, filled with random integers
     * within the range [0, bound).
     *
     * @param size  The size of the array.
     * @param bound The upper limit for the random integers (exclusive).
     * @return The generated random array.
     */
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random();  // Create a Random object for generating random numbers.
        int[] array = new int[size];  // Initialize the array.
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);  // Generate random integers in the range [0, bound).
        }
        return array;  // Return the generated array.
    }

    /**
     * Main method to test the HeapSort implementation.
     * It generates a random array, sorts it using Heap Sort, and prints
     * the original and sorted arrays.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Generate a random array with 16 elements and values between 0 and 999.
        int[] array = generateRandomArray(16, 1000);

        // Print the original unsorted array.
        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        // Sort the array using Heap Sort.
        heapSort(array);

        // Print the sorted array.
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}
