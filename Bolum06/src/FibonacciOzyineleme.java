// Ortusen alt problemler: Fibonacci bol ve fethet bicimde yazilabilir ama alt problemler bagimsiz degil.
// F(5) icin 15 cagri; T(n) = T(n-1) + T(n-2) + O(1) = O(phi^n). Cozum: dinamik programlama, O(n).
public class FibonacciOzyineleme {

	static long fib(int n) {
		if (n < 2) return n;
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(fib(5));    // 5
		System.out.println(fib(20));   // 6765
	}
}

/* Beklenen cikti:
5
6765
*/
