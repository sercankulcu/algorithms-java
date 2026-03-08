import java.util.Arrays;
import java.util.Random;

public class Timsort {
    private static final int MIN_MERGE = 4;
    private static int iterationCounter = 1; // Değişimleri saymak için

    public static void timSort(int[] arr) {
        int n = arr.length;

        // Küçük parçaları (run) Insertion Sort ile sırala
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
        }

        // Parçaları birleştir
        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            boolean changed = false;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                changed = true;
            }
            arr[j + 1] = key;
            if (changed) 
            	System.out.printf("Step %02d: %s%n", iterationCounter++, Arrays.toString(arr));
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];

        for (int i = 0; i < len1; i++) leftArr[i] = arr[left + i];
        for (int i = 0; i < len2; i++) rightArr[i] = arr[mid + 1 + i];

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int[] generateRandomArray(int size, int bound) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = 100 + random.nextInt(bound - 100);
        }
        return array;
    }

    public static void main(String[] args) {
    	
        int[] array = generateRandomArray(16, 1000);
        System.out.println("Original Array: " + Arrays.toString(array));
        System.out.println("------------------------------------------");

        timSort(array);

        System.out.println("------------------------------------------");
        System.out.println("Sorted Array:   " + Arrays.toString(array));
    }
}