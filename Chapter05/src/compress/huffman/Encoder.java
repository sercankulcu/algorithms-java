package compress.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

// Encoder class handles the process of encoding data using Huffman coding
public class Encoder {
    // The builder for creating the Huffman tree
    private final HuffmanTreeBuilder huffmanTreeBuilder;
    
    // The builder for creating the bit encoding map
    private final BitEncodingMapBuilder bitEncodingMapBuilder;
    
    // The encoder for encoding the Huffman tree
    private final HuffmanTreeEncoder huffmanTreeEncoder;

    // The Huffman tree used for encoding
    private HuffmanTree huffmanTree;
    
    // The map that stores the bit encoding for each byte
    private Map<Byte, BitSequence> bitEncodingMap;

    // Constructor that initializes the encoder with the required components
    public Encoder(HuffmanTreeBuilder huffmanTreeBuilder, BitEncodingMapBuilder bitEncodingMapBuilder,
                   HuffmanTreeEncoder huffmanTreeEncoder) {
        this.huffmanTreeBuilder = huffmanTreeBuilder;
        this.bitEncodingMapBuilder = bitEncodingMapBuilder;
        this.huffmanTreeEncoder = huffmanTreeEncoder;
    }

    // Getter method to retrieve the Huffman tree, throws an exception if it's not built yet
    public HuffmanTree getHuffmanTree() {
        if (huffmanTree == null) {
            throw new IllegalStateException("Huffman tree has not been built yet");
        }
        return huffmanTree;
    }

    // Getter method to retrieve the entropy of the Huffman tree
    public float getEntropy() {
        return huffmanTreeBuilder.getEntropy();
    }

    // Getter method to retrieve the bit encoding map, throws an exception if it's not built yet
    public Map<Byte, BitSequence> getBitEncodingMap() {
        if (huffmanTree == null) {
            throw new IllegalStateException("Bit encoding map has not been build yet");
        }
        return bitEncodingMap;
    }

    // Main encoding method that compresses the input data and writes the encoded data to the output stream
    public void encode(InputStream inputStream1, InputStream inputStream2,
                       OutputStream outputStream) throws IOException {
        try (outputStream) {
            // Step 1: Build the Huffman tree based on the input data
            huffmanTree = huffmanTreeBuilder.buildTree(inputStream1);
            
            // Step 2: Encode the Huffman tree and write it to the output stream
            huffmanTreeEncoder.encode(huffmanTree, outputStream);
            
            // Step 3: Write the number of symbols (unique bytes) to the output stream
            writeNumSymbols(huffmanTree.getRoot().getFrequency(), outputStream);
            
            // Step 4: Build the bit encoding map for each byte
            bitEncodingMap = bitEncodingMapBuilder.buildEncodingMap(huffmanTree);
            
            // Step 5: Write the encoded bit sequence of the input file to the output stream
            writeBitSequence(inputStream2, outputStream);
        }
    }

    // Writes the number of unique symbols (bytes) to the output stream
    private void writeNumSymbols(int numSymbols, OutputStream outputStream) throws IOException {
        byte[] numSymbolsBuffer = ByteBuffer.allocate(Integer.BYTES).putInt(numSymbols).array();
        outputStream.write(numSymbolsBuffer);
    }

    // Writes the encoded bit sequence for each byte of the input file to the output stream
    private void writeBitSequence(InputStream inputStream, OutputStream outputStream) throws IOException {
        BitSequence buffer = new BitSequence();
        // Step through each byte in the input stream and encode it using the bit encoding map
        while (inputStream.available() > 0) {
            byte byteVal = (byte) inputStream.read();
            // Get the bit sequence for the current byte
            BitSequence bitSequence = bitEncodingMap.get(byteVal);
            // Write the bit sequence to the output stream
            for (int i = 0; i < bitSequence.numBits(); i++) {
                buffer.addBit(bitSequence.isSet(i));
                if (buffer.numBits() == Byte.SIZE) {
                    outputStream.write(buffer.toByte());
                    buffer = new BitSequence(); // Reset the buffer
                }
            }
        }
        // If there are leftover bits, write them to the output stream
        if (buffer.numBits() > 0) {
            outputStream.write(buffer.toByte());
        }
    }
}
