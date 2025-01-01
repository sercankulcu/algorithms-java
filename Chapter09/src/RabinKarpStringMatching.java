import java.util.Random;

public class RabinKarpStringMatching {

    // Prime number for the hash function to reduce collisions
    private static final int PRIME = 101;

    // Function to compute the hash value of a string
    // This function computes the hash by iterating through each character of the string
    private static int computeHash(String str, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash * PRIME + str.charAt(i)) % Integer.MAX_VALUE;  // Rolling hash calculation
        }
        return hash;
    }

    // Function to perform Rabin-Karp string matching and generate the stream
    public static void rabinKarpSearch(String pattern, char timeToBreak) {
        int k = pattern.length();  // Length of the pattern to be matched
        Random random = new Random();  // Random number generator for stream creation
        StringBuilder stream = new StringBuilder();  // StringBuilder to store the generated stream

        // Compute the hash value of the pattern
        int patternHash = computeHash(pattern, k);  // Hash of the pattern to match against
        int windowHash = 0;  // Hash value of the current window in the stream
        
        System.out.println("patternHash " + patternHash);  // Debugging: output the hash of the pattern

        // Start generating the stream and apply the Rabin-Karp algorithm
        int i = 0;  // Index for traversing the stream
        while (true) {
            // Generate a new random character for the stream (A-F)
            char randomChar = (char) ('A' + random.nextInt(6));  // Random letter A-F
            stream.append(randomChar);  // Add the generated character to the stream
            
            // If the length of the stream is shorter than the pattern, keep adding random characters
            while (pattern.length() > stream.length()) {
                randomChar = (char) ('A' + random.nextInt(6));  // Random letter A-F
                stream.append(randomChar);
            }

            // Stop processing if the generated character matches 'timeToBreak' (e.g., 'F')
            if (randomChar == timeToBreak && i > timeToBreak) {
                System.out.println("Stream processing stopped after encountering '" + timeToBreak + "'");
                break;
            }

            // Update the rolling hash for the current window in the stream
            // For the first 'k' characters, build the initial hash
            windowHash = computeHash(stream.substring(i, i + k), k);

            // After filling the first window, check if the pattern matches
            if (patternHash == windowHash && stream.substring(i, i + k).equals(pattern)) {
                System.out.println("Pattern found at index: " + i);  // Output the position where the pattern is found
            }

            i++;  // Move to the next character in the stream
        }

        // Output the entire generated stream once the processing stops
        System.out.println("Generated Stream: " + stream);
    }

    public static void main(String[] args) {
        // Define the pattern to match
        String pattern = "AB";  // This is the pattern we are trying to match in the stream

        // Perform Rabin-Karp search on the generated stream
        char timeToBreak = 'F';  // Stop processing when 'F' is encountered in the stream
        System.out.println("Pattern to match: " + pattern);  // Output the pattern being searched for
        rabinKarpSearch(pattern, timeToBreak);  // Call the search function
    }
}
