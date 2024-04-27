

// Class representing a graph
public class FloydWarshallGraph {
	private int V; // Number of vertices
	private int[][] dist; // 2D array to store shortest distances

	// Constructor
	FloydWarshallGraph(int v) {
		V = v;
		dist = new int[v][v];

		// Initialize distances to infinity
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < v; ++j) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		// Set diagonal elements to 0
		for (int i = 0; i < v; ++i) {
			dist[i][i] = 0;
		}
	}

	// Function to add an edge to the graph
	void addEdge(int src, int dest, int weight) {
		dist[src][dest] = weight;
		dist[dest][src] = weight;
	}

	// Function to perform Floyd-Warshall algorithm
	void floydWarshall() {
		// Iterate through intermediate vertices
		for (int k = 0; k < V; ++k) {
			// Iterate through source vertices
			for (int i = 0; i < V; ++i) {
				// Iterate through destination vertices
				for (int j = 0; j < V; ++j) {
					// If vertex k is on the shortest path from i to j, update the distance
					if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
							dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		// Print the shortest distances
		printSolution();
	}

	// Function to print the solution
	void printSolution() {
		System.out.println("Shortest distances between all pairs of vertices:");
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				if (dist[i][j] == Integer.MAX_VALUE) {
					System.out.print("INF \t");
				} else {
					System.out.print(dist[i][j] + "\t");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		FloydWarshallGraph g = new FloydWarshallGraph(8);

		// Add edges to the graph
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(0, 7, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 5);
		g.addEdge(3, 5, 6);
		g.addEdge(4, 6, 2);
		g.addEdge(5, 6, 7);

		// Run Floyd-Warshall algorithm
		g.floydWarshall();
	}
}
