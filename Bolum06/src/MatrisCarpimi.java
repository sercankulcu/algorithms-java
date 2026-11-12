import java.util.Arrays;

// Klasik matris carpimi: n^2 hucrenin her biri n carpim -> Theta(n^3).
// Ornek: Strassen adim adim slaytindaki 2 x 2 matrisler.
public class MatrisCarpimi {

	static int[][] carp(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}

	public static void main(String[] args) {
		int[][] A = {{1, 3}, {7, 5}};
		int[][] B = {{6, 8}, {4, 2}};
		System.out.println(Arrays.deepToString(carp(A, B)));   // [[18, 14], [62, 66]]
	}
}

/* Beklenen cikti:
[[18, 14], [62, 66]]
*/
