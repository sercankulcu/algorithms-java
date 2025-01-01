public class NQueens {

    // Method to print the solution chessboard
    public static void printSolution(int[][] board, int n) {
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to check if placing a queen at board[row][col] is safe
    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check the column for another queen
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check the upper-left diagonal for another queen
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check the upper-right diagonal for another queen
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Recursive method to solve the N-Queens problem
    public static boolean solveNQueens(int[][] board, int row, int n) {
        // Base case: If all queens are placed, print the solution and return true
        if (row >= n) {
            printSolution(board, n);
            return true; // Exit after finding the first solution
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                // Place the queen
                board[row][col] = 1;

                // Debug statement
                System.out.println("Placing queen at row " + row + ", column " + col);

                // Recur to place the rest of the queens
                if (solveNQueens(board, row + 1, n)) {
                    return true; // Exit as soon as a solution is found
                }

                // Backtrack: Remove the queen from the current position
                board[row][col] = 0;

                // Debug statement
                System.out.println("Backtracking from row " + row + ", column " + col);
            }
        }

        return false; // No solution for the current configuration
    }

    // Main method to run the program
    public static void main(String[] args) {
        int n = 8; // Size of the chessboard (N×N)

        // Initialize the chessboard with 0s (no queens placed)
        int[][] board = new int[n][n];

        // Solve the N-Queens problem
        if (!solveNQueens(board, 0, n)) {
            System.out.println("No solution exists for " + n + " queens.");
        } else {
            System.out.println("A solution has been found and displayed.");
        }
    }
}
