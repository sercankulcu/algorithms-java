// Adim sayma ve O(1) / O(n) ornekleri: dizi toplami O(n), ilk eleman O(1), en buyuk O(n).
public class ZamanKarmasikligi {

	// Sunumdaki "Zaman Karmasikligi" parcasi: 2n + 3 adim -> O(n)
	static int toplamBul(int[] dizi, int n) {
		int toplam = 0;                   // 1 kez
		for (int i = 0; i < n; i++)       // n+1 karsilastirma
			toplam += dizi[i];            // n kez
		return toplam;                    // 1 kez
	}

	// O(1): dizi boyutundan bagimsiz
	static void ilkElemaniYazdir(int[] dizi) {
		if (dizi.length > 0)
			System.out.println("Ilk eleman: "
					+ dizi[0]);
		else
			System.out.println("Dizi bos");
	}

	// O(n): her eleman bir kez incelenir
	static Integer enBuyukBul(int[] dizi) {
		if (dizi.length == 0)
			return null;            // dizi bossa
		int enBuyuk = dizi[0];
		for (int eleman : dizi)
			if (eleman > enBuyuk)
				enBuyuk = eleman;
		return enBuyuk;
	}

	public static void main(String[] args) {
		int[] dizi = {7, 3, 9, 4, 8, 1};
		System.out.println(toplamBul(dizi, dizi.length));   // 32
		ilkElemaniYazdir(dizi);                             // Ilk eleman: 7
		ilkElemaniYazdir(new int[0]);                       // Dizi bos
		System.out.println(enBuyukBul(dizi));               // 9
		System.out.println(enBuyukBul(new int[0]));         // null
	}
}
