package maxflow;

import java.util.*;

// Class representing a graph
public class EdmondsKarp {
    private int V; // Number of vertices in the graph
    private int[][] residualGraph; // Residual graph to store capacities and flows

    // Constructor
    EdmondsKarp(int v) {
        V = v;
        residualGraph = new int[V][V]; // Initialize residual graph as a 2D array
    }

    // Function to add an edge to the graph
    void addEdge(int src, int dest, int capacity) {
        // Add the given capacity for the directed edge from src to dest
        residualGraph[src][dest] = capacity;
    }

    // Implementation of the Edmonds-Karp algorithm for finding maximum flow
    int edmondsKarp(int source, int sink) {
        int maxFlow = 0; // Initialize the maximum flow to 0

        // Array to store the parent of each vertex during BFS (used to reconstruct paths)
        int[] parent = new int[V];

        // Loop until no augmenting path is found
        while (bfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE; // Initialize path flow to infinity

            // Traverse the path from sink to source to find the bottleneck capacity
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]); // Update path flow
            }

            // Update the residual graph with the found path flow
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                System.out.print(v + " <- "); // Print the path for debugging
                residualGraph[u][v] -= pathFlow; // Decrease capacity in the forward edge
                residualGraph[v][u] += pathFlow; // Increase capacity in the reverse edge
            }
            System.out.println(source);

            // Add the path flow to the overall maximum flow
            System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
            maxFlow += pathFlow;
        }

        return maxFlow; // Return the calculated maximum flow
    }

    // Breadth-first search (BFS) to find an augmenting path in the residual graph
    boolean bfs(int source, int sink, int[] parent) {
        // Array to track visited vertices
        boolean[] visited = new boolean[V];

        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source); // Start BFS from the source vertex
        visited[source] = true; // Mark source as visited
        parent[source] = -1; // Source has no parent

        // Perform BFS
        while (!queue.isEmpty()) {
            int u = queue.poll(); // Dequeue a vertex

            // Explore all adjacent vertices of u
            for (int v = 0; v < V; v++) {
                // If the vertex is not visited and there is available capacity
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v); // Enqueue the vertex
                    parent[v] = u; // Set u as the parent of v
                    visited[v] = true; // Mark v as visited
                }
            }
        }

        // Return true if we reached the sink, meaning there is an augmenting path
        return visited[sink];
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        // Create a graph with 6 vertices
        EdmondsKarp g = new EdmondsKarp(6);

        // Add edges to the graph with capacities
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

        // Compute the maximum flow using the Edmonds-Karp algorithm
        int maxFlow = g.edmondsKarp(source, sink);
        System.out.println("Maximum flow from source to sink: " + maxFlow);
    }
}
