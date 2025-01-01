import java.util.ArrayList;
import java.util.Random;

public class ReservoirSampling {

    /**
     * Reservoir sampling algorithm to randomly select `k` items from a stream of unknown size.
     * The stream is simulated using random numbers, and the sampling stops when the specified `timeToBreak` value is encountered.
     * @param k - The number of items to sample.
     * @param timeToBreak - The value when the stream will stop (break the loop).
     * @return - An ArrayList containing the sampled items.
     */
    public static ArrayList<Integer> reservoirSample(int k, int timeToBreak) {
        // Create an ArrayList to store the selected sample (reservoir)
        ArrayList<Integer> reservoir = new ArrayList<>(k);
        // Create an ArrayList to store all the generated random numbers (stream)
        ArrayList<Integer> allNumbers = new ArrayList<>();
        // Random number generator for simulating the stream
        Random random = new Random();

        // Step 1: Initialize the reservoir with the first `k` items from the stream
        for (int i = 0; i < k; i++) {
            int item = random.nextInt(100);  // Simulate an incoming stream item (between 0 and 99)
            reservoir.add(item);  // Add item to the reservoir
            allNumbers.add(item);  // Add item to the list of all generated numbers (stream)
        }

        // Step 2: Process the remaining items in the stream
        int i = k;
        while (true) {
            int item = random.nextInt(100);  // Simulate an incoming stream item (between 0 and 99)
            
            // Break the loop when the item equals the specified timeToBreak value
            if (item == timeToBreak) {
                break;  // Stop the process when the timeToBreak value is encountered
            }

            // Add the item to the list of all generated numbers (stream)
            allNumbers.add(item);

            // Randomly select an index in the reservoir to possibly replace
            int j = random.nextInt(i + 1);

            // If the randomly selected index is within the bounds of the reservoir, replace the item
            if (j < k) {
                reservoir.set(j, item);  // Replace the item at index `j` in the reservoir
            }

            i++;  // Increase the stream index
        }

        // Output the entire generated stream (all numbers)
        System.out.println("Generated Stream: " + allNumbers);

        return reservoir;  // Return the sampled items (reservoir)
    }

    public static void main(String[] args) {
        int k = 5;  // Number of items to sample
        int timeToBreak = 20;  // Value that will stop the stream generation (when encountered)

        // Get the reservoir sample from the simulated stream
        ArrayList<Integer> sample = reservoirSample(k, timeToBreak);

        // Output the selected sample
        System.out.println("Reservoir sample: " + sample);
    }
}
