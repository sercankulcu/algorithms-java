import java.util.Random;

public class ElapsedTimeExampleNano {
	
	final static int SIZE = 100000000;
	static Random random = new Random();

	public static void main(String[] args) {
		// Record the start time
		long startTime = System.nanoTime();

		// Perform some operation or task
		performTask();

		// Record the end time
		long endTime = System.nanoTime();

		// Calculate and print the elapsed time
		long elapsedTime = endTime - startTime;
		System.out.println("Elapsed Time: " + elapsedTime + " nanoseconds");
	}

	private static void performTask() {
		// Simulate some task
		for (int i = 0; i < SIZE; i++) {
			random.nextInt(100); // Adjust the bound as needed
		}
	}
}
