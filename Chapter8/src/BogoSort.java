
import java.util.Arrays;
import java.util.Random;

public class BogoSort {
	
	static Random random = new Random();

	// Function to check if an array is sorted
	static boolean isSorted(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1])
				return false;
		}
		return true;
	}

	// Function to shuffle the array randomly
	static void shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int randomIndex = random.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomIndex];
			array[randomIndex] = temp;
		}
	}

	// Function to perform Bogo Sort
	static void bogoSort(int[] array) {
		while (!isSorted(array)) {
			shuffle(array);
			System.out.println(Arrays.toString(array));
		}
	}

	public static void main(String[] args) {
		int[] array = {5, 2, 7, 1, 3}; // Example array
		// Perform Bogo Sort
		bogoSort(array);
	}
}
