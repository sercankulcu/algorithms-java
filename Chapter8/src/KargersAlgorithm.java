import java.util.ArrayList; // Import for dynamic array implementation
import java.util.HashMap; // Import for graph representation as adjacency list
import java.util.List; // Import for using lists
import java.util.Map; // Import for mapping vertices to their adjacency lists
import java.util.Random; // Import for random edge selection

public class KargersAlgorithm {

    // Class to represent an undirected graph
    static class Graph {
        Map<Integer, List<Integer>> adjacencyList; // Adjacency list representation of the graph

        // Constructor to initialize the graph with the given number of vertices
        Graph(int vertices) {
            adjacencyList = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.put(i, new ArrayList<>()); // Initialize adjacency list for each vertex
            }
        }

        // Adds an undirected edge between two vertices
        void addEdge(int src, int dest) {
            adjacencyList.get(src).add(dest); // Add destination vertex to the source's list
            adjacencyList.get(dest).add(src); // Add source vertex to the destination's list
        }

        // Contracts the edge (u, v) by merging vertex v into vertex u
        void contractEdge(int u, int v) {
            System.out.println("Contracting edge: (" + u + ", " + v + ")");
            List<Integer> adjU = adjacencyList.get(u); // Get adjacency list of vertex u
            List<Integer> adjV = adjacencyList.get(v); // Get adjacency list of vertex v

            adjU.addAll(adjV); // Merge all neighbors of v into u
            adjU.removeIf(vertex -> vertex == u || vertex == v); // Remove self-loops

            // Update adjacency lists of vertices connected to v
            for (int vertex : adjV) {
                List<Integer> adj = adjacencyList.get(vertex);
                for (int i = 0; i < adj.size(); i++) {
                    if (adj.get(i) == v) {
                        adj.set(i, u); // Replace references to v with u
                    }
                }
            }

            adjacencyList.remove(v); // Remove vertex v from the graph
        }

        // Finds the minimum cut in the graph using Karger's randomized algorithm
        int minCut() {
            int minCut = Integer.MAX_VALUE; // Initialize minimum cut value to a large number
            int n = adjacencyList.size(); // Number of vertices in the graph

            // Perform multiple iterations to increase the probability of finding the minimum cut
            for (int i = 0; i < n * n * (int) Math.log(n); i++) {
                Graph copy = copyGraph(); // Create a deep copy of the graph
                int cut = findCut(copy); // Find a cut in the copied graph
                if (cut < minCut) { // Update the minimum cut if a smaller cut is found
                    minCut = cut;
                }
            }
            return minCut;
        }

        // Finds the cut in the graph by repeatedly contracting edges
        int findCut(Graph graph) {
            while (graph.adjacencyList.size() > 2) { // Continue until only two vertices remain
                Random random = new Random(); // Random number generator
                List<Integer> vertices = new ArrayList<>(graph.adjacencyList.keySet());
                int u = vertices.get(random.nextInt(vertices.size())); // Pick a random vertex
                List<Integer> adjU = graph.adjacencyList.get(u);
                int v = adjU.get(random.nextInt(adjU.size())); // Pick a random neighbor
                graph.contractEdge(u, v); // Contract the chosen edge
            }

            // Return the size of the cut (remaining edges between the two vertices)
            return graph.adjacencyList.get(graph.adjacencyList.keySet().iterator().next()).size();
        }

        // Creates a deep copy of the current graph
        Graph copyGraph() {
            Graph copy = new Graph(this.adjacencyList.size());
            for (Map.Entry<Integer, List<Integer>> entry : this.adjacencyList.entrySet()) {
                int vertex = entry.getKey();
                List<Integer> neighbors = entry.getValue();
                for (int neighbor : neighbors) {
                    copy.addEdge(vertex, neighbor);
                }
            }
            return copy;
        }

        // Prints the adjacency list representation of the graph
        void printGraph() {
            System.out.println("Final Contracted Graph:");
            for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
                System.out.print(entry.getKey() + " -> ");
                for (int vertex : entry.getValue()) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        // Create a sample graph with 6 vertices
        int vertices = 6;
        Graph graph = new Graph(vertices);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 0);
        graph.addEdge(0, 2);
        graph.addEdge(1, 5);

        // Run Karger's algorithm to find the minimum cut
        System.out.println("Running Karger's algorithm...");
        int minCut = graph.minCut();
        System.out.println("Minimum cut in the graph: " + minCut);

        // Print the final contracted graph
        graph.printGraph();
    }
}
