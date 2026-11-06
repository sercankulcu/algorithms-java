import java.util.Arrays;

// Boyer-Moore (kotu karakter kurali): oruntu sagdan sola karsilastirilir,
// uyusmazlikta s += max(1, j - son[c]). En kotu O(n * m), pratikte alt dogrusal.
public class BoyerMoore {

	// son[c]: c karakterinin oruntudeki en sagdaki konumu; yoksa -1
	static int[] sonGorulme(String p) {
		int[] son = new int[256];
		Arrays.fill(son, -1);
		for (int i = 0; i < p.length(); i++)
			son[p.charAt(i)] = i;
		return son;
	}

	static int boyerMoore(String t, String p) {
		int n = t.length(), m = p.length();
		int[] son = sonGorulme(p);
		int s = 0;
		while (s <= n - m) {
			int j = m - 1;
			while (j >= 0 &&
				p.charAt(j) == t.charAt(s + j))
				j--;
			if (j < 0) return s;
			s += Math.max(1,
				j - son[t.charAt(s + j)]);
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] son = sonGorulme("abacab");
		System.out.println(son['a'] + " " + son['b'] + " " + son['c'] + " " + son['x']);  // 4 5 3 -1

		son = sonGorulme("abe");
		System.out.println(son['a'] + " " + son['b'] + " " + son['e']);  // 0 1 2
		System.out.println(boyerMoore("merhaba naber", "abe"));          // 9
	}
}
