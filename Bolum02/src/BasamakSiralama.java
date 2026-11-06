import java.util.Arrays;

// Basamak siralamasi (radix sort, LSD): en anlamsiz basamaktan baslayarak her basamaga gore
// kararli saymali siralama. d basamak icin Theta(d (n + 10)).
public class BasamakSiralama {

	static int enBuyuk(int[] dizi) {
		int maks = dizi[0];
		for (int x : dizi)
			if (x > maks) maks = x;
		return maks;
	}

	static int[] basamakSirala(int[] dizi) {
		int maks = enBuyuk(dizi);
		for (int us = 1; maks / us > 0; us *= 10)
			dizi = basamagaGoreSay(dizi, us);
		return dizi;
	}

	static int[] basamagaGoreSay(int[] dizi, int us) {
		int n = dizi.length;
		int[] cikti = new int[n], sayac = new int[10];
		for (int x : dizi) sayac[(x / us) % 10]++;
		for (int i = 1; i < 10; i++) sayac[i] += sayac[i - 1];
		for (int i = n - 1; i >= 0; i--) {
			int b = (dizi[i] / us) % 10;
			cikti[--sayac[b]] = dizi[i];
		}
		return cikti;
	}

	public static void main(String[] args) {
		int[] dizi = {170, 45, 75, 90, 802, 24, 2, 66};
		System.out.println(Arrays.toString(basamakSirala(dizi)));
		// [2, 24, 45, 66, 75, 90, 170, 802]

		int[] d = {32, 15, 41, 23, 11};
		System.out.println(Arrays.toString(basamagaGoreSay(d, 1)));   // [41, 11, 32, 23, 15]
	}
}
