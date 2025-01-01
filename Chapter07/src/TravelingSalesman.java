import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman {

    // Function to solve the Traveling Salesman Problem using the Nearest Neighbor Algorithm
    public static List<Integer> nearestNeighbor(int[][] graph, int start) {

        int n = graph.length;  // Number of cities (size of the graph matrix)
        List<Integer> tour = new ArrayList<>();  // List to store the cities in the tour
        boolean[] visited = new boolean[n];  // Array to keep track of whether a city has been visited
        int distance = 0;  // Variable to keep track of the total distance of the tour

        // Start from the specified starting city
        int currentCity = start;  // Set the current city as the starting city
        tour.add(currentCity);  // Add the starting city to the tour
        visited[currentCity] = true;  // Mark the starting city as visited

        // Repeat until all cities have been visited
        while (tour.size() < n) {
            int nearestCity = -1;  // To store the nearest unvisited city
            int minDistance = Integer.MAX_VALUE;  // To store the minimum distance found

            // Find the nearest unvisited city
            for (int nextCity = 0; nextCity < n; nextCity++) {
                if (!visited[nextCity] && graph[currentCity][nextCity] < minDistance) {
                    // If the city has not been visited and the distance is shorter than the current minimum
                    minDistance = graph[currentCity][nextCity];  // Update the minimum distance
                    nearestCity = nextCity;  // Update the nearest city
                }
            }

            // Move to the nearest unvisited city
            if (nearestCity != -1) {
                tour.add(nearestCity);  // Add the nearest city to the tour
                visited[nearestCity] = true;  // Mark the city as visited
                currentCity = nearestCity;  // Update the current city
                distance += minDistance;  // Add the distance to the total distance
            } else {
                // If no unvisited city is found, break the loop (though this shouldn't happen if all cities are reachable)
                break;
            }
        }

        // Return to the starting city to complete the tour (closing the loop)
        tour.add(start);  // Add the starting city to the end of the tour to complete the cycle
        distance += graph[currentCity][start];  // Add the distance from the last city back to the start
        System.out.println("Total Distance: " + distance);  // Print the total distance of the tour
        return tour;  // Return the list of cities in the tour
    }

    public static void main(String[] args) {
        // Example graph representing distances between cities (adjacency matrix)
        int[][] graph = {
                {0, 10, 15, 20, 25},  // Distances from city 0
                {10, 0, 35, 25, 30},  // Distances from city 1
                {15, 35, 0, 30, 35},  // Distances from city 2
                {20, 25, 30, 0, 40},  // Distances from city 3
                {20, 25, 30, 45, 0}   // Distances from city 4
        };

        int startCity = 0;  // Specify the starting city (city 0 in this case)

        // Find the approximate solution using the Nearest Neighbor Algorithm
        List<Integer> tour = nearestNeighbor(graph, startCity);

        // Print the approximate tour (order of cities visited)
        System.out.println("Approximate Tour: " + tour);
    }
}
