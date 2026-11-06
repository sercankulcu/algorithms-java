import java.util.Arrays;

// O(n!) permutasyon uretimi: k. konuma sirayla her eleman getirilir, geri kalan ozyinelemeyle dizilir.
public class Permutasyon {

	static void takas(int[] dizi, int i, int j) {
		int gecici = dizi[i];
		dizi[i] = dizi[j];
		dizi[j] = gecici;
	}

	static void permutasyon(int[] dizi, int k) {
		if (k == dizi.length) {          // bir permutasyon hazir
			System.out.println(Arrays.toString(dizi));
			return;
		}
		for (int i = k; i < dizi.length; i++) {
			takas(dizi, k, i);             // i. elemani k. konuma al
			permutasyon(dizi, k + 1);      // geri kalanlari diz
			takas(dizi, k, i);             // eski haline getir
		}
	}

	public static void main(String[] args) {
		permutasyon(new int[]{1, 2, 3}, 0);
		// [1, 2, 3]
		// [1, 3, 2]
		// [2, 1, 3]
		// [2, 3, 1]
		// [3, 2, 1]
		// [3, 1, 2]
	}
}
