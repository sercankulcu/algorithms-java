package traversal;

import java.util.*;

// Class representing a graph
public class DFSGraph {
	private int V; // Number of vertices
	private LinkedList<Integer>[] adj; // Adjacency lists

	// Constructor
	DFSGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge to the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list
	}

	// Function to perform DFS traversal
	void DFSUtil(int v, boolean[] visited) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " -> ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}

	// Function to perform DFS traversal from a given source vertex
	void DFS(int v) {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		DFSUtil(v, visited);
	}

	public static void main(String[] args) {
		// Create a graph
		DFSGraph g = new DFSGraph(8);

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

		System.out.println("Depth First Traversal:");

		// Perform DFS traversal starting from vertex 2
		g.DFS(0);
	}
}
