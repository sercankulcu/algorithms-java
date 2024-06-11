import java.util.Random;

public class AlgorithmComplexity {

	final static int SIZE = 100000000;

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
		
		System.out.println(f1(32));
		System.out.println(f2(32));
		System.out.println(f3(32));
		System.out.println(f4(32));
		System.out.println(f5(16));
		System.out.println(f5(32));
		System.out.println(f5(64));
		System.out.println(f6(32));
	}

	public static int f1(int n) {
		int x = 0;
		for (int i = 0; i < n; i++) {
			x++;
		}
		return x;
	}

	public static int f2(int n) {
		int x = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i * i; j++) {
				x++;
			}
		}
		return x;
	}

	public static long f3(int n) {
		if (n <= 1) {
			return 1;
		}
		return f3(n - 1) + f3(n - 1);
	}

	public static int f4(int n) {
		if (n <= 1) {
			return 1;
		}
		return f4(n / 2) + f4(n / 2);
	}

	public static int f5(int n) {
		if (n <= 1) {
			return 1;
		}
		return f1(n) + f5(n / 2) + f5(n / 2);
	}

	public static int f6(int n) {
		int x = 0;
		// 1<<i is the same as 2^i
		// Ignore integer overflow.
		// 1<<i takes constant time.
		for (int i = 0; i < n; i = 1 << i) {
			System.out.print(i + " ");
			x++;
		}
		System.out.println();
		return x;
	}
}
