import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// Veri paralelligi: C = A B, her satir ayri bir gorev; her gorev yalnizca kendi satirina yazar (yaris yok).
// Is W = O(n^3), satir basina derinlik D = O(n^2).
public class ParalelMatrisCarpimi {

	static int n = 3;
	static int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	static int[][] B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
	static int[][] C = new int[n][n];

	// her satir ayri bir gorev
	static class SatirGorevi extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private final int satir;
		SatirGorevi(int satir) { this.satir = satir; }

		protected void compute() {
			for (int j = 0; j < n; j++) {
				C[satir][j] = 0;
				for (int k = 0; k < n; k++)
					C[satir][j] += A[satir][k] * B[k][j];
			}
		}
	}

	public static void main(String[] args) {
		try (var havuz = new ForkJoinPool()) {
			for (int i = 0; i < n; i++)
				havuz.submit(new SatirGorevi(i));
		}   // close(): tum gorevleri bekler
		for (int[] satir : C) System.out.println(Arrays.toString(satir));
		// [30, 24, 18]
		// [84, 69, 54]
		// [138, 114, 90]
	}
}
