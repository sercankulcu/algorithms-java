// Para ustu: kalan tutardan buyuk olmayan en buyuk para secilir. Zaman O(k + adet).
// Kanonik sistemlerde ({50, 25, 10, 5, 1}) en iyi; {4, 3, 1} gibi sistemlerde en iyi olmayabilir.
public class ParaUstu {

	// para[] buyukten kucuge sirali
	static int paraUstu(int[] para, int tutar) {
		int adet = 0;
		for (int p : para) {
			while (tutar >= p) {
				tutar -= p;
				adet++;
			}
		}
		return adet;
	}

	public static void main(String[] args) {
		System.out.println(paraUstu(new int[]{50, 25, 10, 5, 1}, 68));   // 6: 50 + 10 + 5 + 1 + 1 + 1
		System.out.println(paraUstu(new int[]{4, 3, 1}, 6));             // 3: 4 + 1 + 1 (en iyisi 3 + 3)
		System.out.println(paraUstu(new int[]{4, 3, 1}, 10));            // 4: 4 + 4 + 1 + 1 (en iyisi 4 + 3 + 3)
	}
}

/* Beklenen cikti:
6
3
4
*/
