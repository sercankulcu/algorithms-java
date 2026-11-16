import java.util.Arrays;

// Shell (kabuk) siralamasi: aralik n/2, n/4, ..., 1 ile uzak ogeler eklemeli siralanir.
// Bu aralik dizisiyle en kotu O(n^2), pratikte eklemeliden cok daha hizli.
public class KabukSiralama {

	static void kabukSirala(int[] dizi) {
		int n = dizi.length;
		for (int aralik = n / 2; aralik > 0; aralik /= 2)
			for (int i = aralik; i < n; i++) {
				int gecici = dizi[i], j;
				for (j = i; j >= aralik && dizi[j - aralik] > gecici; j -= aralik)
					dizi[j] = dizi[j - aralik];
				dizi[j] = gecici;
			}
	}

	public static void main(String[] args) {
		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		kabukSirala(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] dizi = {5, 2, 6, 1, 4, 3};
		kabukSirala(dizi);
		System.out.println(Arrays.toString(dizi));   // [1, 2, 3, 4, 5, 6]
	}
}

/* Beklenen cikti:
[1, 2, 3, 4, 5, 6, 7, 8]
[1, 2, 3, 4, 5, 6]
*/
