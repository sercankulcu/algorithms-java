import java.util.Arrays;
import java.util.Comparator;

public class JobSequencing {

    // Class to represent a job with its id, deadline, and profit
    static class Job {
        int id;        // Job ID
        int deadline;  // Deadline by which the job should be completed
        int profit;    // Profit earned by completing the job

        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Method to perform job sequencing and maximize the profit
    public static void jobSequencing(Job[] jobs, int n) {
        // Sort the jobs in descending order based on profit
        Arrays.sort(jobs, Comparator.comparingInt((Job job) -> job.profit).reversed());
        
        System.out.println("Sorted Jobs by Profit (Descending):");
        for (Job job : jobs) {
            System.out.println("Job ID: " + job.id + ", Deadline: " + job.deadline + ", Profit: " + job.profit);
        }
        
        // Array to keep track of the free time slots
        int[] result = new int[n];

        // Initialize all time slots to be empty (-1 means empty slot)
        Arrays.fill(result, -1);

        int totalProfit = 0;  // Variable to store total profit from selected jobs
        int jobCount = 0;     // Variable to store the number of jobs successfully scheduled

        // Loop through all the jobs and try to find a suitable slot for each job
        for (int i = 0; i < n; i++) {
            // Debug statement to see which job is being processed
            System.out.print("Processing Job ID: " + jobs[i].id + ", Deadline: " + jobs[i].deadline + ", Profit: " + jobs[i].profit);
            
            // Try to find a slot for this job, starting from the latest available time slot
            for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
                // Check if the time slot is available (i.e., it is not occupied)
                if (result[j] == -1) {
                    result[j] = jobs[i].id;  // Assign the job to this slot
                    totalProfit += jobs[i].profit; // Add the profit to the total profit
                    jobCount++;  // Increment the number of jobs scheduled
                    // Debug statement to show the slot where the job was assigned
                    System.out.println(" Job " + jobs[i].id + " scheduled in slot " + (j + 1));
                    break;  // Break the inner loop since the job is scheduled
                } else {
                    // Debug statement to indicate the slot is already occupied
                    System.out.println(" Slot " + (j + 1) + " is already occupied. Checking earlier slots...");
                }
            }
        }

        // Print the scheduled jobs
        System.out.println("\nScheduled jobs:");
        for (int i = 0; i < n; i++) {
            if (result[i] != -1) {
                System.out.println("Job ID: " + result[i]);
            }
        }

        // Print the total profit from the selected jobs
        System.out.println("\nTotal Profit: " + totalProfit);
        System.out.println("Total Jobs Scheduled: " + jobCount);
    }

    public static void main(String[] args) {
        // Example array of jobs with id, deadline, and profit
        Job[] jobs = {
            new Job(1, 4, 20),
            new Job(2, 1, 10),
            new Job(3, 1, 40),
            new Job(4, 1, 30),
            new Job(5, 3, 50),
            new Job(6, 2, 30)
        };

        // Number of jobs
        int n = jobs.length;

        // Perform job sequencing to maximize profit
        jobSequencing(jobs, n);
    }
}
