import java.util.Arrays;

// Yigin siralamasi (heap sort): once O(n) surede max-heap kurulur, sonra kok n - 1 kez sona alinir.
// Her durumda O(n log n); yerinde, kararsiz.
public class YiginSiralama {

	static void takas(int[] dizi, int i, int j) {
		int gecici = dizi[i];
		dizi[i] = dizi[j];
		dizi[j] = gecici;
	}

	static void yiginSirala(int[] dizi) {
		int n = dizi.length;
		for (int i = n / 2 - 1; i >= 0; i--)   // 1) yigin kur
			yiginlastir(dizi, n, i);
		for (int i = n - 1; i > 0; i--) {      // 2) sirala
			takas(dizi, 0, i);                   // en buyugu sona
			yiginlastir(dizi, i, 0);             // koku suz
		}
	}

	static void yiginlastir(int[] dizi, int n, int i) {
		int enBuyuk = i, sol = 2 * i + 1, sag = 2 * i + 2;
		if (sol < n && dizi[sol] > dizi[enBuyuk]) enBuyuk = sol;
		if (sag < n && dizi[sag] > dizi[enBuyuk]) enBuyuk = sag;
		if (enBuyuk != i) {
			takas(dizi, i, enBuyuk);
			yiginlastir(dizi, n, enBuyuk);
		}
	}

	public static void main(String[] args) {
		int[] dizi = {6, 5, 3, 1, 8, 7, 2, 4};
		int n = dizi.length;
		for (int i = n / 2 - 1; i >= 0; i--)      // yalnizca heap kurma
			yiginlastir(dizi, n, i);
		System.out.println(Arrays.toString(dizi));   // [8, 6, 7, 4, 5, 3, 2, 1]

		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		yiginSirala(a);
		System.out.println(Arrays.toString(a));      // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] b = {3, 9, 2, 1, 4, 5};
		for (int i = b.length / 2 - 1; i >= 0; i--)
			yiginlastir(b, b.length, i);
		System.out.println(Arrays.toString(b));      // [9, 4, 5, 1, 3, 2]
	}
}

/* Beklenen cikti:
[8, 6, 7, 4, 5, 3, 2, 1]
[1, 2, 3, 4, 5, 6, 7, 8]
[9, 4, 5, 1, 3, 2]
*/
