import java.util.Arrays;

// Basitlestirilmis Timsort: MIN_PARCA boyutlu parcalar (run) eklemeli siralanir,
// sonra boyut ikiye katlanarak birlestirilir. O(n log n); kararli.
public class TimSiralama {

	static final int MIN_PARCA = 4;

	static void timSirala(int[] dizi) {
		int n = dizi.length;
		for (int i = 0; i < n; i += MIN_PARCA)            // 1) run'lar
			eklemeliSirala(dizi, i, Math.min(i + MIN_PARCA - 1, n - 1));
		for (int boyut = MIN_PARCA; boyut < n; boyut *= 2)  // 2) birlestir
			for (int sol = 0; sol < n; sol += 2 * boyut) {
				int orta = sol + boyut - 1;
				int sag = Math.min(sol + 2 * boyut - 1, n - 1);
				if (orta < sag) birlestir(dizi, sol, orta, sag);
			}
	}

	// dizi[sol..sag] araligini eklemeli siralar
	static void eklemeliSirala(int[] dizi, int sol, int sag) {
		for (int i = sol + 1; i <= sag; i++) {
			int gecici = dizi[i];
			int j = i - 1;
			while (j >= sol && dizi[j] > gecici) {
				dizi[j + 1] = dizi[j];
				j = j - 1;
			}
			dizi[j + 1] = gecici;
		}
	}

	static void birlestir(int[] A, int p, int q, int r) {
		int[] L = Arrays.copyOfRange(A, p, q + 1);
		int[] R = Arrays.copyOfRange(A, q + 1, r + 1);
		int i = 0, j = 0, k = p;
		while (i < L.length && j < R.length) {
			if (L[i] <= R[j]) A[k++] = L[i++];
			else              A[k++] = R[j++];
		}
		while (i < L.length) A[k++] = L[i++];
		while (j < R.length) A[k++] = R[j++];
	}

	public static void main(String[] args) {
		int[] dizi = {5, 21, 7, 23, 19, 10, 3, 12, 8, 1};
		timSirala(dizi);
		System.out.println(Arrays.toString(dizi));   // [1, 3, 5, 7, 8, 10, 12, 19, 21, 23]
	}
}

/* Beklenen cikti:
[1, 3, 5, 7, 8, 10, 12, 19, 21, 23]
*/
