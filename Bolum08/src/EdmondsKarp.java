import java.util.LinkedList;
import java.util.Queue;

// Edmonds-Karp: Ford-Fulkerson'in BFS'li surumu; her seferinde en az kenarli artis yolu secilir.
// Sure kapasitelerden bagimsizdir: O(V * E^2).
public class EdmondsKarp {

	static int V;
	static int[][] artik;   // artik kapasite matrisi

	static boolean bfs(int kaynak, int hedef, int[] ata) {
		boolean[] ziyaret = new boolean[V];
		Queue<Integer> kuyruk = new LinkedList<>();
		kuyruk.add(kaynak);
		ziyaret[kaynak] = true;
		ata[kaynak] = -1;
		while (!kuyruk.isEmpty()) {
			int u = kuyruk.poll();
			for (int v = 0; v < V; v++)
				if (!ziyaret[v] && artik[u][v] > 0) {
					kuyruk.add(v);
					ata[v] = u;
					ziyaret[v] = true;
				}
		}
		return ziyaret[hedef];
	}

	static int edmondsKarp(int s, int t) {
		int maksAkis = 0;
		int[] ata = new int[V];
		while (bfs(s, t, ata)) {
			int akis = Integer.MAX_VALUE;
			for (int v = t; v != s; v = ata[v])
				akis = Math.min(akis, artik[ata[v]][v]);
			for (int v = t; v != s; v = ata[v]) {
				int u = ata[v];
				artik[u][v] -= akis;  // ileri
				artik[v][u] += akis;  // ters
			}
			maksAkis += akis;
		}
		return maksAkis;
	}

	public static void main(String[] args) {
		// s=0, 1..4, t=5
		V = 6;
		artik = new int[V][V];
		int[][] kenarlar = { {0, 1, 16}, {0, 2, 13}, {1, 2, 10}, {2, 1, 4}, {1, 3, 12},
							 {3, 2, 9}, {2, 4, 14}, {4, 3, 7}, {3, 5, 20}, {4, 5, 4} };
		for (int[] k : kenarlar) artik[k[0]][k[1]] = k[2];

		int[] ata = new int[V];
		System.out.println(bfs(0, 5, ata) + " " + ata[5] + " " + ata[3] + " " + ata[1]);
		// true 3 1 0  (ilk yol s -> 1 -> 3 -> t, darbogaz 12)

		System.out.println(edmondsKarp(0, 5));
		// 23  (s13t +12, s24t +4, s243t +7)
	}
}

/* Beklenen cikti:
true 3 1 0
23
*/
