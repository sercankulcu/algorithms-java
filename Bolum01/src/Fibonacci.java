// O(2^n) saf ozyinelemeli Fibonacci ve O(n) bellekleme surumu.
public class Fibonacci {

	static final int N = 90;

	static long fib(int n) {           // saf ozyineleme
		if (n <= 1) return n;
		return fib(n - 1) + fib(n - 2);
	}

	static long[] bellek = new long[N + 1]; // 0: yok
	static long fibBellek(int n) {     // bellekleme
		if (n <= 1) return n;
		if (bellek[n] == 0)
			bellek[n] = fibBellek(n - 1) + fibBellek(n - 2);
		return bellek[n];
	}

	public static void main(String[] args) {
		System.out.println(fib(10));          // 55
		System.out.println(fib(30));          // 832040
		System.out.println(fibBellek(50));    // 12586269025
		System.out.println(fibBellek(90));    // 2880067194370816120
	}
}

/* Beklenen cikti:
55
832040
12586269025
2880067194370816120
*/
