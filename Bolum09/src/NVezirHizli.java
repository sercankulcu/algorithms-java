// n-Vezir, O(1) guvenlik testi: sutun, ters kosegen (r + c) ve kosegen (r - c + n - 1) bayraklari.
// Tum cozumler sayilir; tahta dizisine gerek kalmaz.
public class NVezirHizli {

	static int n = 8;
	static int sayac = 0;

	static boolean[] sut = new boolean[n];
	static boolean[] k1 = new boolean[2*n-1]; // r+c
	static boolean[] k2 = new boolean[2*n-1]; // r-c+n-1

	static void coz(int r) {
		if (r == n) { sayac++; return; }
		for (int c = 0; c < n; c++) {
			int a = r + c, b = r - c + n - 1;
			if (sut[c] || k1[a] || k2[b])
				continue;
			sut[c] = k1[a] = k2[b] = true;
			coz(r + 1);
			sut[c] = k1[a] = k2[b] = false;
		}
	}

	// Verilen n icin bayraklari yeniden kurar ve cozumleri sayar
	static int say(int boyut) {
		n = boyut;
		sut = new boolean[n];
		k1 = new boolean[2*n-1];
		k2 = new boolean[2*n-1];
		sayac = 0;
		coz(0);
		return sayac;
	}

	public static void main(String[] args) {
		for (int m = 1; m <= 10; m++)
			System.out.print(say(m) + " ");
		System.out.println();   // 1 0 0 2 10 4 40 92 352 724
	}
}

/* Beklenen cikti:
1 0 0 2 10 4 40 92 352 724
*/
