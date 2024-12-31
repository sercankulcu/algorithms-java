public class Permutations {

    static int stepCount = 0;  // Counter to track the number of permutations generated

    // Function to calculate the factorial of a number
    static int factorial(int n) {
        int result = 1;
        // Iteratively multiply result by each number from 1 to n
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;  // Return the factorial result
    }

    public static void main(String[] args) {
        // 2D array where each row represents a different input array
        int[][] arrays = {
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5, 6},
            {1, 2, 3, 4, 5, 6, 7},
            {1, 2, 3, 4, 5, 6, 7, 8}
        };

        // Loop through each array in the 2D array
        for (int i = 0; i < arrays.length; i++) {
            int[] array = arrays[i];  // Get the current array from the 2D array
            stepCount = 0;  // Reset step count for each array

            // Generate permutations for this array for sizes 1 to array.length
            for (int size = 1; size <= array.length; size++) {
                generateAllPermutations(array, size, 0);
            }
            
            // Print the result: step count vs. factorial
            System.out.println(String.format("Generated %6d permutations for input %d out of %5d (%.2f%%)", stepCount, array.length, factorial(array.length), (double) stepCount / factorial(array.length) * 100));

        }
    }

    // Function to generate all permutations of a specific size for an array
    private static void generateAllPermutations(int[] array, int size, int currentIndex) {
        // If the currentIndex reaches the specified size, we have a complete permutation
        if (currentIndex == size) {
            stepCount++;  // Increment the step count
            // Uncomment the following line to print the permutation
            //System.out.println(Arrays.toString(Arrays.copyOf(array, size)));
        } else {
            // Loop through the array, swapping elements to create permutations
            for (int i = currentIndex; i < array.length; i++) {
                // Swap elements at currentIndex and i
                swap(array, currentIndex, i);

                // Recursively generate permutations for the remaining elements
                generateAllPermutations(array, size, currentIndex + 1);

                // Backtrack: Swap elements back to their original positions
                swap(array, currentIndex, i);
            }
        }
    }

    // Function to swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];  // Store element at index i
        array[i] = array[j];  // Assign element at index j to index i
        array[j] = temp;  // Assign the stored element to index j
    }
}
