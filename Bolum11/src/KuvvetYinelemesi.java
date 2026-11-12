import java.util.Arrays;

// Power iteration (kuvvet yinelemesi): x <- A x / ||A x||, baskin ozvektore yakinsar.
// Her adim bir matris-vektor carpimi, O(n^2); hata her adimda yaklasik |lambda2 / lambda1| katina iner.
public class KuvvetYinelemesi {

	static double[] kuvvet(double[][] a, int adim) {
		int n = a.length;
		double[] x = new double[n];
		Arrays.fill(x, 1.0);     // x0 != 0
		for (int k = 0; k < adim; k++) {
			double[] y = new double[n];
			for (int i = 0; i < n; i++)  // y = A x
				for (int j = 0; j < n; j++)
					y[i] += a[i][j] * x[j];
			double norm = 0;
			for (double yi : y) norm += yi * yi;
			norm = Math.sqrt(norm);
			for (int i = 0; i < n; i++)  // ||x|| = 1
				x[i] = y[i] / norm;
		}
		return x;
	}

	// ||A x||: x birim vektorken baskin ozdegerin tahmini
	static double ozdeger(double[][] a, double[] x) {
		double norm = 0;
		for (int i = 0; i < a.length; i++) {
			double yi = 0;
			for (int j = 0; j < a.length; j++) yi += a[i][j] * x[j];
			norm += yi * yi;
		}
		return Math.sqrt(norm);
	}

	static String yaz(double[] x) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < x.length; i++)
			sb.append(String.format(java.util.Locale.ROOT, "%.4f", x[i])).append(i + 1 < x.length ? ", " : "]");
		return sb.toString();
	}

	public static void main(String[] args) {
		double[][] a = {
			{0.511, 0.32, 0.12},
			{0.211, 0.62, 0.12},
			{0.311, 0.12, 0.16}
		};
		System.out.println(yaz(kuvvet(a, 1)));   // [0.6474, 0.6474, 0.4023]
		System.out.println(yaz(kuvvet(a, 3)));   // [0.6539, 0.6539, 0.3807]
		double[] x = kuvvet(a, 8);
		System.out.println(yaz(x));              // [0.6539, 0.6539, 0.3805]
		System.out.println(String.format(java.util.Locale.ROOT, "%.4f", ozdeger(a, x)));   // 0.9008
	}
}

/* Beklenen cikti:
[0.6474, 0.6474, 0.4023]
[0.6539, 0.6539, 0.3807]
[0.6539, 0.6539, 0.3805]
0.9008
*/
