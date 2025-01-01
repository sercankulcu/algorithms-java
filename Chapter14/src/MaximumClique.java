import java.util.ArrayList;
import java.util.List;

/*
 * The Maximum Clique Problem is to find the largest clique (a subset of vertices where every two vertices are connected) in a given graph.
 * 
 * A clique is a set of vertices such that each pair of vertices in the clique is connected by an edge.
 * The goal is to find the maximum sized clique in a graph represented by an adjacency matrix.
 * 
 * */

public class MaximumClique {

	// A helper class to represent a clique solution
	static class Clique {
		List<Integer> vertices;  // List of vertices in the clique
		int size;  // Size of the clique

		// Constructor to initialize the clique with a list of vertices and its size
		public Clique(List<Integer> vertices) {
			this.vertices = new ArrayList<>(vertices);
			this.size = vertices.size();
		}

		// Method to add a vertex to the clique
		public void addVertex(int vertex) {
			this.vertices.add(vertex);
			this.size++;
		}

		// Method to print the clique
		@Override
		public String toString() {
			return "Clique: " + vertices + " | Size: " + size;
		}
	}

	// Method to check if a new vertex can be added to the current clique
	private static boolean isSafe(int v, List<Integer> clique, boolean[][] graph) {
		// Check if vertex 'v' is connected to all vertices in the current clique
		for (int vertex : clique) {
			if (graph[vertex][v] == false) {
				return false;  // If any vertex in the clique is not connected to 'v', return false
			}
		}
		return true;  // If all vertices in the clique are connected to 'v', return true
	}

	// Recursive backtracking method to find the maximum clique
	private static Clique findMaximumClique(boolean[][] graph, List<Integer> currentClique, int start) {
		Clique maxClique = new Clique(currentClique);  // Start with the current clique as the best solution

		// Try adding each possible vertex to the current clique
		for (int v = start; v < graph.length; v++) {
			if (isSafe(v, currentClique, graph)) {  // Check if 'v' can be added to the current clique
				currentClique.add(v);  // Add vertex 'v' to the clique
				Clique newClique = findMaximumClique(graph, currentClique, v + 1);  // Recurse with the new clique

				// If the new clique is larger, update the maximum clique
				if (newClique.size > maxClique.size) {
					maxClique = newClique;
				}

				// Backtrack: Remove the last vertex added to the clique
				currentClique.remove(currentClique.size() - 1);
			}
		}

		return maxClique;  // Return the largest clique found
	}

	// Method to solve the Maximum Clique Problem using backtracking
	public static Clique solveMaximumClique(boolean[][] graph) {
		List<Integer> initialClique = new ArrayList<>();  // Start with an empty clique
		return findMaximumClique(graph, initialClique, 0);  // Find the maximum clique starting from vertex 0
	}

	// Main method to test the Maximum Clique implementation
	public static void main(String[] args) {
		// Example graph (adjacency matrix representation)
		// A graph with 8 vertices (represented as a 2D array where 'true' means an edge exists)
		boolean[][] graph = {
				{false, true, true, true, true, false},  // Vertex 0
				{true, false, true, true, true, false},  // Vertex 1
				{true, true, false, true, true, false},  // Vertex 2
				{true, true, true, false, true, true},   // Vertex 3
				{true, true, true, true, false, true},   // Vertex 4
				{false, false, false, true, true, false} // Vertex 5
		};

		// Solve the Maximum Clique Problem
		Clique maxClique = solveMaximumClique(graph);  // Call the method to find the maximum clique

		// Print the result
		System.out.println("Maximum Clique found:");
		System.out.println(maxClique);  // Output the maximum clique
	}
}
