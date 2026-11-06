import java.util.PriorityQueue;

// Huffman kodlama: en dusuk frekansli iki agac birlestirilir; n karakter icin n - 1 birlestirme.
// Oncelikli kuyrukla O(n log n); kodlar agac bir kez gezilerek uretilir, O(n).
public class Huffman {

	static class Dugum {
		char h;
		int f;
		Dugum sol, sag;
		Dugum(char h, int f) { this.h = h; this.f = f; }
	}

	static Dugum huffman(char[] h, int[] f) {
		PriorityQueue<Dugum> kuyruk =
			new PriorityQueue<>((a, b) -> a.f - b.f);
		for (int k = 0; k < h.length; k++)
			kuyruk.add(new Dugum(h[k], f[k]));
		while (kuyruk.size() > 1) {
			Dugum sol = kuyruk.poll();
			Dugum sag = kuyruk.poll();
			Dugum ata = new Dugum('\0', sol.f + sag.f);
			ata.sol = sol;
			ata.sag = sag;
			kuyruk.add(ata);
		}
		return kuyruk.poll();
	}

	static void kodUret(Dugum d, String kod) {
		if (d.sol == null) {      // yaprak
			System.out.println(d.h + " = " + kod);
			return;
		}
		kodUret(d.sol, kod + "0"); // sol: 0
		kodUret(d.sag, kod + "1"); // sag: 1
	}

	public static void main(String[] args) {
		// PriorityQueue esit frekanslarda (G4-R4, (5)-E5) sirayi garanti etmez; bu giris sirasi
		// sunumdaki agaci verir. Baska bir sira farkli kodlar uretebilir, kod uzunluklari (63 bit) degismez.
		char[] h = {'F', 'O', 'E', 'R', 'G', 'T'};
		int[] f  = { 2,   3,   5,   4,   4,   7 };
		Dugum kok = huffman(h, f);
		System.out.println(kok.f);   // 25
		kodUret(kok, "");
		// F = 000
		// O = 001
		// E = 01
		// T = 10
		// G = 110
		// R = 111
	}
}
