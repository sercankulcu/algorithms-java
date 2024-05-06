
import java.util.*;

public class BFSShortestPath {

	static class Graph {
		private int V; // Number of vertices
		private LinkedList<Integer>[] adj; // Adjacency List representation

		// Constructor
		Graph(int V) {
			this.V = V;
			adj = new LinkedList[V];
			for (int i = 0; i < V; ++i)
				adj[i] = new LinkedList<>();
		}

		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w);
			adj[w].add(v); // for undirected graph
		}

		// Prints shortest path from source to destination
		void printShortestPath(int source, int dest) {
			int[] pred = new int[V];
			int[] dist = new int[V];
			if (!BFS(source, dest, pred, dist))
				System.out.println("No path exists between " + source + " and " + dest);
			else {
				LinkedList<Integer> path = new LinkedList<>();
				int crawl = dest;
				path.add(crawl);
				while (pred[crawl] != -1) {
					path.add(pred[crawl]);
					crawl = pred[crawl];
				}
				System.out.print("Shortest path between " + source + " and " + dest + " is: ");
				for (int i = path.size() - 1; i >= 0; i--) {
					System.out.print(path.get(i));
					if (i != 0)
						System.out.print(" -> ");
				}
				System.out.println("\nShortest distance is: " + dist[dest]);
			}
		}

		// BFS traversal to find shortest path
		boolean BFS(int src, int dest, int[] pred, int[] dist) {
			LinkedList<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[V];
			for (int i = 0; i < V; i++) {
				visited[i] = false;
				dist[i] = Integer.MAX_VALUE;
				pred[i] = -1;
			}
			visited[src] = true;
			dist[src] = 0;
			queue.add(src);
			while (!queue.isEmpty()) {
				int u = queue.poll();
				for (int i : adj[u]) {
					if (!visited[i]) {
						visited[i] = true;
						dist[i] = dist[u] + 1; // unweighted graph so increment by 1
						pred[i] = u;
						queue.add(i);
						if (i == dest)
							return true;
					}
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
		int V = 8;
		Graph g = new Graph(V);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 7);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);

		int source = 0;
		int dest = 6;
		g.printShortestPath(source, dest);
	}
}
