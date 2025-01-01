import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentGraphTraversal {

    // Node class representing each node in the graph
    static class Node {
        int value;
        List<Node> neighbors;

        public Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        // Add neighbor to this node
        public void addNeighbor(Node neighbor) {
            this.neighbors.add(neighbor);
        }
    }

    // BFS Task that will be run in parallel
    static class BFSTask extends RecursiveTask<List<Integer>> {
        private static final long serialVersionUID = 1L;
        private final Node node;
        private final Set<Node> visited;
        private final long taskId;  // Task ID for logging

        public BFSTask(Node node, Set<Node> visited, long taskId) {
            this.node = node;
            this.visited = visited;
            this.taskId = taskId;
        }

        @Override
        protected List<Integer> compute() {
            List<Integer> result = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.add(node);

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                if (visited.contains(current)) {
                    continue;
                }
                visited.add(current);

                // Print the node value along with the task ID
                System.out.println("Task ID: " + taskId + " - Visited Node: " + current.value);

                result.add(current.value);

                // Launch BFS in parallel for all unvisited neighbors
                List<BFSTask> tasks = new ArrayList<>();
                for (Node neighbor : current.neighbors) {
                	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    if (!visited.contains(neighbor)) {
                        @SuppressWarnings("deprecation")
						BFSTask task = new BFSTask(neighbor, visited, Thread.currentThread().getId());
                        tasks.add(task);

                        // Print the task creation log
                        System.out.println("Created Task ID: " + task.taskId + " for Neighbor: " + neighbor.value);

                        task.fork(); // Launch the task asynchronously
                    }
                }

                // Wait for all parallel tasks to finish before continuing
                for (BFSTask task : tasks) {
                    result.addAll(task.join());
                }
            }

            return result;
        }
    }

    // Method to perform Concurrent BFS in parallel using ForkJoinPool
    public static List<Integer> parallelBFS(Node startNode) {
        Set<Node> visited = new HashSet<>();
        try (ForkJoinPool pool = new ForkJoinPool()) {
			@SuppressWarnings("deprecation")
			BFSTask task = new BFSTask(startNode, visited, Thread.currentThread().getId());
			return pool.invoke(task);
		}
    }

    public static void main(String[] args) {
        // Create nodes for the graph
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        // Build the graph by adding neighbors
        node1.addNeighbor(node2);
        node1.addNeighbor(node3);
        node2.addNeighbor(node4);
        node3.addNeighbor(node5);
        node4.addNeighbor(node6);
        node5.addNeighbor(node7);
        node5.addNeighbor(node8);

        // Perform parallel BFS starting from node1
        List<Integer> traversalResult = parallelBFS(node1);

        // Output the result of BFS traversal
        System.out.println("Concurrent BFS Traversal Result: " + traversalResult);
    }
}
