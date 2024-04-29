import java.util.*;

// Class to represent an undirected graph using adjacency list representation
public class MinimumVertexCover {
	private int V; // No. of vertices
	private LinkedList<Integer>[] adj; // Array of lists for Adjacency List Representation

	// Constructor
	MinimumVertexCover(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v); // Graph is undirected, so we add v to w's list as well.
	}

	// Function to print the vertex cover
	void printVertexCover() {
		// Initialize all vertices as not visited.
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		Iterator<Integer> i;

		// Consider all edges one by one
		for (int u = 0; u < V; u++) {
			// An edge is only picked when both visited[u] and visited[v] are false
			if (!visited[u]) {
				// Go through all adjacent vertices of u and pick the first not yet visited vertex
				i = adj[u].iterator();
				while (i.hasNext()) {
					int v = i.next();
					if (!visited[v]) {
						// Add the vertices (u, v) to the result set
						// Mark both vertices u and v as visited to ignore all edges from/to them
						visited[v] = true;
						visited[u] = true;
						break;
					}
				}
			}
		}

		// Print the vertex cover
		for (int j = 0; j < V; j++)
			if (visited[j])
				System.out.print(j + " ");
	}

	// Driver method
	public static void main(String[] args) {
		// Create a graph
		MinimumVertexCover g = new MinimumVertexCover(7);

		// Add edges
		g.addEdge(2, 0);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(0, 5);
		g.addEdge(0, 6);

		// Print the vertex cover
		g.printVertexCover();
	}
}
