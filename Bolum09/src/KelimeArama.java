// Kelime arama: izgarada komsu (yukari/asagi/sol/sag) hucrelerden gecerek kelime olusturulabilir mi?
// Her hucre bir yolda en fazla bir kez; geri donuste ziyaret isareti kaldirilir. O(R * C * 4^L).
public class KelimeArama {

	static int[] ds = {-1, 1, 0, 0};   // yukari, asagi, sol, sag
	static int[] dk = {0, 0, -1, 1};

	static boolean ara(char[][] iz, String kelime, int i,
	            int s, int k, boolean[][] ziyaret) {
		if (i == kelime.length()) return true;
		if (s < 0 || s >= iz.length || k < 0
		    || k >= iz[0].length || ziyaret[s][k]
		    || iz[s][k] != kelime.charAt(i))
			return false;
		ziyaret[s][k] = true;
		for (int d = 0; d < 4; d++)
			if (ara(iz, kelime, i + 1, s + ds[d],
			        k + dk[d], ziyaret))
				return true;
		ziyaret[s][k] = false;   // geri al
		return false;
	}

	// Dis dongu: her hucre baslangic olarak denenir (satir satir)
	static boolean varMi(char[][] iz, String kelime) {
		for (int s = 0; s < iz.length; s++)
			for (int k = 0; k < iz[0].length; k++)
				if (ara(iz, kelime, 0, s, k, new boolean[iz.length][iz[0].length]))
					return true;
		return false;
	}

	public static void main(String[] args) {
		char[][] iz = {
			"ABCE".toCharArray(),
			"SFCS".toCharArray(),
			"ADEE".toCharArray()
		};
		System.out.println(varMi(iz, "ABCCED"));   // true
		System.out.println(varMi(iz, "SEE"));      // true
		System.out.println(varMi(iz, "ABCB"));     // false: B iki kez kullanilamaz
		System.out.println(varMi(iz, "SADE"));     // true
	}
}
