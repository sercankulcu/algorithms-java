import java.util.Arrays;
import java.util.Random;

// BogoSort: dizi sirali olana kadar rastgele karistirilir (Las Vegas).
// Her karistirmada sirali olma olasiligi 1/n!: beklenen n! tur, beklenen sure O(n * n!).
public class BogoSort {

	static Random zar = new Random(42);
	static int tur = 0;   // karistirma sayaci (gosterim icin)

	static void bogoSirala(int[] a) {
		while (!siraliMi(a))
			karistir(a);        // Fisher-Yates
	}

	static boolean siraliMi(int[] a) {
		for (int i = 0; i + 1 < a.length; i++)
			if (a[i] > a[i + 1]) return false;
		return true;
	}

	static void karistir(int[] a) {
		tur++;
		for (int i = a.length - 1; i > 0; i--) {
			int j = zar.nextInt(i + 1); // 0 <= j <= i
			int t = a[i]; a[i] = a[j]; a[j] = t;
		}
	}

	public static void main(String[] args) {
		int[] a = {5, 2, 8, 4};
		bogoSirala(a);
		System.out.println(Arrays.toString(a));   // [2, 4, 5, 8]
		System.out.println("karistirma: " + tur);   // 84 (tohum 42; beklenen deger 4! = 24)
	}
}
