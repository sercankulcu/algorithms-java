
public class ExponentialSearch {

    // Function to perform Exponential Search
    public static int exponentialSearch(int[] arr, int x) {
        int n = arr.length;

        // If the element is found at index 0, return 0
        if (arr[0] == x)
            return 0;

        // Find the range for binary search by doubling the index until arr[i] is greater than or equal to x
        int i = 1;
        while (i < n && arr[i] <= x)
            i *= 2;

        // Perform binary search in the found range
        return binarySearch(arr, x, i / 2, Math.min(i, n - 1));
    }

    // Function to perform Binary Search within a given range
    public static int binarySearch(int[] arr, int x, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If the element is found at mid, return mid
            if (arr[mid] == x)
                return mid;

            // If x is less than the element at mid, search in the left half
            if (arr[mid] > x)
                high = mid - 1;

            // If x is greater than the element at mid, search in the right half
            else
                low = mid + 1;
        }

        // If the element is not found, return -1
        return -1;
    }

    // Function to print the search result
    public static void printSearchResult(int index, int key) {
        if (index != -1)
            System.out.println("Element " + key + " found at index " + index);
        else
            System.out.println("Element " + key + " not found in the array");
    }

    // Main method to test ExponentialSearch class
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int key = 10;

        System.out.println("Original array:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        int index = exponentialSearch(arr, key);
        printSearchResult(index, key);
    }
}
