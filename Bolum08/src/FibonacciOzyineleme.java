// Fibonacci duz ozyineleme: tanimin birebir kopyasi, dogru ama yavas.
// Cagri sayisi 2 F(n+1) - 1: Theta(phi^n), phi ~ 1.618; ayni alt problemler tekrar hesaplanir.
public class FibonacciOzyineleme {

	static long fib(int n) {
		if (n <= 1)
			return n;         // taban durumlari
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(fib(5));    // 5
		System.out.println(fib(10));   // 55
		System.out.println(fib(30));   // 832040
	}
}

/* Beklenen cikti:
5
55
832040
*/
