import java.util.Arrays;

// Kesirli sirt cantasi: kar / agirlik orani en buyuk olan once alinir, son nesneden kesirli parca.
// Zaman O(n log n) (siralama); tarama O(n).
public class KesirliSirtCantasi {

	static class Nesne {
		int kar, agirlik;
		Nesne(int kar, int agirlik) { this.kar = kar; this.agirlik = agirlik; }
		double oran() { return (double) kar / agirlik; }
	}

	static double kesirli(Nesne[] s, int W) {
		Arrays.sort(s, (a, b) -> Double.compare(b.oran(), a.oran()));
		double kar = 0;
		int kalan = W;
		for (Nesne x : s) {
			if (x.agirlik <= kalan) {        // tamami sigar
				kalan -= x.agirlik;
				kar += x.kar;
			} else {                         // kesirli parca
				kar += x.kar * (double) kalan / x.agirlik;
				break;
			}
		}
		return kar;
	}

	public static void main(String[] args) {
		Nesne[] s = { new Nesne(60, 10), new Nesne(100, 20), new Nesne(120, 30) };
		System.out.println(kesirli(s, 50));   // 240.0: 60 + 100 + 120 * 20/30
	}
}

/* Beklenen cikti:
240.0
*/
