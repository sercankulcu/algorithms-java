import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Bellman-Ford: butun kenarlar V - 1 tur gevsetilir; negatif kenarla calisir, O(V * E).
// Ek bir denetim turunda hala gevseyen kenar varsa negatif dongu vardir.
public class BellmanFord {

	static class Kenar {
		int bas, son, agirlik;
		Kenar(int bas, int son, int agirlik) { this.bas = bas; this.son = son; this.agirlik = agirlik; }
	}

	static final int SONSUZ = Integer.MAX_VALUE;
	static int V;
	static List<Kenar> kenarlar;
	static int[] sonUzaklik;   // sonucu main'e tasimak icin eklendi

	static void bellmanFord(int kaynak) {
		int[] uzaklik = new int[V];
		int[] onceki = new int[V];
		sonUzaklik = uzaklik;                     // eklendi
		Arrays.fill(uzaklik, SONSUZ);
		uzaklik[kaynak] = 0;
		for (int i = 1; i < V; i++) {
			for (Kenar k : kenarlar) {
				int u = k.bas, v = k.son, w = k.agirlik;
				if (uzaklik[u] != SONSUZ
						&& uzaklik[u] + w < uzaklik[v]) {
					uzaklik[v] = uzaklik[u] + w;
					onceki[v] = u;
				}
			}
		}
		for (Kenar k : kenarlar) { // denetim
			int u = k.bas, v = k.son, w = k.agirlik;
			if (uzaklik[u] != SONSUZ
					&& uzaklik[u] + w < uzaklik[v])
				throw new IllegalStateException(
						"Negatif dongu");
		}
	}

	static void cizgeKur(int dugumSayisi, int[][] liste) {
		V = dugumSayisi;
		kenarlar = new ArrayList<>();
		for (int[] k : liste) kenarlar.add(new Kenar(k[0], k[1], k[2]));
	}

	static String yaz(int[] d) {
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < d.length; i++)
			s.append(i > 0 ? ", " : "").append(d[i] == SONSUZ ? "inf" : String.valueOf(d[i]));
		return s.append("]").toString();
	}

	public static void main(String[] args) {
		// Kenar listesi bu sirayla gevsetilir: (2,1,-2), (0,1,4), (0,2,5), (1,3,3)
		cizgeKur(4, new int[][] { {2, 1, -2}, {0, 1, 4}, {0, 2, 5}, {1, 3, 3} });
		bellmanFord(0);
		System.out.println(yaz(sonUzaklik));   // [0, 3, 5, 6]

		// Negatif dongu: S -> A (2), A -> B (1), B -> C (-3), C -> A (1); S=0, A=1, B=2, C=3
		cizgeKur(4, new int[][] { {0, 1, 2}, {1, 2, 1}, {2, 3, -3}, {3, 1, 1} });
		try {
			bellmanFord(0);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());   // Negatif dongu
		}
		System.out.println(yaz(sonUzaklik));   // [0, -1, 1, -2]  (V - 1 = 3 tur sonu)
	}
}

/* Beklenen cikti:
[0, 3, 5, 6]
Negatif dongu
[0, -1, 1, -2]
*/
