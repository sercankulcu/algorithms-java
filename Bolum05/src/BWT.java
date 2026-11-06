import java.util.Arrays;

// Burrows-Wheeler donusumu: siralanmis dondurmelerin son sutunu L; LF eslemesiyle geri cevrilir.
// Dogrudan donusum O(n^2 log n); ters donusum O(n).
public class BWT {

	// Dogrudan yontem: n dondurme uretilir, siralanir, son karakterler alinir
	static String bwt(String s) {
		int n = s.length();
		String[] don = new String[n];
		for (int i = 0; i < n; i++)
			don[i] = s.substring(i) + s.substring(0, i);
		Arrays.sort(don);
		StringBuilder L = new StringBuilder();
		for (String r : don) L.append(r.charAt(n - 1));
		return L.toString();
	}

	static String bwtTers(String L) {   // $ ile
		int n = L.length();
		int[] sira = new int[n];
		int[] say = new int[256];
		int[] C = new int[256];
		for (int i = 0; i < n; i++)
			sira[i] = say[L.charAt(i)]++;
		for (int c = 1; c < 256; c++)
			C[c] = C[c - 1] + say[c - 1];
		StringBuilder s = new StringBuilder();
		int i = 0;                   // $ satiri
		for (int k = 0; k < n - 1; k++) {
			char c = L.charAt(i);
			s.insert(0, c);
			i = C[c] + sira[i];        // LF(i)
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(bwt("BANANA"));       // NNBAAA
		System.out.println(bwt("abraca"));       // caraab
		System.out.println(bwt("banana$"));      // annb$aa
		System.out.println(bwtTers("annb$aa"));  // banana
	}
}
