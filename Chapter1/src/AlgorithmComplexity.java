import java.util.Random;

public class AlgorithmComplexity {

	// Size of the array used in the example
	final static int SIZE = 100000000;

	// Linear Search function to find an element in an array
	public static int linearSearch(int[] array, int target) {
		// Iterate through the array to check if the target exists
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i; // Return the index if the target is found
			}
		}
		// Return -1 if the target is not found in the array
		return -1;
	}

	public static void main(String[] args) {
		// Create a random number generator
		Random random = new Random();

		// Create an array of SIZE elements and populate it with random numbers
		int[] array = new int[SIZE];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(10000); // Generate a random number between 0 and 9999
		}

		// Set the target number that will be searched in the array
		int target = 10001;

		// Record the start time for the linear search operation
		long startTime = System.currentTimeMillis();

		// Perform the linear search for the target in the array
		linearSearch(array, target);

		// Record the end time after the linear search is complete
		long endTime = System.currentTimeMillis();

		// Calculate and display the elapsed time for the linear search
		long elapsedTime = endTime - startTime;
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");

		// Print the results of calling each function with an input of 32
		System.out.println("    O(n): " + f1(16) + "\t" + f1(32));
		System.out.println("  O(n^3): " + f2(16) + "\t" + f2(32));
		System.out.println("  O(2^n): " + f3(16) + "\t" + f3(32));
		System.out.println("    O(n): " + f4(16) + "\t" + f4(32));
		System.out.println("O(nlogn): " + f5(16) + "\t" + f5(32));
		System.out.println(" O(logn): " + f6(16) + "\t" + f6(32));
	}

	// f1 demonstrates linear time complexity: O(n)
	public static int f1(int n) {
		int x = 0;
		// Iterate n times and increment x in each iteration (linear complexity)
		for (int i = 0; i < n; i++) {
			x++;
		}
		return x;
	}

	// f2 demonstrates quadratic time complexity: O(n^3) 
	public static int f2(int n) {
		int x = 0;
		// Iterate n times and for each i, run an inner loop that runs i*i times (quadratic complexity)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i * i; j++) {
				x++;
			}
		}
		return x;
	}

	// f3 demonstrates exponential time complexity: O(2^n)
	public static long f3(int n) {
		// Base case: If n is less than or equal to 1, return 1
		if (n <= 1) {
			return 1;
		}
		// Otherwise, recursively call f3 on (n-1) and add the results (exponential complexity)
		return f3(n - 1) + f3(n - 1);
	}

	// f4 demonstrates logarithmic time complexity: O(log n)
	public static int f4(int n) {
		// Base case: If n is less than or equal to 1, return 1
		if (n <= 1) {
			return 1;
		}
		// Otherwise, recursively call f4 on (n / 2) twice (logarithmic complexity)
		return f4(n / 2) + f4(n / 2);
	}

	// f5 demonstrates logarithmic complexity with additional linear complexity: O(n + log n)
	public static int f5(int n) {
		// Base case: If n is less than or equal to 1, return 1
		if (n <= 1) {
			return 1;
		}
		// Call f1 (linear) and recursively call f5 with n / 2 twice (logarithmic complexity)
		return f1(n) + f5(n / 2) + f5(n / 2);
	}

	// f6 demonstrates logarithmic growth in a different form: O(log n)
	public static int f6(int n) {
		int x = 0;
		// Use bit shifting (i = 1 << i is the same as i = 2^i)
		// The loop will run until i reaches or exceeds n (logarithmic complexity)
		for (int i = 0; i < n; i = 1 << i) {
			x++;
		}
		return x;
	}
}
