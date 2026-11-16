import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Kruskal: kenarlar artan agirlikla denenir; dongu kurmayan kenar MST'ye girer.
// Union-Find ile dongu denetimi; siralama baskin, O(E log E).
public class Kruskal {

	static class Kenar implements Comparable<Kenar> {
		int u, v, w;
		Kenar(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public int compareTo(Kenar o) { return Integer.compare(w, o.w); }
		public String toString() { return "(" + u + "," + v + ")=" + w; }
	}

	static List<Kenar> sonAgac;   // sonucu main'e tasimak icin eklendi

	static int bul(int[] ata, int i) {
		if (ata[i] != i)
			ata[i] = bul(ata, ata[i]); // yol sikistirma
		return ata[i];
	}

	static void kruskal(int V, List<Kenar> ke) {
		Collections.sort(ke);         // artan
		int[] ata = new int[V + 1];
		for (int i = 1; i <= V; i++) ata[i] = i;
		List<Kenar> agac = new ArrayList<>();
		int k = 0;
		while (agac.size() < V - 1) {
			Kenar e = ke.get(k++);
			int x = bul(ata, e.u);
			int y = bul(ata, e.v);
			if (x != y) {               // ayri agac
				agac.add(e);
				ata[x] = y;               // birlestir
			}
		}
		sonAgac = agac;               // eklendi
	}

	public static void main(String[] args) {
		// Bes dugumlu cizge (1..5)
		List<Kenar> ke = new ArrayList<>();
		int[][] liste = { {1, 3, 2}, {2, 5, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 4}, {1, 4, 6} };
		for (int[] e : liste) ke.add(new Kenar(e[0], e[1], e[2]));
		kruskal(5, ke);
		System.out.println(sonAgac);   // [(1,3)=2, (2,5)=2, (1,2)=3, (3,4)=4]
		int toplam = 0;
		for (Kenar e : sonAgac) toplam += e.w;
		System.out.println(toplam);    // 11  ((2,3) dongu kurdugu icin atlandi, (1,4) hic incelenmedi)
	}
}

/* Beklenen cikti:
[(1,3)=2, (2,5)=2, (1,2)=3, (3,4)=4]
11
*/
