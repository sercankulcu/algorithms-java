
public class Fibonacci {

	// Function to calculate the nth Fibonacci number using dynamic programming
	public static int fibonacci(int n) {
		// Create an array to store Fibonacci numbers
		int[] fib = new int[n + 1];

		// Base cases: Fibonacci(0) = 0 and Fibonacci(1) = 1
		fib[0] = 0;
		fib[1] = 1;

		// Calculate Fibonacci numbers iteratively
		for (int i = 2; i <= n; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}

		// Return the nth Fibonacci number
		return fib[n];
	}

	public static void main(String[] args) {
		int n = 10; // The Fibonacci number to be calculated

		// Calculate the nth Fibonacci number
		int fibN = fibonacci(n);

		// Print the nth Fibonacci number
		System.out.println("Fibonacci(" + n + ") = " + fibN);
	}
}
