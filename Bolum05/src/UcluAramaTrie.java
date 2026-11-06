// Uclu arama trie'si (TST): her dugumde bir karakter ve sol (<), orta (=), sag (>) baglantilari.
// Arama O(L + log N) beklenen; bellek dugum basina 3 baglanti.
public class UcluAramaTrie {

	static class TstDugum {
		char c;
		TstDugum sol, orta, sag;
		boolean son;   // kelime burada biter mi?

		TstDugum(char c) {
			this.c = c;
		}
	}

	TstDugum kok;

	void ekle(String s) {
		kok = ekle(kok, s, 0);
	}

	TstDugum ekle(TstDugum d, String s, int i) {
		char ch = s.charAt(i);
		if (d == null) d = new TstDugum(ch);
		if (ch < d.c) d.sol = ekle(d.sol, s, i);
		else if (ch > d.c) d.sag = ekle(d.sag, s, i);
		else if (i < s.length() - 1) d.orta = ekle(d.orta, s, i + 1);
		else d.son = true;
		return d;
	}

	boolean ara(String s) {
		TstDugum d = kok;
		int i = 0;
		while (d != null) {
			char ch = s.charAt(i);
			if (ch < d.c) d = d.sol;
			else if (ch > d.c) d = d.sag;
			else if (i == s.length() - 1) return d.son;
			else { d = d.orta; i++; }
		}
		return false;
	}

	public static void main(String[] args) {
		UcluAramaTrie t = new UcluAramaTrie();
		for (String k : new String[] {"kar", "kal", "at", "ve", "kas"}) t.ekle(k);
		System.out.println(t.ara("kas"));  // true
		System.out.println(t.ara("al"));   // false
		System.out.println(t.ara("ka"));   // false
	}
}
