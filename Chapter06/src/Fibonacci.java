public class Fibonacci {

    // Function to calculate the nth Fibonacci number using dynamic programming
    public static int fibonacci(int n) {
        // Create an array to store Fibonacci numbers up to n
        int[] fib = new int[n + 1];

        // Base cases: Fibonacci(0) = 0 and Fibonacci(1) = 1
        fib[0] = 0; // The 0th Fibonacci number is 0
        fib[1] = 1; // The 1st Fibonacci number is 1

        // Calculate Fibonacci numbers iteratively from 2 to n
        for (int i = 2; i <= n; i++) {
            // Fibonacci of i is the sum of the previous two Fibonacci numbers
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        // Return the nth Fibonacci number
        return fib[n];
    }

    public static void main(String[] args) {
        int n = 20; // The Fibonacci number to be calculated (in this case, the 10th Fibonacci number)

        // Call the fibonacci function to calculate the nth Fibonacci number
        for(int i = 1; i <= n; i++) {
        	// Print the nth Fibonacci number
        	System.out.println("Fibonacci(" + i + ") = " + fibonacci(i));
        }
    }
}
