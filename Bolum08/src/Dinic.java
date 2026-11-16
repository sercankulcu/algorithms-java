import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Dinic: BFS ile duzey cizgesi kurulur, duzeyli DFS ile engelleyici akis gonderilir.
// En cok V - 1 faz; basla[] (kaldigi yer) dizisiyle faz basina O(V * E), toplam O(V^2 * E).
public class Dinic {

	static class Kenar {
		int hedef, kapasite, akis, tersIndis;
		Kenar(int hedef, int kapasite, int tersIndis) {
			this.hedef = hedef; this.kapasite = kapasite; this.tersIndis = tersIndis;
		}
	}

	static int V;
	static List<Kenar>[] komsu;
	static int[] seviye;

	// Ileri kenar u'nun, kapasitesi 0 olan ters kenar v'nin listesine eklenir
	static void kenarEkle(int u, int v, int c) {
		komsu[u].add(new Kenar(v, c, komsu[v].size()));
		komsu[v].add(new Kenar(u, 0, komsu[u].size() - 1));
	}

	// BFS: artik kapasitesi pozitif kenarlarla duzeyler; hedefe ulasildi mi?
	static boolean bfs(int kaynak, int hedef) {
		Arrays.fill(seviye, -1);
		seviye[kaynak] = 0;
		Queue<Integer> kuyruk = new LinkedList<>();
		kuyruk.add(kaynak);
		while (!kuyruk.isEmpty()) {
			int u = kuyruk.poll();
			for (Kenar k : komsu[u])
				if (seviye[k.hedef] < 0 && k.kapasite - k.akis > 0) {
					seviye[k.hedef] = seviye[u] + 1;
					kuyruk.add(k.hedef);
				}
		}
		return seviye[hedef] >= 0;
	}

	static int dfs(int u, int t, int akis, int[] basla) {
		if (u == t) return akis;
		for (; basla[u] < komsu[u].size(); basla[u]++) {
			Kenar k = komsu[u].get(basla[u]);
			if (seviye[k.hedef] == seviye[u] + 1 && k.akis < k.kapasite) {
				int d = dfs(k.hedef, t, Math.min(akis, k.kapasite - k.akis), basla);
				if (d > 0) {
					k.akis += d;                                 // ileri kenar
					komsu[k.hedef].get(k.tersIndis).akis -= d;   // ters kenar
					return d;
				}
			}
		}
		return 0;   // u'dan t'ye duzeyli yol kalmadi
	}

	// DINIC: duzey cizgesi kurulabildikce engelleyici akis gonder
	static int dinic(int kaynak, int hedef) {
		int maksimumAkis = 0, faz = 0;
		while (bfs(kaynak, hedef)) {
			int[] basla = new int[V];            // her fazda sifirlanir
			int fazAkis = 0, delta;
			while ((delta = dfs(kaynak, hedef, Integer.MAX_VALUE, basla)) > 0)
				fazAkis += delta;
			maksimumAkis += fazAkis;
			System.out.println("Faz " + (++faz) + ": +" + fazAkis + ", basla = "
					+ Arrays.toString(basla) + ", |f| = " + maksimumAkis);
		}
		return maksimumAkis;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		// s=0, 1..4, t=5; kenarlar bu sirayla eklenir (komsu listelerindeki sira basla[] izini belirler)
		V = 6;
		komsu = new List[V];
		for (int i = 0; i < V; i++) komsu[i] = new ArrayList<>();
		seviye = new int[V];
		int[][] kenarlar = { {0, 1, 10}, {0, 2, 10}, {1, 3, 4}, {1, 2, 2}, {1, 4, 8},
							 {2, 4, 9}, {4, 3, 6}, {3, 5, 10}, {4, 5, 10} };
		for (int[] k : kenarlar) kenarEkle(k[0], k[1], k[2]);
		System.out.println(dinic(0, 5));
		// Faz 1: +14, basla = [2, 3, 3, 2, 4, 0], |f| = 14   ({s,1,3,t} 4, {s,1,4,t} 6, {s,2,4,t} 4)
		// Faz 2: +5, basla = [2, 4, 3, 2, 2, 0], |f| = 19    ({s,2,4,3,t} 5)
		// 19
	}
}

/* Beklenen cikti:
Faz 1: +14, basla = [2, 3, 3, 2, 4, 0], |f| = 14
Faz 2: +5, basla = [2, 4, 3, 2, 2, 0], |f| = 19
19
*/
