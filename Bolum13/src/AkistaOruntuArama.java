// Akista oruntu arama (Rabin-Karp yuvarlanan ozet): her yeni karakterde ozet O(1) guncellenir.
// Beklenen O(n + m); ozet esitse startsWith ile dogrulanir (int tasmasi mod 2^32 aritmetigidir).
public class AkistaOruntuArama {

	static void ara(String akis, String oruntu) {
		int m = oruntu.length(), B = 101, h = 0, hp = 0;
		int gucu = 1;                         // B^(m-1)
		for (int i = 0; i < m - 1; i++) gucu *= B;
		for (int i = 0; i < m; i++)
			hp = hp * B + oruntu.charAt(i);
		for (int t = 0; t < akis.length(); t++) {
			char c = akis.charAt(t);
			if (t >= m) h -= akis.charAt(t - m) * gucu;
			h = h * B + c;          // giren eklenir
			if (t >= m - 1 && h == hp
					&& akis.startsWith(oruntu, t - m + 1))
				System.out.println("eslesme: " + (t - m + 1));
		}
	}

	public static void main(String[] args) {
		ara("CABDAB", "AB");
		// eslesme: 1
		// eslesme: 4
	}
}
