package compress.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

// HuffmanTreeDecoder class responsible for decoding a Huffman tree from an InputStream
public class HuffmanTreeDecoder {
    // Tracks the current bit position while decoding the tree
    private int bitPosition;

    // Method to decode the Huffman tree from the given input stream
    public HuffmanTree decode(InputStream inputStream) throws IOException {
        // Get the bit sequence representing the Huffman tree
        BitSequence sequence = getHuffmanTreeBitSequence(inputStream);
        
        // Reset bit position for decoding
        bitPosition = 0;
        
        // Decode the sequence into a Huffman tree and return the result
        return new HuffmanTree(decode(sequence));
    }

    // Method to extract the Huffman tree bit sequence from the input stream
    private BitSequence getHuffmanTreeBitSequence(InputStream inputStream) throws IOException {
        // Read the length of the tree in bits (stored as an integer)
        byte[] treeLengthInBitsBuffer = new byte[Integer.BYTES];
        inputStream.read(treeLengthInBitsBuffer);
        
        // Convert the byte array into an integer representing the number of bits in the tree
        int treeLengthInBits = ByteBuffer.wrap(treeLengthInBitsBuffer).getInt();
        
        // Calculate the number of bytes needed to represent the tree's bits
        int treeLengthInBytes = (int) Math.ceil((float) treeLengthInBits / Byte.SIZE);
        
        // Read the actual Huffman tree bits into a byte array
        byte[] tree = new byte[treeLengthInBytes];
        inputStream.read(tree);
        
        // Create a BitSequence object to store the decoded bits
        BitSequence sequence = new BitSequence();
        
        // Iterate through each byte in the tree and add its bits to the BitSequence
        outerLoop:
        for (byte treeByte : tree) {
            for (int i = 0; i < Byte.SIZE; i++) {
                // Add the individual bit to the sequence
                sequence.addBit(isSet(treeByte, i));
                
                // If we've added all the bits for the tree, stop reading
                if (sequence.numBits() == treeLengthInBits) {
                    break outerLoop;
                }
            }
        }
        
        // Return the BitSequence representing the Huffman tree
        return sequence;
    }

    // Recursive method to decode a BitSequence into a Huffman tree structure
    private TreeNode decode(BitSequence sequence) {
        // If the current bit is '1', it represents a leaf node
        if (sequence.isSet(bitPosition)) {
            bitPosition++;  // Move to the next bit
            
            // Extract the next Byte.SIZE bits for the leaf node's byte value
            BitSequence byteBits = new BitSequence(
                    sequence.toString().substring(bitPosition, Math.min(bitPosition + Byte.SIZE, sequence.numBits())));
            bitPosition += byteBits.numBits();
            
            // Create a LeafNode with the decoded byte value and return it
            return new LeafNode(byteBits.toByte());
        } else {
            // If the current bit is '0', it represents an internal node
            bitPosition++;  // Move to the next bit
            
            // Recursively decode the left and right children of the internal node
            TreeNode leftChild = decode(sequence);
            TreeNode rightChild = decode(sequence);
            
            // Return an InternalNode with the decoded left and right children
            return new InternalNode(leftChild, rightChild);
        }
    }

    // Helper method to check if a specific bit is set in a byte value
    private boolean isSet(byte value, int bitPosition) {
        // Perform a bitwise AND with a mask to check if the bit at the specified position is set
        return (value & (1 << bitPosition)) != 0;
    }
}
