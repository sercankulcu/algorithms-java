
public class CountingSort {

    // Function to perform counting sort
    public static void countingSort(int[] arr) {
        int n = arr.length;

        // Find the maximum element in the array to determine the size of count array
        int max = findMax(arr);

        // Create a count array to store the count of each element
        int[] count = new int[max + 1];

        // Store the count of each element in the count array
        countElements(arr, count);

        // Modify the count array to store the actual position of each element in the sorted array
        modifyCountArray(count);

        // Create a temporary array to store the sorted result
        int[] output = new int[n];

        // Build the output array
        buildOutputArray(arr, count, output);

        // Copy the sorted elements back to the original array
        copyArray(output, arr);
    }

    // Function to find the maximum element in the array
    private static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Function to count the occurrences of each element in the array
    private static void countElements(int[] arr, int[] count) {
        for (int value : arr) {
            count[value]++;
        }
    }

    // Function to modify the count array to store the actual position of each element in the sorted array
    private static void modifyCountArray(int[] count) {
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i - 1];
        }
    }

    // Function to build the output array using count array
    private static void buildOutputArray(int[] arr, int[] count, int[] output) {
        for (int i = arr.length - 1; i >= 0; --i) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
    }

    // Function to copy elements from one array to another
    private static void copyArray(int[] src, int[] dest) {
        for (int i = 0; i < src.length; ++i) {
            dest[i] = src[i];
        }
    }

    // Function to print the array
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Main method to test CountingSort class
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Original array:");
        printArray(arr);

        countingSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
