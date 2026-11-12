// Sonek trie'si: metnin tum sonekleri bir trie'ye eklenir; her alt dizgi kokten bir yoldur.
// Kurulum O(n^2) zaman ve dugum; alt dizgi sorgusu O(m).
public class SonekTrie {

	static class TrieDugumu {
		TrieDugumu[] cocuk = new TrieDugumu[26];  // 'a'..'z'
		boolean kelimeSonu;                       // sonek biter mi?
	}

	static TrieDugumu sonekTrie(String t) {
		TrieDugumu kok = new TrieDugumu();
		for (int s = 0; s < t.length(); s++) {
			TrieDugumu d = kok;
			for (int j = s; j < t.length(); j++) {
				int i = t.charAt(j) - 'a';
				if (d.cocuk[i] == null)
					d.cocuk[i] = new TrieDugumu();
				d = d.cocuk[i];
			}
			d.kelimeSonu = true;  // sonek bitti
		}
		return kok;
	}

	// p kokten bir yol mu? (p, metnin alt dizgisi mi?)
	static boolean altDizgiMi(TrieDugumu kok, String p) {
		TrieDugumu d = kok;
		for (char ch : p.toCharArray()) {
			d = d.cocuk[ch - 'a'];
			if (d == null) return false;
		}
		return true;
	}

	static int dugumSayisi(TrieDugumu d) {
		int say = 1;
		for (TrieDugumu c : d.cocuk)
			if (c != null) say += dugumSayisi(c);
		return say;
	}

	public static void main(String[] args) {
		TrieDugumu kok = sonekTrie("abab");
		System.out.println(dugumSayisi(kok));       // 8
		System.out.println(altDizgiMi(kok, "ba"));  // true
		System.out.println(altDizgiMi(kok, "bb"));  // false
	}
}

/* Beklenen cikti:
8
true
false
*/
