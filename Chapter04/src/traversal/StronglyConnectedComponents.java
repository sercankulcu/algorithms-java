package traversal;

import java.util.*;

// Class representing a directed graph that finds strongly connected components
public class StronglyConnectedComponents {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adj; // Adjacency lists

    // Constructor
    StronglyConnectedComponents(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Add a directed edge v -> w
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // DFS used to fill stack according to finishing times
    void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                fillOrder(n, visited, stack);
        }

        stack.push(v);
    }

    // Get the transpose of the graph
    StronglyConnectedComponents getTranspose() {
        StronglyConnectedComponents g = new StronglyConnectedComponents(V);

        for (int v = 0; v < V; v++) {
            Iterator<Integer> i = adj[v].iterator();
            while (i.hasNext()) {
                int n = i.next();
                g.adj[n].add(v); // reverse edge
            }
        }

        return g;
    }

    // Standard DFS for printing one SCC
    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Find and print all strongly connected components
    void printSCCs() {
        Stack<Integer> stack = new Stack<>();

        // Step 1: Fill vertices in stack according to finishing times
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                fillOrder(i, visited, stack);
        }

        // Step 2: Create reversed graph
        StronglyConnectedComponents gr = getTranspose();

        // Step 3: Process all vertices in order defined by stack
        Arrays.fill(visited, false);
        int componentCount = 0;

        while (!stack.empty()) {
            int v = stack.pop();

            if (!visited[v]) {
                componentCount++;
                System.out.print("Component " + componentCount + ": ");
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }

        System.out.println("Toplam strongly connected component sayisi: " + componentCount);
    }

    // Main method
    public static void main(String[] args) {
        StronglyConnectedComponents g = new StronglyConnectedComponents(8);

        // First SCC: 0,1,2
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);

        // Edge from first SCC to second SCC
        g.addEdge(2, 3); // 2 -> 3 var
        // 3 -> 2 yok

        // Second SCC: 3,4,5
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 3);

        // Edge from second SCC to third SCC
        g.addEdge(5, 6);

        // Third SCC: 6,7
        g.addEdge(6, 7);
        g.addEdge(7, 6);

        System.out.println("Strongly Connected Components:");
        g.printSCCs();
    }
}