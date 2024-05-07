package shortest;

import java.util.*;

public class BellmanFordGraph {
	// Inner class representing an edge
	class Edge {
		int src;
		int dest;
		int weight;

		Edge() {
			src = dest = weight = 0;
		}
	};

	int V; // Number of vertices
	List<List<Edge>> adj; // Adjacency list representation

	// Constructor
	BellmanFordGraph(int v) {
		V = v;
		adj = new ArrayList<>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>());
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		Edge edge = new Edge();
		edge.src = src;
		edge.dest = dest;
		edge.weight = weight;
		adj.get(src).add(edge);
	}

	// Function to find the shortest paths from a given source vertex
	void bellmanFord(int src) {
		int[] dist = new int[V];
		int[] parent = new int[V];

		// Step 1: Initialize distances from src to all other vertices as INFINITE
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		parent[src] = 0;

		// Step 2: Relax all edges |V| - 1 times
		for (int i = 1; i < V; ++i) {
			System.out.println("* for vertex " + i);
			int j = 0;
			for (List<Edge> edges : adj) {
				System.out.println("** for adjacency of " + j++);
				for (Edge edge : edges) {
					int u = edge.src;
					int v = edge.dest;
					int weight = edge.weight;
					System.out.println("*** for edge " + u + " " + v + " " + weight);
					if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
						System.out.print("**** shorter path found " + dist[v] + " -> ");
						dist[v] = dist[u] + weight;
						parent[v] = u;
						System.out.println(dist[v]);
					}
				}
			}
		}

		// Step 3: Check for negative-weight cycles
		for (List<Edge> edges : adj) {
			for (Edge edge : edges) {
				int u = edge.src;
				int v = edge.dest;
				int weight = edge.weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					System.out.println("Graph contains negative-weight cycle");
					return;
				}
			}
		}

		// Print the shortest distances
		printSolution(src, dist, parent);
	}

	// Function to print the solution
	void printSolution(int src, int[] dist, int[] parent) {
		System.out.println("Shortest paths from vertex 0:");
		for (int i = 0; i < V; ++i) {
			System.out.print("(" + src + " --> " + i + "): " + dist[i] + " path: ");
			for(int j = i; j > 0; j = parent[j]) {
				System.out.print(" -> " + parent[j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		BellmanFordGraph g = new BellmanFordGraph(8);

		// Add edges to the graph
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 4);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 6);
		g.addEdge(3, 5, 4);
		g.addEdge(4, 6, -3); // negative weight
		g.addEdge(5, 6, 1); 

		// Run Bellman-Ford algorithm
		g.bellmanFord(0);
	}
}
