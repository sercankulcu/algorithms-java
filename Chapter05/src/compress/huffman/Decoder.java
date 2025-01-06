package compress.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

// Decoder class handles the process of decoding the Huffman encoded data
public class Decoder {
    // The decoder for decoding the Huffman tree
    private final HuffmanTreeDecoder huffmanTreeDecoder;

    // The Huffman tree used for decoding
    private HuffmanTree huffmanTree;

    // Constructor to initialize the decoder with the HuffmanTreeDecoder
    public Decoder(HuffmanTreeDecoder huffmanTreeDecoder) {
        this.huffmanTreeDecoder = huffmanTreeDecoder;
    }

    // Getter method to retrieve the Huffman tree, throws an exception if it's not decoded yet
    public HuffmanTree getHuffmanTree() {
        if (huffmanTree == null) {
            throw new IllegalStateException("Huffman tree has not been decoded yet");
        }
        return huffmanTree;
    }

    // Main decoding method that decodes the input data and writes the decoded data to the output stream
    public void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Step 1: Decode the Huffman tree from the input stream
        huffmanTree = huffmanTreeDecoder.decode(inputStream);
        
        // Step 2: Get the root node of the Huffman tree and the number of symbols
        TreeNode node = huffmanTree.getRoot();
        int numSymbols = readNumSymbols(inputStream), numReadSymbols = 0;
        
        // Step 3: Process the bits from the input stream and navigate through the Huffman tree to decode
        outerLoop:
        while (inputStream.available() > 0) {
            byte byteVal = (byte) inputStream.read();
            // Step through each bit in the byte
            for (int i = 0; i < Byte.SIZE; i++) {
                // If a leaf node is reached, write the decoded byte to the output stream
                if (node instanceof LeafNode leafNode) {
                    outputStream.write(leafNode.getValue());
                    node = huffmanTree.getRoot();
                    // Stop if the number of decoded symbols matches the total symbols
                    if (++numReadSymbols == numSymbols) {
                        break outerLoop;
                    }
                }
                // Traverse to the left or right child based on the bit value (0 or 1)
                node = isSet(byteVal, i) ? node.getRightChild() : node.getLeftChild();
            }
        }

        // If the loop ends and a leaf node is reached, write the final decoded byte
        if (node instanceof LeafNode leafNode) {
            outputStream.write(leafNode.getValue());
        }
        
        // Close the output stream after writing all decoded data
        outputStream.close();
    }

    // Reads the number of unique symbols (bytes) from the input stream
    private int readNumSymbols(InputStream inputStream) throws IOException {
        byte[] numSymbolsBuffer = new byte[Integer.BYTES];
        inputStream.read(numSymbolsBuffer);
        return ByteBuffer.wrap(numSymbolsBuffer).getInt();
    }

    // Checks if a specific bit in a byte is set (1)
    private boolean isSet(byte value, int bitPosition) {
        return (value & (1 << bitPosition)) != 0;
    }
}
