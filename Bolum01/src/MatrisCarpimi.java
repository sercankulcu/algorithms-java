import java.util.Arrays;

// O(n^3) matris carpimi: n^2 ogenin her biri icin n carpma.
public class MatrisCarpimi {

	static int[][] matrisCarp(int[][] A, int[][] B) {
		int n = A.length, m = B[0].length, p = B.length;
		int[][] sonuc = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				int eleman = 0;
				for (int k = 0; k < p; k++)
					eleman += A[i][k] * B[k][j];
				sonuc[i][j] = eleman;
			}
		return sonuc;
	}

	public static void main(String[] args) {
		int[][] A = {{1, 2}, {3, 4}};
		int[][] B = {{5, 6}, {7, 8}};
		System.out.println(Arrays.deepToString(matrisCarp(A, B)));   // [[19, 22], [43, 50]]
	}
}

/* Beklenen cikti:
[[19, 22], [43, 50]]
*/
