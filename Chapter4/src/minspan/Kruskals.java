package minspan;

import java.util.*;

// Class representing a graph for Kruskal's algorithm
public class Kruskals {
	private int V; // Number of vertices in the graph
	private List<Edge> edges; // List to store all edges of the graph

	// Inner class representing an edge in the graph
	class Edge implements Comparable<Edge> {
		int src;   // Source vertex of the edge
		int dest;  // Destination vertex of the edge
		int weight; // Weight of the edge

		// Constructor to initialize an edge
		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		// Compare edges based on their weights (used for sorting)
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	}

	// Constructor to initialize the graph
	Kruskals(int v) {
		V = v; // Set the number of vertices
		edges = new ArrayList<>(); // Initialize the list of edges
	}

	// Method to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		edges.add(new Edge(src, dest, weight)); // Add the edge to the edge list
	}

	// Function to find the Minimum Spanning Tree (MST) using Kruskal's algorithm
	void kruskalMST() {
		// Array to represent disjoint sets for union-find operations
		int[] parent = new int[V];
		List<Edge> result = new ArrayList<>(); // List to store edges of the MST

		// Step 1: Sort all edges in non-decreasing order of their weights
		Collections.sort(edges);

		// Step 2: Initialize each vertex as its own parent (single-element set)
		for (int i = 0; i < V; ++i)
			parent[i] = i;

		// Variable to keep track of the index of the edge being considered
		int index = 0;

		// The MST will have exactly V-1 edges
		while (result.size() < V - 1) {
			// Pick the smallest edge (next edge in the sorted list)
			Edge nextEdge = edges.get(index++);

			// Find the components (sets) of the source and destination vertices
			int x = find(parent, nextEdge.src);
			int y = find(parent, nextEdge.dest);

			// If the source and destination belong to different components,
			// including this edge will not form a cycle
			if (x != y) {
				// Add this edge to the result (MST)
				result.add(nextEdge);

				// Merge the two components (union operation)
				union(parent, x, y);
			}
		}

		// Step 3: Print the edges and total weight of the MST
		printMST(result);
	}

	// Function to find the root of the set containing element i (with path compression)
	int find(int[] parent, int i) {
		if (parent[i] != i) // If i is not the root of its set
			parent[i] = find(parent, parent[i]); // Recursively find and compress the path
		return parent[i];
	}

	// Function to perform union of two sets containing elements x and y
	void union(int[] parent, int x, int y) {
		int xSet = find(parent, x); // Find the root of the set containing x
		int ySet = find(parent, y); // Find the root of the set containing y
		parent[xSet] = ySet; // Merge the two sets by updating the parent
	}

	// Function to print the edges and total weight of the MST
	void printMST(List<Edge> result) {
		int total = 0; // Variable to store the total weight of the MST
		System.out.println("Edges of Minimum Spanning Tree:");
		for (Edge edge : result) {
			// Print each edge of the MST
			System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
			total += edge.weight; // Add the weight of the edge to the total
		}
		// Print the total weight of the MST
		System.out.println("Total weight is: " + total);
	}

	// Main method to test Kruskal's algorithm
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		Kruskals g = new Kruskals(8);

		// Add edges with weights to the graph
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 5);
		g.addEdge(3, 4, 6);
		g.addEdge(3, 5, 6);
		g.addEdge(4, 6, 2);
		g.addEdge(4, 7, 3);
		g.addEdge(5, 6, 7);

		// Find and print the Minimum Spanning Tree
		g.kruskalMST();
	}
}
