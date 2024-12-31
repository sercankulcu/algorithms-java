package shortest;

public class FloydWarshall {

    private int V; // Number of vertices in the graph
    private int[][] dist; // 2D array to store the shortest distances between pairs of vertices
    private int[][] parent; // 2D array to store the parent of each vertex for path reconstruction

    // Constructor to initialize the graph
    FloydWarshall(int v) {
        V = v;
        dist = new int[v][v]; // Initialize distance matrix
        parent = new int[v][v]; // Initialize parent matrix

        // Set initial distances to infinity (no edges between vertices)
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < v; ++j) {
                dist[i][j] = Integer.MAX_VALUE; // Infinity signifies no direct path
                parent[i][j] = -1; // -1 signifies no parent or predecessor
            }
        }

        // Set the diagonal of the distance matrix to 0 (distance from a vertex to itself)
        for (int i = 0; i < v; ++i) {
            dist[i][i] = 0; // Distance from a vertex to itself is 0
        }
    }

    // Method to add an edge to the graph
    void addEdge(int src, int dest, int weight) {
        dist[src][dest] = weight; // Set the weight of the edge from src to dest
        dist[dest][src] = weight; // Since the graph is undirected, set the reverse edge as well
        parent[src][dest] = dest; // Set the parent of the destination vertex
    }

    // Floyd-Warshall algorithm to compute shortest paths between all pairs of vertices
    void floydWarshall() {
        // Iterate through all vertices as potential intermediate vertices
        for (int k = 0; k < V; ++k) {
            System.out.println("* Intermediate vertex: " + k);

            // Iterate through all pairs of vertices (source and destination)
            for (int i = 0; i < V; ++i) {
                System.out.println("** Source vertex: " + i);

                for (int j = 0; j < V; ++j) {
                    System.out.println("*** Destination vertex: " + j);

                    // Check if vertex k is part of a shorter path from i to j
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {

                        // Update the shortest distance
                        dist[i][j] = dist[i][k] + dist[k][j];

                        // Update the parent to reflect the new path
                        parent[i][j] = parent[i][k];

                        System.out.println("**** Shorter path found via " + k + ". New distance: " + dist[i][j]);
                    }
                }
            }
        }

        // Print the final results
        printSolution();
    }

    // Method to print the shortest distances and paths
    void printSolution() {
        System.out.println("Shortest distances between all pairs of vertices:");

        // Print the distance matrix
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF \t"); // Print "INF" if no path exists
                } else {
                    System.out.print(dist[i][j] + "\t"); // Print the shortest distance
                }
            }
            System.out.println();
        }

        System.out.println("\nShortest paths between every pair of vertices:");

        // Print the reconstructed paths for all pairs of vertices
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (parent[i][j] == -1) {
                    // Skip if no path exists
                    continue;
                } else {
                    // Reconstruct and print the path from vertex i to vertex j
                    System.out.print("Path from " + i + " to " + j + ": " + i);
                    int k = i;

                    // Follow the parent pointers to reconstruct the full path
                    while (k != j && k != -1) {
                        k = parent[k][j];
                        System.out.print(" -> " + k);
                    }
                    System.out.println();
                }
            }
        }
    }

    // Main method to test the Floyd-Warshall algorithm
    public static void main(String[] args) {

        // Create a graph with 8 vertices
        FloydWarshall g = new FloydWarshall(8);

        // Add edges with weights to the graph
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 7, 3);
        g.addEdge(1, 2, 4);
        g.addEdge(2, 3, 3);
        g.addEdge(2, 4, 6);
        g.addEdge(3, 5, 4);
        g.addEdge(4, 6, 3);
        g.addEdge(5, 6, 1);
        g.addEdge(7, 1, 2);

        // Run the Floyd-Warshall algorithm
        g.floydWarshall();
    }
}
