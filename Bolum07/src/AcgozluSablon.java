import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Acgozlu genel sablon: adaylar olcute gore siralanir, tek geciste uygun olan eklenir, karar geri alinmaz.
// Ornek ornekleme: etkinlik secimi (olcut: en erken biten). Siralama O(n log n) + gecis O(n).
public class AcgozluSablon {

	static class Aday {
		int id, bas, bitis;
		Aday(int id, int bas, int bitis) { this.id = id; this.bas = bas; this.bitis = bitis; }
	}

	static class Cozum {
		List<Integer> secilen = new ArrayList<>();
		int sonBitis = Integer.MIN_VALUE;
		boolean uygunMu(Aday a) { return a.bas >= sonBitis; }   // cakisma yok
		void ekle(Aday a) { secilen.add(a.id); sonBitis = a.bitis; }
	}

	static void sirala(Aday[] adaylar) {
		Arrays.sort(adaylar, (x, y) -> x.bitis - y.bitis);   // en erken biten once
	}

	static Cozum acgozlu(Aday[] adaylar) {
		Cozum s = new Cozum();   // bos cozum
		sirala(adaylar);         // olcute gore
		for (Aday a : adaylar) {
			if (s.uygunMu(a))      // kisit bozulmaz
				s.ekle(a);           // geri alinmaz
		}
		return s;
	}

	public static void main(String[] args) {
		int[] bas   = {1, 2, 4, 6, 5, 8, 0, 3, 7, 10};
		int[] bitis = {3, 5, 6, 8, 7, 9, 2, 4, 9, 12};
		Aday[] a = new Aday[bas.length];
		for (int i = 0; i < a.length; i++) a[i] = new Aday(i + 1, bas[i], bitis[i]);
		System.out.println(acgozlu(a).secilen);   // [7, 8, 3, 4, 6, 10]
	}
}
