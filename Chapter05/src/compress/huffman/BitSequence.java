package compress.huffman;

import java.util.Objects;

// The BitSequence class represents a sequence of bits and provides methods for manipulating and converting the bits
public class BitSequence {
    // Constants for representing '0' and '1' bits as characters
    private static final char FALSE_BIT = '0', TRUE_BIT = '1';

    // StringBuilder used to hold the sequence of bits
    private final StringBuilder sequence;

    // Default constructor initializes an empty bit sequence
    public BitSequence() {
        sequence = new StringBuilder();
    }

    // Constructor that initializes the bit sequence with an existing string of bits
    public BitSequence(String sequence) {
        this.sequence = new StringBuilder(sequence);
    }

    // Returns the number of bits in the sequence
    public int numBits() {
        return sequence.length();
    }

    // Checks if the bit at the given position is set (i.e., equals '1')
    public boolean isSet(int position) {
        return sequence.charAt(position) == TRUE_BIT;
    }

    // Appends a bit ('1' or '0') to the sequence
    public void addBit(boolean isSet) {
        this.sequence.append(isSet ? TRUE_BIT : FALSE_BIT);
    }

    // Converts the bit sequence into a byte array, padding if necessary
    public byte[] toByteArray() {
        // Calculate the required number of bytes to represent the bits
        int numBytes = (int) Math.ceil((float) numBits() / Byte.SIZE);
        byte[] bytes = new byte[numBytes];
        int allocatedBytes = 0;
        BitSequence currSequence = new BitSequence();

        // Iterate over each bit and group them into bytes
        for (int i = 0; i < numBits(); i++) {
            currSequence.addBit(isSet(i));
            if (currSequence.numBits() == Byte.SIZE) {
                bytes[allocatedBytes++] = currSequence.toByte();
                currSequence = new BitSequence();
            }
        }

        // If there are remaining bits that didn't complete a byte, write them as the last byte
        if (currSequence.numBits() != 0) {
            bytes[allocatedBytes] = currSequence.toByte();
        }
        return bytes;
    }

    // Converts the bit sequence into a byte (assumes the sequence is no larger than 8 bits)
    public byte toByte() {
        // Ensure that the bit sequence has at most 8 bits
        if (numBits() > Byte.SIZE) {
            throw new RuntimeException(
                    "Bit sequence cannot be larger than " + Byte.SIZE + " bits to be converted into a byte");
        }

        // Ensure the bit sequence is exactly 8 bits long by appending '0's if necessary
        StringBuilder auxiliar = new StringBuilder(toString());
        while (auxiliar.length() < Byte.SIZE) {
            auxiliar.append(FALSE_BIT);
        }
        
        // Convert the bit sequence string to a byte
        return Integer.valueOf(auxiliar.reverse().toString(), 2).byteValue();
    }

    // Override equals method to compare BitSequence objects based on their bit strings
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSequence that = (BitSequence) o;
        return sequence.toString().equals(that.sequence.toString());
    }

    // Override hashCode to generate hash based on the bit sequence string
    @Override
    public int hashCode() {
        return Objects.hash(sequence.toString());
    }

    // Override toString to return the string representation of the bit sequence
    @Override
    public String toString() {
        return sequence.toString();
    }
}
