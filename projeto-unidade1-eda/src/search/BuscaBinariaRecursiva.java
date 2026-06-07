package search;

import java.util.Comparator;

public class BuscaBinariaRecursiva {

	public static <T extends Comparable<? super T>> int busca(T[] A, T chave) {
		return busca(A, chave, Comparator.naturalOrder());
	}

	public static <T> int busca(T[] A, T chave, Comparator<? super T> comp) {
		if (A == null || A.length == 0) return -1;
		return buscaRec(A, chave, comp, 0, A.length - 1);
	}

	private static <T> int buscaRec(T[] A, T chave, Comparator<? super T> comp, int left, int right) {
		if (left > right) return -1;
		int mid = left + ((right - left) >>> 1);
		int cmp = comp.compare(A[mid], chave);
		if (cmp == 0) return mid;
		if (cmp < 0) return buscaRec(A, chave, comp, mid + 1, right);
		return buscaRec(A, chave, comp, left, mid - 1);
	}

	public static int busca(int[] A, int chave) {
		if (A == null || A.length == 0) return -1;
		return buscaRec(A, chave, 0, A.length - 1);
	}

	private static int buscaRec(int[] A, int chave, int left, int right) {
		if (left > right) return -1;
		int mid = left + ((right - left) >>> 1);
		if (A[mid] == chave) return mid;
		if (A[mid] < chave) return buscaRec(A, chave, mid + 1, right);
		return buscaRec(A, chave, left, mid - 1);
	}

}
