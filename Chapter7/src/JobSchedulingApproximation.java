import java.util.*;

public class JobSchedulingApproximation {

	static class Job implements Comparable<Job> {
		int id;
		int deadline;
		int profit;

		public Job(int id, int deadline, int profit) {
			this.id = id;
			this.deadline = deadline;
			this.profit = profit;
		}

		// Compare jobs based on their deadlines in non-decreasing order
		public int compareTo(Job other) {
			return this.deadline - other.deadline;
		}
	}

	// Greedy algorithm for job scheduling by deadline
	public static List<Integer> scheduleJobs(Job[] jobs) {
		Arrays.sort(jobs); // Sort jobs based on deadlines
		List<Integer> scheduledJobs = new ArrayList<>();
		boolean[] slot = new boolean[jobs.length];

		for (Job job : jobs) {
			for (int i = Math.min(jobs.length, job.deadline) - 1; i >= 0; i--) {
				if (!slot[i]) {
					// Slot available, schedule the job
					slot[i] = true;
					scheduledJobs.add(job.id);
					break;
				}
			}
		}

		return scheduledJobs;
	}

	public static void main(String[] args) {
		// Example usage
		Job[] jobs = {
				new Job(1, 2, 100),
				new Job(2, 1, 50),
				new Job(3, 2, 200),
				new Job(4, 1, 80),
				new Job(5, 3, 150)
		};

		List<Integer> scheduledJobs = scheduleJobs(jobs);
		System.out.println("Scheduled Jobs: " + scheduledJobs);
	}
}
