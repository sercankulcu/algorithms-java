import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

// PRAM toplama (ikili agacta indirgeme): her turda a[i] += a[i + adim], adim her turda iki katina cikar.
// n/2 islemciyle log2 n tur: is W = O(n), derinlik D = O(log n). Toplam a[0]'da.
public class PramToplama {

	// Sunumdaki dongu: ayni turdaki tum i'ler birbirinden bagimsizdir
	static int pramToplama(int[] a) {
		int n = a.length;
		for (int adim = 1; adim < n; adim *= 2) {
			// ayni turdaki tum i'ler paralel
			for (int i = 0; i + adim < n; i += 2 * adim)
				a[i] = a[i] + a[i + adim];
			System.out.println("adim " + adim + ": " + Arrays.toString(a));
		}  // tur sonu: senkronizasyon
		// toplam a[0]'da
		return a[0];
	}

	// Ayni algoritma gercekten paralel: her turun ic dongusu paralel akisla, tur sonu bariyerdir.
	// Her i farkli hucrelere yazdigi icin yaris yoktur; sonuc is parcacigi sirasina bagli degildir.
	static int paralelToplama(int[] a) {
		int n = a.length;
		try (ForkJoinPool havuz = new ForkJoinPool(4)) {
			for (int adim = 1; adim < n; adim *= 2) {
				final int s = adim;
				int adet = (n - s + 2 * s - 1) / (2 * s);   // bu turdaki i sayisi
				havuz.submit(() -> IntStream.range(0, adet).parallel()
						.forEach(t -> a[2 * s * t] += a[2 * s * t + s])).join();
			}
		}   // close(): havuz kapanir
		return a[0];
	}

	public static void main(String[] args) {
		int[] a = {2, 3, 5, 1, 7, 6, 8, 4};
		System.out.println("toplam: " + pramToplama(a));
		// adim 1: [5, 3, 6, 1, 13, 6, 12, 4]
		// adim 2: [11, 3, 6, 1, 25, 6, 12, 4]
		// adim 4: [36, 3, 6, 1, 25, 6, 12, 4]
		// toplam: 36

		int[] b = new int[1000];
		for (int i = 0; i < b.length; i++) b[i] = i + 1;
		System.out.println("paralel: " + paralelToplama(b));   // paralel: 500500
	}
}
