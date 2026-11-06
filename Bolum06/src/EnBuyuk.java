// En buyuk oge: dizi ikiye bolunur, iki yarinin en buyugu karsilastirilir.
// n yaprak, n - 1 karsilastirma; T(n) = 2T(n/2) + O(1) = O(n).
public class EnBuyuk {

	static int enBuyuk(int[] a, int alt, int ust) {
		if (alt == ust) return a[alt];    // taban
		int orta = (alt + ust) / 2;
		int sol = enBuyuk(a, alt, orta);
		int sag = enBuyuk(a, orta + 1, ust);
		return Math.max(sol, sag);        // birlestir
	}

	public static void main(String[] args) {
		int[] a = {5, 2, 9, 4, 7};
		System.out.println(enBuyuk(a, 0, 4));   // 9
	}
}
