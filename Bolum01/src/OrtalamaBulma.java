import java.util.Scanner;

// Ilk algoritma ornegi: uc sayinin ortalamasi. Girdi boyutundan bagimsiz, O(1).
// Sunumdaki Scanner(System.in) yerine sabit bir dizgiden okunur (klavye beklenmez).
public class OrtalamaBulma {

	public static void main(String[] args) {
		Scanner giris = new Scanner("10 20 45");
		System.out.println("Birinci sayiyi girin: ");
		double sayi1 = giris.nextDouble();
		System.out.println("Ikinci sayiyi girin: ");
		double sayi2 = giris.nextDouble();
		System.out.println("Ucuncu sayiyi girin: ");
		double sayi3 = giris.nextDouble();
		double ortalama = (sayi1 + sayi2 + sayi3) / 3;
		System.out.println("Sayilarin ortalamasi: "
				+ ortalama);              // 25.0
		giris.close();
	}
}

/* Beklenen cikti:
Birinci sayiyi girin:
Ikinci sayiyi girin:
Ucuncu sayiyi girin:
Sayilarin ortalamasi: 25.0
*/
