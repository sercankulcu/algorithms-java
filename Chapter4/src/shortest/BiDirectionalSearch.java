package shortest;

import java.util.*;

// Class representing a graph and implementing bidirectional search
public class BiDirectionalSearch {

	private int V; // Number of vertices in the graph
	private List<List<Integer>> adj; // Adjacency lists to represent the graph

	// Constructor to initialize the graph
	BiDirectionalSearch(int v) {
		V = v; // Set the number of vertices
		adj = new ArrayList<>(v); // Create an adjacency list for each vertex
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>()); // Initialize each adjacency list
	}

	// Function to add an undirected edge between two vertices
	void addEdge(int v, int w) {
		adj.get(v).add(w); // Add vertex w to the adjacency list of vertex v
		adj.get(w).add(v); // Add vertex v to the adjacency list of vertex w (undirected graph)
	}

	// Function to find the shortest path between two vertices using bidirectional search
	int bidirectionalSearch(int src, int dest) {
		// If the source and destination are the same, return 0 as the distance
		if (src == dest) {
			System.out.println("(" + src + " --> " + dest + "): " + src);
			return 0;
		}

		// Arrays to store distances from the source and destination vertices
		int[] distSrc = new int[V];
		int[] distDest = new int[V];
		Arrays.fill(distSrc, -1); // Initialize distances from source as -1 (unvisited)
		Arrays.fill(distDest, -1); // Initialize distances from destination as -1 (unvisited)

		// Parent arrays for path reconstruction
		int[] parentSrc = new int[V];
		int[] parentDest = new int[V];
		Arrays.fill(parentSrc, -1);
		Arrays.fill(parentDest, -1);

		// Queues for BFS from the source and destination
		Queue<Integer> queueSrc = new LinkedList<>();
		Queue<Integer> queueDest = new LinkedList<>();

		// Mark the source and destination vertices as visited and initialize their distances
		distSrc[src] = 0;
		distDest[dest] = 0;
		parentSrc[src] = src;
		parentDest[dest] = dest;
		queueSrc.add(src);
		queueDest.add(dest);

		// Main loop for bidirectional BFS
		while (!queueSrc.isEmpty() && !queueDest.isEmpty()) {
			// BFS from the source side
			int v = queueSrc.poll();
			for (int neighbor : adj.get(v)) {
				if (distSrc[neighbor] == -1) { // If the neighbor is unvisited
					distSrc[neighbor] = distSrc[v] + 1; // Update its distance
					parentSrc[neighbor] = v; // Track its parent
					queueSrc.add(neighbor);

					// Check if this vertex is already visited from the destination side
					if (distDest[neighbor] != -1) {
						printPath(neighbor, parentSrc, parentDest, src, dest);
						return distSrc[neighbor] + distDest[neighbor];
					}
				}
			}

			// BFS from the destination side
			v = queueDest.poll();
			for (int neighbor : adj.get(v)) {
				if (distDest[neighbor] == -1) { // If the neighbor is unvisited
					distDest[neighbor] = distDest[v] + 1; // Update its distance
					parentDest[neighbor] = v; // Track its parent
					queueDest.add(neighbor);

					// Check if this vertex is already visited from the source side
					if (distSrc[neighbor] != -1) {
						printPath(neighbor, parentSrc, parentDest, src, dest);
						return distSrc[neighbor] + distDest[neighbor];
					}
				}
			}
		}

		// If no path exists between source and destination
		System.out.println("No path exists between " + src + " and " + dest);
		return -1;
	}

	// Function to print the path using parent arrays
	void printPath(int meetingPoint, int[] parentSrc, int[] parentDest, int src, int dest) {
		// Build the path from source to meeting point
		List<Integer> path = new ArrayList<>();
		for (int v = meetingPoint; v != src; v = parentSrc[v]) {
			path.add(v);
		}
		path.add(src);
		Collections.reverse(path);

		// Append the path from meeting point to destination
		for (int v = parentDest[meetingPoint]; v != dest; v = parentDest[v]) {
			path.add(v);
		}
		if(!path.contains(dest))
			path.add(dest);

		// Print the path
		System.out.print("(" + src + " --> " + dest + "): Path:[ ");
		for (int v : path) {
			System.out.print(v + " ");
		}
		System.out.print("]  ");
	}

	public static void main(String[] args) {
		// Create a graph with 8 vertices
		BiDirectionalSearch g = new BiDirectionalSearch(8);

		// Add edges to the graph
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 7);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);

		// Print the shortest paths from vertex 0 to all other vertices
		System.out.println("Shortest paths from vertex 0:");
		for (int i = 0; i < 8; i++) {
			int distance = g.bidirectionalSearch(0, i);
			if (distance != -1) {
				System.out.println("Distance: " + distance);
			}
		}
	}
}
