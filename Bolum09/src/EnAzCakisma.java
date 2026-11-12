import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// En az cakisma sezgiseli (min-conflicts): once tum vezirler yerlestirilir, sonra cakismalar yerel olarak onarilir.
// Yerel arama: geri donus yok, tam degil (adim siniri gerekir); buyuk n icin de birkac on adimda cozer.
public class EnAzCakisma {

	static Random rnd = new Random(42);
	static int[] yer;        // yer[k]: k. sutundaki vezirin satiri, -1: bos

	// (r, k) hucresine konacak vezirin diger sutunlardaki vezirlerle cakisma sayisi
	static int cakisma(int k, int r) {
		int say = 0;
		for (int j = 0; j < yer.length; j++) {
			if (j == k || yer[j] < 0) continue;
			if (yer[j] == r || Math.abs(yer[j] - r) == Math.abs(j - k)) say++;
		}
		return say;
	}

	// Sutun k icin en az cakismali satir; esitlikte rastgele
	static int enIyiSatir(int k) {
		int n = yer.length, enAz = Integer.MAX_VALUE;
		List<Integer> adaylar = new ArrayList<>();
		for (int r = 0; r < n; r++) {
			int c = cakisma(k, r);
			if (c < enAz) { enAz = c; adaylar.clear(); }
			if (c == enAz) adaylar.add(r);
		}
		return adaylar.get(rnd.nextInt(adaylar.size()));
	}

	static int[] enAzCakisma(int n, int maksAdim) {
		yer = new int[n];
		Arrays.fill(yer, -1);
		// baslangic: acgozlu yerlesim
		for (int k = 0; k < n; k++)
			yer[k] = enIyiSatir(k);
		for (int adim = 1; adim <= maksAdim; adim++) {
			List<Integer> cakisan = new ArrayList<>();
			for (int k = 0; k < n; k++)
				if (cakisma(k, yer[k]) > 0) cakisan.add(k);
			if (cakisan.isEmpty()) return yer;            // cakisma yok
			int k = cakisan.get(rnd.nextInt(cakisan.size()));  // rastgele cakisan sutun
			yer[k] = enIyiSatir(k);                       // argmin_r cakisma(k, r)
		}
		return null;   // basarisiz
	}

	static boolean gecerliMi(int[] y) {
		for (int i = 0; i < y.length; i++)
			for (int j = i + 1; j < y.length; j++)
				if (y[i] == y[j] || Math.abs(y[i] - y[j]) == j - i) return false;
		return true;
	}

	public static void main(String[] args) {
		int[] y = enAzCakisma(8, 1000);
		System.out.println(Arrays.toString(y) + " " + gecerliMi(y));   // [5, 3, 1, 7, 4, 6, 0, 2] true (tohum 42)

		y = enAzCakisma(200, 10000);
		System.out.println(y != null && gecerliMi(y));   // true
	}
}

/* Beklenen cikti:
[5, 3, 1, 7, 4, 6, 0, 2] true
true
*/
