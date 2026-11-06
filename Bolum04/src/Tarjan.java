import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Tarjan: guclu baglantili bilesenler (SCC) tek DFS ile, O(V + E).
// indeks = kesif sirasi, dusuk = enDusukBag; dusuk == indeks olan dugum bilesenin koku.
public class Tarjan {

	static int n, sayac;
	static List<Integer>[] komsu;
	static int[] indeks, dusuk;          // indeks 0: tanimsiz
	static boolean[] yiginda;
	static Deque<Integer> yigin = new ArrayDeque<>();

	// TARJAN_SCC: indeksi tanimsiz her dugumden scc baslatilir
	static void tarjan() {
		indeks = new int[n];
		dusuk = new int[n];
		yiginda = new boolean[n];
		sayac = 0;
		for (int v = 0; v < n; v++)
			if (indeks[v] == 0)
				scc(v);
	}

	static void scc(int v) {
		indeks[v] = dusuk[v] = ++sayac;
		yigin.push(v); yiginda[v] = true;
		for (int w : komsu[v]) {
			if (indeks[w] == 0) {
				scc(w);
				dusuk[v] = Math.min(dusuk[v], dusuk[w]);
			} else if (yiginda[w])
				dusuk[v] = Math.min(dusuk[v], indeks[w]);
		}
		if (dusuk[v] == indeks[v]) {
			int w;
			do {
				w = yigin.pop(); yiginda[w] = false;
				System.out.print(w + " ");
			} while (w != v);
			System.out.println();
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void cizgeKur(int dugumSayisi, int[][] kenarlar) {
		n = dugumSayisi;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) komsu[k[0]].add(k[1]);
	}

	public static void main(String[] args) {
		// Izleme cizgesi: 0->1, 1->2, 2->0, 2->3, 3->4, 4->3 (komsu[2] = [0, 3])
		cizgeKur(5, new int[][] { {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3} });
		tarjan();
		// 4 3
		// 2 1 0
		System.out.println(Arrays.toString(dusuk));   // [1, 1, 1, 4, 4]
	}
}
