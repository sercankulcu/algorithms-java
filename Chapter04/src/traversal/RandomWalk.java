package traversal;

import java.util.*;

//Class to perform a Random Walk on a graph
public class RandomWalk {

	// Inner class to represent the graph using an adjacency list
	// This class is nested within RandomWalk and can access its members
	private class GraphForRandomWalk {
		// Number of vertices
		private int V;
		// Adjacency list where each index represents a vertex
		// and the list contains its neighbors.
		private List<List<Integer>> adj;

		// Constructor for the inner Graph class
		public GraphForRandomWalk(int v) {
			this.V = v;
			adj = new ArrayList<>(v);
			// Initialize adjacency list for each vertex
			for (int i = 0; i < v; ++i) {
				adj.add(new ArrayList<>());
			}
		}

		// Method to add an edge to the graph (u -> v)
		public void addEdge(int u, int v) {
			// Add vertex v to the adjacency list of vertex u
			adj.get(u).add(v);
		}

		// Method to get the neighbors of a given vertex
		public List<Integer> getNeighbors(int v) {
			// Ensure the vertex is within valid bounds
			if (v < 0 || v >= V) {
				throw new IndexOutOfBoundsException("Vertex " + v + " is out of bounds.");
			}
			return adj.get(v);
		}

		// Method to get the number of vertices
		public int getNumVertices() {
			return V;
		}
	}

	// The graph on which the random walk will be performed
	private GraphForRandomWalk graph;
	// Starting vertex of the random walk
	private int startNode;
	// Number of steps to take in the random walk
	private int numberOfSteps;
	// Random number generator
	private Random random;
	// List to store the path taken by the random walker
	private List<Integer> path;

	// Constructor for the RandomWalk class
	// It takes the number of vertices for the graph, start node, and steps
	public RandomWalk(int numVertices, int startNode, int numberOfSteps) {
		// Create an instance of the inner GraphForRandomWalk class
		this.graph = new GraphForRandomWalk(numVertices);

		// Check if the start node is valid for the created graph
		if (startNode < 0 || startNode >= graph.getNumVertices()) {
			throw new IllegalArgumentException("Start node " + startNode + " is out of bounds for a graph with " + numVertices + " vertices.");
		}
		this.startNode = startNode;
		this.numberOfSteps = numberOfSteps;
		this.random = new Random();
		this.path = new ArrayList<>();
		// Add the starting node to the path
		this.path.add(startNode);
	}

	// Method to add an edge to the internal graph instance
	public void addEdge(int u, int v) {
		// Delegate the addEdge call to the inner graph instance
		graph.addEdge(u, v);
	}

	// Method to perform the random walk traversal
	public void traverse() {
		// Start at the specified node
		int currentNode = startNode;

		// Perform the specified number of steps
		for (int i = 0; i < numberOfSteps; i++) {
			// Get the neighbors of the current node using the inner graph instance
			List<Integer> neighbors = graph.getNeighbors(currentNode);

			// If the current node has no neighbors, the walk stops
			if (neighbors.isEmpty()) {
				System.out.println("Random walk stopped at node " + currentNode + " (no outgoing edges).");
				break; // Exit the loop if no neighbors
			}

			// Randomly select one of the neighbors
			int nextNodeIndex = random.nextInt(neighbors.size());
			int nextNode = neighbors.get(nextNodeIndex);

			// Move to the selected neighbor
			currentNode = nextNode;
			// Add the next node to the path
			path.add(currentNode);

			// Optional: Print the current step and position
			// System.out.println("Step " + (i + 1) + ": Moved to node " + currentNode);
		}
	}

	// Method to get the path taken by the random walk
	public List<Integer> getPath() {
		return path;
	}

	// Method to get the final node of the random walk
	public int getFinalNode() {
		// If the path is not empty, return the last node in the path
		if (!path.isEmpty()) {
			return path.get(path.size() - 1);
		}
		// Otherwise, return the start node (if no steps were taken or graph was empty)
		return startNode;
	}

	// Main method for demonstration
	public static void main(String[] args) {
		// Create a RandomWalk instance.
		// The graph is created internally by the RandomWalk constructor.
		// We specify the number of vertices (5), start node (0), and steps (10).
		RandomWalk g = new RandomWalk(8, 0, 15);

		// Add edges to the graph (creating connections between vertices)
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

		// Perform the random walk
		g.traverse();
		// Get and print the path taken
		System.out.println("Random Walk Path: " + g.getPath());
		// Print the final node
		System.out.println("Random Walk Final Node: " + g.getFinalNode());
	}
}
