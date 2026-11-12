// Gezgin satici (TSP) dal sinir: sinirli derinlik oncelikli arama, alt sinir = en ucuz cikis kenarlari.
// Daha siki alt sinirlar: iki en kisa kenar ve indirgenmis maliyet matrisi (her biri O(n^2)).
// En kotu durum O(n!); sinir testi uretilen dugum sayisini cok azaltir.
public class GezginSatici {

	static final int INF = Integer.MAX_VALUE;
	static int n;
	static int[][] d;
	static int[] enKucuk;        // her sehrin en ucuz cikis kenari
	static boolean[] ziyaret;
	static int enIyi;

	static void hazirla(int[][] m) {
		d = m;
		n = m.length;
		enKucuk = new int[n];
		for (int i = 0; i < n; i++) {
			enKucuk[i] = INF;
			for (int j = 0; j < n; j++)
				if (j != i && d[i][j] < enKucuk[i]) enKucuk[i] = d[i][j];
		}
		ziyaret = new boolean[n];
		enIyi = INF;
	}

	static void tsp(int sehir, int maliyet, int sayac) {
		if (sayac == n) {           // tur tamam
			int tur = maliyet + d[sehir][0];
			if (tur < enIyi) enIyi = tur;
			return;
		}
		for (int s = 0; s < n; s++) {
			if (ziyaret[s]) continue;
			int yeni = maliyet + d[sehir][s];
			ziyaret[s] = true;
			if (altSinir(s, yeni) < enIyi) // sinir
				tsp(s, yeni, sayac + 1);
			ziyaret[s] = false;      // geri al
		}
	}

	// maliyet(yol) + son sehrin ve gidilmemis sehirlerin en ucuz cikis kenarlari
	static int altSinir(int son, int maliyet) {
		int alt = maliyet + enKucuk[son];
		for (int v = 0; v < n; v++)
			if (!ziyaret[v])       // gidilmemis
				alt += enKucuk[v];
		return alt;
	}

	// tur >= (1/2) * toplam(her sehre degen en kisa iki kenar)
	static double ikiKenarSinir() {
		int toplam = 0;
		for (int v = 0; v < n; v++) {
			int m1 = INF, m2 = INF;     // en kucuk iki
			for (int u = 0; u < n; u++) {
				if (u == v) continue;
				if (d[v][u] < m1) { m2 = m1; m1 = d[v][u]; }
				else if (d[v][u] < m2) m2 = d[v][u];
			}
			toplam += m1 + m2;
		}
		return toplam / 2.0;  // her kenar iki kez
	}

	static int satirMin(int[][] m, int i) {
		int mn = INF;
		for (int j = 0; j < n; j++) mn = Math.min(mn, m[i][j]);
		return mn;
	}

	static int sutunMin(int[][] m, int j) {
		int mn = INF;
		for (int i = 0; i < n; i++) mn = Math.min(mn, m[i][j]);
		return mn;
	}

	static void satirdanCikar(int[][] m, int i, int mn) {
		for (int j = 0; j < n; j++)
			if (m[i][j] != INF) m[i][j] -= mn;   // INF dokunulmaz
	}

	static void sutundanCikar(int[][] m, int j, int mn) {
		for (int i = 0; i < n; i++)
			if (m[i][j] != INF) m[i][j] -= mn;
	}

	// Satir ve sutun indirgemesi: cikarilan toplam bir alt sinirdir
	static int indirge(int[][] m) {
		int toplam = 0;
		for (int i = 0; i < n; i++) {       // satirlar
			int mn = satirMin(m, i);
			if (mn > 0 && mn < INF) {
				satirdanCikar(m, i, mn); toplam += mn;
			}
		}
		for (int j = 0; j < n; j++) {       // sutunlar
			int mn = sutunMin(m, j);
			if (mn > 0 && mn < INF) {
				sutundanCikar(m, j, mn); toplam += mn;
			}
		}
		return toplam;
	}

	static int coz(int[][] m) {
		hazirla(m);
		ziyaret[0] = true;           // 0. sehirden baslanir
		tsp(0, 0, 1);
		return enIyi;
	}

	static void matrisYazdir(int[][] m) {
		for (int[] satir : m) {
			StringBuilder sb = new StringBuilder();
			for (int x : satir) sb.append(x == INF ? "INF" : String.valueOf(x)).append(' ');
			System.out.println(sb.toString().trim());
		}
	}

	public static void main(String[] args) {
		int[][] d4 = {
			{0, 3, 2, 15},
			{3, 0, 21, 5},
			{2, 21, 0, 8},
			{15, 5, 8, 0}
		};
		System.out.println("en kisa tur: " + coz(d4));     // 18 (0 -> 1 -> 3 -> 2 -> 0)

		// altSinir(3, 8): yol 0 -> 1 -> 3, ziyaret = {0, 1, 3}
		hazirla(d4);
		ziyaret[0] = ziyaret[1] = ziyaret[3] = true;
		System.out.println("altSinir(3, 8) = " + altSinir(3, 8));   // 15

		System.out.println("ikiKenarSinir = " + ikiKenarSinir());   // 18.0

		int[][] m = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				m[i][j] = (i == j) ? INF : d4[i][j];   // kosegen sonsuz
		System.out.println("indirge = " + indirge(m));             // 14
		matrisYazdir(m);
		// INF 1 0 11
		// 0 INF 18 0
		// 0 19 INF 4
		// 10 0 3 INF

		int[][] d6 = {
			{0, 29, 20, 21, 16, 31},
			{29, 0, 15, 29, 28, 40},
			{20, 15, 0, 15, 14, 25},
			{21, 29, 15, 0, 12, 18},
			{16, 28, 14, 12, 0, 22},
			{31, 40, 25, 18, 22, 0}
		};
		System.out.println("6 sehir: " + coz(d6));         // 115 (0 -> 1 -> 2 -> 3 -> 5 -> 4 -> 0)
	}
}

/* Beklenen cikti:
en kisa tur: 18
altSinir(3, 8) = 15
ikiKenarSinir = 18.0
indirge = 14
INF 1 0 11
0 INF 18 0
0 19 INF 4
10 0 3 INF
6 sehir: 115
*/
