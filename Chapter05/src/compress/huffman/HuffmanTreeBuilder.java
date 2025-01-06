package compress.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

// HuffmanTreeBuilder class is responsible for building a Huffman tree from an input stream
public class HuffmanTreeBuilder {
    // Constant representing the number of unique byte values (0 to 255)
    private static final int NUM_UNIQUE_BYTES = 256;

    // Holds the entropy of the data, calculated during tree construction
    private Float entropy;

    // Getter for the calculated entropy value
    public float getEntropy() {
        // If entropy has not been calculated, throw an exception
        if (entropy == null) {
            throw new IllegalStateException("Entropy has not been calculated yet");
        }
        return entropy;
    }

    // Main method to build the Huffman tree from the provided input stream
    public HuffmanTree buildTree(InputStream inputStream) throws IOException {
        // Build the initial leaf nodes from the byte frequencies in the input stream
        PriorityQueue<TreeNode> treeNodes = buildLeafNodes(inputStream);
        
        // Calculate the entropy of the data based on the frequency of bytes
        entropy = calculateEntropy(treeNodes);
        
        // Build the Huffman tree from the leaf nodes
        HuffmanTree tree = buildHuffmanTree(treeNodes);
        
        // If the tree's root is a leaf node, we add a dummy right child to make it internal
        if (tree.getRoot() instanceof LeafNode leafNode) {
            TreeNode rightChild = new LeafNode((byte) 0);  // Add a dummy leaf node with value 0
            TreeNode newRoot = new InternalNode(leafNode, rightChild);  // Create a new internal node as the root
            tree = new HuffmanTree(newRoot);  // Rebuild the tree with the new root
        }
        
        return tree;
    }

    // Method to build leaf nodes based on the frequencies of bytes in the input stream
    private PriorityQueue<TreeNode> buildLeafNodes(InputStream inputStream) throws IOException {
        // Array to hold leaf nodes for each unique byte (0-255)
        final LeafNode[] byteCount = new LeafNode[NUM_UNIQUE_BYTES];
        
        // Read the input stream byte by byte and count the frequency of each byte
        while (inputStream.available() > 0) {
            byte byteVal = (byte) inputStream.read();
            int unsignedByteVal = byteVal & 0xFF;  // Convert byte to unsigned value (0-255)
            
            // If this byte hasn't been encountered before, create a new leaf node
            if (byteCount[unsignedByteVal] == null) {
                byteCount[unsignedByteVal] = new LeafNode(byteVal);
            }
            // Increment the frequency for this byte
            byteCount[unsignedByteVal].increaseFrequency();
        }
        
        // Filter out null entries and convert the leaf nodes to a PriorityQueue for building the tree
        return Arrays.stream(byteCount)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    // Method to calculate the entropy of the data based on byte frequencies
    private float calculateEntropy(Collection<TreeNode> leafNodes) {
        // Calculate the total frequency of all bytes
        int frequenciesSum = leafNodes.stream().map(TreeNode::getFrequency).mapToInt(f -> f).sum();
        
        // Function to calculate log base 2
        Function<Float, Double> log2 = val -> Math.log(val) / Math.log(2);
        
        // Calculate the entropy using the formula:
        // Entropy = - Σ p(x) * log2(p(x)), where p(x) is the probability of byte x
        return (float) leafNodes
                .stream()
                .map(TreeNode::getFrequency)  // Get the frequency of each byte
                .map(frequency -> (float) frequency / frequenciesSum)  // Calculate the probability p(x)
                .map(percentage -> percentage * log2.apply((float) 1 / percentage))  // p(x) * log2(1/p(x))
                .mapToDouble(f -> f)  // Convert to double for summation
                .sum();
    }

    // Method to build the actual Huffman tree from the leaf nodes using a priority queue
    private HuffmanTree buildHuffmanTree(PriorityQueue<TreeNode> treeNodes) {
        // While there is more than one node in the queue, combine the two nodes with the lowest frequencies
        while (treeNodes.size() > 1) {
            // Poll the two nodes with the smallest frequencies
            TreeNode leftChild = treeNodes.poll();
            TreeNode rightChild = treeNodes.poll();
            
            // Create an internal node combining the two smallest nodes
            InternalNode internalNode = new InternalNode(leftChild, rightChild);
            
            // Add the new internal node back into the priority queue
            treeNodes.add(internalNode);
        }
        
        // The remaining node in the queue is the root of the Huffman tree
        return new HuffmanTree(treeNodes.peek());
    }
}
