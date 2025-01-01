import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {

    // Class to represent an activity with an id, start, and finish times
    static class Activity {
        int id;     // Activity ID
        int start;  // Start time of the activity
        int finish; // Finish time of the activity

        public Activity(int id, int start, int finish) {
            this.id = id;
            this.start = start;
            this.finish = finish;
        }
    }

    // Method to perform the Activity Selection Problem
    public static void selectActivities(Activity[] activities) {
        // Sort the activities based on their finishing times
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        // The first activity is always selected
        System.out.println("Selected Activities:");

        int i = 0;
        System.out.println("Activity ID: " + activities[i].id + ", Start: " + activities[i].start + ", Finish: " + activities[i].finish);

        // Select the rest of the activities
        for (int j = 1; j < activities.length; j++) {
            // If the start time of the current activity is greater than or equal to the finish time of the last selected activity
            if (activities[j].start >= activities[i].finish) {
                // Select the current activity
                System.out.println("Activity ID: " + activities[j].id + ", Start: " + activities[j].start + ", Finish: " + activities[j].finish);
                i = j; // Update the last selected activity
            }
        }
    }

    public static void main(String[] args) {
        // Original array of activities
        Activity[] activities = {
            new Activity(1, 1, 3),
            new Activity(2, 2, 5),
            new Activity(3, 4, 6),
            new Activity(4, 6, 8),
            new Activity(5, 5, 7),
            new Activity(6, 8, 9),
            new Activity(7, 0, 2),
            new Activity(8, 3, 4),
            new Activity(9, 7, 9),
            new Activity(10, 10, 12)
        };

        // Perform activity selection on the combined list of activities
        selectActivities(activities);
    }
}
