import java.util.Random;

public class ElapsedTimeNano {
	
	// Constant SIZE to define the number of iterations in the task
	final static int SIZE = 100000000;
	
	// Random object to generate random numbers
	static Random random = new Random();

	public static void main(String[] args) {
		// Record the start time in nanoseconds using System.nanoTime()
		long startTime = System.nanoTime();

		// Perform some operation or task (simulate some computation)
		performTask();

		// Record the end time in nanoseconds after the task has completed
		long endTime = System.nanoTime();

		// Calculate the elapsed time by subtracting startTime from endTime
		long elapsedTime = endTime - startTime;

		// Print the elapsed time in nanoseconds
		System.out.println("Elapsed Time: " + elapsedTime + " nanoseconds");
	}

	// Simulate a task by performing some operation that consumes time
	private static void performTask() {
		// Loop to simulate work by generating random numbers
		for (int i = 0; i < SIZE; i++) {
			// Generate a random integer between 0 and 99 (exclusive) to simulate work
			random.nextInt(100); // Adjust the bound as needed
		}
	}
}
