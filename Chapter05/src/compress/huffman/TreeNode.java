package compress.huffman;

// Abstract class representing a node in a tree, which implements Comparable for frequency-based comparison.
public abstract class TreeNode implements Comparable<TreeNode> {
    // Left and right children of the tree node
    private final TreeNode leftChild, rightChild;
    
    // Frequency of the node (used in Huffman coding to represent weight)
    private int frequency;

    // Constructor initializing frequency, with left and right children set to null
    protected TreeNode(int frequency) {
        this(frequency, null, null);
    }

    // Constructor initializing frequency, left child, and right child
    protected TreeNode(int frequency, TreeNode leftChild, TreeNode rightChild) {
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // Getter for the frequency of the node
    public int getFrequency() {
        return frequency;
    }

    // Method to increment the frequency of the node (used during tree updates)
    public void increaseFrequency() {
        frequency++;
    }

    // Getter for the left child node
    public TreeNode getLeftChild() {
        return leftChild;
    }

    // Getter for the right child node
    public TreeNode getRightChild() {
        return rightChild;
    }

    // Abstract method to accept a TreeVisitor, allowing different types of visits on tree nodes
    public abstract void accept(TreeVisitor visitor);

    // Compares two tree nodes based on their frequency values (used for sorting in Huffman coding)
    @Override
    public int compareTo(TreeNode o) {
        return Integer.compare(frequency, o.frequency);
    }
}
