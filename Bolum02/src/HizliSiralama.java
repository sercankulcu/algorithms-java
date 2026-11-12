import java.util.Arrays;

// Hizli siralama (quicksort): pivot etrafinda parcala, iki tarafi ozyinelemeyle sirala.
// Ortalama Theta(n log n), en kotu (sirali girdi) Theta(n^2); yerinde, kararsiz.
public class HizliSiralama {

	static void takas(int[] dizi, int i, int j) {
		int gecici = dizi[i];
		dizi[i] = dizi[j];
		dizi[j] = gecici;
	}

	// Lomuto bolumlemesi, pivot = son oge
	static int parcala(int[] dizi, int alt, int ust) {
		int pivot = dizi[ust];
		int i = alt - 1;
		for (int j = alt; j < ust; j++) {
			if (dizi[j] <= pivot) {
				i++;
				takas(dizi, i, j);
			}
		}
		takas(dizi, i + 1, ust);
		return i + 1;
	}

	static void hizliSirala(int[] dizi, int alt, int ust) {
		if (alt < ust) {
			int p = parcala(dizi, alt, ust);
			hizliSirala(dizi, alt, p - 1);
			hizliSirala(dizi, p + 1, ust);
		}
	}

	// Sozde kod PARCALA(A, p, q): pivot x = A[p] (ilk oge)
	static int PARCALA(int[] A, int p, int q) {
		int x = A[p];            // pivot
		int i = p;
		for (int j = p + 1; j <= q; j++) {
			if (A[j] <= x) {
				i = i + 1;
				takas(A, i, j);
			}
		}
		takas(A, p, i);
		return i;
	}

	// Sozde kod HIZLI-SIRALA(A, p, r); Java'da indisler 0'dan baslar: ilk cagri (A, 0, n - 1)
	static void HIZLI_SIRALA(int[] A, int p, int r) {
		if (p < r) {
			int q = PARCALA(A, p, r);
			HIZLI_SIRALA(A, p, q - 1);
			HIZLI_SIRALA(A, q + 1, r);
		}
	}

	public static void main(String[] args) {
		int[] dizi = {2, 8, 7, 1, 3, 5, 6, 4};
		System.out.println(parcala(dizi, 0, dizi.length - 1));   // 3
		System.out.println(Arrays.toString(dizi));               // [2, 1, 3, 4, 7, 5, 6, 8]

		int[] d = {5, 2, 8, 1, 9, 3};
		hizliSirala(d, 0, d.length - 1);
		System.out.println(Arrays.toString(d));                  // [1, 2, 3, 5, 8, 9]

		int[] A = {6, 10, 13, 5, 8, 3, 2, 11};
		System.out.println(PARCALA(A, 0, A.length - 1));         // 3
		System.out.println(Arrays.toString(A));                  // [2, 5, 3, 6, 8, 13, 10, 11]

		int[] B = {6, 10, 13, 5, 8, 3, 2, 11};
		HIZLI_SIRALA(B, 0, B.length - 1);
		System.out.println(Arrays.toString(B));                  // [2, 3, 5, 6, 8, 10, 11, 13]
	}
}

/* Beklenen cikti:
3
[2, 1, 3, 4, 7, 5, 6, 8]
[1, 2, 3, 5, 8, 9]
3
[2, 5, 3, 6, 8, 13, 10, 11]
[2, 3, 5, 6, 8, 10, 11, 13]
*/
