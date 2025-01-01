package minspan;

import java.util.*;

// Class implementing Reverse Delete algorithm to find the Minimum Spanning Tree (MST)
public class ReverseDelete {

    // Inner class to represent an edge in the graph
    class Edge implements Comparable<Edge> {
        int src;    // Source vertex of the edge
        int dest;   // Destination vertex of the edge
        int weight; // Weight of the edge

        // Constructor to initialize an edge
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        // Override compareTo method to sort edges in descending order by weight
        @Override
        public int compareTo(Edge other) {
            return other.weight - this.weight; // Descending order
        }
    }

    private int V; // Number of vertices in the graph
    private List<Edge> edges; // List of all edges in the graph

    // Constructor to initialize the graph
    public ReverseDelete(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    // Function to add an edge to the graph
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight)); // Add edge to the list
    }

    // Function to find the Minimum Spanning Tree using Reverse Delete algorithm
    public void reverseDeleteMST() {
        // Step 1: Sort edges in descending order of their weights
        Collections.sort(edges);

        // Step 2: Iterate through all edges and try removing them
        int i = 0;
        while (i < edges.size()) {
            Edge edge = edges.get(i); // Get the current edge
            edges.remove(i); // Temporarily remove the edge

            // Step 3: Check if the graph remains connected
            if (!isConnected()) {
                edges.add(i, edge); // If disconnected, add the edge back
                i++;
            }
        }

        // Step 4: Print the resulting MST
        printMST(edges);
    }

    // Function to check if the graph is still connected
    private boolean isConnected() {
        // Step 1: Initialize a disjoint set to manage connectivity of vertices
        DisjointSet ds = new DisjointSet(V);

        // Step 2: Iterate through all edges and perform union operations
        for (Edge edge : edges) {
            int srcParent = ds.find(edge.src); // Find representative of source vertex
            int destParent = ds.find(edge.dest); // Find representative of destination vertex

            if (srcParent != destParent) {
                ds.union(srcParent, destParent); // Union the two sets
            }
        }

        // Step 3: Check if all vertices belong to the same connected component
        int representative = ds.find(0); // Get the representative of the first vertex
        for (int i = 1; i < V; i++) {
            if (ds.find(i) != representative) { // If any vertex has a different representative
                return false; // Graph is not connected
            }
        }

        return true; // Graph is connected
    }

    // Function to print the edges and total weight of the MST
    void printMST(List<Edge> result) {
        int total = 0; // Variable to store the total weight of the MST
        System.out.println("Edges of Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight); // Print edge details
            total += edge.weight; // Add the weight of the edge to the total
        }
        System.out.println("Total weight is: " + total); // Print the total weight of the MST
    }

    // Main method to test the Reverse Delete algorithm
    public static void main(String[] args) {
        int V = 8; // Number of vertices in the graph
        ReverseDelete g = new ReverseDelete(V);

        // Add edges to the graph
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 1);
        g.addEdge(0, 7, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 3, 3);
        g.addEdge(2, 4, 5);
        g.addEdge(3, 4, 6);
        g.addEdge(3, 5, 6);
        g.addEdge(4, 6, 2);
        g.addEdge(4, 7, 3);
        g.addEdge(5, 6, 7);

        // Find and print the Minimum Spanning Tree
        g.reverseDeleteMST();
    }
}

// Class representing a Disjoint Set (Union-Find) data structure
class DisjointSet {
    int[] parent; // Array to store the parent of each vertex
    int[] rank;   // Array to store the rank of each vertex

    // Constructor to initialize the disjoint set
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each vertex is its own parent initially
        }
    }

    // Function to find the representative (root) of a set
    public int find(int x) {
        if (parent[x] != x) { // Path compression for efficiency
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Function to union two sets
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        // Union by rank for efficiency
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}
