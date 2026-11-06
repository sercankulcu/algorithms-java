import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

// Sayfalama (k cerceveli onbellek): LRU ve FIFO k-rekabetci; isaretleme (rastgele) 2 H_k-rekabetci.
// lruHata istek basina O(k) (bagli liste); LinkedHashMap ile LRU istek basina O(1).
public class Sayfalama {

	static Random zar = new Random(42);

	static int lruHata(int[] istekler, int k) {
		var onb = new LinkedList<Integer>();
		int hata = 0;
		for (int sayfa : istekler) {
			if (onb.remove((Integer) sayfa)) {
				onb.addLast(sayfa); // en yeni yap
			} else {
				hata++;
				if (onb.size() == k)
					onb.removeFirst(); // en eskiyi at
				onb.addLast(sayfa);
			}
		}
		return hata;
	}

	static class LruOnbellek<A, D>
			extends LinkedHashMap<A, D> {
		private static final long serialVersionUID = 1L;
		private final int k;

		LruOnbellek(int k) {
			super(16, 0.75f, true); // erisim sirasi
			this.k = k;
		}

		@Override
		protected boolean removeEldestEntry(
				Map.Entry<A, D> enEski) {
			return size() > k;      // k asilinca at
		}
	}

	static int fifoHata(int[] istekler, int k) {
		Queue<Integer> kuyruk = new LinkedList<>();
		Set<Integer> kume = new HashSet<>();
		int hata = 0;
		for (int p : istekler) {
			if (kume.contains(p)) continue;   // isabet
			hata++;
			if (kume.size() == k)
				kume.remove(kuyruk.poll()); // en eski
			kuyruk.offer(p); kume.add(p);
		}
		return hata;
	}

	// Isaretleme: hata olunca isaretsiz sayfalardan biri rastgele cikarilir
	static int isaretleme(int[] istekler, int k) {
		Set<Integer> onb = new HashSet<>();
		Set<Integer> isr = new HashSet<>();   // isaretli
		int hata = 0;
		for (int p : istekler) {
			if (!onb.contains(p)) {
				hata++;
				if (onb.size() == k) {
					if (isr.size() == k) isr.clear(); // yeni evre
					List<Integer> aday = new ArrayList<>(onb);
					aday.removeAll(isr);          // isaretsizler
					int i = zar.nextInt(aday.size());
					onb.remove(aday.get(i));
				}
				onb.add(p);
			}
			isr.add(p);
		}
		return hata;
	}

	public static void main(String[] args) {
		System.out.println(lruHata(new int[]{1, 2, 3, 1, 4, 2}, 3));   // 5

		int[] dizi = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
		System.out.println("FIFO: " + fifoHata(dizi, 3));   // 10
		System.out.println("LRU: " + lruHata(dizi, 3));     // 9

		// Ayni dizi O(1) LRU onbellegiyle
		LruOnbellek<Integer, Integer> onb = new LruOnbellek<>(3);
		int hata = 0;
		for (int p : dizi)
			if (onb.get(p) == null) { hata++; onb.put(p, p); }
		System.out.println("LruOnbellek: " + hata + " " + onb.keySet());   // 9 [0, 3, 2]

		System.out.println("isaretleme: " + isaretleme(dizi, 3));   // 7 (tohum 42; sunumdaki calistirma 9)

		// Belady anomalisi: FIFO'da cerceve artinca hata artabilir
		int[] belady = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
		System.out.println(fifoHata(belady, 3) + " " + fifoHata(belady, 4));   // 9 10
		System.out.println(lruHata(belady, 3) + " " + lruHata(belady, 4));     // 10 8
	}
}
