import java.util.Arrays;

// Eklemeli siralama: her oge soldaki sirali kisimda yerine kaydirilarak eklenir.
// En kotu O(n^2), sirali dizide O(n); kararli ve yerinde.
public class EklemeliSiralama {

	static void eklemeliSirala(int[] dizi) {
		int n = dizi.length;
		for(int i = 1; i < n; i++) {
			int gecici = dizi[i];
			int j = i - 1;
			while(j >= 0 && dizi[j] > gecici) {
				dizi[j+1] = dizi[j];
				j = j - 1;
			}
			dizi[j+1] = gecici;
		}
	}

	public static void main(String[] args) {
		int[] a = {3, 1, 5, 2, 6, 4};
		eklemeliSirala(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] dizi = {5, 1, 9, 2, 10};
		eklemeliSirala(dizi);
		System.out.println(Arrays.toString(dizi));   // [1, 2, 5, 9, 10]
	}
}

/* Beklenen cikti:
[1, 2, 3, 4, 5, 6]
[1, 2, 5, 9, 10]
*/
