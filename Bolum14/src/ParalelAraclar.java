import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Java'da paralellik araclari: parallelStream ile kelime sayma, Arrays.parallelSort, AtomicInteger.
// Paralel akis, icinde calistigi ForkJoinPool'u kullanir; havuz programin sonunda kapatilir.
public class ParalelAraclar {

	public static void main(String[] args) {
		List<String> kelimeler = List.of(
				"apple", "banana", "apple", "orange", "banana", "apple",
				"banana", "banana", "orange", "apple", "cherry", "banana", "apple");
		try (ForkJoinPool havuz = new ForkJoinPool(4)) {
			Map<String, Long> adet = havuz.submit(() -> kelimeler.parallelStream()
					.collect(Collectors.groupingByConcurrent(k -> k, Collectors.counting()))).join();
			System.out.println(new TreeMap<>(adet));   // {apple=5, banana=5, cherry=1, orange=2}

			// Paylasilan sayac: sayac++ yaris durumu yaratir, AtomicInteger guvenlidir
			AtomicInteger sayac = new AtomicInteger();
			havuz.submit(() -> IntStream.range(0, 100000).parallel()
					.forEach(i -> sayac.incrementAndGet())).join();
			System.out.println(sayac.get());           // 100000
		}

		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		Arrays.parallelSort(a);
		System.out.println(Arrays.toString(a));        // [1, 2, 3, 4, 5, 6, 7, 8]
	}
}
