package minspan;

import java.util.*;

// Class representing a graph for Borůvka's algorithm
public class Boruvkas {
	private int V; // Number of vertices in the graph
	private List<Edge> edges; // List to store all edges of the graph

	// Inner class to represent an edge in the graph
	class Edge {
		int src;   // Source vertex of the edge
		int dest;  // Destination vertex of the edge
		int weight; // Weight of the edge

		// Constructor to initialize an edge
		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}

	// Constructor to initialize the graph
	Boruvkas(int v) {
		V = v; // Set the number of vertices
		edges = new ArrayList<>(); // Initialize the list of edges
	}

	// Method to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		edges.add(new Edge(src, dest, weight)); // Add a new edge to the list
	}

	// Function to find the Minimum Spanning Tree (MST) using Borůvka's algorithm
	void boruvkaMST() {
		// Parent array to keep track of connected components
		int[] parent = new int[V];
		List<Edge> result = new ArrayList<>(); // List to store the edges of the MST

		// Initialize each vertex as its own parent (separate component)
		for (int i = 0; i < V; ++i)
			parent[i] = i;

		// Array to keep track of the cheapest edge for each component
		int[] cheapest = new int[V];
		Arrays.fill(cheapest, -1); // Initialize all cheapest edges as -1

		int numTrees = V; // Initially, each vertex is its own component
		// Loop until the graph is reduced to one component (MST)
		while (numTrees > 1) {
			// Find the cheapest edge for each component
			for (int i = 0; i < edges.size(); ++i) {
				int set1 = find(parent, edges.get(i).src); // Find the component of the source vertex
				int set2 = find(parent, edges.get(i).dest); // Find the component of the destination vertex

				// Skip if both vertices belong to the same component
				if (set1 == set2)
					continue;

				// Update the cheapest edge for the component of the source vertex
				if (cheapest[set1] == -1 || edges.get(i).weight < edges.get(cheapest[set1]).weight)
					cheapest[set1] = i;

				// Update the cheapest edge for the component of the destination vertex
				if (cheapest[set2] == -1 || edges.get(i).weight < edges.get(cheapest[set2]).weight)
					cheapest[set2] = i;
			}

			// Add the cheapest edges to the MST
			for (int i = 0; i < V; ++i) {
				if (cheapest[i] != -1) {
					int set1 = find(parent, edges.get(cheapest[i]).src); // Component of source vertex
					int set2 = find(parent, edges.get(cheapest[i]).dest); // Component of destination vertex

					// Skip if both vertices belong to the same component
					if (set1 == set2)
						continue;

					// Add the edge to the MST
					result.add(edges.get(cheapest[i]));

					// Merge the two components
					union(parent, set1, set2);

					// Decrease the number of components
					numTrees--;
				}
			}

			// Reset the cheapest array for the next iteration
			Arrays.fill(cheapest, -1);
		}

		// Print the edges of the constructed MST
		printMST(result);
	}

	// Function to find the component (subset) of a vertex using path compression
	int find(int[] parent, int i) {
		if (parent[i] != i) // If i is not the root of its component
			parent[i] = find(parent, parent[i]); // Recursively find the root and perform path compression
		return parent[i];
	}

	// Function to perform the union of two subsets (components)
	void union(int[] parent, int x, int y) {
		int xSet = find(parent, x); // Find the root of the first subset
		int ySet = find(parent, y); // Find the root of the second subset
		parent[xSet] = ySet; // Merge the two subsets by updating the parent
	}

	// Function to print the edges of the MST
	void printMST(List<Edge> result) {
		System.out.println("Edges of Minimum Spanning Tree:");
		int total = 0; // Variable to track the total weight of the MST
		for (Edge edge : result) {
			System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight); // Print each edge
			total += edge.weight; // Add the weight of the edge to the total
		}
		System.out.println("Total weight is: " + total); // Print the total weight of the MST
	}

	// Main method to test Borůvka's algorithm
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		Boruvkas g = new Boruvkas(8);

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
		g.boruvkaMST();
	}
}
