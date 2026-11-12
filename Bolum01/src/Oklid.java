// Oklid algoritmasi: ebob(a, b) = ebob(b, a % b). Bolme sayisi O(log min(a, b)).
public class Oklid {

	static int ebob(int a, int b) {
		if (b == 0)
			return a;
		return ebob(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(ebob(1599, 650));   // 13 (5 bolme)
		System.out.println(ebob(48, 18));      // 6
	}
}

/* Beklenen cikti:
13
6
*/
