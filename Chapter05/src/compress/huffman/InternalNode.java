package compress.huffman;

// InternalNode class representing an internal node in a Huffman tree, extending TreeNode
public class InternalNode extends TreeNode {

    // Constructor to initialize an internal node with left and right children
    public InternalNode(TreeNode leftChild, TreeNode rightChild) {
        // Internal nodes have a frequency equal to the sum of the frequencies of their children
        super(leftChild.getFrequency() + rightChild.getFrequency(), leftChild, rightChild);
    }

    // Override of the accept method to handle visiting this node and its children
    @Override
    public void accept(TreeVisitor visitor) {
        // Visit the current internal node
        visitor.visit(this);
        
        // Recursively accept the visitor for the left and right children
        getLeftChild().accept(visitor);
        getRightChild().accept(visitor);
    }
}
