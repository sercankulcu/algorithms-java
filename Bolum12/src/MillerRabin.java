import java.util.Random;

// Miller-Rabin asallik testi (Monte Carlo): bilesik sayi her turda en cok 1/4 olasilikla gecer.
// k tur, her tur O(log n) modular carpma; hizli modular us O(log d).
public class MillerRabin {

	static Random zar = new Random(42);

	static boolean asalMi(long n, int k) {
		if (n < 4) return n == 2 || n == 3;
		if (n % 2 == 0) return false;
		long d = n - 1; int s = 0;     // n-1 = 2^s * d
		while (d % 2 == 0) { d /= 2; s++; }
		for (int tur = 0; tur < k; tur++) {
			long a = 2 + zar.nextLong(n - 3); // [2, n-2]
			long x = usMod(a, d, n);     // a^d mod n
			if (x == 1 || x == n - 1) continue;
			boolean tanik = true;
			for (int r = 1; r < s && tanik; r++) {
				x = x * x % n;
				if (x == n - 1) tanik = false;
			}
			if (tanik) return false;     // kesin bilesik
		}
		return true;                  // muhtemelen asal
	}

	static long usMod(long a, long d, long n) {
		long sonuc = 1;
		a %= n;
		while (d > 0) {
			if ((d & 1) == 1)          // bit 1 ise
				sonuc = sonuc * a % n;
			a = a * a % n;             // kare al
			d >>= 1;                   // sonraki bit
		}
		return sonuc;
	}

	public static void main(String[] args) {
		System.out.println(usMod(2, 35, 561));     // 263
		System.out.println(usMod(2, 560, 561));    // 1 (Fermat testi aldanir)
		System.out.println(asalMi(561, 10));       // false (Carmichael: 3 * 11 * 17)
		System.out.println(asalMi(61, 10));        // true
		System.out.println(asalMi(221, 10));       // false (13 * 17)
		System.out.println(asalMi(1000000007, 10));   // true
	}
}
