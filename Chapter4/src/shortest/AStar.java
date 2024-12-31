package shortest;

import java.util.*;

public class AStar {
    // Inner class representing a vertex with its heuristic value
    class Vertex {
        int vertex; // The vertex index
        int heuristic; // The heuristic value for the vertex

        Vertex(int v, int h) {
            vertex = v; 
            heuristic = h;
        }
    }

    private int V; // Number of vertices in the graph
    private LinkedList<Vertex>[] adj; // Adjacency list representation of the graph
    private int[] heuristicValues; // Array to store heuristic values of all vertices

    // Constructor to initialize the graph
    AStar(int v) {
        V = v; // Set the number of vertices
        adj = new LinkedList[V]; // Initialize adjacency lists for each vertex
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>(); // Initialize each list

        heuristicValues = new int[V]; // Initialize the heuristic values array
    }

    // Method to add an edge to the graph
    void addEdge(int src, int dest, int weight) {
        // Add a directed edge from src to dest with a given weight
        adj[src].add(new Vertex(dest, weight));
    }

    // Method to set the heuristic value for a vertex
    void setHeuristicValue(int vertex, int heuristic) {
        heuristicValues[vertex] = heuristic; // Assign heuristic value to the vertex
    }

    // Implementation of the A* algorithm
    void aStar(int src, int dest) {
        // Priority queue to store vertices based on their f-values (g + h)
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.heuristic));

        // Array to keep track of whether a vertex has been visited
        boolean[] visited = new boolean[V];

        // Array to store g-values (actual cost from source to the vertex)
        int[] gValues = new int[V];
        Arrays.fill(gValues, Integer.MAX_VALUE); // Initialize g-values to infinity
        gValues[src] = 0; // Cost to reach the source is 0

        // Add the source vertex to the priority queue with its heuristic value
        pq.add(new Vertex(src, heuristicValues[src]));

        // While there are vertices to process in the priority queue
        while (!pq.isEmpty()) {
            // Extract the vertex with the smallest f-value (lowest g + h)
            int u = pq.poll().vertex;
            System.out.println("*   " + u + " is extracted from queue");

            // Mark the current vertex as visited
            visited[u] = true;

            // If the destination is reached, print the result and return
            if (u == dest) {
                System.out.println("(" + src + " --> " + dest + "): " + gValues[u]);
                return;
            }

            // Explore the neighbors of the current vertex
            for (Vertex v : adj[u]) {
                int vertex = v.vertex; // Neighbor vertex
                int weight = v.heuristic; // Edge weight
                System.out.println("** for each neighbour " + vertex + " with weight " + weight);

                // Update g-value and add to priority queue if a shorter path is found
                if (!visited[vertex] && gValues[u] != Integer.MAX_VALUE && gValues[u] + weight < gValues[vertex]) {
                    System.out.print("*** shorter path found " + gValues[vertex] + "->");
                    gValues[vertex] = gValues[u] + weight; // Update g-value
                    System.out.println(gValues[vertex]);
                    // Add the vertex to the priority queue with updated f-value
                    pq.add(new Vertex(vertex, gValues[vertex] + heuristicValues[vertex]));
                }
            }
        }

        // If the destination vertex cannot be reached
        System.out.println("Destination not reachable from the source");
    }

    public static void main(String[] args) {
        // Create a graph with 8 vertices
    	AStar g = new AStar(8);

        // Add edges with weights
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 7, 3);
        g.addEdge(1, 2, 4);
        g.addEdge(2, 3, 3);
        g.addEdge(2, 4, 6);
        g.addEdge(3, 5, 4);
        g.addEdge(4, 6, 3);
        g.addEdge(5, 6, 1);

        // Set heuristic values for all vertices
        g.setHeuristicValue(0, 6);
        g.setHeuristicValue(1, 11);
        g.setHeuristicValue(2, 10);
        g.setHeuristicValue(3, 9);
        g.setHeuristicValue(4, 8);
        g.setHeuristicValue(5, 7);
        g.setHeuristicValue(6, 0); // Destination vertex has heuristic value 0
        g.setHeuristicValue(7, 12);

        // Perform A* search from vertex 0 to vertex 6
        System.out.println("Shortest paths from vertex 0:");
        g.aStar(0, 6);
    }
}
