import java.util.Arrays;

// Boru hatti: p islemcili zincir, m satir; p_j satir r'yi t = r + j aninda gonderir.
// m + p - 2 adim (saf yontem m (p - 1)); verimlilik m / (m + p - 2).
public class BoruHatti {

	public static void main(String[] args) {
		int p = 5, m = 4;
		int[][] x = {        // x[j]: p_j'nin 4 satirlik sutunu
			{2, 1, 7, 4},
			{3, 7, 2, 9},
			{3, 4, 7, 3},
			{6, 1, 0, 2},
			{8, 6, 3, 5}
		};
		int adim = 0;
		for (int t = 0; t < m + p - 2; t++) {
			// tum j'ler ayni anda
			for (int j = 0; j < p - 1; j++) {
				int r = t - j;
				if (r >= 0 && r < m)
					x[j + 1][r] += x[j][r];
			}
			adim++;
		}
		System.out.println(adim + " adim");               // 7 adim
		System.out.println(Arrays.toString(x[p - 1]));   // [22, 19, 19, 23]
	}
}
