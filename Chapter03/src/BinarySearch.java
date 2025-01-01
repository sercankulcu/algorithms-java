public class BinarySearch {

    // Function to perform binary search on a sorted array
    // The binary search algorithm has O(log n) time complexity
    public static int binarySearch(int[] array, int target) {
        int left = 0; // Initialize the left boundary of the search range
        int right = array.length - 1; // Initialize the right boundary of the search range

        // Loop continues until the left boundary exceeds the right boundary
        while (left <= right) {
            // Calculate the middle index of the current range
            int mid = left + (right - left) / 2;

            // Print the value at the middle index for debugging purposes
            System.out.println("mid: " + array[mid]);

            // Check if the target value is at the middle index
            if (array[mid] == target) {
                return mid; // Return the index if the target is found
            } else if (array[mid] < target) {
                // If the target is greater than the middle element,
                // search in the right half of the array
                left = mid + 1;
            } else {
                // If the target is smaller than the middle element,
                // search in the left half of the array
                right = mid - 1;
            }
        }

        // If the target value is not found, return -1
        return -1;
    }

    public static void main(String[] args) {
        // Sorted array in which we will search for the target
    	int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Perform binary search and store the result (index of the target)
        int index = binarySearch(arr, key);

        // Display the result of the search
        if (index != -1) {
            System.out.println("Target " + key + " found at index " + index);
        } else {
            System.out.println("Target " + key + " not found in the array");
        }
    }
}
