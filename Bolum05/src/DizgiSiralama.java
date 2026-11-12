import java.util.Arrays;

// Sozlukce (lexicographic) karsilastirma ve karsilastirmali dizgi siralama.
// karsilastir O(W); sozlukselSirala O(N^2) karsilastirma x O(W) = O(N^2 * W).
public class DizgiSiralama {

	// Negatif: a once, pozitif: b once, 0: esit (String.compareTo gibi)
	static int karsilastir(String a, String b) {
		int n = Math.min(a.length(), b.length());
		for (int k = 0; k < n; k++) {
			char x = a.charAt(k), y = b.charAt(k);
			if (x != y)
				return x - y;   // ilk farkli karakter
		}
		return a.length() - b.length();
	}

	static void sozlukselSirala(String[] a) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (a[i].compareTo(a[j]) > 0) {
					String gecici = a[i];
					a[i] = a[j];
					a[j] = gecici;
				}
	}

	public static void main(String[] args) {
		System.out.println(karsilastir("apple", "apricot"));  // -2
		System.out.println(karsilastir("car", "cart"));       // -1

		String[] a = {"eabcdf", "abcde", "bcdea", "cdeab", "deabcg"};
		sozlukselSirala(a);
		System.out.println(Arrays.toString(a));  // [abcde, bcdea, cdeab, deabcg, eabcdf]

		String[] b = {"banana", "orange", "grape", "apple", "kiwi"};
		sozlukselSirala(b);
		System.out.println(Arrays.toString(b));  // [apple, banana, grape, kiwi, orange]
	}
}

/* Beklenen cikti:
-2
-1
[abcde, bcdea, cdeab, deabcg, eabcdf]
[apple, banana, grape, kiwi, orange]
*/
