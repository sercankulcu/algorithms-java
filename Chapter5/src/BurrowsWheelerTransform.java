
import java.util.Arrays;

public class BurrowsWheelerTransform {

	// Burrows-Wheeler Transform (BWT) algorithm
	public static String burrowsWheelerTransform(String text) {
		int n = text.length();
		String[] rotations = new String[n];

		// Generate all cyclic rotations of the text and store them in an array
		for (int i = 0; i < n; i++) {
			rotations[i] = text.substring(i) + text.substring(0, i);
		}
		
		System.out.println(Arrays.toString(rotations));

		// Sort the rotations lexicographically
		Arrays.sort(rotations);
		System.out.println(Arrays.toString(rotations));

		// Extract the last characters of each rotation to form the BWT string
		StringBuilder bwt = new StringBuilder();
		for (String rotation : rotations) {
			bwt.append(rotation.charAt(n - 1));
		}

		return bwt.toString();
	}

	public static void main(String[] args) {
		
		// Print the Burrows-Wheeler Transform (BWT) string
		System.out.println("Burrows-Wheeler Transform (BWT) " + burrowsWheelerTransform("banana"));
		System.out.println("Burrows-Wheeler Transform (BWT) " + burrowsWheelerTransform("sercan"));
	}
}
