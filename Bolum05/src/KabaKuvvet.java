// Kaba kuvvet dizgi esleme: oruntu her kaymada soldan saga karsilastirilir.
// En kotu durum O(n * m), ek bellek O(1).
public class KabaKuvvet {

	// p'nin t icindeki ilk gecisinin konumu; yoksa -1
	static int kabaKuvvet(String t, String p) {
		int n = t.length(), m = p.length();
		for (int i = 0; i <= n - m; i++) {
			int j = 0;
			while (j < m &&
				t.charAt(i + j) == p.charAt(j))
				j++;
			if (j == m) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(kabaKuvvet("HELLO WORLD", "LO"));   // 3
		System.out.println(kabaKuvvet("HELLO WORLD", "XY"));   // -1
	}
}
