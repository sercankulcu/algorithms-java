import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman {

    /**
     * Function to solve the Traveling Salesman Problem using the Held-Karp algorithm (Dynamic Programming).
     * @param graph The adjacency matrix representing the graph. graph[i][j] is the cost of traveling from city i to city j.
     * @param tour A list to store the order of cities in the optimal tour.
     * @return The minimum cost of the optimal Traveling Salesman tour.
     */
    public static int tsp(int[][] graph, List<Integer> tour) {
        int n = graph.length;

        // Create a DP table where dp[i][mask] stores the minimum cost to visit all cities in 'mask' ending at city 'i'.
        int[][] dp = new int[n][1 << n]; // 1 << n represents all subsets of cities.
        int[][] parent = new int[n][1 << n]; // Table to track the parent city for each state.

        // Initialize the DP table with -1 to indicate uncalculated states.
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Base case: the cost of visiting a single city (subset containing only the start city).
        for (int i = 0; i < n; i++) {
            dp[i][1 << i] = graph[i][0]; // Cost of returning to the start city from city i.
        }

        // Iterate over all subsets of cities (represented as bitmasks).
        for (int mask = 1; mask < (1 << n); mask++) { // mask represents a subset of cities.
            for (int i = 0; i < n; i++) { // Iterate over all cities.
                // Check if city 'i' is in the current subset represented by 'mask'.
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < n; j++) {
                        // Check if city 'j' is in the subset and is not the same as city 'i'.
                        if ((mask & (1 << j)) != 0 && i != j) {
                            // Calculate the cost of reaching city 'i' via city 'j'.
                            int prevMask = mask ^ (1 << i); // Remove city 'i' from the subset.
                            if (dp[j][prevMask] != -1) { // If the previous state is valid.
                                int newCost = dp[j][prevMask] + graph[j][i];
                                if (dp[i][mask] == -1 || newCost < dp[i][mask]) {
                                    dp[i][mask] = newCost; // Update the minimum cost for this state.
                                    parent[i][mask] = j; // Store city 'j' as the parent of city 'i' in this state.
                                }
                            }
                        }
                    }
                }
            }
        }

        // Find the minimum cost of the complete tour (visiting all cities and returning to the start city).
        int minCost = Integer.MAX_VALUE;
        int endCity = -1; // The last city visited in the optimal tour.
        for (int i = 0; i < n; i++) {
            if (dp[i][(1 << n) - 1] != -1) { // Check if the final state is valid for city 'i'.
                int tourCost = dp[i][(1 << n) - 1] + graph[0][i]; // Include the cost of returning to the start city.
                if (tourCost < minCost) {
                    minCost = tourCost;
                    endCity = i; // Update the last city in the optimal tour.
                }
            }
        }

        // Reconstruct the tour path using the parent table.
        reconstructTour(parent, endCity, (1 << n) - 1, tour);

        return minCost; // Return the minimum cost of the tour.
    }

    /**
     * Function to reconstruct the optimal tour path recursively.
     * @param parent The parent table used for backtracking.
     * @param currentCity The current city being processed.
     * @param mask The current subset of cities (represented as a bitmask).
     * @param tour The list to store the order of cities in the optimal tour.
     */
    private static void reconstructTour(int[][] parent, int currentCity, int mask, List<Integer> tour) {
        if (mask == 1) { // Base case: only the start city remains.
            return;
        }
        int prevCity = parent[currentCity][mask]; // Find the previous city in the tour.
        reconstructTour(parent, prevCity, mask ^ (1 << currentCity), tour); // Recur with the previous state.
        tour.add(currentCity); // Add the current city to the tour.
    }

    public static void main(String[] args) {
        // Adjacency matrix representing the graph (costs between cities).
        int[][] graph = {
            {0, 10, 15, 20, 25},
            {10, 0, 35, 25, 30},
            {15, 35, 0, 30, 35},
            {20, 25, 30, 0, 40},
            {20, 25, 30, 45, 0}
        };

        // Solve the Traveling Salesman Problem.
        List<Integer> tour = new ArrayList<>();
        int minCost = tsp(graph, tour);

        // Print the minimum cost of the tour.
        System.out.println("Minimum Cost of Traveling Salesman Tour: " + minCost);

        // Print the optimal tour path.
        System.out.print("Optimal Tour Path: ");
        for (int city : tour) {
            System.out.print(city + " ");
        }
    }
}
