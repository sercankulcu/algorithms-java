import java.util.Arrays; // Importing the Arrays class to use its utility methods
import java.util.Random; // Importing the Random class for generating random numbers

public class BogoSort {

    // Creating a single Random object for generating random numbers
    static Random random = new Random();

    /**
     * Function to check if an array is sorted in ascending order.
     * 
     * @param array the array to be checked
     * @return true if the array is sorted, false otherwise
     */
    static boolean isSorted(int[] array) {
        // Iterate through the array to check if every element is less than or equal to the next one
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false; // Return false if any two consecutive elements are out of order
            }
        }
        return true; // Return true if the entire array is sorted
    }

    /**
     * Function to shuffle the array randomly.
     * 
     * @param array the array to be shuffled
     */
    static void shuffle(int[] array) {
        // Swap each element with another randomly chosen element
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length); // Generate a random index
            // Swap the current element with the randomly chosen one
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    /**
     * Function to perform Bogo Sort on the given array.
     * Bogo Sort is a highly inefficient sorting algorithm that shuffles the array
     * repeatedly until it is sorted.
     * 
     * @param array the array to be sorted
     */
    static void bogoSort(int[] array) {
        // Continue shuffling the array until it becomes sorted
        while (!isSorted(array)) {
            shuffle(array); // Shuffle the array
            System.out.println(Arrays.toString(array)); // Print the current state of the array
        }
    }

    /**
     * The main method, which serves as the entry point for the program.
     * Creates an example array and sorts it using Bogo Sort.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] array = {5, 2, 7, 1, 3}; // Example unsorted array
        // Perform Bogo Sort on the array
        bogoSort(array);
    }
}
