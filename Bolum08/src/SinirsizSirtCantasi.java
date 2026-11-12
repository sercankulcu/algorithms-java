// Sinirsiz sirt cantasi: her esyadan istenildigi kadar alinabilir.
// dp[c] = max_j (dp[c - w_j] + v_j), kapasite kucukten buyuge; O(n * W) zaman, O(W) bellek.
public class SinirsizSirtCantasi {

	static int sinirsizSirtCantasi(int[] w, int[] v, int W) {
		int[] dp = new int[W + 1];       // dp[c]: c kapasiteyle en iyi
		for (int c = 1; c <= W; c++)
			for (int j = 0; j < w.length; j++)
				if (w[j] <= c)
					dp[c] = Math.max(dp[c], dp[c - w[j]] + v[j]);
		return dp[W];
	}

	public static void main(String[] args) {
		int[] w = {1, 3, 4, 5}, v = {15, 50, 60, 90};
		System.out.println(sinirsizSirtCantasi(w, v, 8));   // 140: 50 + 90 (3 kg + 5 kg)
		for (int W = 0; W <= 8; W++) System.out.print(sinirsizSirtCantasi(w, v, W) + " ");
		System.out.println();   // 0 15 30 50 65 90 105 120 140
	}
}

/* Beklenen cikti:
140
0 15 30 50 65 90 105 120 140
*/
