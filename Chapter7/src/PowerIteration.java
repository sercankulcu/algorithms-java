import java.util.Arrays;

public class PowerIteration {

    // Function to perform power iteration
    public static double[] powerIteration(double[][] matrix, int iterations) {
        int n = matrix.length; // Size of the matrix (assuming square matrix)
        
        // Initialize the eigenvector x with random or specific non-zero values
        double[] x = new double[n]; // Initial guess for the eigenvector
        for (int i = 0; i < n; i++) {
            x[i] = 1.0; // Set all values to 1 initially (can be any non-zero value)
        }

        // Perform the power iteration for the specified number of iterations
        for (int iter = 0; iter < iterations; iter++) {
            double[] x_new = new double[n]; // Placeholder for the updated eigenvector

            // Multiply the matrix with the current eigenvector
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    x_new[i] += matrix[i][j] * x[j]; // Perform matrix-vector multiplication
                }
            }

            // Normalize the new eigenvector to prevent numerical overflow or underflow
            double norm = 0.0;
            for (int i = 0; i < n; i++) {
                norm += x_new[i] * x_new[i]; // Compute the squared L2 norm
            }
            norm = Math.sqrt(norm); // Take the square root to get the L2 norm

            // Normalize the new eigenvector by dividing each element by the norm
            for (int i = 0; i < n; i++) {
                x_new[i] /= norm; // Normalize the vector to unit length
            }

            // Update the eigenvector for the next iteration
            x = Arrays.copyOf(x_new, n); // Update x to the new eigenvector
            System.out.println(Arrays.toString(x)); // Print the current approximation of the eigenvector
        }

        return x; // Return the dominant eigenvector after the iterations
    }

    public static void main(String[] args) {
        // Example: Power iteration for a 3x3 matrix
        double[][] matrix = {
                {0.511, 0.32, 0.12},
                {0.211, 0.62, 0.12},
                {0.311, 0.12, 0.16}
        };

        int iterations = 10; // Set the number of iterations for convergence
        // Print the dominant eigenvector
        System.out.println("Dominant Eigenvector: " + Arrays.toString(powerIteration(matrix, iterations)));
    }
}
