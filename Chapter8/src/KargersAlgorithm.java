import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KargersAlgorithm {

    // Class to represent an undirected graph
    static class Graph {
        // Adjacency list representation of the graph
        Map<Integer, List<Integer>> adjacencyList;

        Graph(int vertices) {
            adjacencyList = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        // Function to add an edge between two vertices
        void addEdge(int src, int dest) {
            adjacencyList.get(src).add(dest);
            adjacencyList.get(dest).add(src);
        }

        // Function to contract an edge between two vertices
        void contractEdge(int u, int v) {
            System.out.println("Contracting edge: (" + u + ", " + v + ")");
            // Merge the adjacent vertices of u and v
            List<Integer> adjU = adjacencyList.get(u);
            List<Integer> adjV = adjacencyList.get(v);
            adjU.addAll(adjV);
            // Remove self-loops
            adjU.removeIf(vertex -> vertex == u || vertex == v);
            // Update references to v to point to u
            for (int vertex : adjV) {
                List<Integer> adj = adjacencyList.get(vertex);
                for (int i = 0; i < adj.size(); i++) {
                    if (adj.get(i) == v) {
                        adj.set(i, u);
                    }
                }
            }
            // Remove vertex v from the graph
            adjacencyList.remove(v);
        }

        // Function to find the minimum cut in the graph
        int minCut() {
            int minCut = Integer.MAX_VALUE;
            int n = adjacencyList.size();
            // Perform the contraction n^2 * log(n) times to increase the probability of finding the minimum cut
            for (int i = 0; i < n * n * (int) Math.log(n); i++) {
                Graph copy = copyGraph();
                int cut = findCut(copy);
                if (cut < minCut) {
                    minCut = cut;
                }
            }
            return minCut;
        }

        // Function to find the cut in a graph
        int findCut(Graph graph) {
            while (graph.adjacencyList.size() > 2) {
                // Choose a random edge (u, v) uniformly at random
                Random random = new Random();
                List<Integer> vertices = new ArrayList<>(graph.adjacencyList.keySet());
                int u = vertices.get(random.nextInt(vertices.size()));
                List<Integer> adjU = graph.adjacencyList.get(u);
                int v = adjU.get(random.nextInt(adjU.size()));
                // Contract the edge (u, v)
                graph.contractEdge(u, v);
            }
            // The remaining edges form a cut, return the size of the cut
            return graph.adjacencyList.get(graph.adjacencyList.keySet().iterator().next()).size();
        }

        // Function to create a deep copy of the graph
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

        // Function to print the contracted graph
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
        // Create a sample graph
        int vertices = 6;
        Graph graph = new Graph(vertices);
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
        // Print the contracted graph
        graph.printGraph();
    }
}
