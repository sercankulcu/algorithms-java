import java.util.Arrays;
import java.util.Random;

/*
 * 
 * The Shell Sort algorithm improves upon Insertion Sort by sorting elements that are far apart before progressively reducing 
 * the gap between them, allowing the algorithm to move elements closer to their correct positions more efficiently.
 * 
 * */

public class ShellSort {

    // Function to perform Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length; // Get the length of the array

        // Start with a large gap and reduce it after each iteration
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort on elements that are gap apart
            for (int i = gap; i < n; i++) {
                // Save the element at arr[i] in temp and make a hole at position i
                int temp = arr[i];

                // Shift elements that are gap apart until the correct location for arr[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap]; // Shift element to the right
                }

                // Put temp (the original arr[i]) in its correct location
                arr[j] = temp;

                // Print the array state after each pass for visualization
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    // Function to generate a random array of integers within a specified range
    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random(); // Create an instance of the Random class
        int[] array = new int[size]; // Initialize an array of the given size
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound); // Generate random integers between 0 and the bound
        }
        return array; // Return the generated array
    }

    // Main method to test ShellSort class
    public static void main(String[] args) {
        // Generate a random array with 16 elements, each between 0 and 999
        int[] array = generateRandomArray(16, 1000);

        // Print the original unsorted array
        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        // Sort the array using Shell Sort
        shellSort(array);

        // Print the sorted array after sorting is completed
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}
