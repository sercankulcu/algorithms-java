package match;

public class RabinKarp {

    // A prime number used for the hash calculation to reduce hash collisions
    private static final int PRIME = 101;

    // Rabin-Karp String Matching Algorithm
    // This function searches for the 'pattern' in the 'text' and returns the starting index of the first match, or -1 if no match is found
    public static int rabinKarpStringMatch(String text, String pattern) {
        int n = text.length();   // Length of the text
        int m = pattern.length(); // Length of the pattern

        // Calculate the hash of the pattern and the first substring of text with the same length as the pattern
        int patternHash = calculateHash(pattern, m);
        int textHash = calculateHash(text, m);

        // Iterate through the text, sliding a window of size m
        for (int i = 0; i <= n - m; i++) {
            // If the hash values match, perform a character-by-character check to avoid false positives
            if (patternHash == textHash && checkEqual(text, pattern, i, i + m - 1)) {
                System.out.println(pattern + ":" + patternHash + " - " + text.subSequence(i, i + m) + ":" + textHash);
                return i; // Match found, return the starting index of the match
            }
            // Update the hash value for the next substring by removing the first character of the previous window and adding the next character
            if (i < n - m) {
                System.out.println(pattern + ":" + patternHash + " - " + text.subSequence(i, i + m) + ":" + textHash);
                textHash = recalculateHash(text, i, i + m, textHash, m);
            }
        }

        // Return -1 if no match is found after checking all possible substrings
        return -1;
    }

    // Function to calculate the hash value of a substring of a given length
    private static int calculateHash(String str, int len) {
        int hash = 0;
        // Calculate hash by considering the position of each character
        for (int i = 0; i < len; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i); // Hash formula: sum of (character value * prime^position)
        }
        return hash;
    }

    // Function to recalculate the hash value when the window slides to the next substring
    private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int len) {
        // Remove the contribution of the old character and add the new character's contribution to the hash
        int newHash = oldHash - str.charAt(oldIndex); 
        newHash /= PRIME;  // Shift hash value by dividing by prime
        newHash += str.charAt(newIndex) * Math.pow(PRIME, len - 1); // Add the new character's contribution
        return newHash;
    }

    // Function to check if two substrings are equal character by character
    private static boolean checkEqual(String text, String pattern, int start, int end) {
        for (int i = start, j = 0; i <= end; i++, j++) {
            if (text.charAt(i) != pattern.charAt(j)) {
                return false; // Return false if characters don't match
            }
        }
        return true; // Return true if all characters match
    }

    public static void main(String[] args) {
        // Sample input text and pattern to search
        String text = "acaabracadabra";
        String pattern = "cad";

        // Perform the Rabin-Karp string matching
        int matchIndex = rabinKarpStringMatch(text, pattern);

        // Output the result
        if (matchIndex != -1) {
            System.out.println("\"" + pattern + "\" found at index " + matchIndex + " in the \"" + text + "\"");
        } else {
            System.out.println("\"" + pattern + "\" not found in the \"" + text + "\"");
        }
    }
}
