import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// Eszamanli cizge gezinme: ziyaret edilmemis her komsu icin yeni bir gorev fork() edilir.
// Paylasilan ziyaret kumesi ConcurrentHashMap.newKeySet(); add() atomik kontrol+ekle yapar. Is O(n + m).
public class EsZamanliGezinme {

	static class Dugum {
		final int id;
		final List<Dugum> komsular = new ArrayList<>();
		Dugum(int id) { this.id = id; }
	}

	static Set<Dugum> ziyaret = ConcurrentHashMap.newKeySet();

	static class GezinmeGorevi extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private final transient Dugum u;
		GezinmeGorevi(Dugum u) { this.u = u; }

		protected void compute() {
			List<GezinmeGorevi> alt = new ArrayList<>();
			for (Dugum komsu : u.komsular) {
				if (!ziyaret.add(komsu)) continue; // atomik kontrol+ekle
				GezinmeGorevi g = new GezinmeGorevi(komsu);
				g.fork();                  // alt agac paralel gezilir
				alt.add(g);
			}
			for (GezinmeGorevi g : alt) g.join();
		}
	}

	public static void main(String[] args) {
		int n = 8;
		Dugum[] v = new Dugum[n];
		for (int i = 0; i < n; i++) v[i] = new Dugum(i);
		int[][] kenarlar = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 5}, {2, 5}, {6, 7}};
		for (int[] k : kenarlar) {
			v[k[0]].komsular.add(v[k[1]]);
			v[k[1]].komsular.add(v[k[0]]);
		}
		try (ForkJoinPool havuz = new ForkJoinPool(4)) {
			ziyaret.add(v[0]);
			havuz.invoke(new GezinmeGorevi(v[0]));
		}
		// Ziyaret sirasi is parcacigina bagli olabilir; kume sirali yazdirilir
		Set<Integer> sonuc = new TreeSet<>();
		for (Dugum d : ziyaret) sonuc.add(d.id);
		System.out.println(sonuc);   // [0, 1, 2, 3, 4, 5] (6 ve 7 baska bilesende)
	}
}
