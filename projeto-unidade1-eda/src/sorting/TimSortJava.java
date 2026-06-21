package sorting;

import java.util.Arrays;
import java.util.Comparator;



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
