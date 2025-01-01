public class PrintFirstElement {

    public static void main(String[] args) {
        // Create an array of integers
        int[] array = {5, 8, 12, 4, 6, 10, 7, 2, 9, 3};

        // Check if the array is not empty to avoid ArrayIndexOutOfBoundsException
        if (array.length > 0) {
            // If the array is not empty, print the first element (index 0)
            System.out.println("The first element is: " + array[0]);
        } else {
            // If the array is empty, print a message indicating that
            System.out.println("The array is empty.");
        }
    }
}
