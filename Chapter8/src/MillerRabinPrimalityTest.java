import java.util.Random; // Import for generating random numbers

public class MillerRabinPrimalityTest {

    // Function to perform the Miller-Rabin primality test
    static boolean isPrime(int n, int k) {
        // Corner cases: Small numbers and basic checks
        if (n <= 1 || n == 4) return false; // 1 and 4 are not prime
        if (n <= 3) return true; // 2 and 3 are prime

        // Write n-1 as 2^d * r where r is odd (this is the odd component of n-1)
        int d = n - 1;
        while (d % 2 == 0) {
            d /= 2; // Divide by 2 until d becomes odd
        }

        // Perform k rounds of the Miller-Rabin test
        for (int i = 0; i < k; i++) {
            if (!witness(n, d)) { // If any witness proves n is composite
                return false;
            }
        }

        // If all k rounds pass, n is likely a prime
        return true;
    }

    // Function to test a witness for the primality of n
    static boolean witness(int n, int d) {
        Random random = new Random();
        int a = 2 + random.nextInt(n - 4); // Pick a random base a in the range [2, n-2]

        // Compute a^d mod n using modular exponentiation
        int x = power(a, d, n);

        // If x == 1 or x == n-1, the test passes for this round
        if (x == 1 || x == n - 1) {
            return true;
        }

        // Repeat squaring x and check for potential primality
        while (d != n - 1) {
            x = (x * x) % n; // Compute x^2 mod n
            d *= 2; // Double d

            // If x becomes 1, n is composite
            if (x == 1) return false;

            // If x becomes n-1, the test passes for this round
            if (x == n - 1) return true;
        }

        // If no condition for primality is met, n is composite
        return false;
    }

    // Function to compute (base^exponent) % modulus efficiently
    static int power(int base, int exponent, int modulus) {
        int result = 1; // Initialize result
        base %= modulus; // Ensure base is within modulus range

        // Perform modular exponentiation
        while (exponent > 0) {
            // If the current bit of exponent is set, multiply result by base
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }

            // Shift exponent right by 1 and square the base
            exponent >>= 1;
            base = (base * base) % modulus;
        }
        return result; // Return final result
    }

    public static void main(String[] args) {
        int n = 61; // Number to test for primality
        int k = 5; // Number of iterations for accuracy (higher k reduces error probability)

        // Test n for primality using k iterations
        if (isPrime(n, k)) {
            System.out.println(n + " is likely prime.");
        } else {
            System.out.println(n + " is composite.");
        }
        
        n = 100; // Number to test for primality
        k = 5; // Number of iterations for accuracy (higher k reduces error probability)

        // Test n for primality using k iterations
        if (isPrime(n, k)) {
            System.out.println(n + " is likely prime.");
        } else {
            System.out.println(n + " is composite.");
        }
    }
}
