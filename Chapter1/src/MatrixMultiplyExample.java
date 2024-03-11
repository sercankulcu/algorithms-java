
public class MatrixMultiplyExample {

	public static void main(String[] args) {
		// Define two example matrices
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

		// Perform matrix multiplication
		int[][] result = multiplyMatrices(matrixA, matrixB);

		// Display the original matrices and the result
		System.out.println("Matrix A:");
		printMatrix(matrixA);

		System.out.println("\nMatrix B:");
		printMatrix(matrixB);

		System.out.println("\nResult Matrix:");
		printMatrix(result);
	}

	// Function to multiply two matrices
	private static int[][] multiplyMatrices(int[][] A, int[][] B) {
		int rowsA = A.length;
		int colsA = A[0].length;
		int colsB = B[0].length;

		int[][] result = new int[rowsA][colsB];

		for (int i = 0; i < rowsA; i++) {
			for (int j = 0; j < colsB; j++) {
				for (int k = 0; k < colsA; k++) {
					result[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return result;
	}

	// Function to print a matrix
	private static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			for (int element : row) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}
}
