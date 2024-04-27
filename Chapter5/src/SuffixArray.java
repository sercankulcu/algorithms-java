
import java.util.Arrays;

public class SuffixArray {

	// Suffix Array construction algorithm
	public static int[] buildSuffixArray(String text) {
		int n = text.length();
		Suffix[] suffixes = new Suffix[n];

		// Generate all suffixes of the text and store them in an array
		for (int i = 0; i < n; i++) {
			suffixes[i] = new Suffix(i, text.substring(i));
		}

		// Sort the suffixes lexicographically using Java's Arrays.sort() method
		Arrays.sort(suffixes);

		// Create the suffix array from the sorted suffixes
		int[] suffixArray = new int[n];
		for (int i = 0; i < n; i++) {
			suffixArray[i] = suffixes[i].index;
		}

		System.out.println(Arrays.toString(suffixes));
		return suffixArray;
	}

	// Suffix class to represent a suffix with its index and string value
	static class Suffix implements Comparable<Suffix> {
		int index;
		String suffixValue;

		Suffix(int index, String suffixValue) {
			this.index = index;
			this.suffixValue = suffixValue;
		}

		// Implement compareTo method to compare suffixes lexicographically
		@Override
		public int compareTo(Suffix other) {
			return this.suffixValue.compareTo(other.suffixValue);
		}
		
		public String toString() {
			return index + "-" + suffixValue;
		}
	}

	public static void main(String[] args) {
		String text = "alabama";

		// Build the suffix array for the input text
		int[] suffixArray = buildSuffixArray(text);

		// Print the suffix array
		System.out.println("Suffix Array for the text \"" + text + "\":");
		System.out.println(Arrays.toString(suffixArray));
	}
}
