// Ikili trie ile en uzun onek eslesmesi (IP yonlendirme): yol boyunca gorulen en derin rota kazanir.
// Zaman O(adres uzunlugu).
public class IkiliTrie {

	static class IkiliDugum {
		IkiliDugum[] cocuk = new IkiliDugum[2];  // '0', '1'
		String rota;                             // bu onege bagli rota (yoksa null)
	}

	IkiliDugum kok = new IkiliDugum();

	// onek* -> rota kaydi
	void rotaEkle(String onek, String rota) {
		IkiliDugum d = kok;
		for (char ch : onek.toCharArray()) {
			int i = ch - '0';
			if (d.cocuk[i] == null) d.cocuk[i] = new IkiliDugum();
			d = d.cocuk[i];
		}
		d.rota = rota;
	}

	String enUzunEslesme(String adres) {
		IkiliDugum d = kok;
		String enIyi = null;
		for (int k = 0; k < adres.length(); k++) {
			if (d.rota != null) enIyi = d.rota;  // daha uzun onek
			d = d.cocuk[adres.charAt(k) - '0'];
			if (d == null) return enIyi;
		}
		return d.rota != null ? d.rota : enIyi;
	}

	public static void main(String[] args) {
		IkiliTrie t = new IkiliTrie();
		t.rotaEkle("0", "A");
		t.rotaEkle("01", "B");
		t.rotaEkle("011", "C");
		t.rotaEkle("1", "D");
		t.rotaEkle("110", "E");
		System.out.println(t.enUzunEslesme("0110"));  // C
		System.out.println(t.enUzunEslesme("1001"));  // D
		System.out.println(t.enUzunEslesme("1101"));  // E
	}
}
