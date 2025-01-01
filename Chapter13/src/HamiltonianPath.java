import java.util.Arrays;

public class HamiltonianPath {

    /**
     * Method to find a Hamiltonian Path in the given graph.
     * 
     * @param graph The adjacency matrix representation of the graph.
     * @param path  The array to store the Hamiltonian Path.
     * @param pos   The current position in the path.
     * @return true if a Hamiltonian Path is found, false otherwise.
     */
    public static boolean findHamiltonianPath(int[][] graph, int[] path, int pos) {
        // Base case: If all vertices are included in the path, return true
        if (pos == graph.length) {
            return true;
        }

        // Try different vertices as the next candidate in the path
        for (int v = 0; v < graph.length; v++) {
            // Check if adding vertex v to the path is safe
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v; // Add vertex v to the path
                System.out.println("Adding vertex " + v + " at position " + pos);

                // Recursively attempt to complete the path
                if (findHamiltonianPath(graph, path, pos + 1)) {
                    return true; // A valid path is found
                }

                // Backtrack: Remove vertex v from the path
                path[pos] = -1;
                System.out.println("Backtracking from vertex " + v + " at position " + pos);
            }
        }

        return false; // No valid path found
    }

    /**
     * Helper method to check if adding a vertex to the path is valid.
     * 
     * @param v     The vertex to check.
     * @param graph The adjacency matrix representation of the graph.
     * @param path  The current Hamiltonian Path being constructed.
     * @param pos   The current position in the path.
     * @return true if the vertex can be added to the path, false otherwise.
     */
    private static boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        // Ensure the vertex is adjacent to the last vertex in the path
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Ensure the vertex is not already included in the path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true; // Vertex is safe to add
    }

    /**
     * Main method to test the Hamiltonian Path implementation.
     */
    public static void main(String[] args) {
        // Example graph with 12 vertices (represented as an adjacency matrix)
        int[][] graph = {
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0}
        };

        // Path array to store the Hamiltonian Path
        int[] path = new int[graph.length];
        Arrays.fill(path, -1); // Initialize the path with -1

        // Start the path with the first vertex (vertex 0)
        path[0] = 0;

        System.out.println("Attempting to find a Hamiltonian Path in the graph...");

        // Find and print the Hamiltonian Path if it exists
        if (findHamiltonianPath(graph, path, 1)) {
            System.out.println("Hamiltonian Path found:");
            System.out.println(Arrays.toString(path));
        } else {
            System.out.println("No Hamiltonian Path exists in the graph.");
        }
    }
}
