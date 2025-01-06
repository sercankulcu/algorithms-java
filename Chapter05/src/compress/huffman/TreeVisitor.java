package compress.huffman;

// Interface defining methods for visiting different types of tree nodes.
public interface TreeVisitor {

    // Method to visit and perform operations on an InternalNode.
    void visit(InternalNode node);

    // Method to visit and perform operations on a LeafNode.
    void visit(LeafNode node);
}
