package match;
import java.util.Arrays;

public class KMPStringMatch {

	// Preprocess the pattern to compute the prefix function
	private static int[] computePrefixFunction(String pattern) {
		int m = pattern.length();
		int[] pi = new int[m];
		int k = 0;

		// Compute the prefix function for each character in the pattern
		for (int q = 1; q < m; q++) {
			while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
				k = pi[k - 1];
			}
			if (pattern.charAt(k) == pattern.charAt(q)) {
				k++;
			}
			pi[q] = k;
		}
		System.out.println(Arrays.toString(pi));
		return pi;
	}

	// Search for the pattern in the text using the KMP algorithm
	public static int kmpStringMatch(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		int[] pi = computePrefixFunction(pattern);
		int q = 0;

		// Iterate through the characters of the text
		for (int i = 0; i < n; i++) {
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
				q = pi[q - 1];
			}
			if (pattern.charAt(q) == text.charAt(i)) {
				q++;
			}
			if (q == m) {
				return i - m + 1; // Match found, return the starting index
			}
		}
		return -1; // No match found
	}

	public static void main(String[] args) {
		String text = "abracacdacabra";
		String pattern = "cacdaca";

		int matchIndex = kmpStringMatch(text, pattern);

		if (matchIndex != -1) {
			System.out.println("Pattern found at index " + matchIndex + " in the text.");
		} else {
			System.out.println("Pattern not found in the text.");
		}
	}
}
