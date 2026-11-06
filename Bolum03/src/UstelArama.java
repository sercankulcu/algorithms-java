// Ustel arama: konum 1, 2, 4, 8, ... diye ikiye katlanarak aralik bulunur, sonra ikili arama.
// O(log i) (i: aranan ogenin konumu); sinirsiz dizilerde kullanilir.
public class UstelArama {

	static int ustelArama(int[] dizi, int aranan, int n) {
		if (dizi[0] == aranan) return 0;
		int konum = 1;
		while (konum < n && dizi[konum] <= aranan)
			konum *= 2;               // 1, 2, 4, 8, ...
		int bas = konum / 2, son = Math.min(konum, n - 1);
		while (bas <= son) {        // ikili arama
			int orta = (bas + son) / 2;
			if (dizi[orta] == aranan) return orta;
			if (dizi[orta] < aranan) bas = orta + 1;
			else son = orta - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] dizi = {2, 9, 30, 32, 38, 47, 61, 69, 79, 81};
		System.out.println(ustelArama(dizi, 47, 10));   // 5
		System.out.println(ustelArama(dizi, 2, 10));    // 0
		System.out.println(ustelArama(dizi, 40, 10));   // -1
	}
}
