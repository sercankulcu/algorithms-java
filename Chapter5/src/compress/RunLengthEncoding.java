package compress;
public class RunLengthEncoding {

	// Method to perform RLE compression
	public static String compress(String input) {
		StringBuilder compressed = new StringBuilder(); // Initialize a StringBuilder to store the compressed output

		// Iterate through each character in the input string
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i); // Get the current character
			int count = 1; // Initialize the count of consecutive characters

			// Continue counting consecutive characters if there are more characters of the same type
			while (i + 1 < input.length() && input.charAt(i + 1) == currentChar) {
				count++;
				i++;
			}

			// Append the count and character to the compressed string
			compressed.append(count);
			compressed.append(currentChar);
		}

		// Return the compressed string
		return compressed.toString();
	}

	// Method to perform RLE decompression
	public static String decompress(String input) {
		StringBuilder decompressed = new StringBuilder(); // Initialize a StringBuilder to store the decompressed output

		// Iterate through each pair of count and character in the input string
		for (int i = 0; i < input.length(); i += 2) {
			int count = Character.getNumericValue(input.charAt(i)); // Get the count of consecutive characters
			char currentChar = input.charAt(i + 1); // Get the character

			// Append the character count times to the decompressed string
			for (int j = 0; j < count; j++) {
				decompressed.append(currentChar);
			}
		}

		// Return the decompressed string
		return decompressed.toString();
	}

	public static void main(String[] args) {
		// Original string to compress and decompress
		String original = "aaaaaaaaabbbbbbececececececdddddddddecb";
		System.out.println("Original: \t" + original);

		// Compress the original string using RLE
		String compressed = compress(original);
		System.out.println("Compressed: \t" + compressed);

		// Decompress the compressed string using RLE
		String decompressed = decompress(compressed);
		System.out.println("Decompressed: \t" + decompressed);
	}
}
