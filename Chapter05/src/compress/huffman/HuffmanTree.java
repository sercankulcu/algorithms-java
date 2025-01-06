package compress.huffman;

// HuffmanTree class represents a Huffman tree with a single root node
public class HuffmanTree {
    // The root node of the Huffman tree
    private final TreeNode root;

    // Constructor that initializes the Huffman tree with the given root node
    public HuffmanTree(TreeNode root) {
        this.root = root;
    }

    // Getter method for the root of the Huffman tree
    public TreeNode getRoot() {
        return root;
    }
}
