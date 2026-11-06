import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Genislik oncelikli arama (BFS): katman katman gezinme, O(V + E).
// uzaklik[] ve ata[] dizileri agirliksiz cizgede en kisa yolu verir.
public class BFS {

	static int n;
	static List<Integer>[] komsu;
	static int[] uzaklik, ata;

	static void bfs(int s) {
		boolean[] ziyaret = new boolean[n];
		Queue<Integer> kuyruk = new LinkedList<>();
		ziyaret[s] = true; kuyruk.add(s);
		while (!kuyruk.isEmpty()) {
			int v = kuyruk.poll();
			System.out.print(v + " ");
			for (int w : komsu[v])
				if (!ziyaret[w]) {
					ziyaret[w] = true;
					kuyruk.add(w);
				}
		}
	}

	static void bfsUzaklik(int s) {
		Arrays.fill(uzaklik, -1);
		Arrays.fill(ata, -1); uzaklik[s] = 0;
		Queue<Integer> kuyruk = new LinkedList<>();
		kuyruk.add(s);
		while (!kuyruk.isEmpty()) {
			int v = kuyruk.poll();
			for (int w : komsu[v])
				if (uzaklik[w] == -1) {  // yeni
					uzaklik[w] = uzaklik[v] + 1;
					ata[w] = v;
					kuyruk.add(w);
				}
		}
	}

	static List<Integer> yol(int[] ata, int s, int t) {
		LinkedList<Integer> yol = new LinkedList<>();
		for (int v = t; v != -1; v = ata[v])
			yol.addFirst(v);   // hedeften geriye
		if (yol.getFirst() != s)
			return null;       // t'ye ulasilamadi
		return yol;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void cizgeKur(int dugumSayisi, int[][] kenarlar, boolean yonsuz) {
		n = dugumSayisi;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) {
			komsu[k[0]].add(k[1]);
			if (yonsuz) komsu[k[1]].add(k[0]);
		}
		uzaklik = new int[n];
		ata = new int[n];
	}

	public static void main(String[] args) {
		// DFS izlemesindeki yonlu cizge
		int[][] yonlu = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 0},
						  {2, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		cizgeKur(8, yonlu, false);
		bfs(0);
		System.out.println();   // 0 1 2 7 3 4 5 6

		// 1'den 8'e numarali yonsuz agac (0 kullanilmaz): 1-2, 1-3, 1-4, 1-5, 2-6, 2-7, 3-8
		int[][] agac = { {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 6}, {2, 7}, {3, 8} };
		cizgeKur(9, agac, true);
		bfsUzaklik(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(uzaklik, 1, 9)));
		// [0, 1, 1, 1, 1, 2, 2, 2]
		System.out.println(Arrays.toString(Arrays.copyOfRange(ata, 1, 9)));
		// [-1, 1, 1, 1, 1, 2, 2, 3]
		System.out.println(yol(ata, 1, 8));   // [1, 3, 8]
		System.out.println(yol(ata, 1, 0));   // null (0'a ulasilamaz)
	}
}
