package compress;

import java.util.*;

public class LZWCompression {

	// Method to compress a string
	public static List<Integer> compress(String uncompressed) {
		// Create a dictionary to store substrings and their corresponding codes
		Map<String, Integer> dictionary = new HashMap<>();

		// Initialize the dictionary with single characters
		for (int i = 0; i < 256; i++) {
			dictionary.put("" + (char)i, i);
		}

		// Initialize variables
		String current = "";  // Current substring being processed
		List<Integer> result = new ArrayList<>();  // Resulting list of compressed codes

		// Iterate through each character in the input string
		for (char c : uncompressed.toCharArray()) {
			String temp = current + c;  // Append current character to current substring

			// Check if the current substring is in the dictionary
			if (dictionary.containsKey(temp)) {
				current = temp;  // Update current substring
			} else {
				// Add code for current substring to the result
				result.add(dictionary.get(current));

				// Add the new substring to the dictionary with the next available code
				dictionary.put(temp, dictionary.size());
				System.out.print(temp + " ");

				// Reset current substring to the current character
				current = "" + c;
			}
		}
		System.out.println();

		// Add the code for the last substring to the result
		if (!current.equals("")) {
			result.add(dictionary.get(current));
		}

		// Return the list of compressed codes
		return result;
	}

	// Method to decompress a list of codes
	public static String decompress(List<Integer> compressed) {
		// Create a dictionary to store codes and their corresponding substrings
		Map<Integer, String> dictionary = new HashMap<>();

		// Initialize the dictionary with single characters
		for (int i = 0; i < 256; i++) {
			dictionary.put(i, "" + (char)i);
		}

		// Initialize variables
		StringBuilder result = new StringBuilder();  // Resulting decompressed string
		String previous = "" + (char)(int)compressed.remove(0);  // Previous substring

		// Append the first character to the result
		result.append(previous);

		// Iterate through each code in the compressed list
		for (int k : compressed) {
			String current;  // Current substring

			// Check if the code is in the dictionary
			if (dictionary.containsKey(k)) {
				current = dictionary.get(k);  // Get the corresponding substring
			} else if (k == dictionary.size()) {
				// Special case for when the code represents a substring that's not in the dictionary
				current = previous + previous.charAt(0);
			} else {
				throw new IllegalArgumentException("Bad compressed k: " + k);
			}

			// Append the current substring to the result
			result.append(current);

			// Add the new substring to the dictionary with the next available code
			dictionary.put(dictionary.size(), previous + current.charAt(0));

			// Update the previous substring
			previous = current;
		}

		// Return the decompressed string
		return result.toString();
	}

	public static void main(String[] args) {
		// Original string to compress and decompress
		String original = "ababbabcababba";
		System.out.println("Original: " + original);

		// Compress the original string
		List<Integer> compressed = compress(original);
		System.out.println("Compressed: " + compressed);

		// Decompress the compressed list of codes
		String decompressed = decompress(compressed);
		System.out.println("Decompressed: " + decompressed);
	}
}
