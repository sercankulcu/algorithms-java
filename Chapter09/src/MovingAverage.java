import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MovingAverage {

    // Class to maintain a moving average using a circular queue
    static class MovingAverageCalculator {
        private final int k;  // Size of the moving window (number of elements to consider for the moving average)
        private final Queue<Integer> window;  // Queue to hold the last k elements
        private int sum;  // Sum of the elements currently in the window

        // Constructor to initialize the moving average calculator with a given window size 'k'
        public MovingAverageCalculator(int k) {
            this.k = k;
            this.window = new LinkedList<>();  // Initialize the queue to store the last k elements
            this.sum = 0;  // Initialize the sum to 0
        }

        // Method to add a new element to the stream and return the current moving average
        public double next(int val) {
            // If the window has reached its maximum size 'k', remove the oldest element
            if (window.size() == k) {
                sum -= window.poll();  // Subtract the oldest element from the sum
            }

            // Add the new element to the window and update the sum
            window.offer(val);  // Add the new value to the queue
            sum += val;  // Add the new value to the sum

            // Return the moving average of the last k elements
            return sum / (double) window.size();  // Calculate the moving average
        }
    }

    public static void main(String[] args) {
        int k = 5;  // Size of the moving average window (last 5 elements in this case)
        int timeToExit = 99;  // Stop condition for generating new data (exit when value equals 99)
        MovingAverageCalculator movingAverage = new MovingAverageCalculator(k);  // Create a moving average calculator instance

        // Simulate a data stream by generating random numbers
        Random random = new Random();  // Random number generator
        System.out.println("Moving average of the last " + k + " elements in the data stream:");

        // Continuously generate random values and compute the moving average
        while (true) {
            int randomValue = random.nextInt(timeToExit + 1);  // Generate a random number between 0 and 99
            if (randomValue == timeToExit) {
                break;  // Exit the loop if the generated value equals the exit condition (99)
            }

            // Output the newly generated value and the moving average of the last k elements
            System.out.println("New value: " + randomValue + " => Moving Average: " + movingAverage.next(randomValue));
        }
    }
}
