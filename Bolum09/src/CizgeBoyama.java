import java.util.Arrays;

// Cizge boyama (m-renk): dugumler sirayla, renkler 1..m sirayla denenir; komsuyla ayni renk olamaz.
// En kotu durum O(m^n) dal, uygunMu O(n).
public class CizgeBoyama {

	static int n, m;
	static int[][] graf;     // komsuluk matrisi
	static int[] renk;       // 0: boyanmadi

	// uygunMu: v'nin c renkli komsusu yoksa true
	static boolean uygunMu(int v, int c) {
		for (int u = 0; u < n; u++)
			if (graf[v][u] == 1 && renk[u] == c) return false;
		return true;
	}

	static boolean boya(int v) {
		if (v == n) return true;          // bitti
		for (int c = 1; c <= m; c++) {
			if (uygunMu(v, c)) {
				renk[v] = c;                  // sec
				if (boya(v + 1)) return true; // kesfet
				renk[v] = 0;                  // geri al
			}
		}
		return false;                     // cikmaz
	}

	static void kur(int dugum, int renkSayisi, int[][] kenarlar) {
		n = dugum; m = renkSayisi;
		graf = new int[n][n];
		renk = new int[n];
		for (int[] e : kenarlar) { graf[e[0]][e[1]] = 1; graf[e[1]][e[0]] = 1; }
	}

	public static void main(String[] args) {
		// Kod izleme: bes dugum, m = 3
		kur(5, 3, new int[][]{{0, 1}, {0, 3}, {1, 3}, {1, 4}, {2, 4}, {3, 4}});
		System.out.println(boya(0) + " " + Arrays.toString(renk));   // true [1, 2, 2, 3, 1]

		// Alti dugumlu cizge, m = 3
		kur(6, 3, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {1, 4}, {3, 4}, {2, 5}, {4, 5}, {0, 5}});
		System.out.println(boya(0) + " " + Arrays.toString(renk));   // true [1, 2, 2, 3, 1, 3]

		// Ayni cizge iki renkle boyanamaz
		kur(6, 2, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {1, 4}, {3, 4}, {2, 5}, {4, 5}, {0, 5}});
		System.out.println(boya(0));   // false
	}
}
