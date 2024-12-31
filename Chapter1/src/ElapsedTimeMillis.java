import java.util.Random;

public class ElapsedTimeMillis {
	
	// Constant SIZE to define the number of elements in the array
	final static int SIZE = 500000000;

	// Random object to generate random numbers
	static Random random = new Random();

	public static void main(String[] args) {
		// Start measuring time (in milliseconds)
		long startTime = System.currentTimeMillis();

		// Create a new array of integers with SIZE elements
		int[] array = new int[SIZE];

		// Fill the array with random values
		fillArrayWithRandomValues(array);

		// Stop measuring time after the task is done
		long endTime = System.currentTimeMillis();

		// Calculate the elapsed time by subtracting start time from end time
		long elapsedTime = endTime - startTime;

		// Display the elapsed time in milliseconds
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
	}

	// Function to fill an array with random values between 0 and 99
	private static void fillArrayWithRandomValues(int[] array) {
		// Loop through the entire array and assign random values
		for (int i = 0; i < array.length; i++) {
			// Generate a random integer between 0 and 99 (exclusive)
			array[i] = random.nextInt(100); // Adjust the bound as needed
		}
	}
}
