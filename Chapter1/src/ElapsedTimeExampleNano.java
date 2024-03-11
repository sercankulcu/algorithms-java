
public class ElapsedTimeExampleNano {

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
		for (int i = 0; i < 1000000; i++) {
			// Some operation
		}
	}
}
