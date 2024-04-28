
import java.util.ArrayList;
import java.util.List;

public class TravelingSalesmanApproximation {

	// Function to solve the Traveling Salesman Problem using Nearest Neighbor Algorithm
	public static List<Integer> nearestNeighbor(int[][] graph, int start) {

		int n = graph.length;
		List<Integer> tour = new ArrayList<>();
		boolean[] visited = new boolean[n]; // Keep track of visited cities
		int distance = 0;

		// Start from the specified starting city
		int currentCity = start;
		tour.add(currentCity);
		visited[currentCity] = true;

		// Repeat until all cities have been visited
		while (tour.size() < n) {
			int nearestCity = -1;
			int minDistance = Integer.MAX_VALUE;

			// Find the nearest unvisited city
			for (int nextCity = 0; nextCity < n; nextCity++) {
				if (!visited[nextCity] && graph[currentCity][nextCity] < minDistance) {
					minDistance = graph[currentCity][nextCity];
					nearestCity = nextCity;
				}
			}

			// Move to the nearest unvisited city
			if (nearestCity != -1) {
				tour.add(nearestCity);
				visited[nearestCity] = true;
				currentCity = nearestCity;
				distance += minDistance;
			} else {
				// If no unvisited city is found, break the loop
				break;
			}
		}

		// Return to the starting city to complete the tour
		tour.add(start);
		distance += graph[currentCity][start];
		System.out.println(distance);
		return tour;
	}

	public static void main(String[] args) {
		int[][] graph = {
				{0, 10, 15, 20, 25},
				{10, 0, 35, 25, 30},
				{15, 35, 0, 30, 35},
				{20, 25, 30, 0, 40},
				{20, 25, 30, 45, 0}
		};

		int startCity = 0; // Starting city index

		// Find the approximate solution using the Nearest Neighbor Algorithm
		List<Integer> tour = nearestNeighbor(graph, startCity);

		// Print the approximate tour
		System.out.println("Approximate Tour: " + tour);
	}
}
