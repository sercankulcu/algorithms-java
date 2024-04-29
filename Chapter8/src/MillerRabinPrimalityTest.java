
import java.util.Random;

public class MillerRabinPrimalityTest {

	// Function to perform the Miller-Rabin primality test
	static boolean isPrime(int n, int k) {
		// Corner cases
		if (n <= 1 || n == 4) return false;
		if (n <= 3) return true;

		// Find r such that n = 2^d * r + 1 for some r >= 1
		int d = n - 1;
		while (d % 2 == 0)
			d /= 2;

		// Witness loop
		for (int i = 0; i < k; i++) {
			if (!witness(n, d))
				return false;
		}
		return true;
	}

	// Function to test a witness to the primality of n
	static boolean witness(int n, int d) {
		Random random = new Random();
		int a = 2 + random.nextInt(n - 4); // Random integer in the range [2, n - 2]
		int x = power(a, d, n); // Compute a^d mod n

		if (x == 1 || x == n - 1)
			return true;

		while (d != n - 1) {
			x = (x * x) % n;
			d *= 2;

			if (x == 1) return false;
			if (x == n - 1) return true;
		}
		return false;
	}

	// Function to compute (base^exponent) % modulus
	static int power(int base, int exponent, int modulus) {
		int result = 1;
		base %= modulus;

		while (exponent > 0) {
			if (exponent % 2 == 1)
				result = (result * base) % modulus;

			exponent >>= 1;
		base = (base * base) % modulus;
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 61; // Number to test for primality
		int k = 5; // Number of iterations for the test

		if (isPrime(n, k))
			System.out.println(n + " is likely prime.");
		else
			System.out.println(n + " is composite.");
	}
}
