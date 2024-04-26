
public class FibonacciExample {

	public static void main(String[] args) {

		int n = 45;

		long startTime = System.currentTimeMillis();
		// Iterative approach
		System.out.println("Fibonacci sequence (Iterative):");
		for (int i = 0; i < n; i++) {
			System.out.print(fibonacciIterative(i) + " ");
		}
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println();
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");

		startTime = System.currentTimeMillis();
		// Recursive approach
		System.out.println("Fibonacci sequence (Recursive):");
		for (int i = 0; i < n; i++) {
			System.out.print(fibonacciRecursive(i) + " ");
		}
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		System.out.println();
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
	}

	// Iterative approach to calculate the nth Fibonacci number
	private static int fibonacciIterative(int n) {
		if (n <= 1) {
			return n;
		}

		int prev = 0;
		int current = 1;

		for (int i = 2; i <= n; i++) {
			int next = prev + current;
			prev = current;
			current = next;
		}

		return current;
	}

	// Recursive approach to calculate the nth Fibonacci number
	private static int fibonacciRecursive(int n) {
		if (n <= 1) {
			return n;
		}
		return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
	}
}
