package compress.huffman;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

// HuffmanTreeEncoder class responsible for encoding a Huffman tree and writing it to an OutputStream
public class HuffmanTreeEncoder {

    // Method to encode the Huffman tree and write the encoded data to the provided OutputStream
    public void encode(HuffmanTree huffmanTree, OutputStream outputStream) throws IOException {
        // Create a BitSequence to store the encoded tree structure
        BitSequence sequence = new BitSequence();
        
        // Start the encoding process by recursively encoding the tree's root
        encodeHelper(huffmanTree.getRoot(), sequence);
        
        // Convert the BitSequence to a byte array (the encoded tree data)
        byte[] encodedTree = sequence.toByteArray();
        
        // Allocate space for the tree's length in bits and convert it to a byte array
        byte[] treeLengthInBits = ByteBuffer.allocate(Integer.BYTES).putInt(sequence.numBits()).array();
        
        // Write the length of the tree and the encoded tree data to the output stream
        outputStream.write(treeLengthInBits);
        outputStream.write(encodedTree);
    }

    // Recursive helper method to encode each node of the Huffman tree
    private void encodeHelper(TreeNode node, BitSequence bitSequence) {
        // Base case: if the node is null, just return
        if (node == null) {
            return;
        }
        
        // If the node is a LeafNode, encode it as a leaf
        if (node instanceof LeafNode leafNode) {
            // Add a '1' bit to indicate it's a leaf node
            bitSequence.addBit(true);
            
            // For leaf nodes, add each bit of the byte value to the BitSequence
            for (int i = 0; i < Byte.SIZE; i++) {
                // Check if the ith bit of the leaf node's byte value is set, and add it
                bitSequence.addBit(isSet(leafNode.getValue(), i));
            }
        } else {
            // If the node is an internal node, encode it as an internal node
            bitSequence.addBit(false);
            
            // Recursively encode the left and right children of the internal node
            encodeHelper(node.getLeftChild(), bitSequence);
            encodeHelper(node.getRightChild(), bitSequence);
        }
    }

    // Helper method to check if a specific bit is set in a byte value
    private boolean isSet(byte value, int bitPosition) {
        // Perform a bitwise AND with a mask to check if the bit at the specified position is set
        return (value & (1 << bitPosition)) != 0;
    }
}
