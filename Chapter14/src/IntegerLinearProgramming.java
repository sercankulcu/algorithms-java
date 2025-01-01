import java.util.Arrays;

public class IntegerLinearProgramming {

    // A helper class to represent a solution (x, y) and its corresponding objective value
    static class Solution {
        int x;
        int y;
        int objectiveValue;

        // Constructor to initialize x, y, and the objective value
        public Solution(int x, int y, int objectiveValue) {
            this.x = x;
            this.y = y;
            this.objectiveValue = objectiveValue;
        }

        // Override toString() method to print the solution in a readable format
        @Override
        public String toString() {
            return "x = " + x + ", y = " + y + ", Objective Value = " + objectiveValue;
        }
    }

    // Method to check if a solution (x, y) is feasible according to the problem's constraints
    public static boolean isFeasible(int x, int y) {
        // Check if the values of x and y satisfy all constraints
        // x + y must be less than or equal to 4, x should be less than or equal to 3, 
        // y should be less than or equal to 2, and both x and y should be non-negative
        return (x + y <= 4) && (x <= 3) && (y <= 2) && (x >= 0) && (y >= 0);
    }

    // Method to solve the ILP using a branch-and-bound approach
    public static Solution solveILP() {
        int maxObjectiveValue = Integer.MIN_VALUE; // Initialize the maximum objective value to a very low number
        Solution bestSolution = null; // Variable to store the best solution found

        // Try all integer combinations for x (0 to 3) and y (0 to 2) within the bounds of the constraints
        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 2; y++) {
                // Check if the current pair (x, y) satisfies the constraints
                if (isFeasible(x, y)) {
                    // Calculate the objective value for the current solution: Z = 3x + 2y
                    int objectiveValue = 3 * x + 2 * y;
                    System.out.println("Checking x = " + x + ", y = " + y + ": Objective Value = " + objectiveValue);

                    // Update the best solution if the current objective value is better
                    if (objectiveValue > maxObjectiveValue) {
                        maxObjectiveValue = objectiveValue;
                        bestSolution = new Solution(x, y, objectiveValue); // Update best solution
                    }
                }
            }
        }

        // Return the best solution found
        return bestSolution;
    }

    // Main method to test the Integer Linear Programming solution
    public static void main(String[] args) {
        System.out.println("Solving Integer Linear Programming (ILP)...");

        // Solve the ILP by calling the solveILP method
        Solution optimalSolution = solveILP();

        // Check if a feasible solution was found and print it
        if (optimalSolution != null) {
            System.out.println("\nOptimal solution found:");
            System.out.println(optimalSolution); // Print the optimal solution
        } else {
            System.out.println("No feasible solution found.");
        }
    }
}
