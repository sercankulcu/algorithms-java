import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class MergeSort {

    // Recursive task to sort subarrays in parallel
    static class MergeSortTask extends RecursiveTask<int[]> {

		private static final long serialVersionUID = 7163709881195328565L;
		private int[] array;
        
        // Constructor to initialize the task with the array to be sorted
        public MergeSortTask(int[] array) {
            this.array = array;
        }
        
        @Override
        protected int[] compute() {
            // Base case: If the array has only one element, it's already sorted
            if (array.length <= 1) {
                return array;
            }

            // Split the array into two halves
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            // Recursively sort both halves in parallel
            MergeSortTask leftTask = new MergeSortTask(left);
            MergeSortTask rightTask = new MergeSortTask(right);
            leftTask.fork();  // Start sorting left half in a separate thread
            rightTask.fork();  // Start sorting right half in a separate thread

            // Wait for the results of the left and right subarrays
            int[] leftSorted = leftTask.join();
            int[] rightSorted = rightTask.join();

            // Merge the two sorted halves
            return merge(leftSorted, rightSorted);
        }
        
        // Helper function to merge two sorted arrays
        private int[] merge(int[] left, int[] right) {
            int[] result = new int[left.length + right.length];
            int i = 0, j = 0, k = 0;
            
            // Merge the two arrays into the result
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    result[k++] = left[i++];
                } else {
                    result[k++] = right[j++];
                }
            }

            // If there are any remaining elements in the left array
            while (i < left.length) {
                result[k++] = left[i++];
            }

            // If there are any remaining elements in the right array
            while (j < right.length) {
                result[k++] = right[j++];
            }
            
            return result;
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

    public static void main(String[] args) {
        // Example array to be sorted
        int[] array = generateRandomArray(16, 1000);
        System.out.println("Original Array: " + Arrays.toString(array));

        // Create a ForkJoinPool to execute the tasks in parallel
        ForkJoinPool pool = new ForkJoinPool();
        
        // Create a MergeSortTask to sort the array in parallel
        MergeSortTask task = new MergeSortTask(array);
        
        // Invoke the parallel sort task and get the result
        int[] sortedArray = pool.invoke(task);
        
        // Output the sorted array
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
        
        pool.close();
    }
}
