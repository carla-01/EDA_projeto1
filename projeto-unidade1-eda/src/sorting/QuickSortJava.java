package sorting;

/**
 * QuickSort clássico para arrays primitivos int[].
 *
 * Uso neste projeto: base de comparacao para os experimentos com int[],
 * confrontando uma implementacao propria com Arrays.sort(int[]).
 */
public final class QuickSortJava {

	private QuickSortJava() {
	}

	public static void sort(int[] A) {
		if (A == null || A.length <= 1) return;
		quickSort(A, 0, A.length - 1);
	}

	private static void quickSort(int[] A, int left, int right) {
		if (left < right) {
			int pivot = partition(A, left, right);
			quickSort(A, left, pivot - 1);
			quickSort(A, pivot + 1, right);
		}
	}

	private static int partition(int[] A, int left, int right) {
		int pivot = A[left];
		int i = left + 1;
		int j = right;

		while (i <= j) {
			if (A[i] <= pivot) {
				i++;
			} else if (A[j] > pivot) {
				j--;
			} else {
				SortUtils.swap(A, i, j);
				i++;
				j--;
			}
		}

		SortUtils.swap(A, left, j);
		return j;
	}
}
