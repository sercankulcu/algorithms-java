import java.util.ArrayList;
import java.util.List;

// Cizgeyi bellekte tutmak: komsuluk listesi, O(V + E) bellek ve kurulum.
// Yonsuz kenar iki listeye de eklenir; listelerin toplam uzunlugu 2E olur.
public class KomsulukListesi {

	@SuppressWarnings({"unchecked", "rawtypes"})
	static List<Integer>[] kur(int n, int[][] kenarlar) {
		List<Integer>[] komsu = new List[n];
		for (int v = 0; v < n; v++)
			komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) {
			komsu[k[0]].add(k[1]);
			komsu[k[1]].add(k[0]);  // yonsuz: ters yon de eklenir
		}
		return komsu;
	}

	public static void main(String[] args) {
		// Dort dugumlu yonsuz cizge: 0-1, 0-2, 1-2, 2-3
		int[][] kenarlar = { {0, 1}, {0, 2}, {1, 2}, {2, 3} };
		List<Integer>[] komsu = kur(4, kenarlar);
		for (int v = 0; v < komsu.length; v++) {
			StringBuilder s = new StringBuilder(v + ":");
			for (int i = 0; i < komsu[v].size(); i++)
				s.append(i == 0 ? " " : ", ").append(komsu[v].get(i));
			System.out.println(s);
		}
		// 0: 1, 2
		// 1: 0, 2
		// 2: 0, 1, 3
		// 3: 2
	}
}
