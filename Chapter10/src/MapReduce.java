import java.util.*;
import java.util.concurrent.*;

public class MapReduce {

    private static long taskCounter = 0; // Static counter for generating unique task IDs

    // Mapper task to count the occurrences of words in a list
    static class MapTask extends RecursiveTask<Map<String, Integer>> {
        private static final long serialVersionUID = 1L;
        private final List<String> data; // Data chunk to be processed by this task
        private final long taskId;  // Task ID for logging
        
        // Constructor for the MapTask, initializes the data and assigns a unique task ID
        public MapTask(List<String> data) {
            this.data = data;
            this.taskId = taskCounter++;  // Assign a unique task ID and increment the counter
            System.out.println("Created MapTask with Task ID: " + taskId);  // Log the creation of the task
        }

        @Override
        protected Map<String, Integer> compute() {
            Map<String, Integer> resultMap = new HashMap<>(); // Map to store word counts for this task
            System.out.println("Executing MapTask with Task ID: " + taskId);  // Log the execution of the task
            // Loop through each word in the data and count its occurrences
            for (String word : data) {
                resultMap.put(word, resultMap.getOrDefault(word, 0) + 1);
            }
            return resultMap;  // Return the word counts for the current data chunk
        }
    }

    // Reducer task to combine word counts from multiple map tasks
    static class ReduceTask extends RecursiveTask<Map<String, Integer>> {
        private static final long serialVersionUID = 1L;
        private final List<Map<String, Integer>> intermediateResults; // Intermediate results from the map tasks
        private final long taskId;  // Task ID for logging
        
        // Constructor for the ReduceTask, initializes the intermediate results and assigns a unique task ID
        public ReduceTask(List<Map<String, Integer>> intermediateResults) {
            this.intermediateResults = intermediateResults;
            this.taskId = taskCounter++;  // Assign a unique task ID and increment the counter
            System.out.println("Created ReduceTask with Task ID: " + taskId);  // Log the creation of the task
        }

        @Override
        protected Map<String, Integer> compute() {
            System.out.println("Executing ReduceTask with Task ID: " + taskId);  // Log the execution of the task
            Map<String, Integer> finalResult = new HashMap<>(); // Map to store the final aggregated results
            // Aggregate the results from all map tasks
            for (Map<String, Integer> result : intermediateResults) {
                for (Map.Entry<String, Integer> entry : result.entrySet()) {
                    finalResult.put(entry.getKey(),
                            finalResult.getOrDefault(entry.getKey(), 0) + entry.getValue()); // Combine word counts
                }
            }
            return finalResult;  // Return the final aggregated word counts
        }
    }

    // Method to simulate the MapReduce process with parallel execution
    public static Map<String, Integer> mapReduce(List<String> data, int numThreads) {
        try (ForkJoinPool pool = new ForkJoinPool(numThreads)) {  // Create a ForkJoinPool with the specified number of threads
            // Split the data into chunks for parallel processing
            int chunkSize = data.size() / numThreads;
            List<MapTask> mapTasks = new ArrayList<>();

            // Split data into chunks and create MapTasks for each chunk
            for (int i = 0; i < numThreads; i++) {
                int start = i * chunkSize;
                int end = (i == numThreads - 1) ? data.size() : (i + 1) * chunkSize;
                List<String> chunk = data.subList(start, end);  // Get a chunk of the data
                mapTasks.add(new MapTask(chunk));  // Add a new MapTask to the list
            }

            // Execute the map tasks in parallel by forking each task asynchronously
            List<Map<String, Integer>> mapResults = new ArrayList<>();
            for (MapTask task : mapTasks) {
                task.fork(); // Launch the map task asynchronously
            }

            // Wait for all map tasks to complete and collect the results
            for (MapTask task : mapTasks) {
                mapResults.add(task.join());  // Collect the result of each map task
            }

            // Create a reduce task to aggregate the results from all map tasks
            ReduceTask reduceTask = new ReduceTask(mapResults);
            return pool.invoke(reduceTask);  // Invoke the reduce task to combine the results
        }
    }

    public static void main(String[] args) {
        // Example data (list of words) for testing the MapReduce process
        List<String> data = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana", "apple", "banana", "banana", "orange", "apple", "cherry", "banana", "apple");

        // Perform MapReduce with 4 threads
        Map<String, Integer> result = mapReduce(data, 4);

        // Output the result of word counts
        System.out.println("Word Counts: " + result);  // Print the final aggregated word counts
    }
}
