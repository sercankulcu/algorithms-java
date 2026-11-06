import java.util.Arrays;

// Son tarihli is siralama: isler kara gore azalan sirayla, son tarihinden onceki en gec bos dilime konur.
// Zaman: siralama O(n log n) + dilim arama O(n * dmaks) -> en kotu O(n^2).
public class IsSiralama {

	static class Is {
		int id, sonTarih, kar;
		Is(int id, int sonTarih, int kar) { this.id = id; this.sonTarih = sonTarih; this.kar = kar; }
	}

	static int isSirala(Is[] is, int n) {
		Arrays.sort(is, (a, b) -> b.kar - a.kar);
		int[] slot = new int[n];
		Arrays.fill(slot, -1);
		int toplam = 0;
		for (int i = 0; i < n; i++) {
			int j = Math.min(n, is[i].sonTarih) - 1;
			for (; j >= 0; j--) {
				if (slot[j] == -1) {
					slot[j] = is[i].id;
					toplam += is[i].kar;
					break;
				}
			}
		}
		return toplam;
	}

	public static void main(String[] args) {
		Is[] is = {
			new Is(1, 4, 20), new Is(2, 1, 10), new Is(3, 1, 40),
			new Is(4, 1, 30), new Is(5, 3, 50), new Is(6, 2, 30)
		};
		System.out.println(isSirala(is, 6));   // 140: dilimler 1-4 = Is 3, Is 6, Is 5, Is 1
	}
}
