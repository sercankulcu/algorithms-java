
public class PowerExample {

	public static void main(String[] args) {
		// Example usage
		int base = 2;
		int exponent = 62;

		// Iterative approach
		long iterativeResult = powerIterative(base, exponent);
		System.out.println(base + " raised to the power " + exponent + " (Iterative): " + iterativeResult);

		// Recursive approach
		long recursiveResult = powerRecursive(base, exponent);
		System.out.println(base + " raised to the power " + exponent + " (Recursive): " + recursiveResult);

		// Logarithmic approach
		long logarithmicResult = powerLogarithmic(base, exponent);
		System.out.println(base + " raised to the power " + exponent + " (Logarithmic): " + logarithmicResult);
	}

	// Iterative approach to calculate power
	private static long powerIterative(int base, int exponent) {
		long result = 1;

		for (int i = 0; i < exponent; i++) {
			result *= base;
		}

		return result;
	}

	// Recursive approach to calculate power
	private static long powerRecursive(int base, int exponent) {
		if (exponent == 0) {
			return 1;
		} else {
			return base * powerRecursive(base, exponent - 1);
		}
	}

	// Logarithmic approach to calculate power
	private static long powerLogarithmic(int base, int exponent) {
		if (exponent == 0) {
			return 1;
		}

		long halfPower = powerLogarithmic(base, exponent / 2);

		if (exponent % 2 == 0) {
			// Even exponent
			return halfPower * halfPower;
		} else {
			// Odd exponent
			return base * halfPower * halfPower;
		}
	}
}
