/*
 * 
 * The code is an implementation of a layout algorithm for visualizing an H-tree. 
 * The H-tree is a fractal structure, and the layout algorithm places the nodes of the tree in a 2D space 
 * using recursive logic. Each node has its own (x, y) coordinates, and these coordinates are calculated 
 * based on the depth and structure of the tree.
 * 
 * */

public class HTreeLayout {

	// Inner class representing a node in the tree
	private static class TreeNode {
		int id;  // Unique identifier for the node
		int depth;  // Depth of the node in the tree
		double x, y;  // The (x, y) coordinates of the node
		TreeNode parent;  // The parent node of the current node
		TreeNode[] children;  // Array to hold the child nodes (at most two children per node)

		// Constructor to initialize a tree node with an ID, depth, parent, and children
		public TreeNode(int id, int depth, TreeNode parent, TreeNode[] children) {
			this.id = id;
			this.depth = depth;
			this.parent = parent;
			this.children = children;
		}
	}

	public static void main(String[] args) {
		// Example usage
		// Build an example H-tree structure
		TreeNode root = buildHTree();

		// Apply the H-tree layout algorithm to the tree with a given branch length and fractional reduction
		hTreeLayout(root, 50.0, 2.0);

		// Print the tree structure and the (x, y) coordinates of each node
		printTree(root);
	}

	// Function to build a simple example H-tree structure with 7 nodes
	private static TreeNode buildHTree() {
		// Create nodes at different depths
		TreeNode node1 = new TreeNode(1, 0, null, new TreeNode[2]);  // Root node at depth 0
		TreeNode node2 = new TreeNode(2, 1, node1, new TreeNode[2]);  // Child node at depth 1
		TreeNode node3 = new TreeNode(3, 1, node1, new TreeNode[2]);  // Another child at depth 1
		TreeNode node4 = new TreeNode(4, 2, node2, new TreeNode[2]);  // Child node at depth 2
		TreeNode node5 = new TreeNode(5, 2, node2, new TreeNode[2]);  // Another child at depth 2
		TreeNode node6 = new TreeNode(6, 2, node3, new TreeNode[2]);  // Child node at depth 2
		TreeNode node7 = new TreeNode(7, 2, node3, new TreeNode[2]);  // Another child at depth 2

		// Link child nodes to their respective parents
		node1.children[0] = node2;
		node1.children[1] = node3;
		node2.children[0] = node4;
		node2.children[1] = node5;
		node3.children[0] = node6;
		node3.children[1] = node7;

		// Return the root node of the tree
		return node1;
	}

	// Function to lay out the tree using the H-tree layout algorithm
	private static void hTreeLayout(TreeNode d, double branchLength, double fraction) {
		// Base case: root node at depth 0
		if (d.depth == 0) {
			d.x = 0.0;  // Set x-coordinate to 0 for the root node
			d.y = 0.0;  // Set y-coordinate to 0 for the root node
		} else {
			// Position nodes at alternating horizontal and vertical directions based on depth
			if (d.depth % 2 == 1) {  // Odd depth (horizontal position)
				if (d.id == d.parent.children[0].id) {
					d.x = d.parent.x - branchLength;  // Left child
					d.y = d.parent.y;  // Same y as parent
				} else {
					d.x = d.parent.x + branchLength;  // Right child
					d.y = d.parent.y;  // Same y as parent
				}
			} else {  // Even depth (vertical position)
				if (d.id == d.parent.children[0].id) {
					d.x = d.parent.x;  // Same x as parent
					d.y = d.parent.y - branchLength;  // Above the parent
				} else {
					d.x = d.parent.x;  // Same x as parent
					d.y = d.parent.y + branchLength;  // Below the parent
				}
			}
			// Reduce branch length by a fractional amount for the next level of the tree
			branchLength = branchLength / fraction;
		}

		// Recursively layout the children of the current node
		if (d.children != null) {
			// Recursively lay out the left child, if it exists
			if (d.children[0] != null) 
				hTreeLayout(d.children[0], branchLength, fraction);

			// Recursively lay out the right child, if it exists
			if (d.children[1] != null) 
				hTreeLayout(d.children[1], branchLength, fraction);
		}
	}

	// Function to print the tree structure and node coordinates
	private static void printTree(TreeNode root) {
		// Start printing from the root node
		printTreeNode(root, "");
	}

	// Recursive function to print each node in the tree with its coordinates
	private static void printTreeNode(TreeNode node, String indent) {
		// If the node is not null, print its details
		if (node != null) {
			System.out.println(indent + "Node " + node.id + " (x: " + node.x + ", y: " + node.y + ")");
			// Recursively print the children nodes
			if (node.children != null) {
				printTreeNode(node.children[0], indent + "  ");
				printTreeNode(node.children[1], indent + "  ");
			}
		}
	}
}
