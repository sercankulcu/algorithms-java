import java.util.ArrayDeque;
import java.util.List;

// Dal sinir genel semasi (en buyukleme): canli dugum kuyrugundan dugum cikar, sinirla buda, cocuklari uret.
// En kotu durum yine ustel; iyi bir sinir fonksiyonu uretilen dugum sayisini cok azaltir.
// Ornek problem: 0/1 sirt cantasi (W = 16), kuyruk stratejisi FIFO.
public class DalSinirSema {

	static int[] p = {40, 30, 50, 10};   // kazanc (p/w oranina gore azalan)
	static int[] w = {2, 5, 10, 5};      // agirlik
	static int W = 16, n = 4;

	static class Dugum {
		int seviye, kazanc, agirlik;

		Dugum(int seviye, int kazanc, int agirlik) {
			this.seviye = seviye; this.kazanc = kazanc; this.agirlik = agirlik;
		}

		// sol cocuk: sonraki esyayi al, sag cocuk: alma
		List<Dugum> cocuklar() {
			if (seviye == n - 1) return List.of();   // yaprak
			int i = seviye + 1;
			return List.of(new Dugum(i, kazanc + p[i], agirlik + w[i]),
			               new Dugum(i, kazanc, agirlik));
		}
	}

	// Canli dugumlerin tutuldugu kuyruk (strateji: FIFO)
	static class Kuyruk {
		private final ArrayDeque<Dugum> q = new ArrayDeque<>();
		void ekle(Dugum d) { q.add(d); }
		Dugum cikar() { return q.poll(); }
		boolean bosMu() { return q.isEmpty(); }
	}

	// Kesirli sirt cantasi gevsetmesi: ust sinir
	static double sinir(Dugum u) {
		if (u.agirlik > W) return 0;   // olursuz
		double sonuc = u.kazanc;
		int toplamAg = u.agirlik;
		int j = u.seviye + 1;
		while (j < n && toplamAg + w[j] <= W) {
			toplamAg += w[j];
			sonuc += p[j];
			j++;
		}
		if (j < n)                // kesirli parca
			sonuc += (W - toplamAg) * (double) p[j] / w[j];
		return sonuc;
	}

	static boolean olurluMu(Dugum c) {
		return c.agirlik <= W;
	}

	// Sirt cantasinda olurlu her dugum bir tam cozumdur (kalan esyalar alinmaz)
	static boolean tamCozumMu(Dugum c) {
		return true;
	}

	static int deger(Dugum c) {
		return c.kazanc;
	}

	static int dalSinir() {
		Dugum kok = new Dugum(-1, 0, 0);
		Kuyruk kuyruk = new Kuyruk();
		int enIyi = Integer.MIN_VALUE;   // -sonsuz
		kuyruk.ekle(kok);
		while (!kuyruk.bosMu()) {
			Dugum u = kuyruk.cikar();   // strateji
			if (sinir(u) <= enIyi) continue;
			for (Dugum c : u.cocuklar()) {
				if (!olurluMu(c)) continue;
				if (tamCozumMu(c) && deger(c) > enIyi)
					enIyi = deger(c);      // yeni aday
				if (sinir(c) > enIyi)
					kuyruk.ekle(c);        // canli dugum
			}
		}
		return enIyi;
	}

	public static void main(String[] args) {
		System.out.println(sinir(new Dugum(-1, 0, 0)));   // 115.0
		System.out.println(dalSinir());                  // 90
	}
}

/* Beklenen cikti:
115.0
90
*/
