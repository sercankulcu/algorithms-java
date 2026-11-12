// Karmasiklik ornekleri: f1 Theta(n), f2 Theta(n^3), f3 Theta(2^n), f4 Theta(n),
// f5 Theta(n log n), f6 cok yavas buyuyen kaydirmali dongu.
public class KarmasiklikFonksiyonlari {

	public int f1(int n) {
		int x = 0;
		for (int i = 0; i < n; i++) {
			x++;
		}
		return x;
	}

	public int f2(int n) {
		int x = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i * i; j++) {
				x++;
			}
		}
		return x;
	}

	public static int f6(int n) {
		int x = 0;
		// 1 << i, 2^i ile aynidir (tasma yok sayilir)
		for (int i = 0; i < n; i = 1 << i) {
			x++;
		}
		return x;
	}

	public long f3(int n) {
		if (n <= 1) {
			return 1;
		}
		return f3(n - 1) + f3(n - 1);
	}

	public int f4(int n) {
		if (n <= 1) {
			return 1;
		}
		return f4(n / 2) + f4(n / 2);
	}

	public int f5(int n) {
		if (n <= 1) {
			return 1;
		}
		return f1(n) + f5(n / 2) + f5(n / 2);
	}

	public static void main(String[] args) {
		KarmasiklikFonksiyonlari k = new KarmasiklikFonksiyonlari();
		System.out.println(k.f1(4));    // 4
		System.out.println(k.f2(4));    // 14
		System.out.println(k.f2(5));    // 30
		System.out.println(f6(20));     // 5 (i: 0, 1, 2, 4, 16)
		System.out.println(k.f3(3));    // 4 (7 cagri)
		System.out.println(k.f4(8));    // 8
		System.out.println(k.f5(4));    // 12
		System.out.println(k.f5(8));    // 32
	}
}

/* Beklenen cikti:
4
14
30
5
4
8
12
32
*/
