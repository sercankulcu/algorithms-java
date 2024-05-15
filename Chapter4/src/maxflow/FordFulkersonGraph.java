package maxflow;

import java.util.*;

// Class representing a graph
public class FordFulkersonGraph {
	private int V; // Number of vertices
	private int[][] residualGraph; // Residual graph

	// Constructor
	FordFulkersonGraph(int v) {
		V = v;
		residualGraph = new int[V][V];
	}

	// Function to add an edge to the residual graph
	void addEdge(int src, int dest, int capacity) {
		residualGraph[src][dest] = capacity;
	}

	// Ford-Fulkerson algorithm
	int fordFulkersonBFS(int source, int sink) {
		int maxFlow = 0;
		int[] parent = new int[V]; // Array to store the augmenting path
		int pathFlow;

		// Augmenting the flow while there is a path from source to sink
		while (bfs(source, sink, parent)) {
			pathFlow = Integer.MAX_VALUE;
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}

			// Update residual capacities of the edges and reverse edges along the path
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				System.out.print(v + " <- ");
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow;
			}
			System.out.println(source);
			// Add path flow to overall flow
			System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
			maxFlow += pathFlow;
		}

		return maxFlow;
	}

	// Breadth-first search (BFS) to find augmenting paths
	boolean bfs(int source, int sink, int[] parent) {
		boolean[] visited = new boolean[V];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[source] = true;
		parent[source] = -1;

		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v = 0; v < V; v++) {
				if (!visited[v] && residualGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		// Return true if we reached the sink in BFS starting from source
		return visited[sink];
	}

	//Ford-Fulkerson algorithm with DFS
	int fordFulkersonDFS(int source, int sink) {
		int maxFlow = 0;

		// Initialize parent array and augmenting path flow
		int[] parent = new int[V];
		int pathFlow;

		// Augmenting the flow while there is a path from source to sink
		while (dfs(source, sink, parent)) {
			pathFlow = Integer.MAX_VALUE;
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}

			// Update residual capacities of the edges and reverse edges along the path
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				System.out.print(v + " <- ");
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow;
			}
			System.out.println(source);
			// Add path flow to overall flow
			System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
			maxFlow += pathFlow;
		}

		return maxFlow;
	}

	// Depth-First Search (DFS) to find augmenting paths
	boolean dfs(int u, int sink, int[] parent) {
		boolean[] visited = new boolean[V];
		return dfsUtil(u, sink, visited, parent);
	}

	boolean dfsUtil(int u, int sink, boolean[] visited, int[] parent) {
		visited[u] = true;

		if (u == sink)
			return true;

		for (int v = 0; v < V; v++) {
			if (!visited[v] && residualGraph[u][v] > 0) {
				parent[v] = u;
				if (dfsUtil(v, sink, visited, parent))
					return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		// Create a graph
		FordFulkersonGraph g = new FordFulkersonGraph(6);

		// Add edges to the graph
		g.addEdge(0, 1, 16);
		g.addEdge(0, 2, 13);
		g.addEdge(1, 2, 10);
		g.addEdge(1, 3, 12);
		g.addEdge(2, 1, 4);
		g.addEdge(2, 4, 14);
		g.addEdge(3, 2, 9);
		g.addEdge(3, 5, 20);
		g.addEdge(4, 3, 7);
		g.addEdge(4, 5, 4);

		int source = 0; // Source vertex
		int sink = 5; // Sink vertex

		// Find maximum flow using Ford-Fulkerson algorithm
		int maxFlow = g.fordFulkersonBFS(source, sink);
		System.out.println("Maximum flow from source to sink: " + maxFlow);
		
		g = new FordFulkersonGraph(6);

		// Add edges to the graph
		g.addEdge(0, 1, 16);
		g.addEdge(0, 2, 13);
		g.addEdge(1, 2, 10);
		g.addEdge(1, 3, 12);
		g.addEdge(2, 1, 4);
		g.addEdge(2, 4, 14);
		g.addEdge(3, 2, 9);
		g.addEdge(3, 5, 20);
		g.addEdge(4, 3, 7);
		g.addEdge(4, 5, 4);
		
		maxFlow = g.fordFulkersonDFS(source, sink);
		System.out.println("Maximum flow from source to sink: " + maxFlow);
	}
}
