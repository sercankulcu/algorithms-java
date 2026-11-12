import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

// Fisher-Yates karistirma: her permutasyon 1/n! olasilikla, O(n) zaman.
// Saf karistirma (her i icin 0..n-1 arasindan sec) n^n yol uretir; n^n, n!'e bolunmedigi icin yanlidir.
public class Karistirma {

	static Random zar = new Random(42);

	static void karistir(int[] a) {
		for (int i = a.length - 1; i > 0; i--) {
			int j = zar.nextInt(i + 1); // 0 <= j <= i
			int t = a[i]; a[i] = a[j]; a[j] = t;
		}
	}

	// YANLI: her i icin 0..n-1 arasindan sec
	static void safKaristir(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int j = zar.nextInt(n);
			takas(a, i, j);
		}
	}

	static void takas(int[] a, int i, int j) {
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}

	static String anahtar(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int x : a) sb.append(x);
		return sb.toString();
	}

	// Saf karistirmanin n^n cekilis dizisinin hepsini tek tek dener
	static Map<String, Integer> safYollar(int n) {
		Map<String, Integer> sayim = new TreeMap<>();
		int yol = (int) Math.pow(n, n);
		for (int kod = 0; kod < yol; kod++) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = i + 1;
			int c = kod;
			for (int i = 0; i < n; i++) {   // i. cekilis: j = c mod n
				takas(a, i, c % n);
				c /= n;
			}
			sayim.merge(anahtar(a), 1, Integer::sum);
		}
		return sayim;
	}

	// Fisher-Yates'in n! cekilis dizisinin hepsini dener
	static void fyYollar(int[] a, int i, Map<String, Integer> sayim) {
		if (i == 0) { sayim.merge(anahtar(a), 1, Integer::sum); return; }
		for (int j = 0; j <= i; j++) {
			int[] b = a.clone();
			takas(b, i, j);
			fyYollar(b, i - 1, sayim);
		}
	}

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		karistir(a);
		System.out.println(Arrays.toString(a));   // [2, 3, 4, 5, 1] (tohum 42)

		System.out.println("saf: " + safYollar(3));
		// saf: {123=4, 132=5, 213=5, 231=5, 312=4, 321=4} (27 yol)

		Map<String, Integer> fy = new TreeMap<>();
		fyYollar(new int[]{1, 2, 3}, 2, fy);
		System.out.println("Fisher-Yates: " + fy);
		// Fisher-Yates: {123=1, 132=1, 213=1, 231=1, 312=1, 321=1} (6 yol)

		// Deney: 60000 saf karistirmada [1, 2, 3] sonucunun sikligi (beklenen 4/27 = 0.148)
		int adet = 0;
		for (int t = 0; t < 60000; t++) {
			int[] b = {1, 2, 3};
			safKaristir(b);
			if (anahtar(b).equals("123")) adet++;
		}
		System.out.println(String.format(java.util.Locale.ROOT, "%.3f", adet / 60000.0));   // 0.147
	}
}

/* Beklenen cikti:
[2, 3, 4, 5, 1]
saf: {123=4, 132=5, 213=5, 231=5, 312=4, 321=4}
Fisher-Yates: {123=1, 132=1, 213=1, 231=1, 312=1, 321=1}
0.147
*/
