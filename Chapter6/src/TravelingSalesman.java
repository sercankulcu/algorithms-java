import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman {

	// Function to solve the Traveling Salesman Problem using dynamic programming (Held-Karp algorithm)
	public static int tsp(int[][] graph, List<Integer> tour) {
		int n = graph.length;
		int[][] dp = new int[n][1 << n]; // Memoization table to store the minimum costs
		int[][] parent = new int[n][1 << n]; // Table to store the parent city for each state

		// Initialize the memoization table with -1 to indicate uncalculated values
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		// Base case: if only one city is visited (subset containing only the start city)
		for (int i = 0; i < n; i++) {
			dp[i][1 << i] = graph[i][0]; // Cost of returning to the start city from each city
		}

		// Iterate over all subsets of cities
		for (int mask = 1; mask < (1 << n); mask++) {
			for (int i = 0; i < n; i++) {
				// Check if city i is included in the subset represented by the mask
				if ((mask & (1 << i)) != 0) {
					for (int j = 0; j < n; j++) {
						// Check if city j is included in the subset and is not equal to city i
						if ((mask & (1 << j)) != 0 && i != j) {
							// Compute the minimum cost of reaching city i via city j and update the memoization table
							int prevMask = mask ^ (1 << i); // Remove city i from the subset
							if (dp[j][prevMask] != -1) {
								int newCost = dp[j][prevMask] + graph[j][i];
								if (dp[i][mask] == -1 || newCost < dp[i][mask]) {
									dp[i][mask] = newCost;
									parent[i][mask] = j; // Store the parent city for city i and the current state
								}
							}
						}
					}
				}
			}
		}

		// Find the minimum cost of the complete tour
		int minCost = Integer.MAX_VALUE;
		int endCity = -1;
		for (int i = 0; i < n; i++) {
			if (dp[i][(1 << n) - 1] != -1) {
				int tourCost = dp[i][(1 << n) - 1] + graph[0][i]; // Cost of returning to the start city
				if (tourCost < minCost) {
					minCost = tourCost;
					endCity = i; // Update the end city of the tour
				}
			}
		}

		// Reconstruct the tour path
		reconstructTour(parent, endCity, (1 << n) - 1, tour);

		return minCost;
	}

	// Function to reconstruct the tour path recursively
	private static void reconstructTour(int[][] parent, int currentCity, int mask, List<Integer> tour) {
		if (mask == 1) { // Base case: only the start city is left in the subset
			return;
		}
		int prevCity = parent[currentCity][mask];
		reconstructTour(parent, prevCity, mask ^ (1 << currentCity), tour); // Recur with the previous city and the updated mask
		tour.add(currentCity); // Add the current city to the tour
	}

	public static void main(String[] args) {
		int[][] graph = {
				{0, 10, 15, 20, 25},
				{10, 0, 35, 25, 30},
				{15, 35, 0, 30, 35},
				{20, 25, 30, 0, 40},
				{20, 25, 30, 45, 0}
		};

		// Solve the Traveling Salesman Problem using dynamic programming
		List<Integer> tour = new ArrayList<>();
		int minCost = tsp(graph, tour);

		// Print the minimum cost of the optimal tour
		System.out.println("Minimum Cost of Traveling Salesman Tour: " + minCost);

		// Print the tour
		System.out.print("Optimal Tour Path: ");
		for (int city : tour) {
			System.out.print(city + " ");
		}
	}
}
