package shortest;

import java.util.*;

// Class representing a graph
public class DijkstraGraph {
	private int V; // Number of vertices
	private List<List<Edge>> adj; // Adjacency list representation

	// Inner class to represent an edge
	class Edge {
		int dest;
		int weight;

		Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

	// Constructor
	DijkstraGraph(int v) {
		V = v;
		adj = new ArrayList<>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>());
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		adj.get(src).add(new Edge(dest, weight)); // Add dest to src's list with weight
	}

	// Function to find the shortest paths from a given source vertex
	void dijkstra(int src) {
		// Priority queue to store vertices and their tentative distances
		PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.dist));

		// Array to keep track of shortest distances from src
		int[] dist = new int[V];
		int[] parent = new int[V];

		// Array to keep track of visited vertices
		boolean[] visited = new boolean[V];

		// Initialize distances from src to all vertices as infinity
		Arrays.fill(dist, Integer.MAX_VALUE);

		// Set distance of source vertex to itself as 0
		dist[src] = 0;
		parent[src] = 0;

		// Add source vertex to priority queue
		pq.add(new Node(src, 0));

		// Loop until priority queue is empty
		while (!pq.isEmpty()) {
			// Extract the vertex with minimum distance value
			int u = pq.poll().vertex;

			// Mark vertex as visited
			visited[u] = true;
			System.out.println("*   " + u + " is extracted from queue");

			// Update distances of adjacent vertices of the extracted vertex
			for (Edge e : adj.get(u)) {
				int v = e.dest;
				int weight = e.weight;
				System.out.println("**  edge " + u + "->" + v + " with weight " + weight);

				// If shortest path to v through u is shorter, update it
				if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					System.out.print("*** shorter path found " + dist[v] + "->");
					dist[v] = dist[u] + weight;
					parent[v] = u;
					System.out.println(dist[v]);
					pq.add(new Node(v, dist[v]));
				}
			}
		}

		// Print shortest distances from src to all other vertices
		for (int i = 0; i < V; ++i) {
			System.out.print("(" + src + " --> " + i + "): " + dist[i] + " path: ");
			for(int j = i; j > 0; j = parent[j]) {
				System.out.print(" -> " + parent[j]);
			}
			System.out.println();
		}
	}

	// Inner class representing a node in the priority queue
	class Node {
		int vertex;
		int dist;

		Node(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}
	}

	public static void main(String[] args) {
		// Create a graph
		DijkstraGraph g = new DijkstraGraph(8);

		// Add edges to the graph
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 4);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 6);
		g.addEdge(3, 5, 4);
		g.addEdge(4, 6, 3);
		g.addEdge(5, 6, 1);

		// Find shortest paths from source vertex 0
		System.out.println("Shortest paths from vertex 0:");
		g.dijkstra(0);
	}
}
