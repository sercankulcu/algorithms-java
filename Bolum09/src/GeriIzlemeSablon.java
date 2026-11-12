import java.util.ArrayList;
import java.util.List;

// Geri izleme genel sablonu: sec, kesfet, geri al; gecersiz adaylar budanir.
// Ornek ornekleme: toplami hedefe esit alt kumeler (pozitif ogeler, toplam hedefi asarsa budanir).
public class GeriIzlemeSablon {

	static class Aday {
		int indis;
		Aday(int indis) { this.indis = indis; }
	}

	// Kismi cozum: secilen indisler ve toplamlari
	static class Durum {
		int[] a;
		int hedef, toplam;
		List<Integer> secim = new ArrayList<>();
		Durum(int[] a, int hedef) { this.a = a; this.hedef = hedef; }
	}

	static boolean cozumMu(Durum kismi) { return kismi.toplam == kismi.hedef; }

	static void kaydet(Durum kismi) {
		List<Integer> s = new ArrayList<>();
		for (int i : kismi.secim) s.add(kismi.a[i]);
		System.out.print(s + " ");
	}

	// Adaylar: son secilen indisten sonraki ogeler (her alt kume bir kez uretilir)
	static List<Aday> adaylar(Durum kismi) {
		int bas = kismi.secim.isEmpty() ? 0 : kismi.secim.get(kismi.secim.size() - 1) + 1;
		List<Aday> l = new ArrayList<>();
		for (int i = bas; i < kismi.a.length; i++) l.add(new Aday(i));
		return l;
	}

	static boolean gecerliMi(Durum kismi, Aday a) {
		return kismi.toplam + kismi.a[a.indis] <= kismi.hedef;   // budama
	}

	static void ekle(Durum kismi, Aday a) {
		kismi.secim.add(a.indis);
		kismi.toplam += kismi.a[a.indis];
	}

	static void cikar(Durum kismi, Aday a) {
		kismi.secim.remove(kismi.secim.size() - 1);
		kismi.toplam -= kismi.a[a.indis];
	}

	static void geriDon(Durum kismi) {
		if (cozumMu(kismi)) {
			kaydet(kismi);
			return;
		}
		for (Aday a : adaylar(kismi)) {
			if (gecerliMi(kismi, a)) {
				ekle(kismi, a);    // sec
				geriDon(kismi);    // kesfet
				cikar(kismi, a);   // geri al
			}
		}
	}

	public static void main(String[] args) {
		geriDon(new Durum(new int[]{3, 34, 4, 12, 5, 2}, 9));
		System.out.println();   // [3, 4, 2] [4, 5]
	}
}

/* Beklenen cikti:
[3, 4, 2] [4, 5]
*/
