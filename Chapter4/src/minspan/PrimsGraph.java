package minspan;

import java.util.*;

// Class representing a graph
public class PrimsGraph {
	private int V; // Number of vertices
	private List<List<Edge>> adj; // Adjacency lists

	// Inner class representing an edge
	class Edge {
		int dest;
		int weight;

		Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

	// Constructor
	PrimsGraph(int v) {
		V = v;
		adj = new ArrayList<>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>());
	}

	// Function to add an undirected edge to the graph
	void addEdge(int src, int dest, int weight) {
		adj.get(src).add(new Edge(dest, weight));
		adj.get(dest).add(new Edge(src, weight)); // Add dest to src's list and vice versa for an undirected graph
	}

	// Function to find the minimum spanning tree using Prim's algorithm
	void primMST() {
		boolean[] inMST = new boolean[V]; // To track vertices included in MST
		int[] parent = new int[V]; // To store parent of each vertex in MST
		int[] key = new int[V]; // To store key values used to pick minimum weight edge

		// Initialize key values to infinity and inMST to false
		Arrays.fill(key, Integer.MAX_VALUE);
		Arrays.fill(inMST, false);

		// Start with vertex 0
		key[0] = 0; // Key value of source vertex is 0
		parent[0] = -1; // Source vertex is the root of MST

		// Construct MST with V-1 edges
		for (int count = 0; count < V - 1; ++count) {
			// Find vertex with minimum key value that is not yet included in MST
			int u = minKey(key, inMST);

			// Add the selected vertex to MST
			inMST[u] = true;

			// Update key value and parent index of adjacent vertices
			for (Edge e : adj.get(u)) {
				int v = e.dest;
				int weight = e.weight;
				if (!inMST[v] && weight < key[v]) {
					parent[v] = u;
					key[v] = weight;
				}
			}
		}

		// Print the constructed MST
		printMST(parent);
	}

	// Function to find the vertex with minimum key value
	int minKey(int[] key, boolean[] inMST) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int v = 0; v < V; ++v)
			if (!inMST[v] && key[v] < min) {
				min = key[v];
				minIndex = v;
			}
		return minIndex;
	}

	// Function to print the constructed MST
	void printMST(int[] parent) {
		System.out.println("Edges of Minimum Spanning Tree:");
		for (int i = 1; i < V; ++i)
			System.out.println(parent[i] + " - " + i);
	}

	public static void main(String[] args) {
		// Create a graph
		PrimsGraph g = new PrimsGraph(8);

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
		g.addEdge(5, 6, -7);

		// Find minimum spanning tree using Prim's algorithm
		g.primMST();
	}
}
