import java.util.Arrays;
import java.util.Random;

public class QuickSortRandomPivot {

    static Random rand = new Random(); // Random number generator for selecting pivot

    /**
     * Performs Quick Sort on the given array within the specified range.
     * @param array The array to be sorted.
     * @param low The starting index of the range to sort.
     * @param high The ending index of the range to sort.
     */
    static void quickSort(int[] array, int low, int high) {
        if (low < high) { // Base case: single element or invalid range
            // Select a random pivot index and retrieve its value
            int randomIndex = getRandomIndex(low, high);
            int pivot = array[randomIndex];

            // Partition the array around the pivot
            int partitionIndex = partition(array, low, high, pivot);

            // Recursively sort the left and right sub-arrays
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    /**
     * Partitions the array around the given pivot value.
     * Moves elements smaller than the pivot to the left
     * and larger elements to the right.
     * @param array The array to partition.
     * @param low The starting index of the range.
     * @param high The ending index of the range.
     * @param pivot The pivot value.
     * @return The final position of the pivot element in the array.
     */
    static int partition(int[] array, int low, int high, int pivot) {
        // Move pivot element to the end of the range for simplicity
        swap(array, high, findIndex(array, pivot, low, high));
        int pivotIndex = low; // Pointer to place elements smaller than pivot

        // Traverse and rearrange elements
        for (int i = low; i < high; i++) {
            if (array[i] <= pivot) { // Elements <= pivot go to the left
                swap(array, i, pivotIndex);
                pivotIndex++;
            }
        }

        // Place the pivot element in its correct position
        swap(array, pivotIndex, high);
        return pivotIndex;
    }

    /**
     * Finds the index of the pivot value in the specified range of the array.
     * @param array The array to search.
     * @param pivot The pivot value.
     * @param low The starting index of the range.
     * @param high The ending index of the range.
     * @return The index of the pivot value.
     */
    static int findIndex(int[] array, int pivot, int low, int high) {
        for (int i = low; i <= high; i++) {
            if (array[i] == pivot) {
                return i;
            }
        }
        return -1; // Should not occur if the pivot exists
    }

    /**
     * Generates a random index between low and high (inclusive).
     * @param low The lower bound.
     * @param high The upper bound.
     * @return A random index within the specified range.
     */
    static int getRandomIndex(int low, int high) {
        return rand.nextInt(high - low + 1) + low;
    }

    /**
     * Swaps two elements in the array and prints the array (for debugging).
     * @param array The array containing the elements.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        // Print the current state of the array after the swap
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {12, 13, 14, 15, 2, 7, 1, 3, 4, 5, 5, 5, 9, 6, -1, -2, -3, -4}; // Example input array
        System.out.println("Original array: " + Arrays.toString(array));
        // Perform Quick Sort
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
