// FSM ile sayi ayristirma: BASLA, RAKAM, ONDALIK durumlariyla metindeki sayilar ayiklanir.
// Zaman O(n). Kusur: sondaki nokta da sayiya katilir ("5.").
public class SayiAyristirma {

	enum Durum { BASLA, RAKAM, ONDALIK }

	static void sayilariBul(String s) {
		Durum d = Durum.BASLA;
		StringBuilder tampon = new StringBuilder();
		for (char c : s.toCharArray()) {
			boolean rakam = Character.isDigit(c);
			if (rakam) {
				if (d == Durum.BASLA) d = Durum.RAKAM;
				tampon.append(c);
			} else if (c == '.' && d != Durum.ONDALIK) {
				d = Durum.ONDALIK;
				tampon.append(c);
			} else if (d != Durum.BASLA) {  // sayi bitti
				System.out.println(tampon);
				tampon.setLength(0);
				d = Durum.BASLA;
			}
		}
		if (d != Durum.BASLA) System.out.println(tampon);
	}

	public static void main(String[] args) {
		sayilariBul("$20.99 ve 5.");
		// 20.99
		// 5.
	}
}

/* Beklenen cikti:
20.99
5.
*/
