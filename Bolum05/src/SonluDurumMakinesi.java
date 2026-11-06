// DFA simulasyonu: her karakter icin gecis tablosundan tek okuma; sonda kabul durumu denetlenir.
// Zaman O(n), geri donme (backtracking) yok. Alfabe {0, 1}.
public class SonluDurumMakinesi {

	static boolean kabulEder(int[][] gecis, boolean[] kabul,
			String s) {
		int durum = 0;                     // baslangic durumu
		for (char c : s.toCharArray())
			durum = gecis[durum][c - '0'];   // tek gecis
		return kabul[durum];               // son durum kabul mu?
	}

	public static void main(String[] args) {
		// "1 ile biten" dizgiler: q0 -> 0, q1 -> 1; F = {q1}
		int[][] g1 = {{0, 1}, {0, 1}};
		boolean[] k1 = {false, true};
		System.out.println(kabulEder(g1, k1, "1011"));  // true
		System.out.println(kabulEder(g1, k1, "10"));    // false

		// Ikili alfabe ornegi: A=0, B=1, C=2, D=3 (kabul), E=4, F=5
		int[][] g2 = {
			{4, 1},   // A: 0 -> E, 1 -> B
			{1, 2},   // B: 0 -> B, 1 -> C
			{1, 3},   // C: 0 -> B, 1 -> D
			{3, 0},   // D: 0 -> D, 1 -> A
			{4, 5},   // E: 0 -> E, 1 -> F
			{5, 0}    // F: 0 -> F, 1 -> A
		};
		boolean[] k2 = {false, false, false, true, false, false};
		System.out.println(kabulEder(g2, k2, "10011"));  // true
	}
}
