// Ikili arama: sirali dizide aralik her adimda yariya iner, O(log n).
// Dongulu ve ozyinelemeli surumler; ilkKonum tekrarlanan anahtarin ilk indisini bulur.
public class IkiliArama {

	static int ikiliArama(int[] dizi, int n, int aranan) {
		int bas = 0, son = n - 1;
		while (bas <= son) {
			int orta = (bas + son) / 2;
			if (aranan < dizi[orta])
				son = orta - 1;      // sol yari
			else if (aranan > dizi[orta])
				bas = orta + 1;      // sag yari
			else
				return orta;         // bulundu
		}
		return -1;
	}

	static int ara(int[] dizi, int bas,
				   int son, int aranan) {
		if (bas > son) return -1;  // bos aralik
		int orta = (bas + son) / 2;
		if (dizi[orta] == aranan) return orta;
		if (dizi[orta] < aranan)           // sag yari
			return ara(dizi, orta + 1, son, aranan);
		return ara(dizi, bas, orta - 1, aranan);
	}

	static int ilkKonum(int[] dizi, int aranan) {
		int bas = 0, son = dizi.length - 1, sonuc = -1;
		while (bas <= son) {
			int orta = bas + (son - bas) / 2;
			if (dizi[orta] == aranan) {
				sonuc = orta;      // kaydet,
				son = orta - 1;    // solda ara
			} else if (dizi[orta] < aranan)
				bas = orta + 1;
			else
				son = orta - 1;
		}
		return sonuc;
	}

	public static void main(String[] args) {
		int[] a = {5, 7, 10, 17, 23, 34, 55, 67, 99};
		System.out.println(ikiliArama(a, 9, 23));   // 4 (en iyi durum)
		System.out.println(ikiliArama(a, 9, 54));   // -1

		int[] dizi = {0, 4, 7, 10, 14, 23, 45, 47, 53};
		System.out.println(ikiliArama(dizi, 9, 47));   // 7

		int[] b = {1, 3, 5, 6, 7, 8, 11, 14, 16, 18, 20, 22, 23, 25, 27};
		System.out.println(ara(b, 0, b.length - 1, 20));   // 10

		int[] c = {2, 4, 4, 4, 4, 7, 9, 12};
		System.out.println(ilkKonum(c, 4));   // 1
	}
}

/* Beklenen cikti:
4
-1
7
10
1
*/
