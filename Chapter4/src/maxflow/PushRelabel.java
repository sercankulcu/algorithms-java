package maxflow;

import java.util.List;
import java.util.ArrayList;

public class PushRelabel {

    List<Node> nodes; // List to store all the nodes in the graph

    public PushRelabel(int nodeCount) {
        nodes = new ArrayList<>();

        // Initialize height and excess flow of nodes to 0
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(new Node(i, 0, 0)); // Add nodes with initial height 0 and excess flow 0
        }
    }

    // Create an edge between two nodes with the given capacity, initializing flow to zero
    public void addEdge(int source, int dest, int capacity) {
        nodes.get(source).addEdge(nodes.get(dest), 0, capacity); // Add edge to the source node's adjacency list
    }

    /*
     * Max Flow:
     * The maximum flow from source (first node) to sink (last node).
     * This function applies the Push-Relabel algorithm to find the maximum flow.
     */
    public int getMaxFlow(int source, int sink) {
        preflow(nodes.get(source)); // Preprocess the source node to initialize the preflow

        // While there are active nodes (nodes with excess flow), continue pushing or relabeling
        Node activeNode = getActiveNode();
        while (activeNode != null) {
            //System.out.println("Active Node: " + activeNode.id + " Excess Flow: " + activeNode.excessFlow);
            if (!push(sink, activeNode)) {
                relabel(activeNode); // If no push is possible, relabel the node
                System.out.println("Relabeling Node: " + activeNode.id + " New Height: " + activeNode.height);
            }
            activeNode = getActiveNode(); // Get the next active node
        }

        return nodes.get(sink).excessFlow; // The excess flow at the sink node is the maximum flow
    }

    /*
     * Preflow:
     * Initialize the source node's height to the number of nodes (|V|) and push maximum flow
     * to the adjacent nodes. Also, create residual backward edges with zero capacity.
     */
    private void preflow(Node s) {
        s.height = nodes.size(); // Set height of source to number of nodes (|V|)

        System.out.println("Preflow at Source Node: " + s.id + " Initial Height: " + s.height);

        // Saturate all outgoing edges from the source node
        for (Edge e : s.edges) {
            e.flow = e.capacity; // Push maximum flow along the edge
            e.dest.excessFlow += e.flow; // Increase the excess flow of the destination node
            e.dest.addEdge(s, -e.flow, 0); // Add a backward edge with negative flow and zero capacity

            System.out.println("Pushed Flow: " + e.flow + " from Source Node: " + s.id + " to Dest Node: " + e.dest.id);
        }
    }

    /*
     * Push:
     * Push flow from the current node (with excess flow) to its neighbors if the destination node
     * has a lower height and there is available capacity on the edge.
     * If the flow can be pushed, update the flow, excess flows, and reverse edges.
     */
    private boolean push(int sink, Node n) {
        // Try to push flow along each edge of the current node
        for (Edge e : n.edges) {
            if ((n.height > e.dest.height) && (e.flow != e.capacity)) {
                // Push flow to adjacent node if it has lower height and available capacity
                int flow = Math.min(e.capacity - e.flow, n.excessFlow); // Determine the maximum flow we can push
                //System.out.println("Pushing Flow: " + flow + " from Node: " + n.id + " to Node: " + e.dest.id);
                
                if (e.dest.id == sink) {
                    System.out.println("Sink Node: " + e.dest.id + " Excess Flow: " + e.dest.excessFlow + " + " + flow);
                }

                n.excessFlow -= flow; // Decrease the excess flow of the pushing node
                e.dest.excessFlow += flow; // Increase the excess flow of the destination node
                e.flow += flow; // Increase the flow along the edge
                updateReverseEdge(e, flow); // Update reverse edge flow

                return true; // Flow was successfully pushed
            }
        }
        return false; // No flow was pushed
    }

    /*
     * Relabel:
     * If no flow could be pushed from the current node, find the minimum height of the adjacent
     * nodes and update the node's height to one greater than the minimum height.
     */
    private void relabel(Node n) {
        int minAdjHeight = Integer.MAX_VALUE;

        // Find the minimum height of adjacent nodes
        for (Edge e : n.edges) {
            if ((e.flow != e.capacity) && (e.dest.height < minAdjHeight)) {
                minAdjHeight = e.dest.height; // Track the minimum adjacent height
                n.height = minAdjHeight + 1; // Set node's height to one greater than the minimum height
            }
        }
    }

    /*
     * Active Node:
     * This function returns an active node, which is a node with excess flow that is neither the source nor the sink.
     */
    private Node getActiveNode() {
        // Iterate through nodes (excluding source and sink) to find a node with excess flow
        for (int i = 1; i < nodes.size() - 1; i++) {
            if (nodes.get(i).excessFlow > 0) {
                return nodes.get(i); // Return the first active node
            }
        }
        return null; // No active nodes found
    }

    /*
     * Reverse Edge:
     * Updates the reverse edge in the residual graph. If a reverse edge exists, the flow is decreased.
     * If it doesn't exist, a new reverse edge with negative flow is created.
     */
    private void updateReverseEdge(Edge edge, int flow) {
        // Check if reverse edge exists, if so update it, else create a new reverse edge
        for (Edge e : edge.dest.edges) {
            if (e.dest.equals(edge.source)) {
                e.flow -= flow; // Decrease flow in reverse edge
                return;
            }
        }

        // If reverse edge does not exist, create a new reverse edge with negative flow
        edge.dest.addEdge(edge.source, -flow, 0);
    }

    /* Inner Classes for Node and Edge Representation */

    // Node class represents a node in the graph
    class Node {
        int id; // Node identifier
        int height; // Height of the node in the Push-Relabel algorithm
        int excessFlow; // Excess flow at the node
        List<Edge> edges; // List of outgoing edges from the node

        // Constructor to initialize node with id, height, and excess flow
        Node(int id, int height, int excessFlow) {
            this.id = id;
            this.height = height;
            this.excessFlow = excessFlow;
            edges = new ArrayList<>();
        }

        // Method to add an edge to the node
        void addEdge(Node dest, int flow, int capacity) {
            Edge edge = new Edge(this, dest, flow, capacity); // Create a new edge
            edges.add(edge); // Add edge to the node's adjacency list
        }
    }

    // Edge class represents an edge between two nodes with flow and capacity
    class Edge {
        Node source; // Source node
        Node dest; // Destination node
        int flow; // Current flow along the edge
        int capacity; // Capacity of the edge

        // Constructor to initialize edge with source, destination, flow, and capacity
        Edge(Node source, Node dest, int flow, int capacity) {
            this.source = source;
            this.dest = dest;
            this.flow = flow;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {
        // Create a graph with 6 nodes
        PushRelabel g = new PushRelabel(6);

        // Add edges to the graph with respective capacities
        g.addEdge(0, 1, 16);
        g.addEdge(0, 2, 13);
        g.addEdge(1, 2, 10);
        g.addEdge(1, 3, 12);
        g.addEdge(2, 1, 4);
        g.addEdge(2, 4, 14);
        g.addEdge(3, 2, 9);
        g.addEdge(3, 5, 20);
        g.addEdge(4, 3, 7);
        g.addEdge(4, 5, 4);

        int source = 0; // Source node index
        int sink = 5; // Sink node index

        // Find and print the maximum flow from source to sink using the Push-Relabel algorithm
        int maxFlow = g.getMaxFlow(source, sink);
        System.out.println("Maximum flow from source to sink: " + maxFlow); // Output the result
    }
}
