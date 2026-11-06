import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Kosaraju: iki DFS ile guclu baglantili bilesenler, O(V + E).
// 1. tur bitis sirasini yigina yazar; 2. tur ters cizgede yigindan cekilen dugumlerden gezer.
public class Kosaraju {

	static int n;
	static List<Integer>[] komsu, ters;
	static boolean[] ziyaret;
	static Deque<Integer> yigin = new ArrayDeque<>();

	static void kosaraju() {
		for (int v = 0; v < n; v++)  // 1. tur
			if (!ziyaret[v]) dfs1(v);
		Arrays.fill(ziyaret, false); // 2. tur
		while (!yigin.isEmpty()) {
			int v = yigin.pop();
			if (!ziyaret[v]) {   // yeni SCC
				dfs2(v); System.out.println(); }
		}
	}

	static void dfs1(int v) {
		ziyaret[v] = true;
		for (int w : komsu[v])
			if (!ziyaret[w]) dfs1(w);
		yigin.push(v);       // v bitti
	}

	static void dfs2(int v) {     // ters cizgede DFS
		ziyaret[v] = true;
		System.out.print(v + " ");
		for (int w : ters[v])
			if (!ziyaret[w]) dfs2(w);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void cizgeKur(int dugumSayisi, int[][] kenarlar) {
		n = dugumSayisi;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) komsu[k[0]].add(k[1]);
		ters = new List[n];
		for (int v = 0; v < n; v++)
			ters[v] = new ArrayList<>();
		for (int v = 0; v < n; v++)
			for (int w : komsu[v])
				ters[w].add(v);       // v -> w kenari w -> v olur
		ziyaret = new boolean[n];
	}

	public static void main(String[] args) {
		// Tarjan izlemesindeki cizge: 0->1, 1->2, 2->0, 2->3, 3->4, 4->3
		cizgeKur(5, new int[][] { {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3} });
		kosaraju();
		// 0 2 1
		// 3 4
	}
}
