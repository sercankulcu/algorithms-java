import java.util.*;
import java.awt.Point;

public class ClosestPairOfPoints {

    // Comparator to sort points by x-coordinate
    public static class XComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.x, p2.x);  // Compare x-coordinate
        }
    }

    // Comparator to sort points by y-coordinate
    public static class YComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.y, p2.y);  // Compare y-coordinate
        }
    }

    // Method to calculate Euclidean distance between two points
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    // Brute-force method to find the closest pair of points in a small set of points
    public static double bruteForce(List<Point> points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double dist = distance(points.get(i), points.get(j));
                System.out.println("Distance between " + points.get(i).x + "," + points.get(i).y + " and " + 
                		points.get(j).x + "," + points.get(j).y + " is " + dist);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    // Method to find the closest pair of points using the divide-and-conquer approach
    public static double closestPair(List<Point> points, List<Point> pointsByY) {
        int n = points.size();

        // Base case: if there are 3 or fewer points, use brute-force
        if (n <= 3) {
            return bruteForce(points);
        }

        // Divide the points into two halves
        int mid = n / 2;
        Point midPoint = points.get(mid);
        System.out.println("Dividing points at " + midPoint.x + "," + midPoint.y + " (middle point)");

        // Split the points into left and right halves
        List<Point> leftByX = points.subList(0, mid);
        List<Point> rightByX = points.subList(mid, n);

        // Create corresponding sorted-by-Y lists for each half
        List<Point> leftByY = new ArrayList<>();
        List<Point> rightByY = new ArrayList<>();
        for (Point p : pointsByY) {
            if (p.x <= midPoint.x) {
                leftByY.add(p);
            } else {
                rightByY.add(p);
            }
        }

        // Recursively find the closest pair in each half
        System.out.println("Recursively finding closest pair in left half");
        double leftDist = closestPair(leftByX, leftByY);
        System.out.println("Recursively finding closest pair in right half");
        double rightDist = closestPair(rightByX, rightByY);

        // Find the minimum distance in both halves
        double minDist = Math.min(leftDist, rightDist);
        System.out.println("Minimum distance after dividing: " + minDist);

        // Create an array for points near the dividing line
        List<Point> strip = new ArrayList<>();
        for (Point p : pointsByY) {
            if (Math.abs(p.x - midPoint.x) < minDist) {
                strip.add(p);
            }
        }

        // Find the closest points in the strip
        return Math.min(minDist, stripClosest(strip, minDist));
    }

    // Method to find the closest points in the strip (points near the dividing line)
    public static double stripClosest(List<Point> strip, double minDist) {
        double minDistInStrip = minDist;
        int n = strip.size();
        System.out.println("Checking points in the strip for closer pairs...");
        
        // Check each point in the strip and compare it with others
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip.get(j).y - strip.get(i).y) < minDistInStrip; j++) {
                double dist = distance(strip.get(i), strip.get(j));
                System.out.println("Distance between " + strip.get(i).x + "," + strip.get(i).y + " and " + 
                		strip.get(j).x + "," + strip.get(j).y + " in strip: " + dist);
                minDistInStrip = Math.min(minDistInStrip, dist);
            }
        }
        return minDistInStrip;
    }

    // Main method to find the closest pair of points
    public static double closestPairOfPoints(List<Point> points) {
        // Sort points by x-coordinate
        List<Point> pointsByX = new ArrayList<>(points);
        Collections.sort(pointsByX, new XComparator());

        // Sort points by y-coordinate
        List<Point> pointsByY = new ArrayList<>(points);
        Collections.sort(pointsByY, new YComparator());

        // Call the divide-and-conquer algorithm
        return closestPair(pointsByX, pointsByY);
    }

    public static void main(String[] args) {
        // Example set of points
        List<Point> points = Arrays.asList(
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4),
                new Point(7, 7),
                new Point(17, 17),
                new Point(27, 27),
                new Point(28, 27)
        );

        // Find the closest pair of points
        double closestDistance = closestPairOfPoints(points);

        // Output the result
        System.out.println("The closest distance between two points is: " + closestDistance);
    }
}
