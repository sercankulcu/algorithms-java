import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// Paralel birlestirme siralamasi (Fork/Join): iki yari fork() ile paralel siralanir, join() ile birlestirilir.
// Is W = O(n log n); birlestirme sirali oldugu icin derinlik D = O(n).
public class ParalelBirlestirme {

	static class SiralamaGorevi extends RecursiveTask<int[]> {
		private static final long serialVersionUID = 1L;
		private final int[] dizi;
		SiralamaGorevi(int[] dizi) { this.dizi = dizi; }

		protected int[] compute() {
			if (dizi.length <= 1) return dizi;
			int orta = dizi.length / 2;
			var sol = new SiralamaGorevi(
					Arrays.copyOfRange(dizi, 0, orta));
			var sag = new SiralamaGorevi(
					Arrays.copyOfRange(dizi, orta, dizi.length));
			sol.fork();               // paralel baslat
			sag.fork();
			return birlestir(sol.join(), sag.join());
		}
	}

	// Iki sirali diziyi tek sirali dizide birlestir: O(a + b)
	static int[] birlestir(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length)
			c[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
		while (i < a.length) c[k++] = a[i++];
		while (j < b.length) c[k++] = b[j++];
		return c;
	}

	public static void main(String[] args) {
		int[] dizi = {2, 3, 5, 1, 7, 6, 8, 4};
		// kullanim (havuz try ile kapatilir):
		try (ForkJoinPool havuz = new ForkJoinPool()) {
			int[] s = havuz.invoke(
					new SiralamaGorevi(dizi));
			System.out.println(Arrays.toString(s));   // [1, 2, 3, 4, 5, 6, 7, 8]

			Random zar = new Random(42);
			int[] buyuk = new int[100000];
			for (int i = 0; i < buyuk.length; i++) buyuk[i] = zar.nextInt(1000000);
			int[] t = havuz.invoke(new SiralamaGorevi(buyuk));
			int[] beklenen = buyuk.clone();
			Arrays.sort(beklenen);
			System.out.println(Arrays.equals(t, beklenen));   // true
		}
	}
}
