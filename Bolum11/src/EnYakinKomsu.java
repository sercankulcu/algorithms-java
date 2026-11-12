import java.util.Arrays;

// Gezgin satici icin en yakin komsu sezgiseli: her adimda ziyaret edilmemis en yakin sehre git.
// O(n^2) zaman; sabit bir yaklasim orani yoktur (metrik TSP'de bile Theta(log n) olabilir).
public class EnYakinKomsu {

	static int[] enYakinKomsu(int[][] d, int bas) {
		int n = d.length, toplam = 0, simdiki = bas;
		boolean[] ziyaret = new boolean[n];
		int[] tur = new int[n + 1];
		tur[0] = bas; ziyaret[bas] = true;
		for (int k = 1; k < n; k++) {
			int enYakin = -1, enAz = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++)
				if (!ziyaret[j] && d[simdiki][j] < enAz) {
					enAz = d[simdiki][j]; enYakin = j; }
			// en yakin sehre git
			tur[k] = enYakin; ziyaret[enYakin] = true;
			toplam += enAz; simdiki = enYakin;
		}
		// baslangica don
		tur[n] = bas; toplam += d[simdiki][bas];
		return tur;
	}

	static int uzunluk(int[][] d, int[] tur) {
		int t = 0;
		for (int i = 0; i + 1 < tur.length; i++) t += d[tur[i]][tur[i + 1]];
		return t;
	}

	// Kaba kuvvet: 0'dan baslayan tum turlar, en kisasi
	static int enKisa(int[][] d, int[] yol, int k, boolean[] kullan) {
		int n = d.length;
		if (k == n) return uzunluk(d, yol) + d[yol[n - 1]][yol[0]];
		int enIyi = Integer.MAX_VALUE;
		for (int s = 1; s < n; s++) {
			if (kullan[s]) continue;
			kullan[s] = true; yol[k] = s;
			enIyi = Math.min(enIyi, enKisa(d, yol, k + 1, kullan));
			kullan[s] = false;
		}
		return enIyi;
	}

	public static void main(String[] args) {
		int[][] d = {
			{0, 10, 15, 20, 25},
			{10, 0, 35, 25, 30},
			{15, 35, 0, 30, 35},
			{20, 25, 30, 0, 40},
			{25, 30, 35, 40, 0}
		};
		int[] tur = enYakinKomsu(d, 0);
		System.out.println(Arrays.toString(tur));            // [0, 1, 3, 2, 4, 0]
		System.out.println("uzunluk: " + uzunluk(d, tur));   // 125
		int[] yol = new int[d.length];
		boolean[] kullan = new boolean[d.length];
		System.out.println("optimum: " + enKisa(d, yol, 1, kullan));   // 125
	}
}

/* Beklenen cikti:
[0, 1, 3, 2, 4, 0]
uzunluk: 125
optimum: 125
*/
