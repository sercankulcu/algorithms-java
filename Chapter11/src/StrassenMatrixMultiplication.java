public class StrassenMatrixMultiplication {

    // Method to perform Strassen's Matrix Multiplication
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;  // Get the size of the matrices (assuming square matrices)
        
        // Base case: if the matrix is 1x1, just multiply the elements directly
        if (n == 1) {
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0]; // Multiply the single elements
            return result;  // Return the result matrix
        }

        // Split matrices into submatrices (A11, A12, A21, A22 for A and B11, B12, B21, B22 for B)
        int mid = n / 2;  // Middle point to divide the matrix
        int[][] A11 = new int[mid][mid];
        int[][] A12 = new int[mid][mid];
        int[][] A21 = new int[mid][mid];
        int[][] A22 = new int[mid][mid];
        int[][] B11 = new int[mid][mid];
        int[][] B12 = new int[mid][mid];
        int[][] B21 = new int[mid][mid];
        int[][] B22 = new int[mid][mid];

        // Fill the submatrices A11, A12, A21, A22, B11, B12, B21, B22
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + mid];
                A21[i][j] = A[i + mid][j];
                A22[i][j] = A[i + mid][j + mid];
                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + mid];
                B21[i][j] = B[i + mid][j];
                B22[i][j] = B[i + mid][j + mid];
            }
        }

        // Calculate the 7 products using Strassen's formula
        int[][] P1 = multiply(A11, subtract(B12, B22)); // P1 = A11 * (B12 - B22)
        int[][] P2 = multiply(add(A11, A12), B22);     // P2 = (A11 + A12) * B22
        int[][] P3 = multiply(add(A21, A22), B11);     // P3 = (A21 + A22) * B11
        int[][] P4 = multiply(A22, subtract(B21, B11)); // P4 = A22 * (B21 - B11)
        int[][] P5 = multiply(add(A11, A22), add(B11, B22)); // P5 = (A11 + A22) * (B11 + B22)
        int[][] P6 = multiply(subtract(A12, A22), add(B21, B22)); // P6 = (A12 - A22) * (B21 + B22)
        int[][] P7 = multiply(subtract(A11, A21), add(B11, B12)); // P7 = (A11 - A21) * (B11 + B12)

        // Calculate the result matrix C using the 7 products
        int[][] C11 = add(subtract(add(P5, P4), P2), P6); // C11 = P5 + P4 - P2 + P6
        int[][] C12 = add(P1, P2); // C12 = P1 + P2
        int[][] C21 = add(P3, P4); // C21 = P3 + P4
        int[][] C22 = add(subtract(add(P5, P1), P3), P7); // C22 = P5 + P1 - P3 + P7

        // Combine the four submatrices into the final result matrix C
        int[][] C = new int[n][n];  // Initialize the result matrix of the same size
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                C[i][j] = C11[i][j];  // Fill C11 into the top-left corner
                C[i][j + mid] = C12[i][j];  // Fill C12 into the top-right corner
                C[i + mid][j] = C21[i][j];  // Fill C21 into the bottom-left corner
                C[i + mid][j + mid] = C22[i][j];  // Fill C22 into the bottom-right corner
            }
        }

        return C;  // Return the final result matrix
    }

    // Helper method to add two matrices
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;  // Get the size of the matrices (square matrices assumed)
        int[][] result = new int[n][n];  // Initialize the result matrix

        // Add corresponding elements of matrices A and B
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];  // Sum the elements
            }
        }
        return result;  // Return the result matrix
    }

    // Helper method to subtract two matrices
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;  // Get the size of the matrices (square matrices assumed)
        int[][] result = new int[n][n];  // Initialize the result matrix

        // Subtract corresponding elements of matrices A and B
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];  // Subtract the elements
            }
        }
        return result;  // Return the result matrix
    }

    // Method to print a matrix in a readable format
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");  // Print each element
            }
            System.out.println();  // Print a new line after each row
        }
    }

    // Main method to test Strassen's Matrix Multiplication
    public static void main(String[] args) {
        // Example 4x4 matrices for testing the algorithm
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int[][] B = {
            {16, 15, 14, 13},
            {12, 11, 10, 9},
            {8, 7, 6, 5},
            {4, 3, 2, 1}
        };
        
        System.out.println("Matrix A:");
        printMatrix(A);
        
        System.out.println("Matrix B:");
        printMatrix(B);

        // Perform matrix multiplication using Strassen's algorithm
        int[][] result = multiply(A, B);

        // Print the resulting matrix after multiplication
        System.out.println("Resulting Matrix:");
        printMatrix(result);
    }
}
