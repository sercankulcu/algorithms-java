// Kayak kiralama, basa bas stratejisi: B - 1 gun kirala, B. gun satin al.
// Rekabet orani 2 - 1/B (deterministik algoritmalar icin en iyisi); gun basina O(1).
public class KayakKiralama {

	static int basaBas(int B, int d) {  // d bilinmez
		int maliyet = 0;
		for (int gun = 1; gun <= d; gun++) {
			if (gun < B) {
				maliyet += 1;             // kirala
			} else {
				maliyet += B;             // satin al
				break;                    // ucret biter
			}
		}
		return maliyet;
	}

	public static void main(String[] args) {
		int B = 4, d = 6;
		int alg = basaBas(B, d), opt = Math.min(d, B);
		System.out.println(alg + " / " + opt + " = " + (double) alg / opt);   // 7 / 4 = 1.75
		System.out.println(basaBas(10, 10) + " / " + Math.min(10, 10));      // 19 / 10 (en kotu durum)
		System.out.println(basaBas(10, 5));                                 // 5 (= OPT)
	}
}

/* Beklenen cikti:
7 / 4 = 1.75
19 / 10
5
*/
