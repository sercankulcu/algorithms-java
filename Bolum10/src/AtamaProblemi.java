// Atama problemi dal sinir: i. duzeyde i. kisiye bos bir is secilir.
// Alt sinir = yeni + kalanMin[i + 1] (kalan kisilerin satir en kucukleri); en kotu durum O(n!).
public class AtamaProblemi {

	static final int INF = Integer.MAX_VALUE;
	static int[][] c = {
		{5, 8, 4},
		{7, 3, 6},
		{2, 9, 5}
	};
	static int n = c.length;
	static int[] kalanMin = new int[n + 1];   // kalanMin[i] = toplam(r >= i) min_j c[r][j]
	static boolean[] kullanildi = new boolean[n];
	static int enIyi = INF;

	static void ata(int i, int maliyet) {
		if (i == n) {                     // tam atama
			enIyi = Math.min(enIyi, maliyet);
			return;
		}
		for (int j = 0; j < n; j++) {
			if (kullanildi[j]) continue;
			int yeni = maliyet + c[i][j];
			if (yeni + kalanMin[i + 1] >= enIyi)
				continue;                     // sinir
			kullanildi[j] = true;
			ata(i + 1, yeni);
			kullanildi[j] = false;          // geri al
		}
	}

	public static void main(String[] args) {
		for (int i = n - 1; i >= 0; i--) {
			int mn = INF;
			for (int j = 0; j < n; j++) mn = Math.min(mn, c[i][j]);
			kalanMin[i] = kalanMin[i + 1] + mn;
		}
		System.out.println(java.util.Arrays.toString(kalanMin));   // [9, 5, 2, 0]
		ata(0, 0);
		System.out.println("en kucuk maliyet: " + enIyi);          // 9 (4 + 3 + 2)
	}
}

/* Beklenen cikti:
[9, 5, 2, 0]
en kucuk maliyet: 9
*/
