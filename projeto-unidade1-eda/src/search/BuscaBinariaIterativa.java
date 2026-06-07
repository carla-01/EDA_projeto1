package search;

import java.util.Comparator;

public class BuscaBinariaIterativa {

	public static <T extends Comparable<? super T>> int busca(T[] A, T chave) {
		return busca(A, chave, Comparator.naturalOrder());
	}

	public static <T> int busca(T[] A, T chave, Comparator<? super T> comp) {
		if (A == null || A.length == 0) return -1;
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >>> 1);
			int cmp = comp.compare(A[mid], chave);
			if (cmp == 0) return mid;
			if (cmp < 0) left = mid + 1;
			else right = mid - 1;
		}
		return -1;
	}

	public static int busca(int[] A, int chave) {
		if (A == null || A.length == 0) return -1;
		int left = 0, right = A.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >>> 1);
			if (A[mid] == chave) return mid;
			if (A[mid] < chave) left = mid + 1;
			else right = mid - 1;
		}
		return -1;
	}

}
