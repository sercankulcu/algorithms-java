import java.util.ArrayList;
import java.util.List;

// Quickhull: en sol A ve en sag B ortudedir; AB'ye en uzak C de ortudedir, ACB ucgeninin ici elenir.
// Ortalama O(n log n), en kotu O(n^2); yalnizca tam sayi carpimi (capraz) kullanilir.
public class Quickhull {

	static class Nokta {
		int x, y;
		Nokta(int x, int y) { this.x = x; this.y = y; }
		public String toString() { return "(" + x + "," + y + ")"; }
	}

	static List<Nokta> ortu = new ArrayList<>();

	// > 0: P, A->B'nin solunda; < 0: saginda; = 0: ayni dogruda
	static long capraz(Nokta A, Nokta B, Nokta P) {
		return (long)(B.x - A.x) * (P.y - A.y)
		     - (long)(B.y - A.y) * (P.x - A.x);
	}

	static Nokta enSol(List<Nokta> P) {
		Nokta m = P.get(0);
		for (Nokta p : P) if (p.x < m.x || (p.x == m.x && p.y < m.y)) m = p;
		return m;
	}

	static Nokta enSag(List<Nokta> P) {
		Nokta m = P.get(0);
		for (Nokta p : P) if (p.x > m.x || (p.x == m.x && p.y > m.y)) m = p;
		return m;
	}

	// A->B'nin kesin solundaki noktalar
	static List<Nokta> solda(List<Nokta> P, Nokta A, Nokta B) {
		List<Nokta> S = new ArrayList<>();
		for (Nokta p : P) if (capraz(A, B, p) > 0) S.add(p);
		return S;
	}

	// AB'ye en uzak nokta = en buyuk capraz
	static Nokta enUzak(List<Nokta> S, Nokta A, Nokta B) {
		Nokta m = S.get(0);
		for (Nokta p : S) if (capraz(A, B, p) > capraz(A, B, m)) m = p;
		return m;
	}

	static void quickHull(List<Nokta> P) {
		Nokta A = enSol(P), B = enSag(P);
		ortu.add(A); ortu.add(B);
		bul(solda(P, A, B), A, B); // ust kume
		bul(solda(P, B, A), B, A); // alt kume
	}

	static void bul(List<Nokta> S, Nokta A, Nokta B) {
		if (S.isEmpty()) return;   // AB ortu kenari
		Nokta C = enUzak(S, A, B); // max capraz
		ortu.add(C);
		bul(solda(S, A, C), A, C); // AC'nin disi
		bul(solda(S, C, B), C, B); // CB'nin disi
	}  // ACB ucgeninin ici elenir

	public static void main(String[] args) {
		Nokta A = new Nokta(2, 18), B = new Nokta(35, 11);
		System.out.println(capraz(A, B, new Nokta(13, 27)));   // 374
		System.out.println(capraz(A, B, new Nokta(28, 23)));   // 347

		int[][] xy = {{2, 18}, {4, 25}, {10, 5}, {11, 16}, {13, 27}, {14, 19}, {16, 12}, {20, 16},
				{20, 2}, {21, 12}, {23, 20}, {24, 15}, {27, 9}, {27, 15}, {28, 23}, {35, 11}};
		List<Nokta> P = new ArrayList<>();
		for (int[] p : xy) P.add(new Nokta(p[0], p[1]));
		quickHull(P);
		System.out.println(ortu);   // [(2,18), (35,11), (13,27), (4,25), (28,23), (20,2), (10,5)]
		System.out.println(ortu.size() + " kose, " + (P.size() - ortu.size()) + " nokta elendi");   // 7 kose, 9 nokta elendi
	}
}
