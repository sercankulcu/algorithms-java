import java.util.Arrays;

// Birlestirmeli siralama (merge sort): bol, iki yariyi ozyinelemeyle sirala, birlestir.
// T(n) = 2T(n/2) + Theta(n) -> her durumda Theta(n log n); kararli, O(n) ek bellek.
public class BirlestirmeliSiralama {

	static void birlestirmeliSirala(int[] dizi, int sol, int sag) {
		if (sol < sag) {
			int orta = sol + (sag - sol) / 2;
			birlestirmeliSirala(dizi, sol, orta);
			birlestirmeliSirala(dizi, orta + 1, sag);
			birlestir(dizi, sol, orta, sag);
		}
	}

	// Sirali A[p..q] ve A[q+1..r] parcalarini birlestirir: Theta(r - p + 1)
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
		int[] dizi = {6, 5, 3, 1, 8, 7, 2, 4};
		birlestirmeliSirala(dizi, 0, dizi.length - 1);
		System.out.println(Arrays.toString(dizi));   // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] A = {1, 3, 5, 6, 2, 4, 7, 8};         // L = [1, 3, 5, 6], R = [2, 4, 7, 8]
		birlestir(A, 0, 3, 7);
		System.out.println(Arrays.toString(A));      // [1, 2, 3, 4, 5, 6, 7, 8]
	}
}
