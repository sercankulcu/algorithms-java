package match;

public class BruteForceStringMatch {

	public static int bruteForceStringMatch(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		int count = 0;

		// Loop through each character in the text
		for (int i = 0; i <= n - m; i++) {
			int j;

			// Check if the current substring of text matches the pattern
			for (j = 0; j < m; j++) {
				System.out.println("text[" + (i + j) + 
						"] != pattern[" + j + "]\t" + text.charAt(i + j) + "?" + pattern.charAt(j));
				count++;
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break; // If a character doesn't match, break the loop
				}
			}

			// If the inner loop completed without breaking, it means a match was found
			if (j == m) {
				System.out.println(count);
				return i; // Return the starting index of the match
			}
		}
		System.out.println(count);
		return -1; // Return -1 if no match is found
	}

	public static void main(String[] args) {
		String text = "acaddbracaddeabra";
		String pattern = "cadde";
		
		text = "abracacadabra";
		pattern = "cad";

		int matchIndex = bruteForceStringMatch(text, pattern);

		if (matchIndex != -1) {
			System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
		} else {
			System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
		}
	}
}
