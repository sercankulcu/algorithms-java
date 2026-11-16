import java.util.ArrayList;
import java.util.List;

// Trie (onek agaci): ekleme, arama, onek sorgusu, joker arama, otomatik tamamlama, en uzun ortak onek, silme.
// Ekleme/arama O(L), onek sorgusu O(P); alfabe 'a'..'z' (M = 26).
public class Trie {

	public static class TrieDugumu {
		TrieDugumu[] cocuk;  // 'a'..'z'
		boolean kelimeSonu;  // kelime biter mi?

		public TrieDugumu() {
			kelimeSonu = false;
			cocuk = new TrieDugumu[26];
		}
	}

	TrieDugumu kok = new TrieDugumu();

	public void ekle(String kelime) {
		TrieDugumu dugum = kok;
		for (char ch : kelime.toCharArray()) {
			int i = ch - 'a';
			if (dugum.cocuk[i] == null)
				dugum.cocuk[i] = new TrieDugumu();
			dugum = dugum.cocuk[i];
		}
		dugum.kelimeSonu = true;
	}

	public boolean ara(String kelime) {
		TrieDugumu dugum = kok;
		for (char ch : kelime.toCharArray()) {
			int i = ch - 'a';
			if (dugum.cocuk[i] == null)
				return false;
			dugum = dugum.cocuk[i];
		}
		return dugum.kelimeSonu;
	}

	public boolean onekVarMi(String onek) {
		TrieDugumu dugum = kok;
		for (char ch : onek.toCharArray()) {
			int i = ch - 'a';
			if (dugum.cocuk[i] == null)
				return false;
			dugum = dugum.cocuk[i];
		}
		return true;  // ara: dugum.kelimeSonu
	}

	// '.' her harfle eslesir; cagri: joker(kok, desen, 0)
	boolean joker(TrieDugumu d, String s, int j) {
		if (d == null) return false;
		if (j == s.length()) return d.kelimeSonu;
		char ch = s.charAt(j);
		if (ch != '.')
			return joker(d.cocuk[ch - 'a'], s, j + 1);
		for (int i = 0; i < 26; i++)
			if (joker(d.cocuk[i], s, j + 1)) return true;
		return false;
	}

	// onek yolunun sonundaki dugum; yol yoksa null
	TrieDugumu dugumBul(String onek) {
		TrieDugumu d = kok;
		for (char ch : onek.toCharArray()) {
			d = d.cocuk[ch - 'a'];
			if (d == null) return null;
		}
		return d;
	}

	List<String> tamamla(String onek) {
		List<String> sonuc = new ArrayList<>();
		TrieDugumu d = dugumBul(onek);
		if (d != null)
			topla(d, new StringBuilder(onek), sonuc);
		return sonuc;
	}

	void topla(TrieDugumu d, StringBuilder yol,
			List<String> sonuc) {
		if (d.kelimeSonu) sonuc.add(yol.toString());
		for (int i = 0; i < 26; i++)
			if (d.cocuk[i] != null) {
				yol.append((char) ('a' + i));
				topla(d.cocuk[i], yol, sonuc);
				yol.setLength(yol.length() - 1);
			}
	}

	String enUzunOrtakOnek() {
		StringBuilder sb = new StringBuilder();
		TrieDugumu d = kok;
		while (!d.kelimeSonu) {
			int tek = -1, sayi = 0;
			for (int i = 0; i < 26; i++)
				if (d.cocuk[i] != null) { sayi++; tek = i; }
			if (sayi != 1) break;  // dal ya da yaprak
			sb.append((char) ('a' + tek));
			d = d.cocuk[tek];
		}
		return sb.toString();
	}

	// 26 cocugun hepsi null mi? O(M)
	boolean bosMu(TrieDugumu d) {
		for (TrieDugumu c : d.cocuk)
			if (c != null) return false;
		return true;
	}

	// true: ebeveyn d'yi silebilir; cagri: sil(kok, kelime, 0)
	boolean sil(TrieDugumu d, String k, int j) {
		if (j == k.length()) {        // kelime bitti
			if (!d.kelimeSonu) return false;
			d.kelimeSonu = false;
			return bosMu(d);            // cocuksuz mu?
		}
		int i = k.charAt(j) - 'a';
		TrieDugumu c = d.cocuk[i];
		if (c == null) return false;  // kelime yok
		if (sil(c, k, j + 1)) {
			d.cocuk[i] = null;          // cocugu sil
			return !d.kelimeSonu && bosMu(d);
		}
		return false;
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		for (String k : new String[] {"and", "ant", "dad", "do"}) t.ekle(k);
		System.out.println(t.ara("dad") + " " + t.onekVarMi("dad"));  // true true
		System.out.println(t.ara("da") + " " + t.onekVarMi("da"));    // false true
		System.out.println(t.ara("an") + " " + t.onekVarMi("an"));    // false true
		System.out.println(t.ara("dog") + " " + t.onekVarMi("dog"));  // false false

		System.out.println(t.joker(t.kok, "d.d", 0));   // true
		System.out.println(t.joker(t.kok, ".n.", 0));   // true
		System.out.println(t.joker(t.kok, ".o", 0));    // true
		System.out.println(t.joker(t.kok, "b..", 0));   // false
		System.out.println(t.joker(t.kok, "....", 0));  // false

		t.sil(t.kok, "dad", 0);
		System.out.println(t.tamamla(""));  // [and, ant, do]

		Trie t2 = new Trie();
		for (String k : new String[] {"a", "in", "inn", "tea", "ted", "ten", "to"}) t2.ekle(k);
		System.out.println(t2.tamamla("te"));  // [tea, ted, ten]

		Trie t3 = new Trie();
		for (String k : new String[] {"kar", "kart", "karton"}) t3.ekle(k);
		System.out.println(t3.enUzunOrtakOnek());  // kar

		Trie t4 = new Trie();
		for (String k : new String[] {"kart", "karton"}) t4.ekle(k);
		System.out.println(t4.enUzunOrtakOnek());  // kart
	}
}

/* Beklenen cikti:
true true
false true
false true
false false
true
true
true
false
false
[and, ant, do]
[tea, ted, ten]
kar
kart
*/
