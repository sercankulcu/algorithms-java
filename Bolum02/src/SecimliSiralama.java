import java.util.Arrays;

// Secimli siralama: her turda sirasiz kismin en kucugu bulunup basa alinir.
// Her durumda O(n^2) karsilastirma, en fazla n - 1 takas.
public class SecimliSiralama {

	public void sirala(int[] dizi) {
		int n = dizi.length;
		for(int i = 0; i < n - 1; i++) {
			int enKucuk = i;
			for(int j = i + 1; j < n; j++) {
				if(dizi[j] < dizi[enKucuk]) {
					enKucuk = j;
				}
			}
			int gecici = dizi[enKucuk];
			dizi[enKucuk] = dizi[i];
			dizi[i] = gecici;
		}
	}

	public static void main(String[] args) {
		SecimliSiralama s = new SecimliSiralama();
		int[] a = {3, 1, 5, 2, 6, 4};
		s.sirala(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] dizi = {5, 1, 10, 2, 9};
		s.sirala(dizi);
		System.out.println(Arrays.toString(dizi));   // [1, 2, 5, 9, 10]
	}
}
