package sorting;

import java.util.Arrays;

/**
 * Wrapper para a ordenação de arrays primitivos inteiros do Java.
 *
 * Em arrays de tipos primitivos, Arrays.sort(int[]) usa Dual-Pivot QuickSort.
 *
 * Complexidade:
 * - Medio caso: O(n log n)
 * - Pior caso: O(n^2) (teorico do quicksort)
 *
 * Uso neste projeto: base de comparacao para os experimentos com int[].
 */
public final class QuickSortJava {

	private QuickSortJava() {
	}

	public static void sort(int[] A) {
		if (A == null || A.length <= 1) return;
		Arrays.sort(A);
	}
}
