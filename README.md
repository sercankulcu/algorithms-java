# Algorithms in Java

Java code examples for the undergraduate **Algorithms (Algoritmalar)** course at Giresun University. Each `BolumNN/src` folder holds chapter NN of the course and contains the code shown on that chapter's slides as small runnable programs. Class, method and variable names follow the slides, so each program's output can be compared directly with the slide examples. Pseudocode on the slides is completed into working Java with the same names.

Course page and slides: <https://sercankulcu.github.io/teaching/algorithms>

## Chapters

| Folder | Chapter | Programs |
|---|---|---|
| `Bolum01` | Introduction and Algorithm Complexity | Average, step counting and time complexity, primality test, matrix multiplication, permutations, Fibonacci, Euclid's algorithm, running-time measurement, growth of complexity functions |
| `Bolum02` | Sorting Algorithms | Bubble, insertion, selection and Shell sort; merge sort, quicksort (with the pseudocode `PARCALA`/`HIZLI_SIRALA`), heap sort, counting sort, radix sort, Timsort |
| `Bolum03` | Searching Algorithms | Linear, binary, hash-table, interpolation, jump and exponential search |
| `Bolum04` | Graph Algorithms | Adjacency lists, DFS and BFS, Tarjan and Kosaraju (strongly connected components), Kahn's topological sort, bipartite test; Dijkstra, Bellman-Ford, Floyd-Warshall, A*; union-find, Kruskal, Prim, Boruvka, reverse-delete; Ford-Fulkerson, Edmonds-Karp, Dinic, push-relabel |
| `Bolum05` | String Algorithms | Brute force, KMP, Boyer-Moore, Horspool, Rabin-Karp; RLE, LZW, LZ78; string and radix sorting, regular expressions, finite-state machines and pattern automata, number parsing, Levenshtein distance, suffix array, BWT; trie, prefix counter, binary trie, ternary search trie, suffix trie |
| `Bolum06` | Divide and Conquer | Divide-and-conquer template, maximum, fast power, recursive Fibonacci, Quickhull, closest pair, Karatsuba, matrix multiplication |
| `Bolum07` | Greedy Algorithms | Greedy template, making change, activity selection, job sequencing, fractional knapsack, Huffman coding |
| `Bolum08` | Dynamic Programming | Fibonacci (recursive, memoized, tabulated, two variables), 0/1 and unbounded knapsack, subset sum, longest increasing subsequence, longest common subsequence |
| `Bolum09` | Backtracking | Backtracking template, permutations, N-queens (plain and fast), min-conflicts, Sudoku, graph coloring, Hamiltonian path, word search |
| `Bolum10` | Branch and Bound | Branch-and-bound scheme, knapsack, travelling salesman (lower bounds, matrix reduction), assignment problem, maximum clique |
| `Bolum11` | Approximation Algorithms | Nearest-neighbour tour, 2-opt, LPT scheduling, vertex cover, power iteration |
| `Bolum12` | Randomized Algorithms | Shuffling, bogosort, randomized quicksort, Monte Carlo estimate of pi, Miller-Rabin |
| `Bolum13` | Online Algorithms | Ski rental, paging (LRU, FIFO, Belady's anomaly), moving average, online median, reservoir sampling, pattern search in a stream |
| `Bolum14` | Parallel Algorithms | PRAM sum, Blelloch prefix sum, pipelining, parallel merge sort, MapReduce, parallel matrix multiplication, concurrent traversal, Java parallel tools |

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

## Contact

For questions or suggestions, contact [Sercan Külcü](https://github.com/sercankulcu) or open an issue on GitHub.
