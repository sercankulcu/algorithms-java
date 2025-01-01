package traversal;

import java.util.*;

// Class representing a graph that supports BFS (Breadth-First Search) traversal
public class BreadthFirst {
	private int V; // Number of vertices in the graph
	private LinkedList<Integer>[] adj; // Adjacency lists to represent the graph

	// Constructor to initialize the graph with a specified number of vertices
	BreadthFirst(int v) {
		V = v; // Set the number of vertices
		adj = new LinkedList[v]; // Initialize the adjacency list array

		// Initialize each adjacency list in the array
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<>(); // Each vertex gets an empty list initially
	}

	// Function to add an edge between two vertices v and w
	void addEdge(int v, int w) {
		adj[v].add(w); // Add vertex w to the adjacency list of vertex v
	}

	// Function to perform BFS traversal starting from vertex s
	void BFS(int s) {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V]; // Create a boolean array to track visited vertices

		// Create a queue for BFS, which will help in the level-wise traversal
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the starting vertex s as visited and enqueue it
		visited[s] = true;
		queue.add(s); // Enqueue the starting vertex

		// Perform BFS traversal
		while (!queue.isEmpty()) {
			// Dequeue a vertex from the queue and process it (i.e., print it)
			s = queue.poll(); // Remove the front element from the queue
			System.out.print(s + " -> "); // Print the current vertex

			// Get all adjacent vertices of the dequeued vertex s
			// If an adjacent vertex has not been visited, mark it as visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator(); // Iterate through the adjacency list of vertex s
			while (i.hasNext()) {
				int n = i.next(); // Get the adjacent vertex
				if (!visited[n]) { // If the adjacent vertex has not been visited
					visited[n] = true; // Mark it as visited
					queue.add(n); // Enqueue the adjacent vertex
				}
			}
		}
	}

	// Main function to test BFS traversal
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		BreadthFirst g = new BreadthFirst(8);

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

		System.out.println("Breadth First Traversal:");

		// Perform BFS traversal starting from vertex 0
		g.BFS(0); // Output the BFS traversal starting from vertex 0
	}
}
