import java.util.ArrayList;
import java.util.List;

// Maksimum klik dal sinir: K kligi, aday = K'nin hepsine komsu dugumler.
// Sinir: |K| + |aday| <= |enIyi| ise dal budanir; en kotu durum O(2^n).
public class MaksimumKlik {

	static int n = 6;
	static boolean[][] komsu = new boolean[n][n];
	static List<Integer> enIyi = new ArrayList<>();

	static void kenar(int u, int v) {
		komsu[u][v] = komsu[v][u] = true;
	}

	// v, K'deki her dugume komsu mu?
	static boolean uygun(int v, List<Integer> K) {
		for (int u : K)
			if (!komsu[u][v]) return false;
		return true;
	}

	static void klik(List<Integer> K, int bas) {
		if (K.size() > enIyi.size())
			enIyi = new ArrayList<>(K);
		List<Integer> aday = new ArrayList<>();
		for (int v = bas; v < n; v++)
			if (uygun(v, K)) aday.add(v);  // K'ye komsu
		if (K.size() + aday.size() <= enIyi.size())
			return;                        // sinir
		for (int v : aday) {
			K.add(v);
			klik(K, v + 1);
			K.remove(K.size() - 1);        // geri al
		}
	}

	public static void main(String[] args) {
		for (int u = 0; u < 5; u++)          // 0..4 tam cizge
			for (int v = u + 1; v < 5; v++) kenar(u, v);
		kenar(3, 5);
		kenar(4, 5);
		klik(new ArrayList<>(), 0);
		System.out.println("en buyuk klik: " + enIyi);   // [0, 1, 2, 3, 4]
	}
}

/* Beklenen cikti:
en buyuk klik: [0, 1, 2, 3, 4]
*/
