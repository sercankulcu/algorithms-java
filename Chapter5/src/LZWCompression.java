
import java.util.HashMap;
import java.util.Map;

public class LZWCompression {

	// Lempel-Ziv-Welch (LZW) compression algorithm
	public static String compress(String input) {
		Map<String, Integer> dictionary = new HashMap<>();
		int dictSize = 256; // Start with ASCII characters

		// Initialize the dictionary with ASCII characters
		for (int i = 0; i < 256; i++) {
			dictionary.put("" + (char) i, i);
		}

		StringBuilder compressed = new StringBuilder();
		String current = "";

		// Iterate through the input string
		for (char c : input.toCharArray()) {
			String next = current + c;
			// Check if the combination is in the dictionary
			if (dictionary.containsKey(next)) {
				current = next;
			} else {
				// Append the code for the current combination to the compressed string
				compressed.append(dictionary.get(current)).append(" ");
				// Add the new combination to the dictionary
				dictionary.put(next, dictSize++);
				current = "" + c;
			}
		}

		// Append the code for the last combination to the compressed string
		if (!current.equals("")) {
			compressed.append(dictionary.get(current)).append(" ");
		}

		System.out.println(dictionary.toString());
		return compressed.toString();
	}

	public static void main(String[] args) {
		String input = "aaaabbbcccdaaeeeeeaaffaaaaaaaaa";
		String compressed = compress(input);

		// Print the compressed string
		System.out.println("Original: " + input);
		System.out.println("Compressed: " + compressed);
	}
}
