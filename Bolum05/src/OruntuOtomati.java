// Oruntu otomati: durum q = metnin sonunda oruntuyle uyusan en uzun onekin uzunlugu.
// Kurulum O(m * |S|), arama O(n); KMP onek fonksiyonunun otomat bicimi.
public class OruntuOtomati {

	// p icin DFA: dfa[q][c] = q durumunda c okununca yeni durum (0..m)
	static int[][] dfaKur(String p) {
		int m = p.length();
		int[][] dfa = new int[m + 1][256];
		dfa[0][p.charAt(0)] = 1;
		int X = 0;                          // yeniden baslama durumu
		for (int q = 1; q <= m; q++) {
			for (int c = 0; c < 256; c++)
				dfa[q][c] = dfa[X][c];          // uyusmazlik: X gibi davran
			if (q < m) {
				dfa[q][p.charAt(q)] = q + 1;    // eslesme: bir ilerle
				X = dfa[X][p.charAt(q)];
			}
		}
		return dfa;
	}

	static int dfaAra(int[][] dfa, String t, int m) {
		int q = 0;                     // durum
		for (int i = 0; i < t.length(); i++) {
			q = dfa[q][t.charAt(i)];
			if (q == m)
				return i - m + 1;          // eslesme
		}
		return -1;
	}

	public static void main(String[] args) {
		int[][] dfa = dfaKur("ACACAGA");
		System.out.println(dfa[5]['C']);                   // 4
		System.out.println(dfaAra(dfa, "GACACACAGA", 7));  // 3
		System.out.println(dfaAra(dfa, "ACACACAC", 7));    // -1
	}
}
