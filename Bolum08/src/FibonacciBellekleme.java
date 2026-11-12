// Bellekleme (yukaridan asagi DP): her fib(n) bir kez hesaplanip saklanir.
// O(n) zaman, O(n) tablo + yigin; cagri sayisi 2n - 1.
public class FibonacciBellekleme {

	static long[] bellek = new long[50];

	static long fib(int n) {
		if (n <= 1) return n;          // taban
		if (bellek[n] != 0) return bellek[n];
		long a = fib(n - 1);
		long b = fib(n - 2);
		bellek[n] = a + b;
		return bellek[n];
	}

	public static void main(String[] args) {
		System.out.println(fib(5));    // 5
		System.out.println(fib(40));   // 102334155
	}
}

/* Beklenen cikti:
5
102334155
*/
