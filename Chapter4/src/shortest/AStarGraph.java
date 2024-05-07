package shortest;

import java.util.*;

public class AStarGraph {
	// Inner class representing a vertex with heuristic value
	class Vertex {
		int vertex;
		int heuristic;

		Vertex(int v, int h) {
			vertex = v;
			heuristic = h;
		}
	}

	private int V; // Number of vertices
	private LinkedList<Vertex>[] adj; // Adjacency lists
	private int[] heuristicValues; // Heuristic values for vertices

	// Constructor
	AStarGraph(int v) {
		V = v;
		adj = new LinkedList[V];
		for (int i = 0; i < V; ++i)
			adj[i] = new LinkedList<>();

		heuristicValues = new int[V];
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		adj[src].add(new Vertex(dest, weight));
	}

	// Function to set heuristic value for a vertex
	void setHeuristicValue(int vertex, int heuristic) {
		heuristicValues[vertex] = heuristic;
	}

	// A* (A-star) algorithm
	void aStar(int src, int dest) {
		// Priority queue to store vertices and their f-values
		PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.heuristic));

		// Array to keep track of visited vertices
		boolean[] visited = new boolean[V];

		// Array to store g-values (cost from src to vertex)
		int[] gValues = new int[V];
		Arrays.fill(gValues, Integer.MAX_VALUE);
		gValues[src] = 0;

		// Add source vertex to priority queue
		pq.add(new Vertex(src, heuristicValues[src]));

		while (!pq.isEmpty()) {
			// Extract vertex with minimum f-value
			int u = pq.poll().vertex;
			System.out.println("*   " + u + " is extracted from queue");

			// Mark vertex as visited
			visited[u] = true;

			// If destination reached, stop
			if (u == dest) {
				System.out.println("(" + src + " --> " + dest + "): " + gValues[u]);
				return;
			}

			// Traverse adjacent vertices
			for (Vertex v : adj[u]) {
				int vertex = v.vertex;
				int weight = v.heuristic;
				
				System.out.println("** for each neighbour " + vertex + " with weight " + weight);

				// Update g-value if a shorter path is found
				if (!visited[vertex] && gValues[u] != Integer.MAX_VALUE && gValues[u] + weight < gValues[vertex]) {
					System.out.print("*** shorter path found " + gValues[vertex] + "->");
					gValues[vertex] = gValues[u] + weight;
					System.out.println(gValues[vertex]);
					pq.add(new Vertex(vertex, gValues[vertex] + heuristicValues[vertex]));
				}
			}
		}

		// If destination not reached
		System.out.println("Destination not reachable from the source");
	}

	public static void main(String[] args) {
		// Create a graph
		AStarGraph g = new AStarGraph(8);

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

		// Set heuristic values for vertices
		g.setHeuristicValue(0, 6);
		g.setHeuristicValue(1, 11);
		g.setHeuristicValue(2, 10);
		g.setHeuristicValue(3, 9);
		g.setHeuristicValue(4, 8);
		g.setHeuristicValue(5, 7);
		g.setHeuristicValue(6, 0);
		g.setHeuristicValue(7, 12);

		// Perform A* search from source vertex 0 to destination vertex 6
		System.out.println("Shortest paths from vertex 0:");
		//for(int i = 1; i < 8; i++)
			g.aStar(0, 6);
	}
}
