import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Rezervuar ornekleme (Algoritma R, Vitter 1985): uzunlugu bilinmeyen akistan k ogeli esit olasilikli orneklem.
// Oge basina O(1), bellek O(k); her oge k/n olasilikla orneklemde kalir.
public class Rezervuar {

	static Random rastgele = new Random(42);

	static List<Integer> rezervuar(int[] akis, int k) {
		List<Integer> r = new ArrayList<>();
		for (int i = 0; i < k; i++) r.add(akis[i]);
		for (int i = k; i < akis.length; i++) {
			int j = rastgele.nextInt(i + 1); // 0..i
			if (j < k)
				r.set(j, akis[i]);
		}
		return r;
	}

	public static void main(String[] args) {
		int[] akis = {10, 20, 30, 40, 50, 60};
		System.out.println(rezervuar(akis, 3));   // [60, 20, 40] (tohum 42)

		// Deney: 60000 tekrarda her ogenin secilme sikligi (beklenen 3/6 = 0.5)
		int[] sayim = new int[61];
		for (int t = 0; t < 60000; t++)
			for (int x : rezervuar(akis, 3)) sayim[x]++;
		StringBuilder sb = new StringBuilder();
		for (int x : akis) sb.append(String.format(java.util.Locale.ROOT, "%.2f ", sayim[x] / 60000.0));
		System.out.println(sb.toString().trim());   // 0.50 0.50 0.50 0.50 0.50 0.50
	}
}

/* Beklenen cikti:
[60, 20, 40]
0.50 0.50 0.50 0.50 0.50 0.50
*/
