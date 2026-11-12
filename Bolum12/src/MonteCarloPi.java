import java.util.Random;

// Monte Carlo ile pi: birim kareye rastgele nokta at, ceyrek dairenin icindekileri say; pi ~ 4 * ic / n.
// O(n) zaman; hata yaklasik 1/sqrt(n) ile azalir.
public class MonteCarloPi {

	static Random zar = new Random(42);

	static double piTahmin(int n) {
		int ic = 0;
		for (int t = 0; t < n; t++) {
			double x = zar.nextDouble(), y = zar.nextDouble();
			if (x * x + y * y <= 1) ic++;
		}
		return 4.0 * ic / n;
	}

	public static void main(String[] args) {
		System.out.println(piTahmin(300));         // 3.1333333333333333 (tohum 42)
		System.out.println(piTahmin(10000));       // 3.1244
		System.out.println(piTahmin(1000000));     // 3.138504
	}
}

/* Beklenen cikti:
3.1333333333333333
3.1244
3.138504
*/
