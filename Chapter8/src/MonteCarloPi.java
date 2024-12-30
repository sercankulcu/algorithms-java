import java.util.Random;

public class MonteCarloPi {

    /**
     * Estimates the value of π using the Monte Carlo method.
     * @param numPoints The number of random points to generate.
     * @return The estimated value of π.
     */
    static double estimatePi(int numPoints) {
        int countInside = 0; // Count of points inside the quarter circle
        Random random = new Random(); // Random number generator

        // Generate points and check if they fall inside the quarter circle
        for (int i = 0; i < numPoints; i++) {
            // Generate random x and y coordinates in the range [0, 1]
            double x = random.nextDouble();
            double y = random.nextDouble();

            // Check if the point lies within the quarter circle
            if (x * x + y * y <= 1) {
                countInside++; // Increment count if the point is inside
            }
        }

        // Calculate and return the estimated value of π
        return 4.0 * countInside / numPoints;
    }

    public static void main(String[] args) {
        int numPoints = 1_000_000; // Number of points to generate for the simulation
        System.out.println("Estimated value of π: " + estimatePi(numPoints)); // Print the result
    }
}
