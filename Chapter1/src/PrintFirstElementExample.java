
public class PrintFirstElementExample {

	public static void main(String[] args) {
		// Create an array
		int[] array = {5, 8, 12, 4, 6, 10, 7, 2, 9, 3};

		// Check if the array is not empty
		if (array.length > 0) {
			// Print the first element
			System.out.println("The first element is: " + array[0]);
		} else {
			System.out.println("The array is empty.");
		}
	}
}
