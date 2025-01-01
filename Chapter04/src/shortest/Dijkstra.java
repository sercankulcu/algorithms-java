package shortest;

import java.util.*;

// Class representing a graph and implementing Dijkstra's algorithm
public class Dijkstra {
	private int V; // Number of vertices in the graph
	private List<List<Edge>> adj; // Adjacency list representation of the graph

	// Inner class to represent an edge in the graph
	class Edge {
		int dest; // Destination vertex of the edge
		int weight; // Weight of the edge

		Edge(int dest, int weight) {
			this.dest = dest; // Initialize destination vertex
			this.weight = weight; // Initialize edge weight
		}
	}

	// Constructor to initialize the graph
	Dijkstra(int v) {
		V = v; // Set the number of vertices
		adj = new ArrayList<>(v); // Create an adjacency list for each vertex
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>()); // Initialize each adjacency list
	}

	// Function to add a directed edge with a weight to the graph
	void addEdge(int src, int dest, int weight) {
		adj.get(src).add(new Edge(dest, weight)); // Add edge from src to dest with weight
	}

	// Function to find the shortest paths from a given source vertex using Dijkstra's algorithm
	void dijkstra(int src) {
		// Priority queue to store vertices and their tentative distances
		PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.dist));

		// Array to keep track of shortest distances from the source vertex
		int[] dist = new int[V];
		// Array to store the parent of each vertex for path reconstruction
		int[] parent = new int[V];
		// Array to keep track of visited vertices
		boolean[] visited = new boolean[V];

		// Initialize distances to all vertices as infinity
		Arrays.fill(dist, Integer.MAX_VALUE);

		// Distance to the source vertex is 0, and it is its own parent
		dist[src] = 0;
		parent[src] = src;

		// Add the source vertex to the priority queue
		pq.add(new Node(src, 0));

		// Loop until the priority queue is empty
		while (!pq.isEmpty()) {
			// Extract the vertex with the smallest distance
			int u = pq.poll().vertex;

			// Mark the vertex as visited
			visited[u] = true;
			System.out.println("*   " + u + " is extracted from queue");

			// Update distances of adjacent vertices
			for (Edge e : adj.get(u)) {
				int v = e.dest; // Adjacent vertex
				int weight = e.weight; // Weight of the edge

				System.out.println("**  edge " + u + "->" + v + " with weight " + weight);

				// Check if a shorter path to v exists through u
				if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					System.out.print("*** shorter path found " + dist[v] + "->");
					dist[v] = dist[u] + weight; // Update distance
					parent[v] = u; // Update parent
					System.out.println(dist[v]);
					pq.add(new Node(v, dist[v])); // Add the updated vertex to the queue
				}
			}
		}


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

	// Inner class representing a node in the priority queue
	class Node {
		int vertex; // Vertex number
		int dist; // Distance from the source

		Node(int vertex, int dist) {
			this.vertex = vertex; // Initialize vertex
			this.dist = dist; // Initialize distance
		}
	}

	// Main function to demonstrate Dijkstra's algorithm
	public static void main(String[] args) {
		// Create a graph with 8 vertices
		Dijkstra g = new Dijkstra(8);

		// Add directed edges with weights to the graph
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 4);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 6);
		g.addEdge(3, 5, 4);
		g.addEdge(4, 6, 3);
		g.addEdge(5, 6, 1);

		// Find the shortest paths from the source vertex 0
		System.out.println("Shortest paths from vertex 0:");
		g.dijkstra(0);
	}
}
