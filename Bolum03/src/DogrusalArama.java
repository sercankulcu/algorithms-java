// Dogrusal arama: sirasiz dizide bastan sona tarama, en kotu O(n).
// Bekcili surum dongudeki i < n denetimini kaldirir: n + 2 karsilastirma (klasik: 2n).
public class DogrusalArama {

	static int dogrusalArama(int[] dizi, int n,
							 int aranan) {
		int i = 0;
		while (i < n && dizi[i] != aranan)
			i++;
		if (i < n)
			return i;      // bulundu: indis
		else
			return -1;     // bulunamadi
	}

	static int gozcuIleAra(int[] dizi, int n,
						   int aranan) {
		int sonEleman = dizi[n - 1];
		dizi[n - 1] = aranan;     // bekci
		int i = 0;
		while (dizi[i] != aranan)
			i++;
		dizi[n - 1] = sonEleman;  // geri koy
		if (i < n - 1 || sonEleman == aranan)
			return i;
		return -1;
	}

	public static void main(String[] args) {
		int[] dizi = {33, 21, 12, 7, 8, 1, 3, 44, 56, 18};
		System.out.println(dogrusalArama(dizi, 10, 33));   // 0 (en iyi durum)
		System.out.println(dogrusalArama(dizi, 10, 1));    // 5
		System.out.println(dogrusalArama(dizi, 10, 99));   // -1 (en kotu durum)

		int[] d = {7, 3, 9, 4, 6};
		System.out.println(gozcuIleAra(d, 5, 5));          // -1
		System.out.println(gozcuIleAra(d, 5, 6));          // 4 (aranan son ogedir)
		System.out.println(gozcuIleAra(d, 5, 9));          // 2
	}
}
