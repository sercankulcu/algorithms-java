package shortest;

import java.util.*;

public class BellmanFord {
	// Inner class representing an edge in the graph
	class Edge {
		int src;   // Source vertex
		int dest;  // Destination vertex
		int weight; // Weight of the edge

		Edge() {
			src = dest = weight = 0; // Default values
		}
	};

	int V; // Number of vertices in the graph
	List<List<Edge>> adj; // Adjacency list to store edges for each vertex

	// Constructor to initialize the graph
	BellmanFord(int v) {
		V = v; // Set the number of vertices
		adj = new ArrayList<>(v); // Initialize adjacency list
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>()); // Create an empty list for each vertex
	}

	// Method to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		Edge edge = new Edge(); // Create a new edge
		edge.src = src; // Set the source vertex
		edge.dest = dest; // Set the destination vertex
		edge.weight = weight; // Set the weight of the edge
		adj.get(src).add(edge); // Add the edge to the adjacency list of the source vertex
	}

	// Method to find the shortest paths from the source vertex to all other vertices
	void bellmanFord(int src) {
		int[] dist = new int[V]; // Array to store the shortest distance from the source to each vertex
		int[] parent = new int[V]; // Array to store the parent of each vertex in the shortest path

		// Step 1: Initialize distances from the source to all other vertices as infinite
		Arrays.fill(dist, Integer.MAX_VALUE); // Set all distances to infinity
		dist[src] = 0; // Distance to the source is 0
		parent[src] = 0; // The parent of the source is itself

		// Step 2: Relax all edges |V| - 1 times
		for (int i = 1; i < V; ++i) { // Repeat the process for V-1 iterations
			System.out.println("* Relaxing edges for vertex " + i);
			int j = 0;
			for (List<Edge> edges : adj) { // Traverse adjacency lists
				System.out.println("** Processing adjacency list of vertex " + j++);
				for (Edge edge : edges) { // Traverse all edges of the current vertex
					int u = edge.src; // Source vertex of the edge
					int v = edge.dest; // Destination vertex of the edge
					int weight = edge.weight; // Weight of the edge
					System.out.println("*** Processing edge " + u + " -> " + v + " with weight " + weight);
					// Relax the edge if a shorter path is found
					if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
						System.out.print("**** Found shorter path to vertex " + v + ": " + dist[v] + " -> ");
						dist[v] = dist[u] + weight; // Update the distance
						parent[v] = u; // Update the parent
						System.out.println(dist[v]);
					}
				}
			}
		}

		// Step 3: Check for negative-weight cycles
		for (List<Edge> edges : adj) { // Traverse all edges in the graph
			for (Edge edge : edges) {
				int u = edge.src;
				int v = edge.dest;
				int weight = edge.weight;
				// If a shorter path is still found, a negative-weight cycle exists
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					System.out.println("Graph contains a negative-weight cycle");
					return; // Exit the function
				}
			}
		}

		// Print the shortest distances and paths
		printSolution(src, dist, parent);
	}

	// Method to print the shortest distances and paths from the source
	void printSolution(int src, int[] dist, int[] parent) {
		System.out.println("Shortest paths from vertex " + src + ":");
		for (int i = 0; i < V; ++i) {
			System.out.print("(" + src + " --> " + i + "): distance " + dist[i]);
			System.out.print("\nShortest Path: ");
			// Use a stack to store the path in reverse order
			Stack<Integer> pathStack = new Stack<>();
			for (int j = i; j != 0; j = parent[j]) {
				pathStack.push(j); // Add each vertex to the stack
			}
			pathStack.push(src); // Add the source vertex to the stack

			// Print the path in the correct order
			while (!pathStack.isEmpty()) {
				System.out.print(pathStack.pop());
				if (!pathStack.isEmpty()) {
					System.out.print(" -> ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// Create a graph with 8 vertices
		BellmanFord g = new BellmanFord(8);

		// Add edges with weights
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 4);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 6);
		g.addEdge(3, 5, 4);
		g.addEdge(4, 6, -3); // Negative weight edge
		g.addEdge(5, 6, 1);

		// Run Bellman-Ford algorithm from vertex 0
		g.bellmanFord(0);
	}
}
