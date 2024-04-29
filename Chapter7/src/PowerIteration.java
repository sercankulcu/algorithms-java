import java.util.Arrays;

public class PowerIteration {

	// Function to perform power iteration
	public static double[] powerIteration(double[][] matrix, int iterations) {
		int n = matrix.length; // Size of the matrix
		double[] x = new double[n]; // Initial guess for the eigenvector

		// Initialize x with random values or specific initial values
		for (int i = 0; i < n; i++) {
			x[i] = 1.0; // Initial guess can be any non-zero vector
		}

		// Perform power iteration
		for (int iter = 0; iter < iterations; iter++) {
			double[] x_new = new double[n]; // Placeholder for the updated eigenvector

			// Multiply the matrix with the current eigenvector
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					x_new[i] += matrix[i][j] * x[j];
				}
			}

			// Normalize the new eigenvector
			double norm = 0.0;
			for (int i = 0; i < n; i++) {
				norm += x_new[i] * x_new[i];
			}
			norm = Math.sqrt(norm);
			for (int i = 0; i < n; i++) {
				x_new[i] /= norm;
			}

			// Update the eigenvector for the next iteration
			x = Arrays.copyOf(x_new, n);
			System.out.println(Arrays.toString(x));
		}

		return x; // Return the dominant eigenvector
	}

	public static void main(String[] args) {
		// Example: Power iteration for a 3x3 matrix
		double[][] matrix = {
				{0.511, 0.32, 0.12},
				{0.211, 0.62, 0.12},
				{0.311, 0.12, 0.16}
		};

		int iterations = 10; // Number of iterations
		// Print the dominant eigenvector
		System.out.println("Dominant Eigenvector:" + Arrays.toString(powerIteration(matrix, iterations)));
	}
}
