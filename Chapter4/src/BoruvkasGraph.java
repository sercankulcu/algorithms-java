
import java.util.*;

// Class representing a graph
public class BoruvkasGraph {
	private int V; // Number of vertices
	private List<Edge> edges; // List of edges

	// Inner class representing an edge
	class Edge {
		int src;
		int dest;
		int weight;

		Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}

	// Constructor
	BoruvkasGraph(int v) {
		V = v;
		edges = new ArrayList<>();
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		edges.add(new Edge(src, dest, weight));
	}

	// Function to find the minimum spanning tree using Borůvka's algorithm
	void boruvkaMST() {
		// Initialize parent array and result list
		int[] parent = new int[V];
		List<Edge> result = new ArrayList<>();

		// Initialize each vertex as a separate component
		for (int i = 0; i < V; ++i)
			parent[i] = i;

		// Keep track of cheapest edge for each component
		int[] cheapest = new int[V];
		Arrays.fill(cheapest, -1);

		// Variable to track number of components
		int numTrees = V;

		// Loop until there is more than one component
		while (numTrees > 1) {
			// Find the cheapest edge for each component
			for (int i = 0; i < edges.size(); ++i) {
				int set1 = find(parent, edges.get(i).src);
				int set2 = find(parent, edges.get(i).dest);

				if (set1 == set2)
					continue;

				if (cheapest[set1] == -1 || edges.get(i).weight < edges.get(cheapest[set1]).weight)
					cheapest[set1] = i;

				if (cheapest[set2] == -1 || edges.get(i).weight < edges.get(cheapest[set2]).weight)
					cheapest[set2] = i;
			}

			// Add the cheapest edges to the MST
			for (int i = 0; i < V; ++i) {
				if (cheapest[i] != -1) {
					int set1 = find(parent, edges.get(cheapest[i]).src);
					int set2 = find(parent, edges.get(cheapest[i]).dest);

					if (set1 == set2)
						continue;

					result.add(edges.get(cheapest[i]));
					union(parent, set1, set2);
					numTrees--;
				}
			}

			// Reset cheapest array
			Arrays.fill(cheapest, -1);
		}

		// Print the constructed MST
		printMST(result);
	}

	// Function to find the subset of an element
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

		BoruvkasGraph g = new BoruvkasGraph(8);

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

		// Find minimum spanning tree using Borůvka's algorithm
		g.boruvkaMST();
	}
}
