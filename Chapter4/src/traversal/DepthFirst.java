package traversal;

import java.util.*;

// Class representing a graph that supports DFS (Depth-First Search) traversal
public class DepthFirst {
	private int V; // Number of vertices in the graph
	private LinkedList<Integer>[] adj; // Adjacency lists for each vertex

	// Constructor to initialize the graph with a specified number of vertices
	DepthFirst (int v) {
		V = v; // Set the number of vertices
		adj = new LinkedList[v]; // Initialize the adjacency list array

		// Initialize each adjacency list in the array (each list will store the adjacent vertices of a given vertex)
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList(); // Each vertex gets an empty list initially
	}

	// Function to add an edge between two vertices v and w
	void addEdge(int v, int w) {
		adj[v].add(w); // Add vertex w to the adjacency list of vertex v
	}

	// Helper function to perform DFS traversal from a given vertex v
	// This function uses recursion to visit all adjacent vertices
	void DFSUtil(int v, boolean[] visited) {
		// Mark the current vertex as visited and print it
		visited[v] = true; 
		System.out.print(v + " -> "); // Output the current vertex during traversal

		// Recur for all the vertices adjacent to the current vertex v
		Iterator<Integer> i = adj[v].listIterator(); // Get an iterator for the adjacency list of vertex v
		while (i.hasNext()) {
			int n = i.next(); // Get the next adjacent vertex
			if (!visited[n]) // If the adjacent vertex has not been visited
				DFSUtil(n, visited); // Recursively call DFSUtil for the adjacent vertex
		}
	}

	// Function to perform DFS traversal starting from a given source vertex v
	void DFS(int v) {
		// Create a boolean array to keep track of visited vertices
		boolean[] visited = new boolean[V]; // All vertices are initially marked as not visited

		// Call the recursive helper function to perform the DFS traversal
		DFSUtil(v, visited); // Start DFS traversal from vertex v
	}

	// Main method to test the DFS traversal
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		DepthFirst  g = new DepthFirst (8);

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

		// Print a message indicating the start of DFS traversal
		System.out.println("Depth First Traversal:");

		// Perform DFS traversal starting from vertex 0
		g.DFS(0); // Output the DFS traversal starting from vertex 0
	}
}
