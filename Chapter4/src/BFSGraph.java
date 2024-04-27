
import java.util.*;

// Class representing a graph
public class BFSGraph {
	private int V; // Number of vertices
	private LinkedList<Integer>[] adj; // Adjacency lists

	// Constructor
	BFSGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<>();
	}

	// Function to add an edge to the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list
	}

	// Function to perform BFS traversal
	void BFS(int s) {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			// Dequeue a vertex from the queue and print it
			s = queue.poll();
			System.out.print(s + " -> ");

			// Get all adjacent vertices of the dequeued vertex s
			// If an adjacent vertex has not been visited, mark it visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String[] args) {
		// Create a graph
		BFSGraph g = new BFSGraph(8);

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

		System.out.println("Breadth First Traversal:");

		// Perform BFS traversal starting from vertex 2
		g.BFS(0);
	}
}
