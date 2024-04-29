
import java.util.Random;

public class MonteCarloPi {

	public static void main(String[] args) {
		// Number of points to generate
		int numPoints = 1000000;
		// Count of points inside the quarter circle
		int countInside = 0;

		// Create a random number generator
		Random random = new Random();

		// Generate points and check if they fall inside the quarter circle
		for (int i = 0; i < numPoints; i++) {
			// Generate random x and y coordinates in the range [0, 1]
			double x = random.nextDouble();
			double y = random.nextDouble();

			// Check if the point falls inside the quarter circle
			if (x * x + y * y <= 1) {
				countInside++;
			}
		}

		// Estimate the value of π using the ratio of points inside the quarter circle to the total points
		double estimatedPi = 4.0 * countInside / numPoints;
		System.out.println("Estimated value of π: " + estimatedPi);
	}
}
