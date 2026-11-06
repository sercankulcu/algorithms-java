// Hizli us alma: x^n = (x^(n/2))^2, n tekse bir carpim daha.
// T(n) = T(n/2) + O(1) = O(log n) carpim.
public class HizliUs {

	static long us(long x, int n) {
		if (n == 0) return 1;         // taban
		long y = us(x, n / 2);
		if (n % 2 == 0) return y * y; // cift
		return x * y * y;             // tek
	}

	public static void main(String[] args) {
		System.out.println(us(3, 1));    // 3
		System.out.println(us(3, 3));    // 27
		System.out.println(us(3, 6));    // 729
		System.out.println(us(3, 13));   // 1594323
	}
}
