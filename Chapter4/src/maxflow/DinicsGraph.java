package maxflow;

import java.util.*;

// Class representing a graph
public class DinicsGraph {
	private int V; // Number of vertices
	private List<Edge>[] adjacencyList; // Adjacency list representation of the graph
	private int[] level; // Level of each vertex in the BFS tree

	// Inner class representing an edge
	class Edge {
		int to;
		int flow;
		int capacity;
		int reverseIndex;

		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}
	}

	// Constructor
	DinicsGraph(int v) {
		V = v;
		adjacencyList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
		level = new int[V];
	}

	// Function to add an edge to the graph
	void addEdge(int from, int to, int capacity) {
		Edge forwardEdge = new Edge(to, capacity);
		Edge backwardEdge = new Edge(from, 0); // Capacity of backward edge is set to 0 initially
		forwardEdge.reverseIndex = adjacencyList[to].size();
		backwardEdge.reverseIndex = adjacencyList[from].size();
		adjacencyList[from].add(forwardEdge);
		adjacencyList[to].add(backwardEdge);
	}

	// Dinic's algorithm
	int dinics(int source, int sink) {
		int maxFlow = 0;
		while (bfs(source, sink)) {
			int[] start = new int[V];
			while (true) {
				int pathFlow = dfs(source, sink, Integer.MAX_VALUE, start);
				if (pathFlow == 0)
					break;
				System.out.println(source);
				System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
				maxFlow += pathFlow;
			}
		}
		return maxFlow;
	}

	// Breadth-First Search (BFS) to build the level graph
	boolean bfs(int source, int sink) {
		Arrays.fill(level, -1);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		level[source] = 0;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Edge edge : adjacencyList[u]) {
				if (level[edge.to] < 0 && edge.flow < edge.capacity) {
					level[edge.to] = level[u] + 1;
					queue.add(edge.to);
				}
			}
		}
		return level[sink] >= 0;
	}

	// Depth-First Search (DFS) with backtracking to find augmenting paths
	int dfs(int u, int sink, int flow, int[] start) {
		if (u == sink)
			return flow;
		for (int i = start[u]; i < adjacencyList[u].size(); i++) {
			Edge edge = adjacencyList[u].get(i);
			if (level[edge.to] == level[u] + 1 && edge.flow < edge.capacity) {
				int currentFlow = Math.min(flow, edge.capacity - edge.flow);
				int minPathFlow = dfs(edge.to, sink, currentFlow, start);
				if (minPathFlow > 0) {
					edge.flow += minPathFlow;
					System.out.print(edge.to + " <- ");
					adjacencyList[edge.to].get(edge.reverseIndex).flow -= minPathFlow;
					return minPathFlow;
				}
			}
			start[u]++;
		}
		return 0;
	}

	public static void main(String[] args) {
		// Create a graph
		DinicsGraph g = new DinicsGraph(6);

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

		// Find maximum flow using Dinic's algorithm
		int maxFlow = g.dinics(source, sink);
		System.out.println("Maximum flow from source to sink: " + maxFlow);
	}
}
