public class KaratsubaMultiplication {

    // Method to perform Karatsuba multiplication
    public static long karatsuba(long x, long y) {
        // Debug statement to track the method call and input values
        System.out.println("Karatsuba called with x = " + x + " and y = " + y);
        
        // Base case: if the numbers are small enough (single digits), multiply directly
        if (x < 10 || y < 10) {
            System.out.println("Base case reached: multiplying directly " + x + " * " + y);
            return x * y;
        }

        // Find the size of the numbers (the number of digits)
        int n = Math.max(String.valueOf(x).length(), String.valueOf(y).length());
        int half = n / 2;  // The split point in the number

        // Split the numbers into high and low parts
        long power = (long) Math.pow(10, half);  // Power of 10 for splitting
        long high1 = x / power;  // High part of x
        long low1 = x % power;   // Low part of x
        long high2 = y / power;  // High part of y
        long low2 = y % power;   // Low part of y

        // Debug statement to show the split values
        System.out.println("Split x into high1 = " + high1 + " and low1 = " + low1);
        System.out.println("Split y into high2 = " + high2 + " and low2 = " + low2);

        // Recursively calculate three products using Karatsuba's method
        long z0 = karatsuba(low1, low2); // low1 * low2
        long z1 = karatsuba(low1 + high1, low2 + high2); // (low1 + high1) * (low2 + high2)
        long z2 = karatsuba(high1, high2); // high1 * high2

        // Debug statement to show intermediate product results
        System.out.println("Intermediate products: z0 = " + z0 + ", z1 = " + z1 + ", z2 = " + z2);

        // Combine the results using the Karatsuba formula
        long result = (z2 * power * power) + ((z1 - z2 - z0) * power) + z0;

        // Debug statement to show the final result calculation
        System.out.println("Karatsuba result = " + result);

        return result;
    }

    // Main method to test Karatsuba multiplication
    public static void main(String[] args) {
        long x = 1234;  // First number to multiply
        long y = 5678;  // Second number to multiply

        // Perform Karatsuba multiplication
        long result = karatsuba(x, y);

        // Print the result of multiplication
        System.out.println("The result of " + x + " * " + y + " is: " + result);
    }
}
