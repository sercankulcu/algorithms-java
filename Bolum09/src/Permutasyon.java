import java.util.Arrays;

// Permutasyon uretimi: her konuma kullanilmamis bir sayi konur, donuste geri alinir.
// n! permutasyon; kullan[] her donuste eski haline doner.
public class Permutasyon {

	static void permutasyon(int[] p, boolean[] kullan, int k) {
		if (k == p.length) {               // cozum
			System.out.println(Arrays.toString(p));
			return;
		}
		for (int a = 1; a <= p.length; a++) {
			if (!kullan[a]) {                // gecerli
				p[k] = a; kullan[a] = true;    // sec
				permutasyon(p, kullan, k + 1); // kesfet
				kullan[a] = false;             // geri al
			}
		}
	}

	public static void main(String[] args) {
		permutasyon(new int[3], new boolean[4], 0);   // kullan[1..3]
		// [1, 2, 3]
		// [1, 3, 2]
		// [2, 1, 3]
		// [2, 3, 1]
		// [3, 1, 2]
		// [3, 2, 1]
	}
}

/* Beklenen cikti:
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 1, 2]
[3, 2, 1]
*/
