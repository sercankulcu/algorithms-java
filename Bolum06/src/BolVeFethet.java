// Bol ve fethet genel sablonu: bol, yonet (ozyineleme), birlestir.
// Ornek ornekleme: dizinin en buyuk ogesi; a = 2, b = 2, T(n) = 2T(n/2) + O(1) = O(n).
public class BolVeFethet {

	static final int ESIK = 1;           // bu boyutta dogrudan cozulur

	// Problem: a[alt..ust] araligi
	static class Problem {
		int[] a;
		int alt, ust;
		Problem(int[] a, int alt, int ust) { this.a = a; this.alt = alt; this.ust = ust; }
		int boyut() { return ust - alt + 1; }
	}

	static class Sonuc {
		int deger;
		Sonuc(int deger) { this.deger = deger; }
	}

	static Sonuc dogrudanCoz(Problem p) {
		int m = p.a[p.alt];
		for (int i = p.alt + 1; i <= p.ust; i++) m = Math.max(m, p.a[i]);
		return new Sonuc(m);
	}

	static Problem[] bol(Problem p) {
		int orta = (p.alt + p.ust) / 2;
		return new Problem[] { new Problem(p.a, p.alt, orta), new Problem(p.a, orta + 1, p.ust) };
	}

	static Sonuc birlestir(Sonuc[] cozum) {
		int m = cozum[0].deger;
		for (Sonuc s : cozum) m = Math.max(m, s.deger);
		return new Sonuc(m);
	}

	static Sonuc bolVeFethet(Problem p) {
		if (p.boyut() <= ESIK)
			return dogrudanCoz(p);          // taban durumu
		Problem[] alt = bol(p);           // a alt problem, boyut n/b
		Sonuc[] cozum = new Sonuc[alt.length];
		for (int i = 0; i < alt.length; i++)
			cozum[i] = bolVeFethet(alt[i]); // yonet: ozyineleme
		return birlestir(cozum);          // birlestir
	}

	public static void main(String[] args) {
		int[] a = {5, 2, 9, 4, 7};
		System.out.println(bolVeFethet(new Problem(a, 0, a.length - 1)).deger);   // 9
	}
}

/* Beklenen cikti:
9
*/
