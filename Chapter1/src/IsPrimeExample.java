
public class IsPrimeExample {

	// Function to check if a number is prime
	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false; // 0 and 1 are not prime numbers
		}

		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false; // The number is divisible by i, so it's not prime
			}
		}

		return true; // The number is prime
	}

	public static void main(String[] args) {
		// Test the isPrime function with some examples
		int[] testNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 50};

		for (int number : testNumbers) {
			if (isPrime(number)) {
				System.out.println(number + " is a prime number.");
			} else {
				System.out.println(number + " is not a prime number.");
			}
		}
	}
}
