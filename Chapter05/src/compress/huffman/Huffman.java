package compress.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

// Huffman class handles file compression and decompression using Huffman encoding
public class Huffman {
    
    // Compresses the given input file using the provided encoder and writes the output to a file
    public void compressFile(Encoder encoder, File inputFile, File outputFile) throws IOException {
        // Creating buffered input streams for reading the input file
        InputStream inputStream1 = new BufferedInputStream(new FileInputStream(inputFile)),
                inputStream2 = new BufferedInputStream(new FileInputStream(inputFile));
        
        // Creating a buffered output stream for writing to the output file
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        
        // Calling the encode method from the encoder to compress the file
        encoder.encode(inputStream1, inputStream2, outputStream);
    }

    // Decompresses the given input file using the provided decoder and writes the output to a file
    public void decompressFile(Decoder decoder, File inputFile, File outputFile) throws IOException {
        // Creating buffered input and output streams for the file operations
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        
        // Calling the decode method from the decoder to decompress the file
        decoder.decode(inputStream, outputStream);
    }
    
    // Main method to run the program and handle user input for encoding or decoding files
    public static void main(String[] args) throws IOException {
        
        // Instantiate the necessary components for encoding and decoding
        Encoder encoder = new Encoder(new HuffmanTreeBuilder(), new BitEncodingMapBuilder(), new HuffmanTreeEncoder());
        Decoder decoder = new Decoder(new HuffmanTreeDecoder());
        Huffman huffman = new Huffman();
        
        // Using a scanner to read user input for encoding or decoding
        try (Scanner scanner = new Scanner(System.in)) {
            // Reading the user's choice (encode or decode)
            String process = scanner.next();
            
            // If the user wants to encode, call compressFile to encode the input file
            if (process.equals("encode"))
                huffman.compressFile(encoder, new File("src/compress/huffman/input.txt"), new File("src/compress/huffman/output.txt"));
            
            // If the user wants to decode, call decompressFile to decode the output file
            if (process.equals("decode"))
                huffman.decompressFile(decoder, new File("src/compress/huffman/output.txt"), new File("src/compress/huffman/copy.txt"));
        }
    }
}
