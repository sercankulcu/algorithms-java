package match;

import java.util.*;

public class BoyerMooreStringMatch {

	// Preprocess the pattern to compute the bad character shift table
	private static int[] computeBadCharacterShift(String pattern) {
		int m = pattern.length();
		int[] badCharShift = new int[256]; // Assuming ASCII characters

		// Initialize all entries as -1
		Arrays.fill(badCharShift, -1);

		// Fill the table with the last occurrence of each character in the pattern
		for (int i = 0; i < m; i++) {
			badCharShift[pattern.charAt(i)] = i;
		}
		System.out.println(Arrays.toString(badCharShift));
		for (int i = 0; i < 256; i++) {
			if(badCharShift[i] != -1)
				System.out.print(badCharShift[i] + " ");
		}		
		System.out.println();
		return badCharShift;
	}

	// Search for the pattern in the text using the Boyer-Moore algorithm
	public static int boyerMooreStringMatch(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		int[] badCharShift = computeBadCharacterShift(pattern);
		int s = 0;

		// Loop through the text with a step size of pattern length
		while (s <= n - m) {
			int j = m - 1;
			
			System.out.println("text[" + (s + j) + 
					"] != pattern[" + j + "]\t" + text.charAt(s + j) + "?" + pattern.charAt(j));

			// Start matching from the end of the pattern
			while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
				System.out.println("text[" + (s + j) + 
						"] != pattern[" + j + "]\t" + text.charAt(s + j) + "?" + pattern.charAt(j));
				j--;
			}

			if (j < 0) {
				return s; // Match found, return the starting index
			} else {
				// Shift the pattern based on bad character rule and good suffix rule
				s += Math.max(1, j - badCharShift[text.charAt(s + j)]);
				System.out.println("shift " + s + " characters.");
			}
		}
		return -1; // No match found
	}

	public static void main(String[] args) {
		String text = "bbbbabracacdacaacabraca";
		String pattern = "cacdaca";

		int matchIndex = boyerMooreStringMatch(text, pattern);

		if (matchIndex != -1) {
			System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
		} else {
			System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
		}
	}
}
