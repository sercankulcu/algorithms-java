import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
// This class represents a simple graph structure using an adjacency list.
public class VertexCoverApprox {
    private Map<Integer, HashSet<Integer>> adjList;

    public VertexCoverApprox(int vertices) {
        adjList = new HashMap<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new HashSet<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // For undirected graphs
    }

    // This function implements a greedy approximation algorithm for vertex cover
    public Set<Integer> findVertexCover() {
        HashSet<Integer> vertexCover = new HashSet<>();
        
        // Iterate over all edges in the graph
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjList.entrySet()) {
            int vertex = entry.getKey();
            
            // Check if any neighbor of the current vertex is not yet in the cover
            boolean neighborInCover = false;
            for (int neighbor : entry.getValue()) {
                if (vertexCover.contains(neighbor)) {
                    neighborInCover = true;
                    break;
                }
            }
            
            // If no neighbor is in the cover, add the current vertex to the cover
            if (!neighborInCover) {
                vertexCover.add(vertex);
            }
        }

        return vertexCover;
    }

    public static void main(String[] args) {
        int vertices = 8;
        VertexCoverApprox graph = new VertexCoverApprox(vertices);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        System.out.println("Vertex Cover: " + graph.findVertexCover());
    }
}
