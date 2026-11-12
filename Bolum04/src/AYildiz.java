import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// A* arama: acik listeden f = g + h degeri en kucuk dugum genisletilir.
// h kabul edilebilir ise hedef cikarildiginda bulunan yol en kisadir; h = 0 ile Dijkstra'ya doner.
public class AYildiz {

	static class Kenar {
		int v; double w;
		Kenar(int v, double w) { this.v = v; this.w = w; }
	}

	static final double SONSUZ = Double.POSITIVE_INFINITY;
	static List<Kenar>[] komsu;
	static double[] g, h;
	static int[] ata;

	static double aYildiz(int s, int t) {
		Arrays.fill(g, SONSUZ); g[s] = 0;
		Queue<Integer> acik = new PriorityQueue<>(
			Comparator.comparingDouble(v -> g[v] + h[v]));
		acik.add(s);
		while (!acik.isEmpty()) {
			int u = acik.poll();      // en kucuk f
			if (u == t) return g[t];  // hedef
			for (Kenar k : komsu[u])
				if (g[u] + k.w < g[k.v]) {
					g[k.v] = g[u] + k.w; ata[k.v] = u;
					acik.remove(k.v); acik.add(k.v);
				}
		}
		return SONSUZ;
	}

	static final String AD = "sabcdet";   // s=0, a=1, b=2, c=3, d=4, e=5, t=6

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		int n = AD.length();
		komsu = new List[n];
		for (int i = 0; i < n; i++) komsu[i] = new ArrayList<>();
		String[] uv = { "sa", "ab", "bc", "ct", "sd", "de", "et" };
		double[] w =  { 1.5,  2,    3,    4,    2,    3,    2 };
		for (int i = 0; i < uv.length; i++) {          // yonsuz
			int u = AD.indexOf(uv[i].charAt(0)), v = AD.indexOf(uv[i].charAt(1));
			komsu[u].add(new Kenar(v, w[i]));
			komsu[v].add(new Kenar(u, w[i]));
		}
		g = new double[n];
		h = new double[] { 5, 4, 2, 4, 4.5, 2, 0 };    // hedefe tahmini uzaklik
		ata = new int[n];

		int s = AD.indexOf('s'), t = AD.indexOf('t');
		System.out.println(aYildiz(s, t));   // 7.0
		StringBuilder yol = new StringBuilder();
		for (int v = t; v != s; v = ata[v]) yol.insert(0, " -> " + AD.charAt(v));
		System.out.println("s" + yol);       // s -> d -> e -> t
		System.out.println(g[AD.indexOf('c')]);   // 6.5 (c acik listede kaldi, hic genisletilmedi)
	}
}

/* Beklenen cikti:
7.0
s -> d -> e -> t
6.5
*/
