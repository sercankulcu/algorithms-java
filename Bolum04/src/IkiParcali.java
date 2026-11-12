import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// BFS ile iki parcalilik testi: katmanlar iki renge boyanir, O(V + E).
// Ayni renkli iki komsu tek uzunlukta dongu demektir: cizge iki parcali degildir.
public class IkiParcali {

	static int n;
	static List<Integer>[] komsu;
	static int[] renk;
	static Queue<Integer> kuyruk;

	static boolean ikiParcaliMi(int s) {
		Arrays.fill(renk, -1);
		renk[s] = 0; kuyruk.add(s);
		while (!kuyruk.isEmpty()) {
			int v = kuyruk.poll();
			for (int w : komsu[v])
				if (renk[w] == -1) {
					renk[w] = 1 - renk[v];
					kuyruk.add(w);
				} else if (renk[w] == renk[v])
					return false;  // ayni renk
		}
		return true;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void cizgeKur(int dugumSayisi, int[][] kenarlar) {
		n = dugumSayisi;
		komsu = new List[n];
		for (int v = 0; v < n; v++) komsu[v] = new ArrayList<>();
		for (int[] k : kenarlar) {       // yonsuz
			komsu[k[0]].add(k[1]);
			komsu[k[1]].add(k[0]);
		}
		renk = new int[n];
		kuyruk = new LinkedList<>();
	}

	public static void main(String[] args) {
		// 0-1-3-2 dongusu ve 3-4-5 ucgeni
		cizgeKur(6, new int[][] { {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5}, {4, 5} });
		System.out.println(ikiParcaliMi(0));   // false (4 ile 5 ayni renk)
		System.out.println(Arrays.toString(renk));   // [0, 1, 1, 0, 1, 1]

		// Ucgenin 4-5 kenari cikarilinca yalniz cift uzunlukta dongu kalir
		cizgeKur(6, new int[][] { {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5} });
		System.out.println(ikiParcaliMi(0));   // true
	}
}

/* Beklenen cikti:
false
[0, 1, 1, 0, 1, 1]
true
*/
