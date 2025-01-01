import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

// Class to represent a node in the Huffman Tree
class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left, right;

    // Constructor to create a new node
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        left = right = null;
    }
}

// Comparator for building the priority queue (min-heap)
class HuffmanComparator implements java.util.Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.frequency - node2.frequency;
    }
}

public class HuffmanCoding {

    // Method to build the Huffman Tree
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        // Create a priority queue (min-heap) to store nodes of the tree
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new HuffmanComparator());

        // Add all characters and their frequencies as nodes to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree
        while (queue.size() > 1) {
            // Remove the two nodes with the lowest frequencies
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            // Create a new internal node with these two nodes as children
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            // Add the merged node back to the priority queue
            queue.add(merged);
        }

        // The remaining node is the root of the Huffman Tree
        return queue.poll();
    }

    // Method to generate the Huffman codes from the tree
    public static void generateHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        // Base case: if we reach a leaf node, add the code for the character
        if (root == null) {
            return;
        }

        // If the node is a leaf (contains a character), add the code to the map
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        // Recur for the left and right subtrees, append '0' and '1' to the code respectively
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    // Method to print the Huffman Codes
    public static void printHuffmanCodes(Map<Character, String> huffmanCodes) {
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method to perform Huffman coding and return the result
    public static HuffmanNode huffmanCoding(String text) {
        // Step 1: Calculate the frequency of each character in the input text
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        return buildHuffmanTree(frequencyMap);
    }

    // Method to decode the encoded text using the Huffman Tree
    public static String decodeHuffmanCodes(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode currentNode = root;

        // Traverse the encoded string and follow the tree paths to decode
        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            // If a leaf node is reached, add the character to the decoded text
            if (currentNode.left == null && currentNode.right == null) {
                decodedText.append(currentNode.character);
                currentNode = root; // Reset to root for the next character
            }
        }

        return decodedText.toString();
    }

    public static void main(String[] args) {
        // Example text to encode and decode
        String text = "this is an example for huffman encoding";

        // Perform Huffman coding on the example text
        // Step 2: Build the Huffman Tree
        HuffmanNode root = huffmanCoding(text);

        // Step 3: Generate the Huffman codes from the tree
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        // Step 4: Print the Huffman codes
        System.out.println("Huffman Codes for the given text:");
        printHuffmanCodes(huffmanCodes);

        // Step 5: Encode the text using Huffman codes
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        // Print the encoded text
        System.out.println("\nEncoded Text: ");
        System.out.println(encodedText);

        // Step 6: Decode the encoded text
        String decodedText = decodeHuffmanCodes(encodedText.toString(), root);
        System.out.println("\nDecoded Text: ");
        System.out.println(decodedText);
    }
}
