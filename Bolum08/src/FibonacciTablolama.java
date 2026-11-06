// Tablolama (asagidan yukari DP): fib[0..n] kucukten buyuge doldurulur.
// O(n) zaman, O(n) bellek. Ilk satir n = 0 ve n = 1'i dizi kurulmadan karsilar;
// o satir olmasaydi n = 0'da fib[1] = 1 dizi disina tasardi.
public class FibonacciTablolama {

	static int fibonacci(int n) {
		if (n <= 1) return n;   // n = 0 ve n = 1
		int[] fib = new int[n + 1];
		fib[0] = 0;   // taban durumlari
		fib[1] = 1;
		for (int i = 2; i <= n; i++)
			fib[i] = fib[i - 1] + fib[i - 2];
		return fib[n];
	}

	public static void main(String[] args) {
		System.out.println(fibonacci(0));    // 0
		System.out.println(fibonacci(1));    // 1
		System.out.println(fibonacci(9));    // 34
		System.out.println(fibonacci(20));   // 6765
	}
}
