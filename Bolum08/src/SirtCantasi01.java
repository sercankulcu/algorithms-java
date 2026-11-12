// 0/1 sirt cantasi: K[i][c] = max(K[i-1][c], v_i + K[i-1][c - w_i]).
// O(n * W) zaman (sozde-polinom); tek boyutlu surum O(W) bellek, kapasite buyukten kucuge gezilir.
public class SirtCantasi01 {

	static int sirtCantasi(int[] v, int[] w, int W) {
		int n = v.length;
		int[][] K = new int[n + 1][W + 1];
		for (int i = 1; i <= n; i++)
			for (int c = 1; c <= W; c++) {
				int vi = v[i - 1], wi = w[i - 1];
				if (wi > c)            // sigmaz
					K[i][c] = K[i - 1][c];
				else                   // alma / al
					K[i][c] = Math.max(K[i - 1][c],
					            vi + K[i - 1][c - wi]);
			}
		return K[n][W];
	}

	// Tek boyutlu 0/1: satir i yalnizca satir i-1'i kullanir
	static int sirtCantasiTek(int[] v, int[] w, int W) {
		int n = v.length;
		int[] dp = new int[W + 1];
		for (int i = 0; i < n; i++)
			for (int c = W; c >= w[i]; c--)
				dp[c] = Math.max(dp[c],
				      v[i] + dp[c - w[i]]);
		return dp[W];
	}

	public static void main(String[] args) {
		int[] v = {5, 4, 3, 2}, w = {4, 3, 2, 1};
		System.out.println(sirtCantasi(v, w, 6));      // 9: esya 2, 3, 4 (4 + 3 + 2)
		System.out.println(sirtCantasiTek(v, w, 6));   // 9

		// Acgozlu (oran) 160 verir, en iyisi 220
		System.out.println(sirtCantasi(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50));   // 220
		// Resimdeki canta (15 kg): 12 kg agirligindaki esya disindaki dordu
		System.out.println(sirtCantasi(new int[]{4, 2, 1, 2, 10}, new int[]{12, 2, 1, 1, 4}, 15));   // 15
	}
}

/* Beklenen cikti:
9
9
220
15
*/
