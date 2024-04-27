
public class RunLengthEncoding {

	// Run-Length Encoding (RLE) compression algorithm
	public static String compress(String input) {
		StringBuilder compressed = new StringBuilder();
		int count = 1;

		// Iterate through the input string
		for (int i = 1; i <= input.length(); i++) {
			// Check if the current character is equal to the previous one
			if (i == input.length() || input.charAt(i) != input.charAt(i - 1)) {
				// Append the character and its count to the compressed string
				compressed.append(input.charAt(i - 1));
				compressed.append(count);
				count = 1; // Reset the count for the next character
			} else {
				count++; // Increment the count for consecutive characters
			}
		}

		return compressed.toString();
	}

	public static void main(String[] args) {
		String input = "aaaabbbcccdeeeeeffaaaaaaaaa";
		String compressed = compress(input);

		// Print the compressed string
		System.out.println("Original: " + input);
		System.out.println("Compressed: " + compressed);
	}
}
