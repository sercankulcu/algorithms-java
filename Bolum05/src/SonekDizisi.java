import java.util.Arrays;

// Sonek dizisi (suffix array): soneklerin sirali baslangic indisleri; p ile baslayanlar bitisik bir aralik.
// Kaba kuvvet kurulum O(n^2 log n); arama iki ikili aramayla O(m log n).
public class SonekDizisi {

	// Kaba kuvvet: tum sonekler sozlukce siralanir
	static int[] sonekDizisi(String t) {
		Integer[] sira = new Integer[t.length()];
		for (int i = 0; i < sira.length; i++) sira[i] = i;
		Arrays.sort(sira, (x, y) -> t.substring(x).compareTo(t.substring(y)));
		int[] sa = new int[sira.length];
		for (int i = 0; i < sa.length; i++) sa[i] = sira[i];
		return sa;
	}

	// p ile baslayan soneklerin SA'daki yari acik araligi [bas, son)
	static int[] ara(String t, int[] sa, String p) {
		int lo = 0, hi = sa.length;
		while (lo < hi) {   // ilk >= p
			int mid = (lo + hi) / 2;
			if (t.substring(sa[mid])
				.compareTo(p) < 0) lo = mid + 1;
			else hi = mid;
		}
		int bas = lo;
		hi = sa.length;
		while (lo < hi) {   // p ile baslamayan
			int mid = (lo + hi) / 2;
			if (t.startsWith(p, sa[mid]))
				lo = mid + 1;
			else hi = mid;
		}
		return new int[] { bas, lo };
	}

	public static void main(String[] args) {
		String t = "banana";
		int[] sa = sonekDizisi(t);
		System.out.println(Arrays.toString(sa));  // [5, 3, 1, 0, 4, 2]

		int[] r = ara(t, sa, "an");
		System.out.println(Arrays.toString(r));   // [1, 3]
		for (int i = r[0]; i < r[1]; i++)
			System.out.print(sa[i] + " ");
		System.out.println();                     // 3 1
	}
}
