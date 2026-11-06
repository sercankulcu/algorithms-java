// Hash ile arama: anahtar dogrudan kovasina gider, ortalama O(1).
// Carpismalar dogrusal yoklama ile cozulur; kumelenmede en kotu O(n).
public class HashArama {

	static int hashFonksiyonu(String anahtar,
							  int m) {
		int toplam = 0;
		for (char k : anahtar.toCharArray())
			toplam += k;        // Unicode kodu
		return toplam % m;    // m: tablo boyu
	}

	// Yardimci: dogrusal yoklama ile ekleme (tablo dolu degilken)
	static void hashEkle(Integer[] tablo, int anahtar) {
		int m = tablo.length;
		int indis = anahtar % m;
		while (tablo[indis] != null)
			indis = (indis + 1) % m;
		tablo[indis] = anahtar;
	}

	static int hashArama(Integer[] tablo, int anahtar) {
		int m = tablo.length;
		int indis = anahtar % m;   // hash
		while (tablo[indis] != null) {
			if (tablo[indis] == anahtar)
				return indis;          // bulundu
			indis = (indis + 1) % m; // sonraki kova
		}
		return -1;                 // bos kova: yok
	}

	public static void main(String[] args) {
		System.out.println(hashFonksiyonu("veli", 10));   // 2 (toplam 432)
		System.out.println(hashFonksiyonu("ali", 10));    // 0
		System.out.println(hashFonksiyonu("ila", 10));    // 0 (ali ile carpisir)

		Integer[] tablo = new Integer[10];
		int[] anahtarlar = {16, 12, 45, 29, 6, 122, 5, 58, 75};
		for (int x : anahtarlar)
			hashEkle(tablo, x);
		System.out.println(java.util.Arrays.toString(tablo));
		// [58, 75, 12, 122, null, 45, 16, 6, 5, 29]
		System.out.println(hashArama(tablo, 75));   // 1 (7 yoklama)
		System.out.println(hashArama(tablo, 14));   // -1 (kova 4 bos)
	}
}
