package minspan;

import java.util.*;

public class ReverseDeleteGraph {

	class Edge implements Comparable<Edge> {
		int src;
		int dest;
		int weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return other.weight - this.weight;
		}
	}

	private int V; // Number of vertices
	private List<Edge> edges;

	public ReverseDeleteGraph(int V) {
		this.V = V;
		edges = new ArrayList<>();
	}

	public void addEdge(int src, int dest, int weight) {
		edges.add(new Edge(src, dest, weight));
	}

	public void reverseDeleteMST() {
		Collections.sort(edges);

		int i = 0;
		while (i < edges.size()) {
			Edge edge = edges.get(i);
			edges.remove(i);

			if (!isConnected()) {
				edges.add(i, edge);
				i++;
			}
		}

		// Print the constructed MST
		printMST(edges);
	}

	private boolean isConnected() {
		DisjointSet ds = new DisjointSet(V);
		for (Edge edge : edges) {
			int srcParent = ds.find(edge.src);
			int destParent = ds.find(edge.dest);

			if (srcParent != destParent) {
				ds.union(srcParent, destParent);
			}
		}

		// Check if all vertices are in the same set
		int representative = ds.find(0);
		for (int i = 1; i < V; i++) {
			if (ds.find(i) != representative) {
				return false;
			}
		}

		return true;
	}

	// Function to print the constructed MST
	void printMST(List<Edge> result) {
		int total = 0;
		System.out.println("Edges of Minimum Spanning Tree:");
		for (Edge edge : result) {
			System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
			total += edge.weight;
		}
		System.out.println("Total weight is: " + total);
	}

	public static void main(String[] args) {
		int V = 8;
		ReverseDeleteGraph g = new ReverseDeleteGraph(V);
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
		g.addEdge(5, 6, 7);

		g.reverseDeleteMST();
	}
}

class DisjointSet {
	int[] parent;
	int[] rank;

	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (rank[xRoot] < rank[yRoot]) {
			parent[xRoot] = yRoot;
		} else if (rank[yRoot] < rank[xRoot]) {
			parent[yRoot] = xRoot;
		} else {
			parent[yRoot] = xRoot;
			rank[xRoot]++;
		}
	}
}
