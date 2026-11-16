import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// LZW sikistirma ve acma: sozluk tek karakterlerle baslar, her adimda w + c eklenir.
// Sozluk karma tabloyla tutulursa O(n) beklenen zaman.
public class LZW {

	// Sikistirici icin ilk sozluk: metindeki karakterler alfabetik sirayla (A=0, B=1, C=2)
	static Map<String, Integer> ilkSozluk(String s) {
		Map<String, Integer> sozluk = new HashMap<>();
		TreeSet<Character> harfler = new TreeSet<>();
		for (char c : s.toCharArray()) harfler.add(c);
		for (char c : harfler) sozluk.put("" + c, sozluk.size());
		return sozluk;
	}

	// Acici icin ilk sozluk: 0..255 ASCII
	static List<String> ilkSozluk() {
		List<String> sozluk = new ArrayList<>();
		for (int i = 0; i < 256; i++) sozluk.add("" + (char) i);
		return sozluk;
	}

	static List<Integer> lzw(String s) {
		Map<String, Integer> sozluk =
			ilkSozluk(s);  // A=0, B=1, C=2
		String w = "";
		List<Integer> kod = new ArrayList<>();
		for (char c : s.toCharArray()) {
			String wc = w + c;
			if (sozluk.containsKey(wc)) {
				w = wc;
			} else {
				kod.add(sozluk.get(w));
				sozluk.put(wc, sozluk.size());
				w = "" + c;
			}
		}
		if (!w.isEmpty())
			kod.add(sozluk.get(w));
		return kod;
	}

	static String lzwAc(List<Integer> kod) {
		List<String> sozluk =
			ilkSozluk();      // 0..255: ASCII
		String w = sozluk.get(kod.get(0));
		StringBuilder s = new StringBuilder(w);
		for (int i = 1; i < kod.size(); i++) {
			int k = kod.get(i);
			String e = k < sozluk.size()
				? sozluk.get(k)
				: w + w.charAt(0); // ozel durum
			s.append(e);
			sozluk.add(w + e.charAt(0));
			w = e;
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(lzw("ABABCABABCA"));  // [0, 1, 3, 2, 3, 5, 0]

		List<Integer> kod = Arrays.asList(97, 97, 98, 256, 99, 257, 261, 99, 98);
		System.out.println(lzwAc(kod));          // aabaacababacb

		// Ozel durum: k henuz sozlukte yok (k == sozluk.size()) -> e = w + w[0]
		System.out.println(lzwAc(Arrays.asList(97, 256)));  // aaa
	}
}

/* Beklenen cikti:
[0, 1, 3, 2, 3, 5, 0]
aabaacababacb
aaa
*/
