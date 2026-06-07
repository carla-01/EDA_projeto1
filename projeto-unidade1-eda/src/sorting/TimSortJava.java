package sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Wrapper da ordenação padrão do Java para objetos.
 *
 * O Arrays.sort(T[]) usa TimSort internamente para tipos de referência.
  *
 * Complexidade:
 * - Pior caso: O(n log n)
 * - Melhor caso (dados parcialmente ordenados): pode se aproximar de O(n)
 *
 * Propriedades: estavel e otimizado para dados reais.
*/

public final class TimSortJava {
	private TimSortJava() {
	}

	public static <T> void sort(T[] A, Comparator<? super T> compResultado) {
		if (A == null || A.length <= 1) return;
		Arrays.sort(A, compResultado);
	}

	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, Comparator.naturalOrder());
	}
}
