package shortest;

import java.util.*;

// Class representing a graph
public class BiDirectionalSearchGraph {
	
	private int V; // Number of vertices
	private List<List<Integer>> adj; // Adjacency lists

	// Constructor
	BiDirectionalSearchGraph(int v) {
		V = v;
		adj = new ArrayList<>(v);
		for (int i = 0; i < v; ++i)
			adj.add(new ArrayList<>());
	}

	// Function to add an undirected edge to the graph
	void addEdge(int v, int w) {
		adj.get(v).add(w);
		adj.get(w).add(v); // Add w to v's list and vice versa for an undirected graph
	}

	// Function to find the shortest path between two vertices using bidirectional search
	int bidirectionalSearch(int src, int dest) {
		// Check if source and destination are same
		if (src == dest) return 0;

		// Arrays to store distances from source and destination
		int[] distSrc = new int[V];
		int[] distDest = new int[V];
		Arrays.fill(distSrc, -1);
		Arrays.fill(distDest, -1);

		// Queues for BFS from source and destination
		Queue<Integer> queueSrc = new LinkedList<>();
		Queue<Integer> queueDest = new LinkedList<>();

		// Mark source and destination as visited
		distSrc[src] = 0;
		distDest[dest] = 0;
		queueSrc.add(src);
		queueDest.add(dest);

		// Bidirectional BFS loop
		while (!queueSrc.isEmpty() && !queueDest.isEmpty()) {
			// BFS from source
			int v = queueSrc.poll();
			for (int neighbor : adj.get(v)) {
				if (distSrc[neighbor] == -1) {
					distSrc[neighbor] = distSrc[v] + 1;
					queueSrc.add(neighbor);

					// If this vertex is already visited by destination BFS, return sum of distances
					if (distDest[neighbor] != -1) {
						return distSrc[neighbor] + distDest[neighbor];
					}
				}
			}

			// BFS from destination
			v = queueDest.poll();
			for (int neighbor : adj.get(v)) {
				if (distDest[neighbor] == -1) {
					distDest[neighbor] = distDest[v] + 1;
					queueDest.add(neighbor);

					// If this vertex is already visited by source BFS, return sum of distances
					if (distSrc[neighbor] != -1) {
						return distSrc[neighbor] + distDest[neighbor];
					}
				}
			}
		}

		// If no path found
		System.out.println("No path exists between " + src + " and " + dest);
		return -1;
	}

	public static void main(String[] args) {
		// Create a graph
		BiDirectionalSearchGraph g = new BiDirectionalSearchGraph(8);

		// Add edges to the graph
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 7);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);
		
		System.out.println("Shortest paths from vertex 0:");
		for(int i = 0; i < 8; i++)
			System.out.println("(" + 0 + " --> " + i + "): " + g.bidirectionalSearch(0, i));
	}
}
