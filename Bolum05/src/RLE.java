// Calistirma uzunlugu kodlamasi (RLE): ardisik ayni karakterler "sayi + karakter" olarak yazilir.
// Zaman O(n): her karakter bir kez okunur.
public class RLE {

	static String rleSikistir(String s) {
		StringBuilder sonuc = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int sayac = 1;
			while (i + 1 < n &&
				s.charAt(i + 1) == c) {
				sayac++;
				i++;
			}
			sonuc.append(sayac).append(c);
		}
		return sonuc.toString();
	}

	public static void main(String[] args) {
		System.out.println(rleSikistir("AAAAABBBCCCCDDD"));  // 5A3B4C3D
		System.out.println(rleSikistir("AAAAABB"));          // 5A2B
	}
}
