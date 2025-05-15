package traversal;

import java.util.*;

//Class to represent the graph
public class TopologicalSort {
	// Number of vertices
	private int V;
	// Adjacency list representation
	private List<List<Integer>> adj;

	// Constructor
	public TopologicalSort(int v) {
		this.V = v;
		adj = new ArrayList<>(v);
		// Initialize adjacency list for each vertex
		for (int i = 0; i < v; ++i) {
			adj.add(new ArrayList<>());
		}
	}

	// Method to add an edge to the graph (u -> v)
	public void addEdge(int u, int v) {
		adj.get(u).add(v);
	}

	// Method to perform Topological Sort using Kahn's Algorithm
	public List<Integer> topologicalSort() {
		// Array to store in-degree of each vertex
		int[] inDegree = new int[V];

		// Calculate in-degrees for all vertices
		// Iterate through all vertices
		for (int u = 0; u < V; ++u) {
			// For each vertex, iterate through its neighbors
			for (int v : adj.get(u)) {
				// Increment the in-degree of the neighbor
				inDegree[v]++;
			}
		}

		// Queue to store vertices with in-degree 0
		Queue<Integer> q = new LinkedList<>();
		// Add all vertices with in-degree 0 to the queue
		for (int i = 0; i < V; ++i) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}

		// List to store the result (topologically sorted vertices)
		List<Integer> result = new ArrayList<>();
		// Counter for visited vertices
		int visitedCount = 0;

		// Process vertices in the queue
		while (!q.isEmpty()) {
			// Get a vertex from the front of the queue
			int u = q.poll();
			// Add the vertex to the result list
			result.add(u);
			// Increment the count of visited vertices
			visitedCount++;

			// Reduce the in-degree of all neighbors of the processed vertex
			// Iterate through all neighbors of u
			for (int v : adj.get(u)) {
				// Decrement the in-degree of neighbor v
				inDegree[v]--;
				// If the in-degree of neighbor v becomes 0, add it to the queue
				if (inDegree[v] == 0) {
					q.add(v);
				}
			}
		}

		// Check if topological sort is possible (graph must be a DAG)
		// If the number of visited vertices is not equal to the total number of vertices,
		// it means there is a cycle in the graph.
		if (visitedCount != V) {
			System.out.println("Graph contains a cycle, Topological Sort not possible.");
			return new ArrayList<>(); // Return an empty list or handle error as needed
		}

		// Return the topologically sorted list of vertices
		return result;
	}

	// Main method for demonstration
	public static void main(String[] args) {
		// Create a graph with 6 vertices
		TopologicalSort g = new TopologicalSort(8);
		// Add edges
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 7);
		g.addEdge(1, 2);
		//g.addEdge(2, 0); // cycle
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);

		// Perform topological sort
		List<Integer> result = g.topologicalSort();

		// Print the topological sort result
		if (!result.isEmpty()) {
			System.out.println("Topological Sort Order:");
			for (int vertex : result) {
				System.out.print(vertex + " ");
			}
			System.out.println();
		}
	}
}