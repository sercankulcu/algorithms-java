import java.util.Random;

public class AlgorithmComplexity {
	
	final static int SIZE = 500000000;

	public static int linearSearch(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i; // Return the index if the target is found
			}
		}
		return -1; // Return -1 if the target is not found in the array
	}

	public static void main(String[] args) {
		Random random = new Random();
		int[] array = new int[SIZE];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(10000); // Adjust the bound as needed
		}
		int target = 10001;
		long startTime = System.currentTimeMillis();
		linearSearch(array, target);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
	}
}
