import java.util.Arrays;
import java.util.Random;

// Rastgele pivotlu hizli siralama: beklenen O(n log n) (yaklasik 1.39 n log2 n karsilastirma), en kotu O(n^2).
// Rastgele secme (QuickSelect): ayni bolumleme, yalnizca tek tarafa inilir; beklenen O(n).
public class RastgeleHizliSiralama {

	static Random zar = new Random(42);

	static void hizliSirala(int[] a, int alt, int ust) {
		if (alt < ust) {
			int p = bol(a, alt, ust); // rastgele
			hizliSirala(a, alt, p - 1);
			hizliSirala(a, p + 1, ust);
		}
	}

	static int bol(int[] a, int alt, int ust) {
		int r = alt + zar.nextInt(ust - alt + 1);
		takas(a, r, ust);             // pivot sona
		int pivot = a[ust], k = alt;
		for (int i = alt; i < ust; i++)
			if (a[i] <= pivot)
				takas(a, i, k++);         // kucukleri sola
		takas(a, k, ust);             // pivot yerine
		return k;
	}

	static void takas(int[] a, int i, int j) {
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}

	// s. en kucuk oge (0 tabanli)
	static int sec(int[] a, int alt, int ust, int s) {
		if (alt == ust) return a[alt];
		int p = bol(a, alt, ust);
		if (s == p) return a[p];
		if (s < p) return sec(a, alt, p - 1, s);
		return sec(a, p + 1, ust, s);
	}

	public static void main(String[] args) {
		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		hizliSirala(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] b = {6, 5, 3, 1, 8, 7, 2, 4};
		System.out.println(sec(b, 0, b.length - 1, 5));   // 6

		int[] c = {6, 5, 3, 1, 8, 7, 2, 4};
		System.out.println(sec(c, 0, c.length - 1, 2));   // 3
	}
}

/* Beklenen cikti:
[1, 2, 3, 4, 5, 6, 7, 8]
6
3
*/
