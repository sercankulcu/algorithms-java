// Karatsuba carpimi: 4 carpim yerine 3 (z2 = ac, z0 = bd, z1 = (a+b)(c+d)).
// T(n) = 3T(n/2) + O(n) = O(n^log2(3)) ~ O(n^1.585).
public class Karatsuba {

	static int basamak(long x) {
		int b = 1;
		while (x >= 10) { x /= 10; b++; }
		return b;
	}

	static long karatsuba(long x, long y) {
		if (x < 10 || y < 10) return x * y; // taban
		int n = Math.max(basamak(x), basamak(y));
		long p = (long) Math.pow(10, n / 2);
		long a = x / p, b = x % p;   // x = a*p + b
		long c = y / p, d = y % p;   // y = c*p + d
		long z0 = karatsuba(b, d);
		long z1 = karatsuba(a + b, c + d);
		long z2 = karatsuba(a, c);
		return z2 * p * p + (z1 - z2 - z0) * p + z0;
	}

	public static void main(String[] args) {
		System.out.println(karatsuba(34, 78));       // 2652
		System.out.println(karatsuba(46, 134));      // 6164
		System.out.println(karatsuba(12, 56));       // 672
		System.out.println(karatsuba(1234, 5678));   // 7006652
	}
}

/* Beklenen cikti:
2652
6164
672
7006652
*/
