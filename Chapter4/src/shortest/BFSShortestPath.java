package shortest;

import java.util.*;

public class BFSShortestPath {

    // Inner class representing the graph
    static class Graph {
        private int V; // Number of vertices in the graph
        private LinkedList<Integer>[] adj; // Adjacency List representation of the graph

        // Constructor to initialize the graph
        Graph(int V) {
            this.V = V;
            adj = new LinkedList[V]; // Create an array of linked lists for adjacency list
            for (int i = 0; i < V; ++i)
                adj[i] = new LinkedList<>(); // Initialize each list
        }

        // Method to add an edge between two vertices in the graph
        void addEdge(int v, int w) {
            adj[v].add(w); // Add vertex `w` to the adjacency list of `v`
            adj[w].add(v); // Add vertex `v` to the adjacency list of `w` (for undirected graph)
        }

        // Method to print the shortest path from a source vertex to a destination vertex
        void printShortestPath(int source, int dest) {
            int[] pred = new int[V]; // Array to store the predecessor of each vertex
            int[] dist = new int[V]; // Array to store the distance of each vertex from the source

            // If BFS fails to find a path between source and destination
            if (!BFS(source, dest, pred, dist)) {
                System.out.println("No path exists between " + source + " and " + dest);
            } else {
                // If a path is found, reconstruct the path using the predecessor array
                LinkedList<Integer> path = new LinkedList<>();
                int crawl = dest;
                path.add(crawl); // Start from the destination vertex
                while (pred[crawl] != -1) { // Traverse back using the predecessor array
                    path.add(pred[crawl]);
                    crawl = pred[crawl];
                }

                // Print the path in reverse order (from source to destination)
                System.out.print("Shortest path between " + source + " and " + dest + " is: ");
                for (int i = path.size() - 1; i >= 0; i--) {
                    System.out.print(path.get(i));
                    if (i != 0)
                        System.out.print(" -> ");
                }

                // Print the shortest distance
                System.out.println("\nShortest distance is: " + dist[dest]);
            }
        }

        // BFS traversal to find the shortest path in an unweighted graph
        boolean BFS(int src, int dest, int[] pred, int[] dist) {
            LinkedList<Integer> queue = new LinkedList<>(); // Queue for BFS traversal
            boolean[] visited = new boolean[V]; // Array to track visited vertices

            // Initialize all vertices as unvisited, distances as infinite, and predecessors as -1
            for (int i = 0; i < V; i++) {
                visited[i] = false;
                dist[i] = Integer.MAX_VALUE; // Infinite distance
                pred[i] = -1; // No predecessor
            }

            // Start BFS from the source vertex
            visited[src] = true; // Mark the source as visited
            dist[src] = 0; // Distance from source to itself is 0
            queue.add(src); // Add source to the queue

            // Standard BFS loop
            while (!queue.isEmpty()) {
                int u = queue.poll(); // Dequeue a vertex

                // Traverse all adjacent vertices of the dequeued vertex
                for (int i : adj[u]) {
                    if (!visited[i]) { // If the vertex has not been visited
                        visited[i] = true; // Mark it as visited
                        dist[i] = dist[u] + 1; // Update the distance (unweighted graph: increment by 1)
                        pred[i] = u; // Set the predecessor
                        queue.add(i); // Add the vertex to the queue

                        // Stop BFS if we reach the destination vertex
                        if (i == dest)
                            return true;
                    }
                }
            }

            // If BFS completes without finding the destination
            return false;
        }
    }

    public static void main(String[] args) {
        int V = 8; // Number of vertices in the graph
        Graph g = new Graph(V); // Create a graph with V vertices

        // Add edges to the graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 7);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);

        int source = 0; // Source vertex
        int dest = 6; // Destination vertex

        // Print the shortest path from source to destination
        g.printShortestPath(source, dest);
    }
}
