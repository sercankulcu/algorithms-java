import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// MapReduce ile kelime sayma: her map gorevi kendi parcasini kendi HashMap'inde sayar (paylasilan durum yok),
// reduce ara sonuclari birlestirir. Is O(n), map adimi parca sayisi kadar paralel.
public class MapReduce {

	static class MapGorevi extends RecursiveTask<Map<String, Integer>> {
		private static final long serialVersionUID = 1L;
		private final transient List<String> parca;
		MapGorevi(List<String> parca) { this.parca = parca; }

		// Map: bir parcadaki kelimeleri say
		protected Map<String, Integer> compute() {
			Map<String, Integer> sonuc = new HashMap<>();
			for (String kelime : parca)
				sonuc.merge(kelime, 1, Integer::sum);
			return sonuc;
		}
	}

	// Ana akis: bir ForkJoinPool is parcacigi icinde calisir, fork() o havuza gider
	static Map<String, Integer> kelimeSay(List<MapGorevi> mapGorevleri) {
		for (MapGorevi g : mapGorevleri) g.fork();
		List<Map<String, Integer>> ara = new ArrayList<>();
		for (MapGorevi g : mapGorevleri) ara.add(g.join());
		// Reduce: ara sonuclari birlestir
		Map<String, Integer> toplam = new HashMap<>();
		for (Map<String, Integer> m : ara)
			m.forEach((k, v) -> toplam.merge(k, v, Integer::sum));
		return toplam;
	}

	public static void main(String[] args) {
		List<String> kelimeler = List.of(
				"apple", "banana", "apple", "orange", "banana", "apple",
				"banana", "banana", "orange", "apple", "cherry", "banana", "apple");
		int gorev = 4, boy = kelimeler.size() / gorev;   // 13 / 4 = 3, son parca 4 kelime
		List<MapGorevi> mapGorevleri = new ArrayList<>();
		for (int g = 0; g < gorev; g++) {
			int bas = g * boy, son = (g == gorev - 1) ? kelimeler.size() : bas + boy;
			mapGorevleri.add(new MapGorevi(kelimeler.subList(bas, son)));
		}
		try (ForkJoinPool havuz = new ForkJoinPool(gorev)) {
			Map<String, Integer> toplam = havuz.submit(() -> kelimeSay(mapGorevleri)).join();
			System.out.println(new TreeMap<>(toplam));   // {apple=5, banana=5, cherry=1, orange=2}
		}
	}
}
