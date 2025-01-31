
// Trie Node class
class TrieNode {
    TrieNode[] children; // Array to store child nodes
    boolean isEndOfWord; // Flag to indicate end of a word
    
    // Constructor
    public TrieNode() {
        children = new TrieNode[26]; // Supports only lowercase English letters (a-z)
        isEndOfWord = false;
    }
}

// Trie class
public class Trie {
	
    private TrieNode root;
    
    // Constructor
    public Trie() {
        root = new TrieNode();
    }
    
    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a'; // Calculate index (0-25)
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(); // Create new node if not present
            }
            node = node.children[index]; // Move to the next node
        }
        node.isEndOfWord = true; // Mark the end of the word
    }
    
    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false; // Word not found
            }
            node = node.children[index];
        }
        return node.isEndOfWord; // Return true if end of word is reached
    }
    
    // Check if a prefix exists in the Trie
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false; // Prefix not found
            }
            node = node.children[index];
        }
        return true; // Prefix exists
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        
        // Insert words into the Trie
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");
        
        // Search words in the Trie
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app")); // true
        System.out.println("Search 'appl': " + trie.search("appl")); // false
        System.out.println("Search 'banana': " + trie.search("banana")); // true
        System.out.println("Search 'ban': " + trie.search("ban")); // false
        
        // Check for prefix
        System.out.println("Prefix 'app': " + trie.startsWith("app")); // true
        System.out.println("Prefix 'ban': " + trie.startsWith("ban")); // true
        System.out.println("Prefix 'bat': " + trie.startsWith("bat")); // false
    }
}
