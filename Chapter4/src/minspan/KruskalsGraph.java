package minspan;

import java.util.*;

// Class representing a graph
public class KruskalsGraph {
	private int V; // Number of vertices
	private int E; // Number of edges
	private List<Edge> edges; // List of edges

	// Inner class representing an edge
	class Edge implements Comparable<Edge> {
		int src;
		int dest;
		int weight;

		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		// Compare two edges based on their weights
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	}

	// Constructor
	KruskalsGraph(int v) {
		V = v;
		edges = new ArrayList<>();
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		edges.add(new Edge(src, dest, weight));
	}

	// Function to find the minimum spanning tree using Kruskal's algorithm
	void kruskalMST() {
		// Initialize parent array and result list
		int[] parent = new int[V];
		List<Edge> result = new ArrayList<>();

		// Sort all edges in non-decreasing order of their weights
		Collections.sort(edges);

		// Create disjoint sets with single elements
		for (int i = 0; i < V; ++i)
			parent[i] = i;

		// Index variable for result array
		int index = 0;

		// Number of edges to be taken is equal to V-1
		while (result.size() < V - 1) {
			// Pick the smallest edge
			Edge nextEdge = edges.get(index++);

			int x = find(parent, nextEdge.src);
			int y = find(parent, nextEdge.dest);

			// If including this edge does not cause cycle, include it in result
			if (x != y) {
				result.add(nextEdge);
				union(parent, x, y);
			}
		}

		// Print the constructed MST
		printMST(result);
	}

	// Function to find the subset of an element i
	int find(int[] parent, int i) {
		if (parent[i] != i)
			parent[i] = find(parent, parent[i]);
		return parent[i];
	}

	// Function to perform union of two subsets
	void union(int[] parent, int x, int y) {
		int xSet = find(parent, x);
		int ySet = find(parent, y);
		parent[xSet] = ySet;
	}

	// Function to print the constructed MST
	void printMST(List<Edge> result) {
		System.out.println("Edges of Minimum Spanning Tree:");
		for (Edge edge : result)
			System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
	}

	public static void main(String[] args) {
		
		KruskalsGraph g = new KruskalsGraph(8);

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

		// Find minimum spanning tree using Kruskal's algorithm
		g.kruskalMST();
	}
}
