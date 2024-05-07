package shortest;

public class FloydWarshallGraph {
	private int V; // Number of vertices
	private int[][] dist; // 2D array to store shortest distances
	private int[][] parent;

	// Constructor
	FloydWarshallGraph(int v) {
		V = v;
		dist = new int[v][v];
		parent = new int[v][v];

		// Initialize distances to infinity
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < v; ++j) {
				dist[i][j] = Integer.MAX_VALUE;
				parent[i][j] = -1;
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
		parent[src][dest] = dest;
	}

	// Function to perform Floyd-Warshall algorithm
	void floydWarshall() {
		// Iterate through intermediate vertices
		for (int k = 0; k < V; ++k) {
			System.out.println("* for intermediate vertex " + k);
			// Iterate through source vertices
			for (int i = 0; i < V; ++i) {
				System.out.println("** for source vertex " + i);
				// Iterate through destination vertices
				for (int j = 0; j < V; ++j) {
					System.out.println("*** for destination vertex " + j);
					// If vertex k is on the shortest path from i to j, update the distance
					if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
							dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						parent[i][j] = parent[i][k];
						System.out.println("**** shorter path found over " + k + " dist: " + dist[i][j]);
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
		System.out.println("\nShortest paths between every pair of vertices:");
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (parent[i][j] == -1) {
					//System.out.println("No path exists between vertex " + i + " and vertex " + j);
				} else {
					System.out.print("Path from " + i + " to " + j + ": " + i);
					int k = i;
					while (k != j && k != -1) {
						k = parent[k][j];
						System.out.print(" -> " + k);
					}
					System.out.println();
				}
			}
		}
	}

public static void main(String[] args) {

	FloydWarshallGraph g = new FloydWarshallGraph(8);

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
	//g.addEdge(7, 1, 2);

	// Run Floyd-Warshall algorithm
	g.floydWarshall();
}
}
