import java.util.Arrays;

public class TravellingSalesman {

    // Stores the final minimum cost
    private static int minCost = Integer.MAX_VALUE;

    // Stores the best path
    private static int[] bestPath;

    /**
     * Solves the Traveling Salesman Problem using branch-and-bound.
     * 
     * @param graph   The adjacency matrix representing the distances between cities.
     * @param visited Array to track visited cities.
     * @param current The current city.
     * @param cost    The current cost of the path.
     * @param count   Number of cities visited so far.
     * @param path    Array to store the current path.
     */
    public static void tsp(int[][] graph, boolean[] visited, int current, int cost, int count, int[] path) {
        // Base case: If all cities are visited and we return to the start city
        if (count == graph.length && graph[current][0] > 0) {
            cost += graph[current][0]; // Add the cost of returning to the start city
            if (cost < minCost) {
                minCost = cost; // Update the minimum cost
                bestPath = Arrays.copyOf(path, path.length); // Save the best path
            }
            return;
        }

        // Explore all possible cities from the current city
        for (int next = 0; next < graph.length; next++) {
            if (!visited[next] && graph[current][next] > 0) {
                // Mark the city as visited
                visited[next] = true;
                path[count] = next;

                // Debug: Print the current path and cost
                System.out.println("Visiting city " + next + " from city " + current + " | Cost: " + (cost + graph[current][next]));

                // Recur with the updated cost and path
                tsp(graph, visited, next, cost + graph[current][next], count + 1, path);

                // Backtrack: Mark the city as unvisited
                visited[next] = false;
                path[count] = -1;

                // Debug: Print backtracking information
                System.out.println("Backtracking from city " + next + " to city " + current);
            }
        }
    }

    public static void main(String[] args) {
        // Example graph with 6 cities (represented as an adjacency matrix)
        int[][] graph = {
            {0, 29, 20, 21, 16, 31},
            {29, 0, 15, 29, 28, 40},
            {20, 15, 0, 15, 14, 25},
            {21, 29, 15, 0, 12, 18},
            {16, 28, 14, 12, 0, 22},
            {31, 40, 25, 18, 22, 0}
        };

        int n = graph.length;

        // Initialize visited array and path array
        boolean[] visited = new boolean[n];
        int[] path = new int[n];
        Arrays.fill(path, -1);

        // Mark the starting city (0) as visited
        visited[0] = true;
        path[0] = 0;

        System.out.println("Starting TSP from city 0...");

        // Solve the TSP
        tsp(graph, visited, 0, 0, 1, path);

        // Print the results
        System.out.println("\nMinimum cost: " + minCost);
        System.out.println("Best path: " + Arrays.toString(bestPath));
    }
}
