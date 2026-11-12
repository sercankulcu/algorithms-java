// O(kok n) asal sayi kontrolu: yalnizca 3, 5, 7, ... < kok(n) + 1 tek bolenler denenir.
public class AsalSayi {

	static boolean asalMi(int n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		else if (n % 2 == 0)
			return false;
		int kokN = (int) Math.sqrt(n) + 1;
		for (int i = 3; i < kokN; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(asalMi(97));   // true (yalnizca 4 bolme: 3, 5, 7, 9)
		System.out.println(asalMi(1));    // false
		System.out.println(asalMi(2));    // true
		System.out.println(asalMi(91));   // false (7 * 13)
	}
}

/* Beklenen cikti:
true
false
true
false
*/
