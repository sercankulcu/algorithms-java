import java.util.PriorityQueue;
import java.util.Random;

public class OnlineMedian {

    /**
     * Online Median Algorithm to maintain the median of a dynamically growing dataset.
     * @param timeToBreak - The value when the stream will stop (break the loop).
     * @return - The current median after processing each element.
     */
    public static double calculateOnlineMedian(int timeToBreak) {
        // Max-heap to store the smaller half of the data
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Min-heap to store the larger half of the data
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Random random = new Random();
        // Continue processing elements until we hit timeToBreak value
        while (true) {
            int num = random.nextInt(100);  // Generate a random number (between 0 and 99)

            // Break the loop when the timeToBreak value is encountered
            if (num == timeToBreak) {
                break;
            }

            // Add the number to the appropriate heap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);  // Add to max-heap if it's smaller or equal to max element
            } else {
                minHeap.offer(num);  // Otherwise, add to min-heap
            }

            // Rebalance the heaps if necessary
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            // Calculate the median after each insertion
            double median;
            if (maxHeap.size() == minHeap.size()) {
                median = (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                median = maxHeap.peek();
            }

            // Output the current median
            System.out.println("After processing " + num + ", Current Median: " + median);
        }

        return 0;  // Return value is not necessary in this case, but for completeness
    }

    public static void main(String[] args) {
        int timeToBreak = 50;  // Example value when to stop processing
        System.out.println("Starting Online Median Algorithm. Stopping when number " + timeToBreak + " is encountered.");
        calculateOnlineMedian(timeToBreak);
    }
}
