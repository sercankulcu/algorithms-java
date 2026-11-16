import java.util.Arrays;

// Kabarcik siralamasi: komsu ciftler karsilastirilir, buyuk olan saga kayar.
// En kotu O(n^2); yerDegistiMi bayragi sayesinde sirali dizide O(n) (erken durma).
public class KabarcikSiralama {

	static void kabarcikSirala(int[] dizi) {
		int n = dizi.length;
		boolean yerDegistiMi;
		for(int i = 0; i < n - 1; i++) {
			yerDegistiMi = false;
			for(int j = 0; j < n - 1 - i; j++) {
				if(dizi[j] > dizi[j+1]) {
					int gecici = dizi[j];
					dizi[j] = dizi[j+1];
					dizi[j+1] = gecici;
					yerDegistiMi = true;
				}
			}
			if(yerDegistiMi == false) break;
		}
	}

	public static void main(String[] args) {
		int[] a = {3, 1, 5, 2, 6, 4};
		kabarcikSirala(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] dizi = {5, 1, 9, 2, 10};
		kabarcikSirala(dizi);
		System.out.println(Arrays.toString(dizi));   // [1, 2, 5, 9, 10]
	}
}

/* Beklenen cikti:
[1, 2, 3, 4, 5, 6]
[1, 2, 5, 9, 10]
*/
