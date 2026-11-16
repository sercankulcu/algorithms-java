import java.util.Arrays;

// Horspool: kaydirma, hizanin son karakterine (t[s + m - 1]) bakan tek bir tablodan okunur.
// Tablo O(m + 256), arama en kotu O(n * m), ortalama alt dogrusal.
public class Horspool {

	static int horspool(String t, String p) {
		int n = t.length(), m = p.length();
		int[] kayd = new int[256];
		Arrays.fill(kayd, m);
		for (int i = 0; i < m - 1; i++)
			kayd[p.charAt(i)] = m - 1 - i;
		int s = 0;
		while (s <= n - m) {
			int j = m - 1;
			while (j >= 0 &&
				p.charAt(j) == t.charAt(s + j))
				j--;
			if (j < 0) return s;
			s += kayd[t.charAt(s + m - 1)];
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(horspool("LO LOELLO O HELLO", "HELLO"));  // 12
		System.out.println(horspool("LO LOELLO O HELLO", "WORLD"));  // -1
	}
}

/* Beklenen cikti:
12
-1
*/
