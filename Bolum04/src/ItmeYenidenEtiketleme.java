import java.util.ArrayList;
import java.util.List;

// Itme-yeniden etiketleme (push-relabel): on akisla baslanir, asiri akis yuksekten alcaga itilir.
// Itilemeyen dugumun yuksekligi artirilir; genel surum O(V^2 * E).
public class ItmeYenidenEtiketleme {

	static class Dugum {
		String ad;
		int yukseklik, asiri;
		List<Kenar> kenarlar = new ArrayList<>();
		Dugum(String ad) { this.ad = ad; }
	}

	static class Kenar {
		Dugum hedef;
		int kapasite, akis;
		Kenar ters;           // ters kenarlar kapasite 0 ile tutulur, akisi negatif olur
		Kenar(Dugum hedef, int kapasite) { this.hedef = hedef; this.kapasite = kapasite; }
	}

	static void kenarEkle(Dugum u, Dugum v, int c) {
		Kenar ileri = new Kenar(v, c), geri = new Kenar(u, 0);
		ileri.ters = geri; geri.ters = ileri;
		u.kenarlar.add(ileri);
		v.kenarlar.add(geri);
	}

	static void tersKenariGuncelle(Kenar k, int akis) {
		k.ters.akis -= akis;
	}

	static boolean it(Dugum d) {
		for (Kenar k : d.kenarlar) {
			if (d.yukseklik > k.hedef.yukseklik
					&& k.akis != k.kapasite) {
				int akis = Math.min(
					k.kapasite - k.akis, d.asiri);
				d.asiri -= akis;
				k.hedef.asiri += akis;
				k.akis += akis;
				tersKenariGuncelle(k, akis);
				return true;   // itme yapildi
			}
		}
		return false;
	}

	static void etiketle(Dugum d) {
		int minY = Integer.MAX_VALUE;
		for (Kenar k : d.kenarlar) {
			// yalnizca artik agdaki kenarlar
			if (k.akis != k.kapasite
					&& k.hedef.yukseklik < minY) {
				minY = k.hedef.yukseklik;
				d.yukseklik = minY + 1;
			}
		}
	}

	// PUSH_RELABEL: on akis, sonra aktif dugum kaldikca if (!it(d)) etiketle(d)
	static int pushRelabel(List<Dugum> dugumler, Dugum kaynak, Dugum hedef) {
		kaynak.yukseklik = dugumler.size();
		for (Kenar k : kaynak.kenarlar) {      // on akis: kaynak kenarlarini doldur
			int c = k.kapasite;
			k.akis = c;
			tersKenariGuncelle(k, c);
			k.hedef.asiri += c;
			kaynak.asiri -= c;
		}
		while (true) {
			Dugum d = null;                      // aktif dugum: asiri > 0, kaynak ve hedef disinda
			for (Dugum x : dugumler)
				if (x != kaynak && x != hedef && x.asiri > 0) { d = x; break; }
			if (d == null) break;
			if (!it(d)) etiketle(d);
		}
		return hedef.asiri;
	}

	public static void main(String[] args) {
		String[] adlar = { "s", "A", "B", "C", "D", "t" };
		List<Dugum> dugumler = new ArrayList<>();
		for (String a : adlar) dugumler.add(new Dugum(a));
		Dugum s = dugumler.get(0), A = dugumler.get(1), B = dugumler.get(2),
			  C = dugumler.get(3), D = dugumler.get(4), t = dugumler.get(5);
		kenarEkle(s, A, 13); kenarEkle(s, B, 10); kenarEkle(B, A, 3);
		kenarEkle(A, C, 6);  kenarEkle(A, t, 7);  kenarEkle(C, D, 10);
		kenarEkle(D, t, 5);

		System.out.println(pushRelabel(dugumler, s, t));   // 12  (t'ye giren 7 + 5)
		System.out.println(-s.asiri);                      // 12  (kaynaktan net cikan 9 + 3)
	}
}
