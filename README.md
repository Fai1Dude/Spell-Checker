# Spell Checker - Dictinary to correct spelling if wrong 🔤

A compact Java console application that loads a word list into a **Trie** and lets you **query, add, delete, search “one-letter-off” words, and optionally save** the updated dictionary back to disk.  
Perfect for data-structure labs or quick spelling-helper demos.


---

## ✨ Key features

| Action (CLI prompt) | What happens | Implementation |
|---------------------|--------------|----------------|
| **check word**      | Reports whether the word exists | `findWord()` walks the trie from root to leaf. :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3} |
| **add new word**    | Inserts while blocking duplicates | `addWord()` throws `WordAlreadyExistsException` if present. |
| **remove word**     | Unmarks the word’s terminal node | `deleteWord()` raises `WordNotFoundException` if absent. |
| **search for similar words** | Returns all words that differ by *exactly one letter* | Brute-forces 26×k mutations, stores hits in a custom linked list. :contentReference[oaicite:4]{index=4} |
| **Save Updated Dictionary** | Serialises the current trie to a text file, one word per line | `getWords()` (DFS) → `saveDictionary()` in `Main.java`. |

---

## 🏗️ Project structure

.
├─ Dictionary.java # Trie + API + custom exceptions
├─ LinkedList.java # Tiny singly linked list used by findSimilar
├─ Node.java # Node wrapper for LinkedList
├─ Main.java # Interactive driver (stdin / stdout)
└─ efficiency.txt # Big-O cheat-sheet for each method


*The trie node is an inner `TrieNode` class with a 26-length child array
and a boolean `isEndOfWord` flag.* :contentReference[oaicite:5]{index=5}:contentReference[oaicite:6]{index=6}

---

## ⚙️ Compile & run

```bash
# 1.  Compile everything (Java 17+ recommended, but any 8+ works)
javac *.java

# 2.  Launch the REPL-style driver
java Main


Sample session:

C:\dict> java Main
dictionary.txt           ← (enter a path)
check word> apple
word found
add new word> pear
Word added successfully
remove word> banana
Dictionary saved successfully.
```

# ⏱️ Algorithmic complexity
Operation	Time	Notes
addWord, findWord, deleteWord	O(k)	k = word length
Load file of n words	O(k · n)	each insert is O(k)
findSimilar	O(k²)	k substitutions × O(k) lookup each
Trie creation	O(1)	empty root node

See efficiency.txt for the original cheat-sheet.
