// Atlayarak arama (jump search): kok(n) boyutlu bloklarla atla, sonra blok icinde dogrusal ara.
// O(kok n) karsilastirma; sirali dizi gerekir.
public class AtlayarakArama {

	static int atlayarakAraBul(int[] dizi, int eleman,
							   int n) {
		int blok = (int) Math.sqrt(n);
		int atlama = blok, konum = 0;
		while (dizi[Math.min(atlama, n) - 1] < eleman) {
			konum = atlama; atlama += blok; // atla
			if (konum >= n) return -1;
		}
		while (dizi[konum] < eleman) {     // dogrusal
			konum++;
			if (konum == Math.min(atlama, n)) return -1;
		}
		if (dizi[konum] == eleman) return konum;
		return -1;
	}

	public static void main(String[] args) {
		int[] dizi = {2, 9, 30, 32, 38, 47, 61, 69, 79, 81};
		System.out.println(atlayarakAraBul(dizi, 69, 10));   // 7
		System.out.println(atlayarakAraBul(dizi, 50, 10));   // -1
		System.out.println(atlayarakAraBul(dizi, 99, 10));   // -1
	}
}

/* Beklenen cikti:
7
-1
-1
*/
