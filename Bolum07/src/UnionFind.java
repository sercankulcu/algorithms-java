import java.util.Arrays;

// Union-Find (ayrik kumeler): bul yol sikistirmayla koke ulasir, birlestir iki koku baglar.
// Yol sikistirmayla bir dizi islemin amortize maliyeti neredeyse sabittir.
public class UnionFind {

	static int bul(int[] ata, int i) {
		if (ata[i] != i)
			ata[i] = bul(ata, ata[i]); // yol sikistirma
		return ata[i];
	}

	static void birlestir(int[] ata, int x, int y) {
		ata[bul(ata, x)] = bul(ata, y);
	}

	public static void main(String[] args) {
		int[] ata = { 0, 1, 2, 3, 4 };   // her oge kendi kumesinde
		birlestir(ata, 0, 1);
		birlestir(ata, 1, 2);
		birlestir(ata, 2, 3);
		System.out.println(Arrays.toString(ata));   // [1, 2, 3, 3, 4]  (zincir 0 -> 1 -> 2 -> 3)
		System.out.println(bul(ata, 0));            // 3
		System.out.println(Arrays.toString(ata));   // [3, 3, 3, 3, 4]  (yol sikisti)
		birlestir(ata, 4, 0);
		System.out.println(Arrays.toString(ata));   // [3, 3, 3, 3, 3]
	}
}

/* Beklenen cikti:
[1, 2, 3, 3, 4]
3
[3, 3, 3, 3, 4]
[3, 3, 3, 3, 3]
*/
