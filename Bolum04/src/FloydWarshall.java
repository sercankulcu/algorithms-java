// Floyd-Warshall: tum dugum ciftleri arasinda en kisa yollar, dinamik programlama, Theta(V^3).
// onceki[i][j]: i'den j'ye en kisa yolda j'den onceki dugum; d[i][i] < 0 negatif dongu demektir.
public class FloydWarshall {

	static final int SONSUZ = Integer.MAX_VALUE;
	static int V;
	static int[][] onceki;

	// FLOYD_WARSHALL ilklendirmesi: kosegen 0, kenar varsa agirlik, yoksa sonsuz; onceki[i][j] = i
	static int[][] ilklendir(int dugumSayisi, int[][] kenarlar) {
		V = dugumSayisi;
		int[][] d = new int[V][V];
		onceki = new int[V][V];
		for (int u = 0; u < V; u++)
			for (int v = 0; v < V; v++) {
				d[u][v] = (u == v) ? 0 : SONSUZ;
				onceki[u][v] = u;
			}
		for (int[] k : kenarlar)
			d[k[0]][k[1]] = k[2];
		return d;
	}

	static void floydWarshall(int[][] d) {  // V x V
		for (int k = 0; k < V; k++)       // ara
			for (int i = 0; i < V; i++)     // kaynak
				for (int j = 0; j < V; j++)   // hedef
					if (d[i][k] != SONSUZ
							&& d[k][j] != SONSUZ
							&& d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
						onceki[i][j] = onceki[k][j];
					}
		for (int i = 0; i < V; i++)
			if (d[i][i] < 0)
				throw new IllegalStateException(
						"Negatif dongu");
	}

	static void yaz(int[][] d) {
		for (int[] satir : d) {
			StringBuilder s = new StringBuilder();
			for (int x : satir)
				s.append(String.format("%4s", x == SONSUZ ? "inf" : String.valueOf(x)));
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		// Kod izlemesi: 0->1 (3), 0->3 (7), 1->0 (8), 1->2 (2), 2->0 (5), 2->3 (1), 3->0 (2)
		int[][] d = ilklendir(4, new int[][] { {0, 1, 3}, {0, 3, 7}, {1, 0, 8}, {1, 2, 2},
											   {2, 0, 5}, {2, 3, 1}, {3, 0, 2} });
		floydWarshall(d);
		yaz(d);
		//    0   3   5   6
		//    5   0   2   3
		//    3   6   0   1
		//    2   5   7   0
		System.out.println(onceki[1][3] + " " + onceki[1][2]);   // 2 1  (yol 1 -> 2 -> 3)

		// Dijkstra Ornek 2'nin cizgesi (dugumler 1..5, burada 0..4 indisleriyle)
		d = ilklendir(5, new int[][] { {4, 1, 4}, {4, 3, 2}, {3, 1, 1}, {3, 2, 1},
									   {1, 0, 3}, {2, 3, 2}, {0, 2, 6}, {0, 3, 3} });
		floydWarshall(d);
		yaz(d);
		//    0   4   4   3 inf
		//    3   0   7   6 inf
		//    6   3   0   2 inf
		//    4   1   1   0 inf
		//    6   3   3   2   0
		System.out.println(onceki[4][0] + 1);   // 2  (Pi[5][1] = 2: yol 5 -> 4 -> 2 -> 1)
	}
}

/* Beklenen cikti:
   0   3   5   6
   5   0   2   3
   3   6   0   1
   2   5   7   0
2 1
   0   4   4   3 inf
   3   0   7   6 inf
   6   3   0   2 inf
   4   1   1   0 inf
   6   3   3   2   0
2
*/
