public class IsPrimeNumber {

	// Function to check if a number is prime
	// A prime number is only divisible by 1 and itself, and it must be greater than 1
	public static boolean isPrime(int number) {
		// Check if the number is less than or equal to 1, as 0 and 1 are not prime numbers
		if (number <= 1) {
			return false; // 0 and 1 are not prime numbers
		}

		// Loop from 2 to the square root of the number
		// We only need to check divisors up to the square root, as factors beyond that would have already been discovered
		for (int i = 2; i <= Math.sqrt(number); i++) {
			// If the number is divisible by i, it is not prime
			if (number % i == 0) {
				return false; // The number is divisible by i, so it's not prime
			}
		}

		// If no divisors are found, the number is prime
		return true; // The number is prime
	}

	public static void main(String[] args) {
		// Define an array of test numbers to check for primality
		int[] testNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 50};

		// Loop through each test number
		for (int number : testNumbers) {
			// Call the isPrime function to check if the number is prime
			if (isPrime(number)) {
				// If the number is prime, print it is a prime number
				System.out.println(number + " is a prime number.");
			} else {
				// If the number is not prime, print it is not a prime number
				System.out.println(number + " is not a prime number.");
			}
		}
	}
}
