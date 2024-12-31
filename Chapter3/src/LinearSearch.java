public class LinearSearch {

    // Function to perform Linear Search
    // Linear Search sequentially checks each element of the array to find the target element.
    public static int linearSearch(int[] array, int target) {
        
        // Iterate through the array, checking each element
        for (int i = 0; i < array.length; i++) {
            System.out.println("offset = " + i); // Print the current index being checked for debugging

            // If the current element matches the target, return its index
            if (array[i] == target) {
                return i; // Return the index if the target is found
            }
        }

        // If the target element is not found, return -1
        return -1; // Return -1 if the target is not found in the array
    }

    // Main method to test LinearSearch class
    public static void main(String[] args) {
        // Define a sorted array to perform the search on
        int[] arr = {2, 3, 4, 7, 8, 9, 10, 21, 22, 31, 32, 33, 37, 40, 42, 44, 45, 46, 47, 48};
        int key = 46; // Define the element to search for

        // Perform linear search to find the target in the array
        int index = linearSearch(arr, key);

        // Display the result of the search
        if (index != -1) {
            // If index is not -1, print the index where the target is found
            System.out.println("Target " + key + " found at index " + index);
        } else {
            // If index is -1, print that the target is not found in the array
            System.out.println("Target " + key + " not found in the array");
        }
    }
}
