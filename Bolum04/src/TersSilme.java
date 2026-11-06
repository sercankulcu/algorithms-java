import java.util.ArrayList;
import java.util.List;

// Ters silme: kenarlar azalan agirlikla denenir; cizgeyi koparmayan kenar silinir.
// Her kenar icin bir baglilik denetimi: O(E * (V + E)).
public class TersSilme {

	static class Kenar {
		int u, v, w;
		Kenar(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public String toString() { return "" + AD.charAt(u) + AD.charAt(v) + "(" + w + ")"; }
	}

	static final String AD = "ABCDEFG";

	static int bul(int[] ata, int i) {
		if (ata[i] != i)
			ata[i] = bul(ata, ata[i]); // yol sikistirma
		return ata[i];
	}

	// Kalan kenarlarla butun dugumler tek bilesende mi? (ayrik kumeler sifirdan kurulur)
	static boolean bagliMi(int V, List<Kenar> ke) {
		int[] ata = new int[V];
		for (int i = 0; i < V; i++) ata[i] = i;
		int bilesen = V;
		for (Kenar e : ke) {
			int x = bul(ata, e.u), y = bul(ata, e.v);
			if (x != y) { ata[x] = y; bilesen--; }
		}
		return bilesen == 1;
	}

	static void tersSil(int V, List<Kenar> ke) {
		ke.sort((a, b) -> b.w - a.w); // azalan
		int i = 0;
		while (i < ke.size()) {
			Kenar e = ke.remove(i);     // gecici sil
			if (!bagliMi(V, ke)) {
				ke.add(i, e);             // geri koy
				i++;
			}
		}
	}

	public static void main(String[] args) {
		// A-G: 7 dugum, 11 kenar, toplam 90
		String[] uv = { "AB", "AD", "BC", "BD", "BE", "CE", "DE", "DF", "EF", "EG", "FG" };
		int[] w =     {  7,    5,    8,    9,    7,    5,    15,   6,    8,    9,    11  };
		List<Kenar> ke = new ArrayList<>();
		for (int i = 0; i < uv.length; i++)
			ke.add(new Kenar(AD.indexOf(uv[i].charAt(0)), AD.indexOf(uv[i].charAt(1)), w[i]));
		tersSil(AD.length(), ke);
		System.out.println(ke);     // [EG(9), AB(7), BE(7), DF(6), AD(5), CE(5)]
		int toplam = 0;
		for (Kenar e : ke) toplam += e.w;
		System.out.println(toplam); // 39
	}
}
