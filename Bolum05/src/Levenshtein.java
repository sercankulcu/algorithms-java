// Levenshtein (duzenleme) uzakligi: ekleme, silme, degistirme sayisinin en azi; dinamik programlama.
// Zaman ve bellek O(m * n).
public class Levenshtein {

	static int levenshtein(String a, String b) {
		int m = a.length(), n = b.length();
		int[][] D = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) D[i][0] = i;  // silme
		for (int j = 0; j <= n; j++) D[0][j] = j;  // ekleme
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++)
				if (a.charAt(i - 1) == b.charAt(j - 1))
					D[i][j] = D[i - 1][j - 1];            // esit
				else
					D[i][j] = 1 + Math.min(D[i - 1][j - 1], // degistir
						Math.min(D[i][j - 1],         // ekle
							D[i - 1][j]));       // sil
		return D[m][n];
	}

	public static void main(String[] args) {
		System.out.println(levenshtein("kitten", "sitting"));  // 3
		System.out.println(levenshtein("ercan", "sercan"));    // 1
		System.out.println(levenshtein("apple", "banana"));    // 5
		System.out.println(levenshtein("kar", "ark"));         // 2
	}
}
