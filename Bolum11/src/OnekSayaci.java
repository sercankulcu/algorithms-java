// Onek sayaci: her dugumde "gecen" alani, o dugumden gecen kelime sayisini tutar.
// onekSayisi O(P); bedeli dugum basina bir int.
public class OnekSayaci {

	static class TrieDugumu {
		TrieDugumu[] cocuk = new TrieDugumu[26];  // 'a'..'z'
		boolean kelimeSonu;                       // kelime biter mi?
		int gecen;                                // buradan gecen kelime sayisi
	}

	TrieDugumu kok = new TrieDugumu();

	void ekle(String kelime) {
		TrieDugumu d = kok;
		d.gecen++;
		for (char ch : kelime.toCharArray()) {
			int i = ch - 'a';
			if (d.cocuk[i] == null) d.cocuk[i] = new TrieDugumu();
			d = d.cocuk[i];
			d.gecen++;          // bir kelime daha gecti
		}
		d.kelimeSonu = true;
	}

	int onekSayisi(String onek) {
		TrieDugumu d = kok;
		for (char ch : onek.toCharArray()) {
			d = d.cocuk[ch - 'a'];
			if (d == null) return 0;
		}
		return d.gecen;
	}

	public static void main(String[] args) {
		OnekSayaci t = new OnekSayaci();
		for (String k : new String[] {"and", "ant", "dad", "do"}) t.ekle(k);
		System.out.println(t.kok.gecen);         // 4
		System.out.println(t.onekSayisi("an"));  // 2
		System.out.println(t.onekSayisi("da"));  // 1
		System.out.println(t.onekSayisi("x"));   // 0
	}
}

/* Beklenen cikti:
4
2
1
0
*/
