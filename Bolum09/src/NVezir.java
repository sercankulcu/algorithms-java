import java.util.Arrays;

// n-Vezir: her satira bir vezir; sutun ve iki capraz kontrol edilir, cikmazda geri alinir.
// guvenliMi O(n); en kotu durumda O(n!) dal. Sutunlar kucukten buyuge: sozluksel ilk cozum.
public class NVezir {

	static int n;

	static boolean guvenliMi(int[][] t,
	        int s, int k) {
		// ayni sutun
		for (int i = 0; i < s; i++)
			if (t[i][k] == 1) return false;
		// sol ust capraz
		for (int i = s-1, j = k-1;
		     i >= 0 && j >= 0; i--, j--)
			if (t[i][j] == 1) return false;
		// sag ust capraz
		for (int i = s-1, j = k+1;
		     i >= 0 && j < n; i--, j++)
			if (t[i][j] == 1) return false;
		return true;
	}

	static boolean coz(int[][] t, int satir) {
		if (satir == n)
			return true;
		for (int sutun = 0; sutun < n;
		     sutun++) {
			if (guvenliMi(t, satir, sutun)) {
				t[satir][sutun] = 1; // yerlestir
				if (coz(t, satir + 1))
					return true;
				t[satir][sutun] = 0; // geri al
			}
		}
		return false;
	}

	// Her satirdaki vezirin sutunu
	static int[] sutunlar(int[][] t) {
		int[] y = new int[n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (t[i][j] == 1) y[i] = j;
		return y;
	}

	public static void main(String[] args) {
		n = 4;
		int[][] t = new int[n][n];
		System.out.println(coz(t, 0) + " " + Arrays.toString(sutunlar(t)));   // true [1, 3, 0, 2]

		n = 8;
		t = new int[n][n];
		System.out.println(coz(t, 0) + " " + Arrays.toString(sutunlar(t)));   // true [0, 4, 7, 5, 2, 6, 1, 3]

		n = 3;
		System.out.println(coz(new int[n][n], 0));   // false
	}
}

/* Beklenen cikti:
true [1, 3, 0, 2]
true [0, 4, 7, 5, 2, 6, 1, 3]
false
*/
