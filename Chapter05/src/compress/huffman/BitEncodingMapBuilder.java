package compress.huffman;

import java.util.HashMap;
import java.util.Map;

// The BitEncodingMapBuilder class is responsible for building the mapping between each byte
// and its corresponding Huffman code (represented as a bit sequence)
public class BitEncodingMapBuilder {
    
    // Builds the encoding map by recursively traversing the Huffman tree and generating bit sequences for each byte
    public Map<Byte, BitSequence> buildEncodingMap(HuffmanTree tree) {
        // Initialize an empty map to hold the byte-to-bit sequence mappings
        Map<Byte, BitSequence> map = new HashMap<>();
        
        // Start the recursive tree traversal from the root of the Huffman tree
        buildHelper(tree.getRoot(), "", map);
        
        // Return the populated map
        return map;
    }

    // Recursive helper method to traverse the Huffman tree and assign bit sequences to each leaf node
    private void buildHelper(TreeNode node, String currSequence, Map<Byte, BitSequence> map) {
        // If the node is a leaf, we have reached a byte value. Add the byte and its corresponding bit sequence to the map.
        if (node instanceof LeafNode leafNode) {
            // Create a new BitSequence with the current binary sequence and store it in the map with the byte value as the key
            map.put(leafNode.getValue(), new BitSequence(currSequence));
        } else {
            // If the node is not a leaf, recursively traverse the left and right children of the internal node
            // Append '0' for left child and '1' for right child to the current sequence
            buildHelper(node.getLeftChild(), currSequence + "0", map);
            buildHelper(node.getRightChild(), currSequence + "1", map);
        }
    }
}
