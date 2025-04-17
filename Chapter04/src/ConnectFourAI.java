import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Main class implementing a Connect Four game with AI opponents using Minimax and MCTS algorithms
public class ConnectFourAI {
    // Constants defining the game board dimensions and player symbols
    private static final int ROWS = 6; // Number of rows in the Connect Four grid
    private static final int COLS = 7; // Number of columns in the Connect Four grid
    private static final char PLAYER = 'X'; // Symbol representing the AI player
    private static final char HUMAN = 'O'; // Symbol representing the human player
    private static final char EMPTY = '.'; // Symbol representing an empty cell

    // Inner class representing the game board and its operations
    static class Board {
        char[][] grid; // 2D array representing the game board
        char currentPlayer; // Tracks whose turn it is (HUMAN or PLAYER)

        // Constructor: Initializes an empty board and sets human as the starting player
        Board() {
            grid = new char[ROWS][COLS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    grid[i][j] = EMPTY; // Fill board with empty cells
                }
            }
            currentPlayer = HUMAN; // Human starts the game
        }

        // Creates a deep copy of the current board for simulation purposes
        Board copy() {
            Board newBoard = new Board();
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    newBoard.grid[i][j] = grid[i][j]; // Copy cell values
                }
            }
            newBoard.currentPlayer = currentPlayer; // Copy current player
            return newBoard;
        }

        // Returns a list of columns that are not full (valid for dropping a disc)
        List<Integer> getValidColumns() {
            List<Integer> validCols = new ArrayList<>();
            for (int col = 0; col < COLS; col++) {
                if (grid[0][col] == EMPTY) { // Check top row of each column
                    validCols.add(col);
                }
            }
            return validCols;
        }

        // Drops a disc for the specified player in the given column
        // Returns true if successful, false if column is full
        boolean dropDisc(int col, char player) {
            for (int row = ROWS - 1; row >= 0; row--) { // Start from bottom row
                if (grid[row][col] == EMPTY) {
                    grid[row][col] = player; // Place disc
                    currentPlayer = (player == HUMAN) ? PLAYER : HUMAN; // Switch player
                    return true;
                }
            }
            return false; // Column is full
        }

        // Checks if the specified player has four discs in a row (horizontal, vertical, or diagonal)
        boolean isWin(char player) {
            // Check horizontal lines
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    if (grid[i][j] == player && grid[i][j + 1] == player &&
                            grid[i][j + 2] == player && grid[i][j + 3] == player) {
                        return true;
                    }
                }
            }
            // Check vertical lines
            for (int i = 0; i < ROWS - 3; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (grid[i][j] == player && grid[i + 1][j] == player &&
                            grid[i + 2][j] == player && grid[i + 3][j] == player) {
                        return true;
                    }
                }
            }
            // Check diagonal (positive slope, bottom-left to top-right)
            for (int i = 3; i < ROWS; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    if (grid[i][j] == player && grid[i - 1][j + 1] == player &&
                            grid[i - 2][j + 2] == player && grid[i - 3][j + 3] == player) {
                        return true;
                    }
                }
            }
            // Check diagonal (negative slope, top-left to bottom-right)
            for (int i = 0; i < ROWS - 3; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    if (grid[i][j] == player && grid[i + 1][j + 1] == player &&
                            grid[i + 2][j + 2] == player && grid[i + 3][j + 3] == player) {
                        return true;
                    }
                }
            }
            return false; // No winning condition found
        }

        // Checks if the game is a draw (board full and no winner)
        boolean isDraw() {
            for (int col = 0; col < COLS; col++) {
                if (grid[0][col] == EMPTY) { // If any column has space, not a draw
                    return false;
                }
            }
            return !isWin(PLAYER) && !isWin(HUMAN); // Draw if no winner and board is full
        }

        // Checks if the game is over (win or draw)
        boolean isGameOver() {
            return isWin(PLAYER) || isWin(HUMAN) || isDraw();
        }

        // Prints the current state of the board to the console
        void printBoard() {
            System.out.println(" 0 1 2 3 4 5 6"); // Column numbers
            for (int i = 0; i < ROWS; i++) {
                System.out.print("|");
                for (int j = 0; j < COLS; j++) {
                    System.out.print(grid[i][j] + "|"); // Print cell contents
                }
                System.out.println();
            }
            System.out.println("---------------"); // Board bottom border
        }
    }

    // Inner class implementing the Minimax algorithm with Alpha-Beta pruning for AI moves
    static class MinimaxAI {
        private final int maxDepth; // Maximum depth for Minimax search

        // Constructor: Sets the maximum depth for the Minimax algorithm
        MinimaxAI(int maxDepth) {
            this.maxDepth = maxDepth;
        }

        // Evaluates the board's state for non-terminal positions using a heuristic
        private int evaluateBoard(Board board) {
            int score = 0;
            // Weights for columns, favoring central columns (more strategic)
            int[] columnWeights = {1, 2, 3, 4, 3, 2, 1};
            for (int col = 0; col < COLS; col++) {
                for (int row = 0; row < ROWS; row++) {
                    if (board.grid[row][col] == PLAYER) {
                        score += columnWeights[col] * (ROWS - row); // Higher rows score more
                    } else if (board.grid[row][col] == HUMAN) {
                        score -= columnWeights[col] * (ROWS - row); // Penalize human positions
                    }
                }
            }
            // Reward potential three-in-a-row opportunities (not blocked)
            score += countPotentialThrees(board, PLAYER) * 50;
            score -= countPotentialThrees(board, HUMAN) * 50;
            return score == 0 ? 1 : score; // Avoid zero score unless board is empty
        }

        // Counts potential three-in-a-row opportunities for the specified player
        private int countPotentialThrees(Board board, char player) {
            int count = 0;
            // Check horizontal
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    int playerCount = 0, emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (board.grid[i][j + k] == player) playerCount++;
                        else if (board.grid[i][j + k] == EMPTY) emptyCount++;
                    }
                    if (playerCount == 3 && emptyCount == 1) count++; // Three discs + one empty
                }
            }
            // Check vertical
            for (int i = 0; i < ROWS - 3; i++) {
                for (int j = 0; j < COLS; j++) {
                    int playerCount = 0, emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (board.grid[i + k][j] == player) playerCount++;
                        else if (board.grid[i + k][j] == EMPTY) emptyCount++;
                    }
                    if (playerCount == 3 && emptyCount == 1) count++;
                }
            }
            // Check diagonal (positive slope)
            for (int i = 3; i < ROWS; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    int playerCount = 0, emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (board.grid[i - k][j + k] == player) playerCount++;
                        else if (board.grid[i - k][j + k] == EMPTY) emptyCount++;
                    }
                    if (playerCount == 3 && emptyCount == 1) count++;
                }
            }
            // Check diagonal (negative slope)
            for (int i = 0; i < ROWS - 3; i++) {
                for (int j = 0; j < COLS - 3; j++) {
                    int playerCount = 0, emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (board.grid[i + k][j + k] == player) playerCount++;
                        else if (board.grid[i + k][j + k] == EMPTY) emptyCount++;
                    }
                    if (playerCount == 3 && emptyCount == 1) count++;
                }
            }
            return count;
        }

        // Minimax algorithm with Alpha-Beta pruning to evaluate moves
        // Returns the score of the best move for the current board state
        int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
            // Terminal states: AI win, human win, draw, or max depth reached
            if (board.isWin(PLAYER)) return 1000 - depth; // AI win, favor quicker wins
            if (board.isWin(HUMAN)) return -1000 + depth; // Human win, favor later losses
            if (board.isDraw()) return 0; // Draw
            if (depth == 0) return evaluateBoard(board); // Evaluate non-terminal state

            if (isMaximizing) { // AI's turn (maximizing score)
                int maxEval = Integer.MIN_VALUE;
                for (int col : board.getValidColumns()) {
                    Board newBoard = board.copy();
                    newBoard.dropDisc(col, PLAYER); // Simulate AI move
                    int eval = minimax(newBoard, depth - 1, alpha, beta, false);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval); // Update alpha
                    if (beta <= alpha) break; // Prune branch
                }
                return maxEval;
            } else { // Human's turn (minimizing score)
                int minEval = Integer.MAX_VALUE;
                for (int col : board.getValidColumns()) {
                    Board newBoard = board.copy();
                    newBoard.dropDisc(col, HUMAN); // Simulate human move
                    int eval = minimax(newBoard, depth - 1, alpha, beta, true);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval); // Update beta
                    if (beta <= alpha) break; // Prune branch
                }
                return minEval;
            }
        }

        // Finds the best column for the AI to play, along with its evaluated score
        int[] findBestMove(Board board) {
            int bestMove = -1;
            int bestValue = Integer.MIN_VALUE;
            for (int col : board.getValidColumns()) {
                Board newBoard = board.copy();
                newBoard.dropDisc(col, PLAYER); // Simulate AI move
                int moveValue = minimax(newBoard, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                if (moveValue > bestValue) {
                    bestValue = moveValue;
                    bestMove = col; // Update best move if score is higher
                }
            }
            return new int[]{bestMove, bestValue}; // Return column and score
        }
    }

    // Inner class implementing Monte Carlo Tree Search (MCTS) for AI moves
    static class MCTSAI {
        // Node class for the MCTS GrayscaleFilter: Represents a game state in the search tree
        static class Node {
            Board board; // Board state at this node
            int visits; // Number of times this node has been visited
            double totalScore; // Cumulative score from simulations
            List<Node> children; // Child nodes (possible moves)
            Node parent; // Parent node
            int move; // Column played to reach this node

            // Constructor: Initializes a node with a board state and move
            Node(Board board, int move) {
                this.board = board;
                this.move = move;
                this.visits = 0;
                this.totalScore = 0;
                this.children = new ArrayList<>();
            }
        }

        private final int iterations; // Number of MCTS iterations
        Random random = new Random(); // Random number generator for simulations

        // Constructor: Sets the number of MCTS iterations
        MCTSAI(int iterations) {
            this.iterations = iterations;
        }

        // Selects a node for expansion using UCT (Upper Confidence Bound for Trees)
        Node select(Node root) {
            Node current = root;
            while (!current.children.isEmpty() && !current.board.isGameOver()) {
                final Node finalCurrent = current;
                current = current.children.stream()
                        .max((a, b) -> Double.compare(
                                a.totalScore / (a.visits + 1) + Math.sqrt(2 * Math.log(finalCurrent.visits + 1) / (a.visits + 1)),
                                b.totalScore / (b.visits + 1) + Math.sqrt(2 * Math.log(finalCurrent.visits + 1) / (b.visits + 1))
                        )).orElse(current); // Select node with highest UCT value
            }
            return current;
        }

        // Expands a node by creating child nodes for all valid moves
        void expand(Node node) {
            for (int col : node.board.getValidColumns()) {
                Board newBoard = node.board.copy();
                newBoard.dropDisc(col, newBoard.currentPlayer); // Simulate move
                Node child = new Node(newBoard, col);
                child.parent = node;
                node.children.add(child); // Add child to node
            }
        }

        // Simulates a random game from the given board state until a terminal state
        double simulate(Board board) {
            Board simBoard = board.copy();
            while (!simBoard.isGameOver()) {
                List<Integer> validCols = simBoard.getValidColumns();
                int col = validCols.get(random.nextInt(validCols.size())); // Random move
                simBoard.dropDisc(col, simBoard.currentPlayer);
            }
            if (simBoard.isWin(PLAYER)) return 1; // AI win
            if (simBoard.isWin(HUMAN)) return -1; // Human win
            return 0; // Draw
        }

        // Backpropagates the simulation result up the tree
        void backpropagate(Node node, double score) {
            while (node != null) {
                node.visits++;
                node.totalScore += score; // Update node statistics
                node = node.parent;
            }
        }

        // Finds the best move by running MCTS and selecting the most visited child
        int[] findBestMove(Board board) {
            Node root = new Node(board.copy(), -1);
            for (int i = 0; i < iterations; i++) {
                Node node = select(root); // Select a node
                if (!node.board.isGameOver()) {
                    expand(node); // Expand if not terminal
                    node = node.children.isEmpty() ? node : node.children.get(0); // Use first child
                }
                double score = simulate(node.board); // Simulate from node
                backpropagate(node, score); // Update tree with result
            }
            Node bestNode = root.children.stream()
                    .max((a, b) -> Integer.compare(a.visits, b.visits))
                    .orElse(null); // Select most visited child
            int bestMove = bestNode != null ? bestNode.move : -1;
            double bestScore = bestNode != null ? bestNode.totalScore / bestNode.visits : 0;
            return new int[]{bestMove, (int)(bestScore * 1000)}; // Return move and scaled score
        }
    }

    // Runs the game loop, allowing a human to play against the AI
    public static void playGame(Scanner scanner) {
        System.out.println("Welcome to Connect Four!");
        System.out.println("Choose AI: 1 for Minimax, 2 for MCTS");
        int aiChoice = scanner.nextInt();
        int maxDepth = 7; // Default Minimax depth
        int mctsIterations = 1000; // Default MCTS iterations

        // Configure AI based on user choice
        if (aiChoice == 1) {
            System.out.println("Enter Minimax depth (1-10, recommended 7): ");
            maxDepth = Math.max(1, Math.min(10, scanner.nextInt())); // Limit depth
        } else {
            System.out.println("Enter MCTS iterations (100-10000, recommended 1000): ");
            mctsIterations = Math.max(100, Math.min(10000, scanner.nextInt())); // Limit iterations
        }

        Board board = new Board(); // Create new game board
        MinimaxAI minimaxAI = new MinimaxAI(maxDepth); // Initialize Minimax AI
        MCTSAI mctsAI = new MCTSAI(mctsIterations); // Initialize MCTS AI

        // Main game loop
        while (!board.isGameOver()) {
            board.printBoard(); // Display board
            if (board.currentPlayer == HUMAN) {
                System.out.println("Your turn (enter column 0-6): ");
                int col;
                do {
                    col = scanner.nextInt();
                    if (!board.getValidColumns().contains(col)) {
                        System.out.println("Invalid move. Try again (0-6): ");
                    }
                } while (!board.dropDisc(col, HUMAN)); // Get valid human move
            } else {
                System.out.println("AI's turn...");
                // Choose AI move based on user selection
                int[] moveAndScore = (aiChoice == 1) ? minimaxAI.findBestMove(board) : mctsAI.findBestMove(board);
                int col = moveAndScore[0];
                int score = moveAndScore[1];
                System.out.println("AI plays column: " + col + " (Score: " + score + ")");
                board.dropDisc(col, PLAYER); // AI makes move
            }
        }

        // Display final board and result
        board.printBoard();
        if (board.isWin(HUMAN)) {
            System.out.println("Congratulations! You win!");
        } else if (board.isWin(PLAYER)) {
            System.out.println("AI wins! Better luck next time.");
        } else {
            System.out.println("It's a draw!");
        }
    }

    // Main method: Entry point for the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create scanner for input
        playGame(scanner); // Start the game
        scanner.close(); // Close scanner
    }
}