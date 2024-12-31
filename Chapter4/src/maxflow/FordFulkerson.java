package maxflow;

import java.util.*;

// Class representing a graph
public class FordFulkerson {
    private int V; // Number of vertices in the graph
    private int[][] residualGraph; // Residual graph for capacities and flows

    // Constructor
    FordFulkerson(int v) {
        V = v; // Set the number of vertices
        residualGraph = new int[V][V]; // Initialize the residual graph as a 2D array
    }

    // Function to add an edge to the graph
    void addEdge(int src, int dest, int capacity) {
        // Add the specified capacity to the directed edge from src to dest
        residualGraph[src][dest] = capacity;
    }
    
 // Function to clear the graph (reset all edges and capacities)
    void clearGraph() {
        for (int i = 0; i < V; i++) {
            Arrays.fill(residualGraph[i], 0); // Set all capacities to 0
        }
    }

    // Ford-Fulkerson algorithm using BFS
    int fordFulkersonBFS(int source, int sink) {
        int maxFlow = 0; // Initialize maximum flow to 0
        int[] parent = new int[V]; // Array to store the augmenting path
        int pathFlow;

        // Loop until no augmenting path is found
        while (bfs(source, sink, parent)) {
            pathFlow = Integer.MAX_VALUE;

            // Traverse the augmenting path from sink to source to find bottleneck capacity
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]); // Find minimum capacity in the path
            }

            // Update residual graph capacities along the augmenting path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                System.out.print(v + " <- "); // Print the augmenting path
                residualGraph[u][v] -= pathFlow; // Decrease forward edge capacity
                residualGraph[v][u] += pathFlow; // Increase reverse edge capacity
            }
            System.out.println(source); // Print source as the starting point of the path

            // Add path flow to the total flow
            System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
            maxFlow += pathFlow;
        }

        return maxFlow; // Return the calculated maximum flow
    }

    // Breadth-First Search (BFS) to find an augmenting path in the residual graph
    boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[V]; // Track visited vertices
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        queue.add(source); // Enqueue the source vertex
        visited[source] = true; // Mark the source as visited
        parent[source] = -1; // Source has no parent

        while (!queue.isEmpty()) {
            int u = queue.poll(); // Dequeue a vertex

            // Explore all vertices adjacent to u
            for (int v = 0; v < V; v++) {
                // If vertex v is not visited and there is available capacity
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v); // Enqueue vertex v
                    parent[v] = u; // Set u as the parent of v
                    visited[v] = true; // Mark v as visited
                }
            }
        }

        // Return true if we reached the sink during BFS
        return visited[sink];
    }

    // Ford-Fulkerson algorithm using DFS
    int fordFulkersonDFS(int source, int sink) {
        int maxFlow = 0; // Initialize maximum flow to 0
        int[] parent = new int[V]; // Array to store the augmenting path
        int pathFlow;

        // Loop until no augmenting path is found
        while (dfs(source, sink, parent)) {
            pathFlow = Integer.MAX_VALUE;

            // Traverse the augmenting path from sink to source to find bottleneck capacity
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]); // Find minimum capacity in the path
            }

            // Update residual graph capacities along the augmenting path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                System.out.print(v + " <- "); // Print the augmenting path
                residualGraph[u][v] -= pathFlow; // Decrease forward edge capacity
                residualGraph[v][u] += pathFlow; // Increase reverse edge capacity
            }
            System.out.println(source); // Print source as the starting point of the path

            // Add path flow to the total flow
            System.out.println("Flow added: " + maxFlow + " + " + pathFlow + " = " + (maxFlow + pathFlow));
            maxFlow += pathFlow;
        }

        return maxFlow; // Return the calculated maximum flow
    }

    // Depth-First Search (DFS) to find an augmenting path
    boolean dfs(int u, int sink, int[] parent) {
        boolean[] visited = new boolean[V]; // Track visited vertices
        return dfsUtil(u, sink, visited, parent); // Recursive DFS utility function
    }

    // Utility function for DFS
    boolean dfsUtil(int u, int sink, boolean[] visited, int[] parent) {
        visited[u] = true; // Mark the current vertex as visited

        // If the sink is reached, return true
        if (u == sink)
            return true;

        // Explore all vertices adjacent to u
        for (int v = 0; v < V; v++) {
            if (!visited[v] && residualGraph[u][v] > 0) {
                parent[v] = u; // Set u as the parent of v
                if (dfsUtil(v, sink, visited, parent)) // Recursive call for v
                    return true;
            }
        }

        return false; // Return false if no path is found
    }

    public static void main(String[] args) {
        // Create a graph with 6 vertices
        FordFulkerson g = new FordFulkerson(6);

        // Add edges with capacities
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

        // Compute maximum flow using BFS-based Ford-Fulkerson
        int maxFlow = g.fordFulkersonBFS(source, sink);
        System.out.println("Maximum flow from source to sink (BFS): " + maxFlow);
        System.out.println();
        // Reset the graph for DFS-based computation
        g = new FordFulkerson(6);
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

        // Compute maximum flow using DFS-based Ford-Fulkerson
        maxFlow = g.fordFulkersonDFS(source, sink);
        System.out.println("Maximum flow from source to sink (DFS): " + maxFlow);
    }
}
