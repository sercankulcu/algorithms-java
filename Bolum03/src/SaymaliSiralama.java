import java.util.Arrays;

// Saymali siralama (counting sort): karsilastirma yok; anahtarlar sayilir, onek toplamlariyla yerlestirilir.
// Theta(n + k) zaman ve bellek (k: en buyuk anahtar); sondan basa gezildigi icin kararli.
public class SaymaliSiralama {

	static int enBuyuk(int[] dizi) {
		int maks = dizi[0];
		for (int x : dizi)
			if (x > maks) maks = x;
		return maks;
	}

	static int[] saymaliSirala(int[] dizi) {
		int maks = enBuyuk(dizi);
		int[] sayac = new int[maks + 1];
		for (int x : dizi) sayac[x]++;
		for (int i = 1; i <= maks; i++) sayac[i] += sayac[i - 1];
		int[] cikti = new int[dizi.length];
		for (int i = dizi.length - 1; i >= 0; i--) {
			cikti[sayac[dizi[i]] - 1] = dizi[i];
			sayac[dizi[i]]--;
		}
		return cikti;
	}

	// Sunumdaki sozde kod (anahtarlar 1..k, diziler 1'den baslar); Java'da A[0], B[0], C[0] kullanilmaz.
	static int[] saymaliSiralaSozde(int[] A, int n, int k) {
		int[] B = new int[n + 1];
		int[] C = new int[k + 1];
		for (int i = 1; i <= k; i++)
			C[i] = 0;
		for (int j = 1; j <= n; j++)
			C[A[j]] = C[A[j]] + 1;   // C[i] = |{anahtar = i}|
		for (int i = 2; i <= k; i++)
			C[i] = C[i] + C[i - 1];  // C[i] = |{anahtar <= i}|
		for (int j = n; j >= 1; j--) {
			B[C[A[j]]] = A[j];
			C[A[j]] = C[A[j]] - 1;
		}
		return B;
	}

	public static void main(String[] args) {
		int[] dizi = {4, 1, 3, 4, 3};
		System.out.println(Arrays.toString(saymaliSirala(dizi)));   // [1, 3, 3, 4, 4]

		int[] A = {0, 4, 1, 3, 4, 3};               // A[1..5]
		int[] B = saymaliSiralaSozde(A, 5, 4);
		System.out.println(Arrays.toString(Arrays.copyOfRange(B, 1, 6)));   // [1, 3, 3, 4, 4]
	}
}

/* Beklenen cikti:
[1, 3, 3, 4, 4]
[1, 3, 3, 4, 4]
*/
