/*
 * 
 * This Java program calculates the Fibonacci sequence in two different ways: iteratively and recursively. 
 * The main method compares the two approaches by printing the Fibonacci numbers for n = 45 and measuring 
 * the execution time for both methods. The Fibonacci sequence is calculated using a standard iterative 
 * approach and a recursive approach.
 * 
 * */

public class FibonacciSequence {

	public static void main(String[] args) {
		// Set the number of terms in the Fibonacci sequence to be printed
		int n = 45;

		// Measure the time taken for the iterative approach
		long startTime = System.currentTimeMillis();
		// Print Fibonacci sequence using the iterative method
		System.out.println("Fibonacci sequence (Iterative):");
		for (int i = 0; i < n; i++) {
			// Print each Fibonacci number calculated iteratively
			System.out.print(fibonacciIterative(i) + " ");
		}
		long endTime = System.currentTimeMillis();
		// Calculate and print the elapsed time for the iterative approach
		long elapsedTime = endTime - startTime;
		System.out.println();
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");

		// Measure the time taken for the recursive approach
		startTime = System.currentTimeMillis();
		// Print Fibonacci sequence using the recursive method
		System.out.println("\nFibonacci sequence (Recursive):");
		for (int i = 0; i < n; i++) {
			// Print each Fibonacci number calculated recursively
			System.out.print(fibonacciRecursive(i) + " ");
		}
		endTime = System.currentTimeMillis();
		// Calculate and print the elapsed time for the recursive approach
		elapsedTime = endTime - startTime;
		System.out.println("\nElapsed Time: " + elapsedTime + " milliseconds");
	}

	// Iterative approach to calculate the nth Fibonacci number
	private static int fibonacciIterative(int n) {
		// Return n if it's 0 or 1, as the first two Fibonacci numbers are defined as 0 and 1
		if (n <= 1) {
			return n;
		}

		// Initialize the first two Fibonacci numbers
		int prev = 0;
		int current = 1;

		// Iterate from 2 to n to calculate the Fibonacci numbers
		for (int i = 2; i <= n; i++) {
			// Calculate the next Fibonacci number
			int next = prev + current;
			// Update the previous and current Fibonacci numbers
			prev = current;
			current = next;
		}

		// Return the nth Fibonacci number
		return current;
	}

	// Recursive approach to calculate the nth Fibonacci number
	private static int fibonacciRecursive(int n) {
		// Base case: if n is 0 or 1, return n
		if (n <= 1) {
			return n;
		}
		// Recursive case: sum of the previous two Fibonacci numbers
		return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
	}
}
