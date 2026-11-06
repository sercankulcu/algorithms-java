import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

// Dijkstra: negatif olmayan agirlikli cizgede tek kaynakli en kisa yollar.
// Oncelikli kuyruk ve tembel silme ile O((V + E) log V); yol onceki[] dizisinden geri kurulur.
public class Dijkstra {

	static class Kenar {
		int hedef, agirlik;
		Kenar(int hedef, int agirlik) { this.hedef = hedef; this.agirlik = agirlik; }
	}

	static final int SONSUZ = Integer.MAX_VALUE;
	static int V;                          // dugumler 1..V, indis 0 kullanilmaz
	static List<List<Kenar>> komsu;
	static int[] sonUzaklik, sonOnceki;    // sonucu main'e tasimak icin eklendi

	static void dijkstra(int kaynak) {
		int[] uzaklik = new int[V + 1];
		int[] onceki = new int[V + 1];
		boolean[] ziyaret = new boolean[V + 1];
		Arrays.fill(uzaklik, SONSUZ);
		uzaklik[kaynak] = 0;
		PriorityQueue<int[]> kuyruk =
			new PriorityQueue<>((a, b) -> a[1] - b[1]);
		kuyruk.add(new int[] { kaynak, 0 });
		while (!kuyruk.isEmpty()) {
			int u = kuyruk.poll()[0];
			if (ziyaret[u]) continue; // eski kayit
			ziyaret[u] = true;
			for (Kenar k : komsu.get(u)) {
				int v = k.hedef, w = k.agirlik;
				if (!ziyaret[v]
						&& uzaklik[u] + w < uzaklik[v]) {
					uzaklik[v] = uzaklik[u] + w;
					onceki[v] = u;
					kuyruk.add(new int[] { v, uzaklik[v] });
				}
			}
		}
		sonUzaklik = uzaklik; sonOnceki = onceki;   // eklendi
	}

	static void yolYaz(int[] onceki, int kaynak, int hedef) {
		Deque<Integer> yigin = new ArrayDeque<>();
		for (int v = hedef; v != kaynak; v = onceki[v])
			yigin.push(v);         // hedeften geriye
		yigin.push(kaynak);
		while (!yigin.isEmpty())
			System.out.print(yigin.pop() + " ");
	}

	static void cizgeKur(int dugumSayisi, int[][] kenarlar, boolean yonsuz) {
		V = dugumSayisi;
		komsu = new ArrayList<>();
		for (int i = 0; i <= V; i++) komsu.add(new ArrayList<>());
		for (int[] k : kenarlar) {
			komsu.get(k[0]).add(new Kenar(k[1], k[2]));
			if (yonsuz) komsu.get(k[1]).add(new Kenar(k[0], k[2]));
		}
	}

	public static void main(String[] args) {
		// Ornek 2: yonlu cizge, kaynak 5
		int[][] ornek2 = { {5, 2, 4}, {5, 4, 2}, {4, 2, 1}, {4, 3, 1},
						   {2, 1, 3}, {3, 4, 2}, {1, 3, 6}, {1, 4, 3} };
		cizgeKur(5, ornek2, false);
		dijkstra(5);
		System.out.println(Arrays.toString(Arrays.copyOfRange(sonUzaklik, 1, 6)));
		// [6, 3, 3, 2, 0]
		System.out.println(Arrays.toString(Arrays.copyOfRange(sonOnceki, 1, 6)));
		// [2, 4, 4, 5, 0]  (kaynagin oncekisi yok: 0)
		yolYaz(sonOnceki, 5, 1);
		System.out.println();   // 5 4 2 1

		// Ornek 1: yonsuz cizge, kaynak 1
		int[][] ornek1 = { {1, 2, 7}, {1, 3, 9}, {1, 6, 14}, {2, 3, 10}, {2, 4, 15},
						   {3, 4, 11}, {3, 6, 2}, {4, 5, 6}, {5, 6, 9} };
		cizgeKur(6, ornek1, true);
		dijkstra(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(sonUzaklik, 1, 7)));
		// [0, 7, 9, 20, 20, 11]
		yolYaz(sonOnceki, 1, 5);
		System.out.println();   // 1 3 6 5
	}
}
