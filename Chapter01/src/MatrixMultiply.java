public class MatrixMultiply {

	public static void main(String[] args) {
		// Define two example 3x3 matrices for matrix multiplication
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

		// Perform matrix multiplication and store the result in a new matrix
		int[][] result = multiplyMatrices(matrixA, matrixB);

		// Display Matrix A
		System.out.println("Matrix A:");
		printMatrix(matrixA);  // Print the first matrix

		// Display Matrix B
		System.out.println("\nMatrix B:");
		printMatrix(matrixB);  // Print the second matrix

		// Display the resulting matrix from the multiplication
		System.out.println("\nResult Matrix:");
		printMatrix(result);  // Print the result matrix
	}

	// Function to multiply two matrices A and B
	// Matrix A must have dimensions [rowsA][colsA] and matrix B must have dimensions [colsA][colsB]
	private static int[][] multiplyMatrices(int[][] A, int[][] B) {
		// Get the number of rows and columns for matrix A
		int rowsA = A.length;           // Rows of A
		int colsA = A[0].length;        // Columns of A (should be equal to rows of B)

		// Get the number of columns for matrix B
		int colsB = B[0].length;        // Columns of B

		// Initialize the result matrix with dimensions [rowsA][colsB]
		int[][] result = new int[rowsA][colsB];

		// Loop through each row of matrix A
		for (int i = 0; i < rowsA; i++) {
			// Loop through each column of matrix B
			for (int j = 0; j < colsB; j++) {
				// Perform the dot product of the i-th row of A and the j-th column of B
				for (int k = 0; k < colsA; k++) {
					// Add the product of A[i][k] and B[k][j] to the result at [i][j]
					result[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		// Return the resulting matrix
		return result;
	}

	// Function to print a matrix
	// It prints each row of the matrix on a new line
	private static void printMatrix(int[][] matrix) {
		// Loop through each row in the matrix
		for (int[] row : matrix) {
			// Loop through each element in the row
			for (int element : row) {
				// Print each element followed by a space
				System.out.print(element + " ");
			}
			// Print a newline after each row
			System.out.println();
		}
	}
}
