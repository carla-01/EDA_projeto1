package sorting;

import java.util.Comparator;


public class QuickSort {

	/**
	 * QuickSort clássico com pivô no início, versão do slide.
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, Comparator.naturalOrder());
	}

	public static <T> void sort(T[] A, Comparator<? super T> compResultado) {
		if (A == null || A.length <= 1) return;
		quickSort(A, 0, A.length - 1, compResultado);
	}

	private static <T> void quickSort(T[] A, int left, int right, Comparator<? super T> compResultado) {
		if (left < right) {
			int pivot = partition(A, left, right, compResultado);
			quickSort(A, left, pivot - 1, compResultado);
			quickSort(A, pivot + 1, right, compResultado);
		}
	}

	private static <T> int partition(T[] A, int left, int right, Comparator<? super T> compResultado) {
		T pivot = A[left];
		int i = left + 1;
		int j = right;

		while (i <= j) {
			if (compResultado.compare(A[i], pivot) <= 0) {
				i++;
			} else if (compResultado.compare(A[j], pivot) > 0) {
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