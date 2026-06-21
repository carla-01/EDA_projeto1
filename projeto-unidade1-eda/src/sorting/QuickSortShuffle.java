package sorting;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;


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
