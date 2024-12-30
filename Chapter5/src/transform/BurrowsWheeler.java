package transform;

import java.util.Arrays;

public class BurrowsWheeler {

    /**
     * Function to compute the Burrows-Wheeler Transform (BWT) of a given string.
     * @param text The input string for which the BWT is to be computed.
     * @return The Burrows-Wheeler Transform (BWT) string.
     */
    public static String burrowsWheelerTransform(String text) {
        int n = text.length(); // Length of the input string.
        String[] rotations = new String[n];

        // Step 1: Generate all cyclic rotations of the input string.
        for (int i = 0; i < n; i++) {
            rotations[i] = text.substring(i) + text.substring(0, i); 
            // For index i, the rotation starts from index i and wraps around.
        }

        // Debug: Print the generated cyclic rotations.
        System.out.println("Cyclic Rotations:");
        System.out.println(Arrays.toString(rotations));

        // Step 2: Sort the cyclic rotations lexicographically.
        Arrays.sort(rotations);

        // Debug: Print the sorted rotations.
        System.out.println("Sorted Rotations:");
        System.out.println(Arrays.toString(rotations));

        // Step 3: Extract the last character of each sorted rotation to form the BWT string.
        StringBuilder bwt = new StringBuilder();
        for (String rotation : rotations) {
            bwt.append(rotation.charAt(n - 1)); // Append the last character of the rotation.
        }

        return bwt.toString(); // Return the final BWT string.
    }

    public static void main(String[] args) {
        // Test case 1: Compute and print the BWT of the string "banana".
        System.out.println("Burrows-Wheeler Transform (BWT) of 'banana': " 
                           + burrowsWheelerTransform("banana"));
        System.out.println();
        // Test case 2: Compute and print the BWT of the string "sercan".
        System.out.println("Burrows-Wheeler Transform (BWT) of 'sercan': " 
                           + burrowsWheelerTransform("sercan"));
    }
}
