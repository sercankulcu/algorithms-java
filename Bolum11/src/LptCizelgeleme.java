import java.util.Arrays;
import java.util.Comparator;

// LPT (en uzun is once): isler azalan sureyle siralanir, her is en az yuklu makineye verilir.
// Cmax <= (4/3 - 1/(3m)) * OPT (Graham, 1969); sure O(n log n + n m).
public class LptCizelgeleme {

	static int lpt(Integer[] isler, int m) {
		Arrays.sort(isler, Comparator.reverseOrder());
		int[] yuk = new int[m];
		for (int p : isler) {
			int k = 0;              // en az yuklu
			for (int i = 1; i < m; i++)
				if (yuk[i] < yuk[k]) k = i;
			yuk[k] += p;
		}
		return Arrays.stream(yuk).max().getAsInt();
	}

	public static void main(String[] args) {
		System.out.println(lpt(new Integer[]{2, 3, 4, 6, 2, 2}, 3));      // 7 (= OPT)
		System.out.println(lpt(new Integer[]{5, 5, 4, 4, 3, 3, 3}, 3));   // 11 (OPT 9, siki ornek)
	}
}
