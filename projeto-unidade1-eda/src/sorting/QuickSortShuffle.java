package sorting;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSort com embaralhamento previo (shuffle).
 *
 * O vetor e embaralhado antes de chamar o QuickSort classico,
 * reduzindo a chance de cair no pior caso em entradas adversas.
 *
 * Complexidade esperada:
 * - Tempo medio esperado: O(n log n)
 * - Pior caso teorico: O(n^2)
 * - Custo extra do shuffle: O(n)
 *
 * Propriedades: in-place (desconsiderando recursao), nao estavel.
 */
public class QuickSortShuffle {

	/**
	 * QuickSort com embaralhamento prévio para reduzir a chance do pior caso.
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, Comparator.naturalOrder());
	}

	public static <T> void sort(T[] A, Comparator<? super T> compResultado) {
		if (A == null || A.length <= 1) return;
		shuffle(A);
		QuickSort.sort(A, compResultado);
	}

	private static <T> void shuffle(T[] A) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = A.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			SortUtils.swap(A, i, j);
		}
	}
}
