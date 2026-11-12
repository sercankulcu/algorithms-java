import java.util.ArrayList;
import java.util.List;

// Boruvka: her turda her bilesen kendi en ucuz dis kenarini secer ve hepsi birlikte eklenir.
// Her turda bilesen sayisi en az yariya iner: en cok log V tur, O(E log V).
public class Boruvka {

	static class Kenar {
		int u, v, w;
		Kenar(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public String toString() { return "" + AD.charAt(u) + AD.charAt(v) + "(" + w + ")"; }
	}

	static final String AD = "ABCDEF";

	static int bul(int[] ata, int i) {
		if (ata[i] != i)
			ata[i] = bul(ata, ata[i]); // yol sikistirma
		return ata[i];
	}

	// BORUVKA(G): sunumdaki dongu, ilklendirme ve donus eklenerek
	static List<Kenar> boruvka(int V, List<Kenar> kenarlar) {
		int[] ata = new int[V];
		for (int i = 0; i < V; i++) ata[i] = i;
		List<Kenar> agac = new ArrayList<>();
		int bilesenSayisi = V;
		while (bilesenSayisi > 1) {
			Kenar[] enUcuz = new Kenar[V];
			for (Kenar e : kenarlar) {        // 1) en ucuzlar
				int k1 = bul(ata, e.u), k2 = bul(ata, e.v);
				if (k1 == k2) continue;         // ayni bilesen
				if (enUcuz[k1] == null || e.w < enUcuz[k1].w)
					enUcuz[k1] = e;
				if (enUcuz[k2] == null || e.w < enUcuz[k2].w)
					enUcuz[k2] = e;
			}
			for (Kenar e : enUcuz) {          // 2) hepsini ekle
				if (e == null) continue;
				int k1 = bul(ata, e.u), k2 = bul(ata, e.v);
				if (k1 == k2) continue;         // zaten eklendi
				agac.add(e);
				ata[k1] = k2;                   // birlestir
				bilesenSayisi--;
			}
		}
		return agac;
	}

	public static void main(String[] args) {
		// A-F cizgesi, kenarlar bu sirayla taranir
		String[] uv = { "AB", "BC", "AD", "BD", "BE", "BF", "CF", "DE", "EF" };
		int[] w =     {  1,    5,    4,    7,    3,    6,    9,    8,    2  };
		List<Kenar> kenarlar = new ArrayList<>();
		for (int i = 0; i < uv.length; i++)
			kenarlar.add(new Kenar(AD.indexOf(uv[i].charAt(0)), AD.indexOf(uv[i].charAt(1)), w[i]));
		List<Kenar> agac = boruvka(AD.length(), kenarlar);
		System.out.println(agac);   // [AB(1), BC(5), AD(4), EF(2), BE(3)]
		int toplam = 0;
		for (Kenar e : agac) toplam += e.w;
		System.out.println(toplam); // 15  (1. tur 4 kenar, 2. tur BE)
	}
}

/* Beklenen cikti:
[AB(1), BC(5), AD(4), EF(2), BE(3)]
15
*/
