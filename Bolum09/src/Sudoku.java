// Sudoku: ilk bos hucreye 1-9 sirayla denenir; satir, sutun ve 3x3 kutu kontrol edilir, cikmazda geri alinir.
// Kaba kuvvet 9^51 atama; budamayla bu bulmacada birkac bin yerlestirme.
public class Sudoku {

	static boolean coz(int[][] t) {
		for (int s = 0; s < 9; s++)
			for (int k = 0; k < 9; k++)
				if (t[s][k] == 0) {
					for (int r = 1; r <= 9; r++)
						if (gecerliMi(t, s, k, r)) {
							t[s][k] = r;
							if (coz(t)) return true;
							t[s][k] = 0;   // geri al
						}
					return false;  // rakam yok
				}
		return true;  // bos hucre yok
	}

	static boolean gecerliMi(int[][] t,
	    int s, int k, int r) {
		for (int i = 0; i < 9; i++)
			if (t[s][i] == r || t[i][k] == r)
				return false;
		int bs = s / 3 * 3;
		int bk = k / 3 * 3;
		for (int i = bs; i < bs + 3; i++)
			for (int j = bk; j < bk + 3; j++)
				if (t[i][j] == r)
					return false;
		return true;
	}

	public static void main(String[] args) {
		String[] bulmaca = {      // 30 ipucu, 51 bos hucre (0)
			"530070000",
			"600195000",
			"098000060",
			"800060003",
			"400803001",
			"700020006",
			"060000280",
			"000419005",
			"000080079"
		};
		int[][] t = new int[9][9];
		for (int s = 0; s < 9; s++)
			for (int k = 0; k < 9; k++)
				t[s][k] = bulmaca[s].charAt(k) - '0';
		System.out.println(coz(t));   // true
		for (int[] satir : t) {
			StringBuilder sb = new StringBuilder();
			for (int x : satir) sb.append(x);
			System.out.println(sb);
		}
		// 534678912
		// 672195348
		// 198342567
		// 859761423
		// 426853791
		// 713924856
		// 961537284
		// 287419635
		// 345286179
	}
}

/* Beklenen cikti:
true
534678912
672195348
198342567
859761423
426853791
713924856
961537284
287419635
345286179
*/
