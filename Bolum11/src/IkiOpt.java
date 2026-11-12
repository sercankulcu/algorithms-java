import java.util.Arrays;

// 2-opt yerel arama: iki kenar cikarilir, uclar capraz baglanir (aradaki yol ters cevrilir).
// Bir tarama O(n^2) aday hamle; iyilestiren hamle kalmayinca tur 2-opt yerel optimumudur.
public class IkiOpt {

	static void ikiOpt(int[] tur, double[][] d) {
		int n = tur.length;
		boolean iyilesti = true;
		while (iyilesti) {
			iyilesti = false;
			for (int i = 0; i < n - 1; i++)
				for (int j = i + 2; j < n; j++) {
					int a = tur[i], b = tur[i + 1];
					int c = tur[j], e = tur[(j + 1) % n];
					double fark = d[a][c] + d[b][e]
					            - d[a][b] - d[c][e];
					if (fark < -1e-9) {   // kisaliyor
						tersCevir(tur, i + 1, j);
						iyilesti = true;
					}
				}
		}
	}

	// tur[i..j] alt dizisini ters cevir
	static void tersCevir(int[] tur, int i, int j) {
		while (i < j) {
			int t = tur[i]; tur[i] = tur[j]; tur[j] = t;
			i++; j--;
		}
	}

	static double uzunluk(int[] tur, double[][] d) {
		double t = 0;
		for (int i = 0; i < tur.length; i++) t += d[tur[i]][tur[(i + 1) % tur.length]];
		return t;
	}

	public static void main(String[] args) {
		// Noktalar: 0 (0,0), 1 (3,4), 2 (3,0), 3 (0,4)
		double[][] nokta = {{0, 0}, {3, 4}, {3, 0}, {0, 4}};
		int n = nokta.length;
		double[][] d = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				d[i][j] = Math.hypot(nokta[i][0] - nokta[j][0], nokta[i][1] - nokta[j][1]);
		int[] tur = {0, 1, 2, 3};
		System.out.println("baslangic: " + uzunluk(tur, d));   // 18.0
		ikiOpt(tur, d);
		System.out.println(Arrays.toString(tur));              // [0, 2, 1, 3]
		System.out.println("2-opt: " + uzunluk(tur, d));       // 14.0
	}
}

/* Beklenen cikti:
baslangic: 18.0
[0, 2, 1, 3]
2-opt: 14.0
*/
