import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class MatrixMultiplication {

    // Recursive task for matrix multiplication
    static class MatrixMultiplicationTask extends RecursiveTask<int[]> {
       
		private static final long serialVersionUID = 3200044597144509417L; // Ensure serializability for ForkJoinPool
		private final int[][] matrixA;  // First matrix to multiply
        private final int[][] matrixB;  // Second matrix to multiply
        private final int[] result;     // Array to store the result of multiplication for the row
        private final int row;          // Row index for which the multiplication is to be performed
        private final int n;            // Size of the matrix (assumed square matrix)

        // Constructor to initialize the task with required parameters
        public MatrixMultiplicationTask(int[][] matrixA, int[][] matrixB, int[] result, int row, int n) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = result;
            this.row = row;
            this.n = n;
        }

        @Override
        protected int[] compute() {
            // Multiply the row from matrixA with matrixB and store the result
            for (int i = 0; i < n; i++) {
                result[i] = 0;  // Initialize the result for this row
                // Perform the matrix multiplication for the current row
                for (int j = 0; j < n; j++) {
                    result[i] += matrixA[row][j] * matrixB[j][i];
                }
            }
            return result;  // Return the result of this row multiplication
        }
    }

    // Method to perform parallel matrix multiplication
    public static int[][] parallelMatrixMultiply(int[][] matrixA, int[][] matrixB, int n) {
        int[][] resultMatrix = new int[n][n];  // Initialize the result matrix

        try ( // Create a ForkJoinPool to execute tasks in parallel
		ForkJoinPool pool = new ForkJoinPool()) {
			// Create tasks for each row of the result matrix
			for (int i = 0; i < n; i++) {
			    MatrixMultiplicationTask task = new MatrixMultiplicationTask(matrixA, matrixB, resultMatrix[i], i, n);
			    pool.submit(task);  // Submit task for the row i
			}

			pool.shutdown();  // Shut down the ForkJoinPool once all tasks are submitted
		}

        return resultMatrix;  // Return the computed result matrix
    }

    // Method to print the matrix in a readable format
    public static void printMatrix(int[][] matrix) {
        // Iterate over each row and element to print the matrix
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");  // Print each element with a tab separator
            }
            System.out.println();  // Move to the next line after printing a row
        }
    }

    public static void main(String[] args) {
        // Define two example 3x3 matrices for multiplication
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        int n = matrixA.length;  // Get the size of the matrix (assuming square matrices)

        // Perform parallel matrix multiplication
        int[][] resultMatrix = parallelMatrixMultiply(matrixA, matrixB, n);

        // Output the matrices and the result of multiplication
        System.out.println("Matrix A:");
        printMatrix(matrixA);  // Print the first matrix
        System.out.println("Matrix B:");
        printMatrix(matrixB);  // Print the second matrix
        System.out.println("Result Matrix (A * B):");
        printMatrix(resultMatrix);  // Print the result of multiplication
    }
}
