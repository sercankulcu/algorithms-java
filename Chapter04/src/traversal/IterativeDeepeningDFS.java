package traversal;

import java.util.*;

//Class to represent a graph and perform IDDFS
public class IterativeDeepeningDFS {
	private int vertices; // Number of vertices in the graph
	private List<List<Integer>> adjList; // Adjacency list to store the graph

	// Constructor to initialize the graph
	public IterativeDeepeningDFS(int vertices) {
		this.vertices = vertices;
		adjList = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			adjList.add(new ArrayList<>());
		}
	}

	// Method to add an edge to the graph (undirected)
	public void addEdge(int v1, int v2) {
		adjList.get(v1).add(v2);
		adjList.get(v2).add(v1); // For undirected graph
	}

	// Depth-Limited Search (DLS) used as a subroutine in IDDFS
	private boolean depthLimitedSearch(int node, int target, int depth, boolean[] visited) {
		// If current depth is 0, stop exploring this path
		if (depth < 0) {
			return false;
		}

		// If the current node is the target, return true
		if (node == target) {
			return true;
		}

		// Mark the current node as visited
		visited[node] = true;

		// Explore all adjacent nodes
		for (int neighbor : adjList.get(node)) {
			// Skip if the neighbor is already visited
			if (!visited[neighbor]) {
				// Recursively perform DLS on the neighbor with reduced depth
				if (depthLimitedSearch(neighbor, target, depth - 1, visited)) {
					return true;
				}
			}
		}

		return false;
	}

	// Iterative Deepening DFS main method
	public boolean iterativeDeepeningDFS(int start, int target, int maxDepth) {
		// Iterate through all depths from 0 to maxDepth
		for (int depth = 0; depth <= maxDepth; depth++) {
			// Initialize visited array for this depth
			boolean[] visited = new boolean[vertices];
			System.out.println("Exploring with depth limit: " + depth);

			// Perform depth-limited search
			if (depthLimitedSearch(start, target, depth, visited)) {
				System.out.println("Target " + target + " found at depth <= " + depth);
				return true;
			}
		}

		// If target is not found within maxDepth
		System.out.println("Target " + target + " not found within depth " + maxDepth);
		return false;
	}

	// Main method to test the IDDFS implementation
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		IterativeDeepeningDFS g = new IterativeDeepeningDFS(8);

		// Add edges to the graph (creating connections between vertices)
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 7);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);

		// Perform IDDFS starting from node 0, searching for node 6, with max depth 3
		g.iterativeDeepeningDFS(0, 6, 3);
		
		g.iterativeDeepeningDFS(0, 6, 4);
	}
}