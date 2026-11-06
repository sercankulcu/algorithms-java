import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Java'da duzenli ifadeler: Pattern ile derle, Matcher ile ara, group ile parcayi al.
// URL dilbilgisinin esdeger duzenli ifadesi de denenir.
public class DuzenliIfade {

	public static void main(String[] args) {
		String girdi = "Ad: Ali Veli, Yas: 30, E-posta: ali.veli@ornek.com";
		Pattern p = Pattern.compile("Yas: (\\d+),");  // derle
		Matcher m = p.matcher(girdi);
		if (m.find()) {                          // ilk eslesmeyi bul
			int yas = Integer.parseInt(m.group(1)); // 1. grup: "30"
			System.out.println("Yas: " + yas);     // Yas: 30
		}

		Matcher ad = Pattern.compile("Ad: (.*?),").matcher(girdi);        // tembel .*?
		if (ad.find()) System.out.println(ad.group(1));                   // Ali Veli
		Matcher ep = Pattern.compile("E-posta: (.*?)$").matcher(girdi);   // satir sonuna kadar
		if (ep.find()) System.out.println(ep.group(1));                   // ali.veli@ornek.com

		// url ::= 'http://' sunucu ':' port '/' -> http://([a-z]+\.)*[a-z]+:[0-9]+/
		String url = "http://([a-z]+\\.)*[a-z]+:[0-9]+/";
		System.out.println("http://didit.csail.mit.edu:4949/".matches(url));  // true
		System.out.println("http://didit.csail.mit.edu/".matches(url));       // false
	}
}
