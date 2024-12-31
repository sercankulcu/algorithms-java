/*
 * 
 * Iterative vs Recursive vs Logarithmic: Three different approaches are shown to compute powers, with varying efficiencies and complexity:
 * Iterative: Repeatedly multiplies the base exponent times.
 * Recursive: Reduces the problem by one power at a time, computing recursively.
 * Logarithmic: Reduces the problem size by half at each step, which results in a more efficient solution for large exponents.
 * 
 * */

public class PowerExample {

    public static void main(String[] args) {
        // Example usage: base and exponent values
        int base = 2;
        int exponent = 62;

        // Iterative approach to calculate power
        long iterativeResult = powerIterative(base, exponent);
        System.out.println(base + " raised to the power " + exponent + " (Iterative  ): " + iterativeResult);

        // Recursive approach to calculate power
        long recursiveResult = powerRecursive(base, exponent);
        System.out.println(base + " raised to the power " + exponent + " (Recursive  ): " + recursiveResult);

        // Logarithmic approach to calculate power (more efficient for large exponents)
        long logarithmicResult = powerLogarithmic(base, exponent);
        System.out.println(base + " raised to the power " + exponent + " (Logarithmic): " + logarithmicResult);
    }

    // Iterative approach to calculate power: Multiplies the base 'exponent' times
    private static long powerIterative(int base, int exponent) {
        long result = 1;  // Initialize the result to 1 (the identity for multiplication)

        // Multiply base 'exponent' times in a loop
        for (int i = 0; i < exponent; i++) {
            result *= base;  // Multiply result by the base in each iteration
        }

        return result;  // Return the final result
    }

    // Recursive approach to calculate power: Base case is exponent == 0 (any number raised to power 0 is 1)
    private static long powerRecursive(int base, int exponent) {
        // Base case: If exponent is 0, return 1 (any number raised to the power 0 is 1)
        if (exponent == 0) {
            return 1;
        } else {
            // Recursive case: Multiply base with the result of power calculation for (exponent - 1)
            return base * powerRecursive(base, exponent - 1);
        }
    }

    // Logarithmic (Efficient) approach to calculate power: Reduces problem size by half in each recursive call
    private static long powerLogarithmic(int base, int exponent) {
        // Base case: If exponent is 0, return 1 (any number raised to the power 0 is 1)
        if (exponent == 0) {
            return 1;
        }

        // Calculate half power for the exponent divided by 2
        long halfPower = powerLogarithmic(base, exponent / 2);

        // If exponent is even, the result is halfPower squared
        if (exponent % 2 == 0) {
            return halfPower * halfPower;
        } else {
            // If exponent is odd, multiply base with halfPower squared
            return base * halfPower * halfPower;
        }
    }
}
