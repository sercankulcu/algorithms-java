# Algorithms in Java

Welcome to the **Algorithms in Java** repository! This project provides clean and well-documented Java implementations of common algorithms. It serves as an educational resource for students, developers, and anyone preparing for coding interviews or studying algorithmic problem-solving.

## Overview

This repository contains Java implementations of various algorithms, ranging from sorting and searching to graph and dynamic programming techniques. Each algorithm is implemented in a separate Java file, designed to be modular, readable, and easy to understand. The code is ideal for learning purposes, reference, or as a starting point for solving algorithmic problems.

## Implemented Algorithms

The following algorithms are included in this repository, organized by category:

### Sorting Algorithms
- **Bubble Sort**: A simple comparison-based sorting algorithm.
- **Selection Sort**: Selects the smallest element and places it in the sorted portion.
- **Insertion Sort**: Builds the sorted array one element at a time.
- **Merge Sort**: A divide-and-conquer sorting algorithm with O(n log n) complexity.
- **Quick Sort**: A fast, in-place sorting algorithm using partitioning.

### Searching Algorithms
- **Linear Search**: Sequentially checks each element for a match.
- **Binary Search**: Efficiently searches a sorted array by dividing the search space.

### Graph Algorithms
- **Depth-First Search (DFS)**: Explores as far as possible along each branch.
- **Breadth-First Search (BFS)**: Explores all neighbors at the current depth.
- **Dijkstra’s Algorithm**: Finds the shortest path in a weighted graph.
- **Kruskal’s Algorithm**: Computes the minimum spanning tree for a graph.

### Dynamic Programming
- **Fibonacci Sequence**: Calculates Fibonacci numbers efficiently.
- **Knapsack Problem**: Solves the 0/1 knapsack problem for optimal item selection.

### Other Algorithms
- **Factorial**: Computes the factorial of a number (iterative and recursive).
- **GCD (Greatest Common Divisor)**: Uses Euclidean algorithm for GCD calculation.

Each algorithm is implemented in a separate file under the `src/` directory.

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor with a Java compiler.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/sercankulcu/algorithms-java.git
   ```
2. Navigate to the project directory:
   ```bash
   cd algorithms-java
   ```
3. Compile the desired Java file(s) using a Java compiler or IDE:
   ```bash
   javac src/<Algorithm>.java
   ```
   Note: Most files are standalone implementations without a `main` method. You may need to create a test class to run or test the code.

### Usage
Each algorithm is implemented as a Java class or method. To use an algorithm:

1. Import the desired class or method into your Java project.
2. Call the algorithm with appropriate inputs. For example:
   ```java
   // Example of Binary Search
   int[] array = {1, 3, 5, 7, 9};
   int index = BinarySearch.search(array, 5);
   System.out.println("Found at index: " + index); // Outputs: Found at index: 2
   ```
3. Refer to the source code in `src/` for detailed method signatures and implementations.

## Contributing
Contributions are welcome! If you'd like to add new algorithms, improve existing implementations, or fix bugs, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

Please ensure your code adheres to Java coding conventions and includes clear comments or documentation.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For questions or suggestions, feel free to contact the repository owner, [Sercan Kulcu](https://github.com/sercankulcu), or open an issue on GitHub.

Happy coding!
