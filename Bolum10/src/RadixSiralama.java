import java.util.Arrays;

// LSD basamak siralamasi (radix sort): sagdan sola her karakter konumunda kararli sayarak siralama.
// Bir gecis O(N + R), R = 256; W gecis -> O(W * (N + R)). Karsilastirma yapmaz.
public class RadixSiralama {

	// En uzun dizginin uzunlugu (W)
	static int enUzun(String[] a) {
		int W = 0;
		for (String s : a) W = Math.max(W, s.length());
		return W;
	}

	static void radixSirala(String[] a) {
		int W = enUzun(a);               // en uzun dizgi
		for (int d = W - 1; d >= 0; d--)
			sayarakSirala(a, d);           // sagdan sola
	}

	static void sayarakSirala(String[] a, int d) {
		int[] say = new int[256];        // ASCII
		for (String s : a) say[kar(s, d)]++;
		for (int r = 1; r < 256; r++) say[r] += say[r - 1];
		String[] gec = new String[a.length];
		for (int i = a.length - 1; i >= 0; i--)   // kararli
			gec[--say[kar(a[i], d)]] = a[i];
		System.arraycopy(gec, 0, a, 0, a.length);
	}

	static int kar(String s, int d) {         // kisa dizgi -> 0
		return d < s.length() ? s.charAt(d) : 0;
	}

	public static void main(String[] args) {
		String[] a = {"banana", "orange", "grape", "apple", "kiwi"};
		radixSirala(a);
		System.out.println(Arrays.toString(a));  // [apple, banana, grape, kiwi, orange]

		String[] b = {"cart", "car", "cat", "ca", "bat"};
		radixSirala(b);
		System.out.println(Arrays.toString(b));  // [bat, ca, car, cart, cat]
	}
}

/* Beklenen cikti:
[apple, banana, grape, kiwi, orange]
[bat, ca, car, cart, cat]
*/
