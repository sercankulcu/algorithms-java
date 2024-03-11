import java.util.Random;

public class AlgorithmComplexity {

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
		int[] array = new int[100000000];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1000); // Adjust the bound as needed
		}
		int target = 1001;
		long startTime = System.currentTimeMillis();
		linearSearch(array, target);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
	}
}
