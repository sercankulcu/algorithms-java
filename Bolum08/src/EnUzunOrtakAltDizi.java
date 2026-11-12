// En uzun ortak alt dizi (LCS): eslesme -> capraz + 1, aksi halde max(ust, sol); cevap son hucre.
// Satirlar y, sutunlar x; O(m * n) zaman ve bellek.
public class EnUzunOrtakAltDizi {

	static int lcs(String x, String y) {
		int m = x.length(), n = y.length();
		int[][] L = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (y.charAt(i - 1) == x.charAt(j - 1))
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j],
					                   L[i][j - 1]);
		return L[n][m];
	}

	public static void main(String[] args) {
		System.out.println(lcs("abdca", "cbda"));         // 3: "bda"
		System.out.println(lcs("AGGTAB", "GXTXAYB"));     // 4: "GTAB"
		System.out.println(lcs("ACCGGTGGACAATTCA", "GGAAAGAGATATGCAC"));   // 9: "GGAAATTCA"
	}
}

/* Beklenen cikti:
3
4
9
*/
