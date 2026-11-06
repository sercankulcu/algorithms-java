import java.util.ArrayDeque;
import java.util.PriorityQueue;

// 0/1 sirt cantasi dal sinir: ust sinir kesirli sirt cantasi gevsetmesidir.
// En iyi once (LC, oncelik kuyrugu) ve FIFO stratejileri; en kotu durum O(2^n), pratikte cok az dugum.
public class SirtCantasiDalSinir {

	// Esyalar p/w oranina gore azalan sirada: 20, 6, 5, 2
	static int[] p = {40, 30, 50, 10};
	static int[] w = {2, 5, 10, 5};
	static int W = 16, n = 4;
	static Dugum kok;

	static class Dugum {
		int seviye, kazanc, agirlik;
		double sinir;

		Dugum(int seviye, int kazanc, int agirlik) {
			this.seviye = seviye; this.kazanc = kazanc; this.agirlik = agirlik;
		}
	}

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

	// En iyi once: her adimda siniri en buyuk canli dugum genisletilir
	static int dalSinir() {
		PriorityQueue<Dugum> kuyruk = new PriorityQueue<>(
				(a, b) -> Double.compare(b.sinir, a.sinir));
		Dugum kok = new Dugum(-1, 0, 0);
		kok.sinir = sinir(kok);
		kuyruk.add(kok);  int enIyi = 0;
		while (!kuyruk.isEmpty()) {
			Dugum u = kuyruk.poll();           // en buyuk sinir
			if (u.sinir <= enIyi || u.seviye == n - 1) continue;
			int i = u.seviye + 1;
			// sol cocuk: i. esyayi al
			Dugum al = new Dugum(i, u.kazanc + p[i],
			                     u.agirlik + w[i]);
			if (al.agirlik <= W && al.kazanc > enIyi)
				enIyi = al.kazanc;
			al.sinir = sinir(al);
			if (al.sinir > enIyi) kuyruk.add(al);
			// sag cocuk: i. esyayi alma
			Dugum alma = new Dugum(i, u.kazanc, u.agirlik);
			alma.sinir = sinir(alma);
			if (alma.sinir > enIyi) kuyruk.add(alma);
		}
		return enIyi;
	}

	// al = 1: sonraki esyayi al, al = 0: alma; cocugun siniri da hesaplanir
	static Dugum cocuk(Dugum u, int al) {
		int i = u.seviye + 1;
		Dugum c = (al == 1)
				? new Dugum(i, u.kazanc + p[i], u.agirlik + w[i])
				: new Dugum(i, u.kazanc, u.agirlik);
		c.sinir = sinir(c);
		return c;
	}

	// FIFO (genislik oncelikli) dal sinir
	static int fifoDalSinir() {
		ArrayDeque<Dugum> kuyruk = new ArrayDeque<>();
		kuyruk.add(kok);  int enIyi = 0;
		while (!kuyruk.isEmpty()) {
			Dugum u = kuyruk.poll();          // ilk giren
			if (u.sinir <= enIyi || u.seviye == n - 1) continue;
			for (int al = 1; al >= 0; al--) { // once al
				Dugum c = cocuk(u, al);         // sinir(c) dahil
				if (c.agirlik <= W && c.kazanc > enIyi)
					enIyi = c.kazanc;               // yeni aday
				if (c.sinir > enIyi) kuyruk.add(c);
			}
		}
		return enIyi;
	}

	public static void main(String[] args) {
		kok = new Dugum(-1, 0, 0);
		kok.sinir = sinir(kok);
		System.out.println("sinir(kok) = " + kok.sinir);    // 115.0
		System.out.println("en iyi once: " + dalSinir());   // 90
		System.out.println("FIFO: " + fifoDalSinir());      // 90
	}
}
