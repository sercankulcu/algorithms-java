# Algorithms in Java

Java code examples for the undergraduate **Algorithms (Algoritmalar)** course at Giresun University. Each `BolumNN/src` folder holds chapter NN of the course and contains the code shown on that chapter's slides as small runnable programs. Class, method and variable names follow the slides, so each program's output can be compared directly with the slide examples. Pseudocode on the slides is completed into working Java with the same names.

Course page and slides: <https://sercankulcu.github.io/teaching/algorithms>

The course continues in **Advanced Algorithms (İleri Algoritmalar)**: divide and conquer, greedy algorithms, dynamic programming, backtracking, branch and bound, approximation, randomized, online and parallel algorithms. Its code is in [advanced-algorithms-java](https://github.com/sercankulcu/advanced-algorithms-java).

## Chapters

| Folder | Chapter | Programs |
|---|---|---|
| `Bolum01` | Introduction and Algorithm Complexity | Average, step counting and time complexity, primality test, matrix multiplication, permutations, Fibonacci, Euclid's algorithm, running-time measurement, growth of complexity functions |
| `Bolum02` | Sorting Algorithms | Bubble, insertion, selection and Shell sort; merge sort, quicksort (with the pseudocode `PARCALA`/`HIZLI_SIRALA`), heap sort, counting sort, radix sort, Timsort |
| `Bolum03` | Searching Algorithms | Linear, binary, hash-table, interpolation, jump and exponential search |
| `Bolum04` | Graph Algorithms | Adjacency lists, DFS and BFS, Tarjan and Kosaraju (strongly connected components), Kahn's topological sort, bipartite test; Dijkstra, Bellman-Ford, Floyd-Warshall, A*; union-find, Kruskal, Prim, Boruvka, reverse-delete; Ford-Fulkerson, Edmonds-Karp, Dinic, push-relabel |
| `Bolum05` | String Algorithms | Brute force, KMP, Boyer-Moore, Horspool, Rabin-Karp; RLE, LZW, LZ78; string and radix sorting, regular expressions, finite-state machines and pattern automata, number parsing, Levenshtein distance, suffix array, BWT; trie, prefix counter, binary trie, ternary search trie, suffix trie |

## Running the examples

Requires **JDK 21**.

Every file is a standalone program with a `main` method in the default package, and no program waits for keyboard input. Programs that use randomness use a fixed seed (`new Random(42)`), so their output is repeatable but may differ from a run shown on a slide. To compile and run one chapter:

```bash
cd Bolum02/src
javac -d ../bin *.java
java -cp ../bin HizliSiralama
```

Each folder is also an Eclipse project (`File > Import > Existing Projects into Workspace`).

Comments and identifiers are in Turkish, written without Turkish characters so the files compile with any platform encoding.

## Automatic check

`ci/run_examples.py` compiles every `BolumNN` folder with JDK 21, runs every program and compares its output with the expected output at the end of the file. GitHub Actions runs it on every push. A program whose output changes from run to run (timings, random numbers, thread order, the network) says so above its expected output and only has to run; the window programs of a Swing chapter are skipped, since the check runs without a screen. To run the check locally:

```bash
python ci/run_examples.py            # every chapter
python ci/run_examples.py Bolum03    # one chapter
```

## Contact

For questions or suggestions, contact [Sercan Külcü](https://github.com/sercankulcu) or open an issue on GitHub.
