// En uzun artan alt dizi (LIS): O(n^2) DP ve O(n log n) sonlar dizisi + ikili arama.
// dp[i] = 1 + max{ dp[j] : j < i, a[j] < a[i] }; cevap max dp[i].
public class EnUzunArtanAltDizi {

	static int lis(int[] a) {
		int n = a.length, enUzun = 0;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;               // tek basina
			for (int j = 0; j < i; j++)
				if (a[j] < a[i] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;   // uzat
			enUzun = Math.max(enUzun, dp[i]);
		}
		return enUzun;
	}

	static int lisHizli(int[] a) {
		int[] son = new int[a.length];
		int uz = 0;                // aktif liste sayisi
		for (int x : a) {
			int alt = 0, ust = uz;   // son[alt] >= x ilk yer
			while (alt < ust) {
				int orta = (alt + ust) / 2;
				if (son[orta] < x) alt = orta + 1;
				else ust = orta;
			}
			son[alt] = x;            // yerine koy / uzat
			if (alt == uz) uz++;
		}
		return uz;
	}

	public static void main(String[] args) {
		int[] a = {5, 2, 8, 6, 3, 6, 9, 7};
		System.out.println(lis(a));        // 4: ornek 2, 3, 6, 9
		System.out.println(lisHizli(a));   // 4

		int[] b = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		System.out.println(lis(b));        // 6: ornek 0, 2, 6, 9, 11, 15
		System.out.println(lisHizli(b));   // 6
	}
}

/* Beklenen cikti:
4
4
6
6
*/
