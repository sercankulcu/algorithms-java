import java.util.Arrays;

// Hamilton yolu: her dugum tam bir kez; yeni dugum son dugume komsu ve yolda olmamali.
// En kotu durum O(n!) yol, uygunMu O(n) -> O(n * n!).
public class HamiltonYolu {

	static boolean bul(int[][] graf, int[] yol, int konum) {
		if (konum == graf.length) return true;
		for (int v = 0; v < graf.length; v++) {
			if (uygunMu(v, graf, yol, konum)) {
				yol[konum] = v;
				if (bul(graf, yol, konum + 1)) return true;
				yol[konum] = -1;     // geri al
			}
		}
		return false;
	}

	static boolean uygunMu(int v, int[][] graf,
	                int[] yol, int konum) {
		if (graf[yol[konum - 1]][v] == 0) return false;
		for (int i = 0; i < konum; i++)
			if (yol[i] == v) return false;
		return true;
	}

	static int[][] cizge(int n, int[][] kenarlar) {
		int[][] g = new int[n][n];
		for (int[] e : kenarlar) { g[e[0]][e[1]] = 1; g[e[1]][e[0]] = 1; }
		return g;
	}

	// yol[0] = 0 ile baslanir, bul(graf, yol, 1) cagrilir
	static void dene(int[][] graf) {
		int[] yol = new int[graf.length];
		Arrays.fill(yol, -1);
		yol[0] = 0;
		System.out.println(bul(graf, yol, 1) + " " + Arrays.toString(yol));
	}

	public static void main(String[] args) {
		dene(cizge(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {3, 4}}));   // true [0, 1, 4, 3, 2]
		dene(cizge(5, new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}));   // true [0, 1, 3, 2, 4]
	}
}

/* Beklenen cikti:
true [0, 1, 4, 3, 2]
true [0, 1, 3, 2, 4]
*/
