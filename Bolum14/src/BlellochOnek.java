import java.util.Arrays;

// Blelloch dislayici onek toplami: yukari supurme (indirgeme agaci) + asagi supurme.
// Is W = O(n), derinlik D = O(log n); her turun ic dongusu paralel calisabilir. n ikinin kuvveti.
public class BlellochOnek {

	static void onekToplami(int[] x) {
		int n = x.length;
		// yukari supurme
		for (int d = 1; d < n; d *= 2)
			for (int i = 2*d - 1; i < n; i += 2*d)
				x[i] += x[i - d];
		System.out.println("yukari: " + Arrays.toString(x));
		x[n - 1] = 0;
		// asagi supurme (ic donguler paralel)
		for (int d = n / 2; d >= 1; d /= 2)
			for (int i = 2*d - 1; i < n; i += 2*d) {
				int t = x[i - d];
				x[i - d] = x[i];
				x[i] += t;
			}
		// x: dislayici onek toplamlari
	}

	public static void main(String[] args) {
		int[] x = {2, 3, 5, 1, 7, 6, 8, 4};
		onekToplami(x);
		System.out.println("onek: " + Arrays.toString(x));
		// yukari: [2, 5, 5, 11, 7, 13, 8, 36]
		// onek: [0, 2, 5, 10, 11, 18, 24, 32]

		int[] y = {2, 3, 5, 1, 7, 6, 8, 4};
		Arrays.parallelPrefix(y, Integer::sum);       // kapsayici onek toplami
		System.out.println(Arrays.toString(y));       // [2, 5, 10, 11, 18, 24, 32, 36]
	}
}
