import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// This class represents a simple graph structure using an adjacency list.
public class VertexCover {
    
    // The adjacency list stores the graph structure, where each vertex is mapped to a set of its neighbors.
    private Map<Integer, HashSet<Integer>> adjList;

    // Constructor to initialize the graph with the given number of vertices.
    public VertexCover(int vertices) {
        adjList = new HashMap<>(vertices); // Initialize the adjacency list with the specified number of vertices.
        
        // For each vertex, create an empty set to store its neighbors.
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new HashSet<>());
        }
    }

    // Adds an undirected edge between two vertices (src and dest) in the graph.
    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest); // Add dest to the adjacency list of src.
        adjList.get(dest).add(src); // Add src to the adjacency list of dest (because the graph is undirected).
    }

    // This function implements a greedy approximation algorithm for finding a vertex cover.
    public Set<Integer> findVertexCover() {
        // HashSet to store the vertices that are part of the vertex cover.
        HashSet<Integer> vertexCover = new HashSet<>();
        
        // Iterate over all vertices and their adjacency lists (edges).
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjList.entrySet()) {
            int vertex = entry.getKey(); // Get the current vertex.
            
            // Flag to check if any neighbor of the current vertex is already in the vertex cover.
            boolean neighborInCover = false;
            
            // Iterate through the neighbors of the current vertex.
            for (int neighbor : entry.getValue()) {
                // If a neighbor is already in the vertex cover, we can skip adding the current vertex.
                if (vertexCover.contains(neighbor)) {
                    neighborInCover = true;
                    break;
                }
            }
            
            // If no neighbor is in the vertex cover, we add the current vertex to the vertex cover.
            if (!neighborInCover) {
                vertexCover.add(vertex);
            }
        }

        // Return the set of vertices that form the vertex cover.
        return vertexCover;
    }

    public static void main(String[] args) {
        // Initialize a graph with 8 vertices.
        int vertices = 8;
        VertexCover graph = new VertexCover(vertices);

        // Add edges to the graph. These edges define the connectivity between the vertices.
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);

        // Print the resulting vertex cover using the greedy approximation algorithm.
        System.out.println("Vertex Cover: " + graph.findVertexCover());
    }
}
