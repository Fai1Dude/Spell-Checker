import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private static final int ALPHABET_SIZE = 26;
    
    private TrieNode root;
    
    // Constructor to initialize empty dictionary
    public Dictionary() {
        root = new TrieNode();
    }
    
    // Constructor to initialize dictionary with a single string
    public Dictionary(String s) throws Dictionary.WordAlreadyExistsException {
        root = new TrieNode();
        addWord(s);
    }
    
    // Constructor to initialize dictionary from file
    public Dictionary(File f) throws IOException, Dictionary.WordAlreadyExistsException {
        root = new TrieNode();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            addWordDuplicate(line);
        }
        br.close();
    }
    // method for adding word by file to ignore duplicats
    public void addWordDuplicate(String s) throws WordAlreadyExistsException {
        if (findWord(s)) {
        }
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }
    
    // Adds new word to dictionary
    public void addWord(String s) throws WordAlreadyExistsException {
        if (findWord(s)) {
            throw new WordAlreadyExistsException("Exeption: word alredy exists");
        }
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
        System.out.println("Word added successfully");
    }
    
    // Searches for word in dictionary
    public boolean findWord(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node != null && node.isEndOfWord;
    }
    
    // Removes word from dictionary
    public void deleteWord(String s) throws WordNotFoundException {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                throw new WordNotFoundException("Exeption: Word not found");
            }
            node = node.children[index];
        }
        if (node == null || !node.isEndOfWord) {
            throw new WordNotFoundException("Exeption: Word not found");
        }
        node.isEndOfWord = false;
    }
    // Searches for words that differ from given word by exactly 1 letter
    public String[] findSimilar(String s) {
        // Create a linked list to store similar words
        LinkedList similarWords = new LinkedList();
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
        char originalLetter = letters[i];
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != originalLetter) {
            letters[i] = c;
            String similarWord = new String(letters);
            if (findWord(similarWord)) {
                // Add similar word to the linked list
                similarWords.add(similarWord);
            }
            }
        }
            letters[i] = originalLetter;
        }
    // Convert the linked list into an array and return it
    return similarWords.toArray();
    }

    // prints contents of the tree
    public void print(){
        TrieNode node = root;
        char[] str = new char[100];
        int level = 0;

        print(node, str, level);
    }
    // prints contents of the tree
    private void print(Dictionary.TrieNode node,char[] str,int level) {
        if(node.isEndOfWord){
            for (int k = level; k < str.length; k++){
                str[k] = 0;
            }
            System.out.println(str);
        }

        for (int i = 0; i < 26; i++){
            if (node.children[i] != null){
                str[level] = (char) (i + 'a');
                print(node.children[i], str, level + 1);
            }
        }
    }
    // returns contents of tree as a linked list
    public LinkedList getWords(){
        TrieNode node = root;
        char[] str = new char[100];
        int level = 0;
        LinkedList words = new LinkedList();
        getWords(node, str, level,words);
        return words;
    }
    // returns contents of tree as a linked list
    private void getWords(Dictionary.TrieNode node,char[] str,int level,LinkedList words) {
        if(node.isEndOfWord){
            for (int k = level; k < str.length; k++){
                str[k] = ' ';
            }
            words.add(new String(str).strip());
        }

        for (int i = 0; i < 26; i++){
            if (node.children[i] != null){
                str[level] = (char) (i + 'a');
                getWords(node.children[i], str, level + 1,words);
            }
        }
}




    // Trie node class
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
    
        TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
        }
    }
    
    // Exception classes
    public static class WordAlreadyExistsException extends Exception {
        String e;
        public WordAlreadyExistsException(String e){
            this.e = e;
        }
        public String toString(){
            return e;
        }
    }
    
    public static class WordNotFoundException extends Exception {
        String e;
        public WordNotFoundException(String e){
            this.e = e;
        }
        public String toString(){
            return e;
        }
    }
    }