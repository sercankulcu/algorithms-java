import java.util.Collections;
import java.util.PriorityQueue;

// Cevrim ici ortanca: sol = kucuk yari (en buyuk yigin), sag = buyuk yari (en kucuk yigin).
// ekle O(log n), ortanca O(1).
public class CevrimiciOrtanca {

	static PriorityQueue<Integer> sol = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> sag = new PriorityQueue<>();

	static void ekle(int x) {
		if (sol.isEmpty() || x <= sol.peek())
			sol.offer(x);          // kucuk yari
		else
			sag.offer(x);          // buyuk yari
		if (sol.size() > sag.size() + 1)
			sag.offer(sol.poll());
		else if (sag.size() > sol.size())
			sol.offer(sag.poll());
	}

	static double ortanca() {
		if (sol.size() == sag.size())
			return (sol.peek() + sag.peek()) / 2.0;
		return sol.peek();
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int x : new int[]{5, 15, 1, 3, 8, 7}) {
			ekle(x);
			sb.append(ortanca()).append(' ');
		}
		System.out.println(sb.toString().trim());   // 5.0 10.0 5.0 4.0 5.0 6.0
	}
}

/* Beklenen cikti:
5.0 10.0 5.0 4.0 5.0 6.0
*/
