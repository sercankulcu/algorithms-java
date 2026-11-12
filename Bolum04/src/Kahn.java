import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Kahn algoritmasi: giris derecesi 0 olan dugumleri kuyrukla yazarak topolojik siralama, O(V + E).
public class Kahn {

	static int n;
	static List<Integer>[] komsu;

	static void kahn() {
		int[] giris = new int[n];
		for (int v = 0; v < n; v++)
			for (int w : komsu[v]) giris[w]++;
		Queue<Integer> kuyruk = new LinkedList<>();
		for (int v = 0; v < n; v++)
			if (giris[v] == 0) kuyruk.add(v);
		while (!kuyruk.isEmpty()) {
			int v = kuyruk.poll();
			System.out.print(v + " ");
			for (int w : komsu[v])
				if (--giris[w] == 0) kuyruk.add(w);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		// DFS izlemesindeki cizgeden 2 -> 0 kenari cikarilmis DAG
		int[][] kenarlar = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 3},
							 {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		n = 8;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) komsu[k[0]].add(k[1]);
		kahn();
		System.out.println();   // 0 1 7 2 3 4 5 6
	}
}

/* Beklenen cikti:
0 1 7 2 3 4 5 6
*/
