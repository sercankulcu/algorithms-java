import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Derinlik oncelikli arama (DFS): ozyinelemeli ve yiginli surum, O(V + E).
// Yiginli surumde komsular sirayla itildigi icin ziyaret sirasi ters cikar.
public class DFS {

	static int n;
	static List<Integer>[] komsu;

	static void dfs(int v, boolean[] ziyaret) {
		ziyaret[v] = true;
		System.out.print(v + " ");
		for (int w : komsu[v])
			if (!ziyaret[w])
				dfs(w, ziyaret);
	}

	static void dfsYigin(int s) {
		boolean[] ziyaret = new boolean[n];
		Deque<Integer> yigin = new ArrayDeque<>();
		yigin.push(s);
		while (!yigin.isEmpty()) {
			int v = yigin.pop();
			if (!ziyaret[v]) {
				ziyaret[v] = true;
				System.out.print(v + " ");
				for (int w : komsu[v])
					yigin.push(w);
			}
		}
	}

	// Yonlu cizge: kenarlar eklenme sirasiyla komsu listelerine yazilir
	@SuppressWarnings({"unchecked", "rawtypes"})
	static void cizgeKur(int dugumSayisi, int[][] kenarlar) {
		n = dugumSayisi;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) komsu[k[0]].add(k[1]);
	}

	public static void main(String[] args) {
		// Izleme cizgesi: komsu[0] = [1, 2, 7], komsu[2] = [0, 3, 4]; 6 ve 7'nin cikan kenari yok
		int[][] kenarlar = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 0},
							 {2, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		cizgeKur(8, kenarlar);

		dfs(0, new boolean[n]);
		System.out.println();   // 0 1 2 3 5 6 4 7

		dfsYigin(0);
		System.out.println();   // 0 7 2 4 6 3 5 1
	}
}
