public class WordSearch {

    // Directions for moving up, down, left, and right in the grid
    private static final int[] rowDir = {-1, 1, 0, 0};
    private static final int[] colDir = {0, 0, -1, 1};

    /**
     * Method to check if the given word exists in the grid.
     * @param board The 2D character grid.
     * @param word The word to search for.
     * @return True if the word exists, false otherwise.
     */
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;     // Number of rows in the grid
        int cols = board[0].length; // Number of columns in the grid

        // Create a visited array to track which cells have been visited during the current search
        boolean[][] visited = new boolean[rows][cols];

        // Traverse each cell in the grid as the starting point for the search
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Call the backtracking function starting from cell (i, j)
                if (backtrack(board, word, 0, i, j, visited)) {
                    return true; // Return true if the word is found
                }
            }
        }

        return false; // Return false if the word is not found in the grid
    }

    /**
     * Backtracking method to explore all possible paths for the word.
     * @param board The 2D character grid.
     * @param word The word to search for.
     * @param index The current character index in the word.
     * @param row The current row position in the grid.
     * @param col The current column position in the grid.
     * @param visited The visited array to track visited cells.
     * @return True if the word is found, false otherwise.
     */
    private static boolean backtrack(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        // Base case: If the entire word is matched
        if (index == word.length()) {
            return true;
        }

        // Check if the current cell is invalid (out of bounds, already visited, or doesn't match the current character)
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Debug: Print the current cell being visited and the character
        System.out.println("Visiting cell (" + row + ", " + col + ") with character '" + board[row][col] + "' for index " + index);

        // Mark the current cell as visited
        visited[row][col] = true;

        // Explore all four possible directions (up, down, left, right)
        for (int d = 0; d < 4; d++) {
            int newRow = row + rowDir[d];
            int newCol = col + colDir[d];
            // Recursively check the next character in the word
            if (backtrack(board, word, index + 1, newRow, newCol, visited)) {
                return true;
            }
        }

        // Backtrack: Unmark the current cell to allow other paths to use it
        visited[row][col] = false;

        // Debug: Print the backtracking step
        System.out.println("Backtracking from cell (" + row + ", " + col + ")");
        return false;
    }

    /**
     * Main method to test the Word Search implementation.
     */
    public static void main(String[] args) {
        // Example grid
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        // Words to search for in the grid
        String[] words = {"ABCCED", "SEE", "ABCB", "SADE"};

        // Check if each word exists in the grid
        for (String word : words) {
            System.out.println("Searching for word: " + word);
            if (exist(board, word)) {
                System.out.println("Word '" + word + "' exists in the grid.\n");
            } else {
                System.out.println("Word '" + word + "' does not exist in the grid.\n");
            }
        }
    }
}
