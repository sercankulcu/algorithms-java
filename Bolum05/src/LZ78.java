import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LZ78: bos sozlukle baslar; sozlukteki en uzun parca w ve sonraki karakter c, (indis(w), c) olarak yazilir.
// Sozluk karma tabloyla tutulursa O(n) beklenen zaman.
public class LZ78 {

	static List<String> lz78(String s) {
		Map<String, Integer> sozluk =
			new HashMap<>();
		List<String> cikti = new ArrayList<>();
		String w = "";
		for (char c : s.toCharArray()) {
			if (sozluk.containsKey(w + c)) {
				w = w + c;               // uzat
			} else {
				int k = w.isEmpty() ? 0
					: sozluk.get(w);
				cikti.add("(" + k + ", " + c + ")");
				sozluk.put(w + c, sozluk.size() + 1);
				w = "";
			}
		}
		if (!w.isEmpty())        // son parca
			cikti.add("(" + sozluk.get(w) + ")");
		return cikti;
	}

	public static void main(String[] args) {
		System.out.println(lz78("aabaacabcabcb"));  // [(0, a), (1, b), (1, a), (0, c), (2, c), (5, b)]
		System.out.println(lz78("aaaa"));           // [(0, a), (1, a), (1)]
	}
}
