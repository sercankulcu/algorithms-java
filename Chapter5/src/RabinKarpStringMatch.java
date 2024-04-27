
public class RabinKarpStringMatch {

	private static final int PRIME = 101; // Prime number used for hashing

	// Search for the pattern in the text using the Rabin-Karp algorithm
	public static int rabinKarpStringMatch(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		int patternHash = calculateHash(pattern, m);
		int textHash = calculateHash(text, m);

		// Iterate through the text with a step size of 1
		for (int i = 0; i <= n - m; i++) {
			// If the hash values match, check character by character
			if (patternHash == textHash && checkEqual(text, pattern, i, i + m - 1)) {
				return i; // Match found, return the starting index
			}
			// Update the hash value for the next substring
			if (i < n - m) {
				textHash = recalculateHash(text, i, i + m, textHash, m);
			}
			System.out.println(patternHash + "\t- " + textHash);
		}
		return -1; // No match found
	}

	// Calculate the hash value for a substring
	private static int calculateHash(String str, int len) {
		int hash = 0;
		for (int i = 0; i < len; i++) {
			hash += str.charAt(i) * Math.pow(PRIME, i);
		}
		return hash;
	}

	// Recalculate the hash value for the next substring
	private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int len) {
		int newHash = oldHash - str.charAt(oldIndex);
		newHash /= PRIME;
		newHash += str.charAt(newIndex) * Math.pow(PRIME, len - 1);
		return newHash;
	}

	// Check if two substrings are equal character by character
	private static boolean checkEqual(String text, String pattern, int start, int end) {
		for (int i = start, j = 0; i <= end; i++, j++) {
			if (text.charAt(i) != pattern.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String text = "abracadabra";
		String pattern = "cad";

		int matchIndex = rabinKarpStringMatch(text, pattern);

		if (matchIndex != -1) {
			System.out.println("Pattern found at index " + matchIndex + " in the text.");
		} else {
			System.out.println("Pattern not found in the text.");
		}
	}
}
