// Ara deger (interpolation) arama: konum dogrusal ara degerle tahmin edilir.
// Duzgun dagilimda ortalama O(log log n), en kotu O(n).
public class AraDegerArama {

	static int araDegerArama(int[] dizi, int n,
							 int aranan) {
		int bas = 0, son = n - 1;
		while (bas <= son && aranan >= dizi[bas]
						  && aranan <= dizi[son]) {
			if (dizi[son] == dizi[bas]) return bas; // 0'a bolme
			int pay = (aranan - dizi[bas]) * (son - bas);
			int indis = bas + pay / (dizi[son] - dizi[bas]);
			if (dizi[indis] == aranan) return indis;
			if (dizi[indis] < aranan) bas = indis + 1;
			else son = indis - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] dizi = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
		System.out.println(araDegerArama(dizi, 15, 24));   // 10 (5 yoklama)
		System.out.println(araDegerArama(dizi, 15, 25));   // -1
	}
}

/* Beklenen cikti:
10
-1
*/
