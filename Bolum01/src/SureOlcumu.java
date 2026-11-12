// Sure olcumu ve adim sayma: dogrusal arama en kotu durumda 3n + 3 adim -> Theta(n).
// Sunumdaki 10^8 eleman bellek icin buyuk oldugundan 10^7 eleman kullanilir.
public class SureOlcumu {

	static int dogrusalArama(int[] a, int hedef) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == hedef)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] dizi = new int[10_000_000];
		for (int i = 0; i < dizi.length; i++)
			dizi[i] = i % 10000;              // 10001 dizide yok: en kotu durum

		long baslangic = System.currentTimeMillis();
		dogrusalArama(dizi, 10001);  // 10^7 eleman
		long bitis = System.currentTimeMillis();
		System.out.println("Gecen sure: "
			+ (bitis - baslangic) + " ms");  // makineye gore degisir

		System.out.println(dogrusalArama(new int[]{4, 7, 2}, 5));   // -1 (12 adim = 3n + 3)
		int[] b = {7, 3, 9, 4, 8, 1};
		System.out.println(dogrusalArama(b, 7));   // 0 (en iyi durum)
		System.out.println(dogrusalArama(b, 1));   // 5 (en kotu durum)
	}
}

/* Not: bu programin ciktisi her calistirmada degisir (zaman olcumu, rastgele sayi ya da
   is parcaciklarinin sirasi); asagidaki bir ornek calistirmadir. */
/* Beklenen cikti:
Gecen sure: 7 ms
-1
0
5
*/
