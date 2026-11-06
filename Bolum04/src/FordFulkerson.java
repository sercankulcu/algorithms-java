// Ford-Fulkerson: artik agda DFS ile bulunan artis yollari boyunca darbogaz kadar akis gonderilir.
// Tam sayi kapasitelerde O(E * |f*|); ters kenarlar onceki akisin geri alinmasini saglar.
public class FordFulkerson {

	static int V;
	static int[][] artik;   // artik kapasite matrisi; baslangicta kapasiteler, ters kenarlar 0

	static boolean dfs(int u, int hedef,
					   boolean[] ziyaret, int[] ata) {
		ziyaret[u] = true;
		if (u == hedef) return true;
		for (int v = 0; v < V; v++) {
			if (!ziyaret[v] && artik[u][v] > 0) {
				ata[v] = u;
				if (dfs(v, hedef, ziyaret, ata))
					return true;
			}
		}
		return false;   // bu daldan hedefe yol yok
	}

	// FORD_FULKERSON: artis yolu kalmayana kadar yol bul, darbogaz kadar akis gonder
	static int fordFulkerson(int kaynak, int hedef, String ad) {
		int maksimumAkis = 0;
		int[] ata = new int[V];
		while (dfs(kaynak, hedef, new boolean[V], ata)) {
			int delta = Integer.MAX_VALUE;       // yoldaki en kucuk kalan kapasite
			for (int v = hedef; v != kaynak; v = ata[v])
				delta = Math.min(delta, artik[ata[v]][v]);
			StringBuilder yol = new StringBuilder();
			for (int v = hedef; v != kaynak; v = ata[v]) {
				int u = ata[v];
				artik[u][v] -= delta;
				artik[v][u] += delta;              // ters kenar
				yol.insert(0, " " + ad.charAt(v));
			}
			maksimumAkis += delta;
			System.out.println(ad.charAt(kaynak) + "" + yol + "  +" + delta + "  |f| = " + maksimumAkis);
		}
		return maksimumAkis;
	}

	static void agKur(int dugumSayisi, int[][] kenarlar) {
		V = dugumSayisi;
		artik = new int[V][V];
		for (int[] k : kenarlar) artik[k[0]][k[1]] = k[2];
	}

	public static void main(String[] args) {
		// Ornek 1: S=0, A=1, B=2, C=3, D=4, T=5; komsular S, A, B, C, D, T sirasiyla denenir
		agKur(6, new int[][] { {0, 1, 4}, {0, 3, 3}, {1, 2, 4}, {2, 3, 3},
							   {2, 5, 2}, {3, 4, 6}, {4, 5, 6} });
		System.out.println(fordFulkerson(0, 5, "SABCDT"));
		// S A B C D T  +3  |f| = 3
		// S A B T  +1  |f| = 4
		// S C B T  +1  |f| = 5   (A olu uc; C -> B ve B -> A ters kenarlari)
		// S C D T  +2  |f| = 7
		// 7

		// Ornek 2: S=0, 1..4, T=5
		agKur(6, new int[][] { {0, 1, 10}, {0, 3, 10}, {1, 2, 4}, {1, 3, 2}, {1, 4, 8},
							   {3, 4, 9}, {4, 2, 6}, {2, 5, 10}, {4, 5, 10} });
		System.out.println(fordFulkerson(0, 5, "S1234T"));
		// S 1 2 T  +4  |f| = 4
		// S 1 3 4 2 T  +2  |f| = 6
		// S 1 4 2 T  +4  |f| = 10
		// S 3 1 4 T  +2  |f| = 12   (3 -> 1 ters kenari)
		// S 3 4 T  +7  |f| = 19
		// 19  (min kesim {S, 3}: 10 + 9)
	}
}
