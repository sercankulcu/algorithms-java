import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelectionTree {

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

    // Class to represent a Node in the Tree
    static class Node {
        Activity activity;
        Node left;  // Left child (represents the next non-overlapping activity)
        Node right; // Right child (represents the next available activity after selecting the left child)

        public Node(Activity activity) {
            this.activity = activity;
            this.left = null;
            this.right = null;
        }
    }

    // Method to build the tree recursively and return the optimal selection
    public static Node buildActivityTree(Activity[] activities, int index) {
    	
        // Sort the activities based on their finish times
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));
        
        
        // Base case: if no more activities are left to process
        if (index >= activities.length) {
            return null;
        }

        Node root = new Node(activities[index]);
        int nextIndex = findNextNonOverlappingActivity(activities, index);

        // Build the tree recursively
        root.left = buildActivityTree(activities, nextIndex);
        root.right = buildActivityTree(activities, index + 1);

        return root;
    }

    // Method to find the index of the next activity that does not overlap with the current one
    public static int findNextNonOverlappingActivity(Activity[] activities, int index) {
        for (int i = index + 1; i < activities.length; i++) {
            if (activities[i].start >= activities[index].finish) {
                return i;
            }
        }
        return activities.length; // No more non-overlapping activities
    }

    // Method to print the activities selected in the tree
    public static void printSelectedActivities(Node root) {
        if (root == null) {
            return;
        }
        // Print the current node's activity
        System.out.println("Activity ID: " + root.activity.id + ", Start: " + root.activity.start + ", Finish: " + root.activity.finish);
        // Recursively print the left child (next non-overlapping activity)
        printSelectedActivities(root.left);
    }

    public static void main(String[] args) {
        // Example array of activities with id, start, and finish times
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
        
        // Build the activity selection tree starting from the first activity
        Node root = buildActivityTree(activities, 0);

        // Print the selected activities
        System.out.println("Selected Activities:");
        printSelectedActivities(root);
    }
}
