// Alt kume toplami: dp[i][j] = ilk i ogeyle toplam j elde edilebilir mi?
// O(n * hedef) zaman ve bellek (sozde-polinom); problem NP-tam.
public class AltKumeToplami {

	static boolean altKume(int[] a, int hedef) {
		int n = a.length;
		boolean[][] dp = new boolean[n + 1][hedef + 1];
		for (int i = 0; i <= n; i++)
			dp[i][0] = true;           // bos kume
		for (int i = 1; i <= n; i++) {
			int x = a[i - 1];
			for (int j = 1; j <= hedef; j++)
				dp[i][j] = dp[i - 1][j]  // x alinmaz
					|| (x <= j && dp[i - 1][j - x]);
		}
		return dp[n][hedef];
	}

	public static void main(String[] args) {
		System.out.println(altKume(new int[]{10, 0, 5, 8, 6, 2, 4}, 15));   // true: {10, 5}
		System.out.println(altKume(new int[]{3, 34, 4, 12, 5, 2}, 9));      // true: {4, 5}
		System.out.println(altKume(new int[]{3, 34, 4, 12, 5, 2}, 30));     // false
	}
}

/* Beklenen cikti:
true
true
false
*/
