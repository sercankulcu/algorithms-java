package minspan;

import java.util.*;

// Class representing a graph for Prim's algorithm
public class Prims {
	private int V; // Number of vertices in the graph
	private List<List<Edge>> adj; // Adjacency list to represent the graph

	// Inner class representing an edge
	class Edge {
		int dest;   // Destination vertex of the edge
		int weight; // Weight of the edge

		// Constructor to initialize an edge
		Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

	// Constructor to initialize the graph
	Prims(int v) {
		V = v; // Set the number of vertices
		adj = new ArrayList<>(v); // Initialize adjacency lists for all vertices
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>()); // Add an empty list for each vertex
	}

	// Function to add an undirected edge to the graph
	void addEdge(int src, int dest, int weight) {
		adj.get(src).add(new Edge(dest, weight)); // Add edge from src to dest
		adj.get(dest).add(new Edge(src, weight)); // Add edge from dest to src (undirected graph)
	}

	// Function to find the Minimum Spanning Tree (MST) using Prim's algorithm
	void primMST() {
		// Array to track vertices included in MST
		boolean[] inMST = new boolean[V];

		// Array to store the parent of each vertex in the MST
		int[] parent = new int[V];

		// Array to store key values used to pick the minimum weight edge
		int[] key = new int[V];

		// Initialize all key values to infinity and mark all vertices as not in MST
		Arrays.fill(key, Integer.MAX_VALUE);
		Arrays.fill(inMST, false);

		// Start with vertex 0
		key[0] = 0; // Key value of source vertex is 0
		parent[0] = -1; // Source vertex is the root of MST

		// Construct MST with V-1 edges
		for (int count = 0; count < V - 1; ++count) {
			// Step 1: Pick the vertex with the minimum key value that is not yet included in MST
			int u = minKey(key, inMST);

			// Step 2: Mark the picked vertex as included in MST
			inMST[u] = true;

			// Step 3: Update key values and parent indices for adjacent vertices of the picked vertex
			for (Edge e : adj.get(u)) {
				int v = e.dest; // Adjacent vertex
				int weight = e.weight; // Weight of the edge u-v

				// Update if the edge weight is smaller than the current key value
				if (!inMST[v] && weight < key[v]) {
					parent[v] = u; // Update parent
					key[v] = weight; // Update key value
				}
			}
		}

		// Print the constructed MST
		printMST(parent);
	}

	// Function to find the vertex with the minimum key value
	int minKey(int[] key, boolean[] inMST) {
		int min = Integer.MAX_VALUE; // Initialize minimum value
		int minIndex = -1; // Index of the vertex with minimum key value

		// Traverse through all vertices to find the minimum key value
		for (int v = 0; v < V; ++v) {
			if (!inMST[v] && key[v] < min) { // Consider vertices not in MST
				min = key[v];
				minIndex = v;
			}
		}
		return minIndex;
	}

	// Function to print the edges and total weight of the MST
	void printMST(int[] parent) {
		System.out.println("Edges of Minimum Spanning Tree:");
		int total = 0; // Variable to store the total weight of the MST

		// Traverse through the parent array to print edges and calculate total weight
		for (int i = 1; i < V; ++i) {
			for (Edge e : adj.get(i)) {
				if (e.dest == parent[i]) { // Check for the correct edge in the adjacency list
					System.out.println(i + " -> " + parent[i] + " w: " + e.weight);
					total += e.weight; // Add the weight of the edge to the total
				}
			}
		}
		// Print the total weight of the MST
		System.out.println("Total weight is: " + total);
	}

	// Main method to test Prim's algorithm
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		Prims g = new Prims(8);

		// Add edges to the graph
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
		g.primMST();
	}
}
