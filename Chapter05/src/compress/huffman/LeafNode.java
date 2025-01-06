package compress.huffman;

// LeafNode class representing a leaf node in a Huffman tree, extending TreeNode
public class LeafNode extends TreeNode {
    // The byte value associated with this leaf node (represents a character or symbol)
    private final byte value;

    // Constructor to initialize the leaf node with the given byte value
    public LeafNode(byte value) {
        // A leaf node has no children, so we pass 0 to the parent constructor
        super(0);
        this.value = value;
    }

    // Getter method to retrieve the byte value of the leaf node
    public byte getValue() {
        return value;
    }

    // Method to return the value as a hexadecimal string (e.g., for display or debugging)
    public String getValueHexString() {
        return "0x" + String.format("%02X", value);  // Format the byte as a two-digit hex string
    }

    // Override of the accept method to handle visiting this node (specific behavior defined in the visitor)
    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);  // Pass the current leaf node to the visitor's visit method
    }
}
