import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Prim: agac tek dugumden buyur; her adimda agaca en ucuz kenarla baglanan dugum eklenir.
// Komsuluk matrisiyle O(V^2); tembel oncelikli kuyrukla O(E log V).
public class Prim {

	static class Kenar {
		int v, w;
		Kenar(int v, int w) { this.v = v; this.w = w; }
	}

	static final int SONSUZ = Integer.MAX_VALUE;
	static int[] sonAta;          // prim(G, kaynak) sonucunu main'e tasimak icin eklendi

	// Agacta olmayan dugumler arasinda anahtari en kucuk olani (esitlikte kucuk indisli)
	static int enKucukAnahtar(int[] anahtar, boolean[] agacta) {
		int enK = Integer.MAX_VALUE, u = -1;
		for (int v = 0; v < anahtar.length; v++)
			if (!agacta[v] && anahtar[v] < enK) {
				enK = anahtar[v];
				u = v;
			}
		return u;
	}

	static void prim(int[][] G, int kaynak) {
		int V = G.length;
		int[] anahtar = new int[V];
		int[] ata = new int[V];
		boolean[] agacta = new boolean[V];
		Arrays.fill(anahtar, Integer.MAX_VALUE);
		anahtar[kaynak] = 0;
		ata[kaynak] = -1;
		for (int s = 0; s < V - 1; s++) {
			int u = enKucukAnahtar(anahtar, agacta);
			agacta[u] = true;
			for (int v = 0; v < V; v++)
				if (G[u][v] != 0 && !agacta[v]
						&& G[u][v] < anahtar[v]) {
					anahtar[v] = G[u][v];
					ata[v] = u;
				}
		}
		sonAta = ata;   // eklendi
	}

	// Oncelikli kuyruk surumunun kullandigi alanlar (dugumler 1..V)
	static List<List<Kenar>> komsu;
	static int[] anahtar, ata;
	static boolean[] agacta;

	static void primPQ(int kaynak) {
		Arrays.fill(anahtar, SONSUZ);
		anahtar[kaynak] = 0;
		PriorityQueue<int[]> pq =
				new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { kaynak, 0 });
		while (!pq.isEmpty()) {
			int u = pq.poll()[0];
			if (agacta[u]) continue;  // eski
			agacta[u] = true;
			for (Kenar k : komsu.get(u))
				if (!agacta[k.v] && k.w < anahtar[k.v]) {
					anahtar[k.v] = k.w; ata[k.v] = u;
					pq.add(new int[] { k.v, k.w });
				}
		}
	}

	public static void main(String[] args) {
		// A-F cizgesinin komsuluk matrisi (0: kenar yok), kaynak B (indis 1)
		//              A  B  C  D  E  F
		int[][] G = { { 0, 1, 0, 3, 0, 0 },    // A
					  { 1, 0, 3, 2, 6, 5 },    // B
					  { 0, 3, 0, 0, 4, 4 },    // C
					  { 3, 2, 0, 0, 3, 0 },    // D
					  { 0, 6, 4, 3, 0, 2 },    // E
					  { 0, 5, 4, 0, 2, 0 } };  // F
		prim(G, 1);
		int toplam = 0;
		for (int v = 0; v < G.length; v++)
			if (sonAta[v] != -1) {
				System.out.print((char) ('A' + sonAta[v]) + "" + (char) ('A' + v) + " ");
				toplam += G[sonAta[v]][v];
			}
		System.out.println("w(T) = " + toplam);   // BA BC BD DE EF w(T) = 11

		// Bes dugumlu yonsuz cizge (1..5), kaynak 1
		int V = 5;
		komsu = new ArrayList<>();
		for (int i = 0; i <= V; i++) komsu.add(new ArrayList<>());
		int[][] liste = { {1, 3, 2}, {2, 5, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 4}, {1, 4, 6} };
		for (int[] e : liste) {
			komsu.get(e[0]).add(new Kenar(e[1], e[2]));
			komsu.get(e[1]).add(new Kenar(e[0], e[2]));
		}
		anahtar = new int[V + 1];
		ata = new int[V + 1];
		agacta = new boolean[V + 1];
		primPQ(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(anahtar, 1, V + 1)));   // [0, 3, 2, 4, 2]
		System.out.println(Arrays.toString(Arrays.copyOfRange(ata, 2, V + 1)));       // [1, 1, 3, 2]
		// MST: 1-2 (3), 1-3 (2), 3-4 (4), 2-5 (2); toplam 11
	}
}

/* Beklenen cikti:
BA BC BD DE EF w(T) = 11
[0, 3, 2, 4, 2]
[1, 1, 3, 2]
*/
