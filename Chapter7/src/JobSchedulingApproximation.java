import java.util.*;

public class JobSchedulingApproximation {

    // Class representing a Job with attributes: ID, deadline, and profit
    static class Job implements Comparable<Job> {
        int id;        // Job ID
        int deadline;  // Deadline by which the job should be completed
        int profit;    // Profit earned by completing the job

        // Constructor to initialize job attributes
        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }

        /**
         * Compare jobs based on their deadlines.
         * This helps in sorting jobs in non-decreasing order of deadlines.
         * @param other Another Job object for comparison.
         * @return Difference between this job's deadline and the other's deadline.
         */
        public int compareTo(Job other) {

        	if (this.deadline != other.deadline) {
                return this.deadline - other.deadline;
            }

            return other.profit - this.profit;
        }
    }

    /**
     * Greedy algorithm for job scheduling.
     * This method schedules jobs such that the total profit is maximized while ensuring deadlines are met.
     * 
     * @param jobs Array of Job objects to be scheduled.
     * @return List of job IDs that are scheduled.
     */
    public static List<Integer> scheduleJobs(Job[] jobs) {
        // Sort jobs by their deadlines in ascending order
        Arrays.sort(jobs);

        // List to store IDs of scheduled jobs
        List<Integer> scheduledJobs = new ArrayList<>();

        // Boolean array to keep track of free time slots
        boolean[] slot = new boolean[jobs.length];

        // Iterate over each job
        for (Job job : jobs) {
            // Find a free slot for this job, starting from the last possible slot
            for (int i = Math.min(jobs.length, job.deadline) - 1; i >= 0; i--) {
                if (!slot[i]) { // If slot is available
                    slot[i] = true;             // Mark the slot as occupied
                    scheduledJobs.add(job.id);  // Schedule the job by adding its ID to the list
                    break;                      // Move to the next job
                }
            }
        }

        // Return the list of scheduled job IDs
        return scheduledJobs;
    }

    public static void main(String[] args) {
        // Example usage with a set of jobs
        Job[] jobs = {
            new Job(1, 2, 100), // Job 1 with deadline 2 and profit 100
            new Job(2, 1, 50),  // Job 2 with deadline 1 and profit 50
            new Job(3, 2, 200), // Job 3 with deadline 2 and profit 200
            new Job(4, 1, 80),  // Job 4 with deadline 1 and profit 80
            new Job(5, 3, 150)  // Job 5 with deadline 3 and profit 150
        };

        // Call the scheduling method and store the result
        List<Integer> scheduledJobs = scheduleJobs(jobs);

        // Print the IDs of the scheduled jobs
        System.out.println("Scheduled Jobs: " + scheduledJobs);
    }
}
