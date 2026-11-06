import java.util.Arrays;

// Knuth-Morris-Pratt: LPS (en uzun oz onek = sonek) tablosuyla metinde geri gitmeden arama.
// LPS O(m), arama O(n); toplam O(n + m).
public class KMP {

	// lps[q]: p[0..q] dizgisinin ayni zamanda soneki olan en uzun oz onekinin uzunlugu
	static int[] lpsHesapla(String p) {
		int m = p.length(), k = 0;
		int[] lps = new int[m];
		for (int q = 1; q < m; q++) {
			while (k > 0 &&
				p.charAt(k) != p.charAt(q))
				k = lps[k - 1];
			if (p.charAt(k) == p.charAt(q))
				k++;
			lps[q] = k;
		}
		return lps;
	}

	static int kmpAra(String t, String p) {
		int n = t.length(), m = p.length();
		int[] lps = lpsHesapla(p);
		int q = 0;  // eslesen uzunluk
		for (int i = 0; i < n; i++) {
			while (q > 0 &&
				p.charAt(q) != t.charAt(i))
				q = lps[q - 1];
			if (p.charAt(q) == t.charAt(i))
				q++;
			if (q == m)
				return i - m + 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(lpsHesapla("ACABACACD")));  // [0, 0, 1, 0, 1, 2, 3, 2, 0]
		System.out.println(Arrays.toString(lpsHesapla("ababd")));      // [0, 0, 1, 2, 0]
		System.out.println(kmpAra("ababcababcabababd", "ababd"));      // 12
	}
}
