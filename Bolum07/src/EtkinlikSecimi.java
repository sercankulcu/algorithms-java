import java.util.Arrays;

// Etkinlik secimi: bitise gore sirala, son secilenle cakismayan ilk etkinligi sec.
// Zaman: siralama O(n log n) + tek gecis O(n).
public class EtkinlikSecimi {

	static class Etkinlik {
		int id, bas, bitis;
		Etkinlik(int id, int bas, int bitis) { this.id = id; this.bas = bas; this.bitis = bitis; }
	}

	static void sec(Etkinlik[] e) {
		Arrays.sort(e, (x, y) -> x.bitis - y.bitis);
		int i = 0;                 // son secilen
		System.out.print(e[i].id + " ");
		for (int j = 1; j < e.length; j++) {
			if (e[j].bas >= e[i].bitis) {
				System.out.print(e[j].id + " ");
				i = j;
			}
		}
	}

	public static void main(String[] args) {
		int[] bas   = {1, 2, 4, 6, 5, 8, 0, 3, 7, 10};
		int[] bitis = {3, 5, 6, 8, 7, 9, 2, 4, 9, 12};
		Etkinlik[] e = new Etkinlik[bas.length];
		for (int k = 0; k < e.length; k++) e[k] = new Etkinlik(k + 1, bas[k], bitis[k]);
		sec(e);
		System.out.println();   // 7 8 3 4 6 10
	}
}
