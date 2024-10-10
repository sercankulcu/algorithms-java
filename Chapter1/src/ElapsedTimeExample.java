
import java.util.Random;

public class ElapsedTimeExample {
	
	final static int SIZE = 500000000;
	static Random random = new Random();


	public static void main(String[] args) {
		// Start measuring time
		long startTime = System.currentTimeMillis();

		// Create a 100-element array and fill it with random values
		int[] array = new int[SIZE];
		fillArrayWithRandomValues(array);

		// Stop measuring time
		long endTime = System.currentTimeMillis();

		// Calculate elapsed time
		long elapsedTime = endTime - startTime;

		// Display the result
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
	}

	// Function to fill an array with random values
	private static void fillArrayWithRandomValues(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(100); // Adjust the bound as needed
		}
	}
}
