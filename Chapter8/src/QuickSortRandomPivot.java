
import java.util.Arrays;
import java.util.Random;

public class QuickSortRandomPivot {
	
	static Random rand = new Random();

	// Function to perform Quick Sort
	static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			// Select random pivot
			int randomIndex = getRandomIndex(low, high);
			int pivot = array[randomIndex];

			// Partition the array around the pivot
			int partitionIndex = partition(array, low, high, pivot);

			// Recursively sort sub-arrays
			quickSort(array, low, partitionIndex - 1);
			quickSort(array, partitionIndex + 1, high);
		}
	}

	// Function to partition the array
	static int partition(int[] array, int low, int high, int pivot) {
		// Move pivot element to the end
		swap(array, high, findIndex(array, pivot, low, high));
		int pivotIndex = low;

		// Rearrange elements smaller than pivot to the left and larger to the right
		for (int i = low; i < high; i++) {
			if (array[i] <= pivot) {
				swap(array, i, pivotIndex);
				pivotIndex++;
			}
		}
		// Move pivot to its final position
		swap(array, pivotIndex, high);
		return pivotIndex;
	}

	// Function to find index of the pivot element
	static int findIndex(int[] array, int pivot, int low, int high) {
		for (int i = low; i <= high; i++) {
			if (array[i] == pivot) {
				return i;
			}
		}
		return -1;
	}

	// Function to generate a random index between low and high (inclusive)
	static int getRandomIndex(int low, int high) {
		return rand.nextInt(high - low + 1) + low;
	}

	// Function to swap two elements in an array
	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		System.out.println(Arrays.toString(array));
	}

	public static void main(String[] args) {
		int[] array = {5, 2, 7, 1, 3, 4, 9, 6}; // Example array
		// Perform Quick Sort
		quickSort(array, 0, array.length - 1);
	}
}
