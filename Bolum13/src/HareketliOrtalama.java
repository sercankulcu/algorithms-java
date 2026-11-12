import java.util.LinkedList;
import java.util.Queue;

// Akista hareketli ortalama: son k degerin ortalamasi, kayan pencere.
// Deger basina O(1) zaman, O(k) bellek.
public class HareketliOrtalama {

	private final int k;
	private final Queue<Integer> pencere
			= new LinkedList<>();
	private int toplam = 0;

	HareketliOrtalama(int k) { this.k = k; }

	double sonraki(int deger) {
		if (pencere.size() == k)
			toplam -= pencere.poll(); // en eski
		pencere.offer(deger);
		toplam += deger;
		return toplam / (double) pencere.size();
	}

	public static void main(String[] args) {
		HareketliOrtalama ho = new HareketliOrtalama(3);
		StringBuilder sb = new StringBuilder();
		for (int x : new int[]{4, 8, 6, 2, 10})
			sb.append(String.format(java.util.Locale.ROOT, "%.2f ", ho.sonraki(x)));
		System.out.println(sb.toString().trim());   // 4.00 6.00 6.00 5.33 6.00
	}
}

/* Beklenen cikti:
4.00 6.00 6.00 5.33 6.00
*/
