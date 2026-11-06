// Bellek azaltma: fib[i] yalnizca son iki degere baglidir, tum dizi saklanmaz.
// O(n) zaman, O(1) ek bellek.
public class FibonacciIkiDegisken {

	static long fib(int n) {
		if (n <= 1) return n;
		long once = 0, simdi = 1;
		for (int i = 2; i <= n; i++) {
			long yeni = once + simdi;
			once = simdi;
			simdi = yeni;
		}
		return simdi;
	}

	public static void main(String[] args) {
		System.out.println(fib(9));    // 34
		System.out.println(fib(50));   // 12586269025
	}
}
