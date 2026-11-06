// Tablolama (asagidan yukari DP): fib[0..n] kucukten buyuge doldurulur.
// O(n) zaman, O(n) bellek. (n >= 1 icin; n = 0'da fib[1] dizi disina tasar.)
public class FibonacciTablolama {

	static int fibonacci(int n) {
		int[] fib = new int[n + 1];
		fib[0] = 0;   // taban durumlari
		fib[1] = 1;
		for (int i = 2; i <= n; i++)
			fib[i] = fib[i - 1] + fib[i - 2];
		return fib[n];
	}

	public static void main(String[] args) {
		System.out.println(fibonacci(9));    // 34
		System.out.println(fibonacci(20));   // 6765
	}
}
