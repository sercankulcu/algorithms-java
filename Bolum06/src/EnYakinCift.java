import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// En yakin nokta cifti: kaba kuvvet O(n^2); bol ve fethet ile orta serit taranarak O(n log n).
// Noktalar bir kez x'e (X) ve bir kez y'ye (Y) gore siralanir; T(n) = 2T(n/2) + O(n).
public class EnYakinCift {

	static class Nokta {
		int x, y;
		Nokta(int x, int y) { this.x = x; this.y = y; }
		public String toString() { return "(" + x + "," + y + ")"; }
	}

	static double mesafe(Nokta p, Nokta q) {
		return Math.hypot(p.x - q.x, p.y - q.y);
	}

	static double kabaKuvvet(List<Nokta> p) {
		double enAz = Double.MAX_VALUE;
		for (int i = 0; i < p.size(); i++)
			for (int j = i + 1; j < p.size(); j++)
				enAz = Math.min(enAz, mesafe(p.get(i), p.get(j)));
		return enAz;
	}

	static double enYakin(List<Nokta> X, List<Nokta> Y) {
		int n = X.size(), orta = n / 2;
		if (n <= 3) return kabaKuvvet(X);
		Nokta m = X.get(orta); // Ysol/Ysag: Y'nin yarilari
		// Y, sirasi korunarak X'in iki yarisina gore ayrilir: O(n), yeniden siralama yok
		Set<Nokta> solKume = new HashSet<>(X.subList(0, orta));
		List<Nokta> Ysol = new ArrayList<>(), Ysag = new ArrayList<>();
		for (Nokta q : Y)
			if (solKume.contains(q)) Ysol.add(q); else Ysag.add(q);
		double dSol = enYakin(X.subList(0, orta), Ysol);
		double dSag = enYakin(X.subList(orta, n), Ysag);
		double d = Math.min(dSol, dSag);
		List<Nokta> s = new ArrayList<>();  // serit
		for (Nokta p : Y)                   // y sirasi
			if (Math.abs(p.x - m.x) < d) s.add(p);
		for (int i = 0; i < s.size(); i++) {
			Nokta p = s.get(i);
			for (int j = i + 1; j < s.size()
			     && s.get(j).y - p.y < d; j++)
				d = Math.min(d, mesafe(p, s.get(j)));
		}
		return d;
	}

	public static void main(String[] args) {
		int[][] xy = {{2, 3}, {3, 4}, {5, 1}, {7, 7}, {12, 30}, {12, 10}, {17, 17}, {27, 27}, {28, 27}, {40, 50}};
		List<Nokta> P = new ArrayList<>();
		for (int[] p : xy) P.add(new Nokta(p[0], p[1]));
		System.out.println(kabaKuvvet(P));   // 1.0 (45 uzaklik)

		List<Nokta> X = new ArrayList<>(P), Y = new ArrayList<>(P);
		X.sort(Comparator.comparingInt(p -> p.x));   // kararli siralama: (12,30), (12,10) sirasi korunur
		Y.sort(Comparator.comparingInt(p -> p.y));
		System.out.println(enYakin(X, Y));   // 1.0: (27,27)-(28,27)
	}
}
