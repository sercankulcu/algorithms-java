import java.util.HashSet;
import java.util.Set;

// Dugum kapsama 2-yaklasimi: iki ucu da kapsanmamis her kenarin iki ucu eklenir (maksimal eslestirme).
// O(m) zaman; sonuc en cok 2 * OPT.
public class DugumKapsama {

	static Set<Character> dugumKapsama(char[][] kenarlar) {
		Set<Character> kapsama = new HashSet<>();
		for (char[] k : kenarlar) {
			char u = k[0], v = k[1];
			if (!kapsama.contains(u) && !kapsama.contains(v)) {
				kapsama.add(u); kapsama.add(v); }  // iki uc
		}
		return kapsama;
	}

	public static void main(String[] args) {
		String[] liste = {"AB", "AC", "AD", "AE", "AF", "BC", "GH", "GI", "HI", "HK", "IJ", "JK", "JL"};
		char[][] kenarlar = new char[liste.length][];
		for (int i = 0; i < liste.length; i++) kenarlar[i] = liste[i].toCharArray();
		Set<Character> k = dugumKapsama(kenarlar);
		System.out.println(k + ", boyut " + k.size());   // [A, B, G, H, I, J], boyut 6 (optimum 5)
	}
}

/* Beklenen cikti:
[A, B, G, H, I, J], boyut 6
*/
