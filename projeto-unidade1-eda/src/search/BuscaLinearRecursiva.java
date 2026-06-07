package search;

import java.util.Comparator;
import java.util.Objects;

public class BuscaLinearRecursiva {

	public static <T> int busca(T[] A, T chave) {
		if (A == null || A.length == 0) return -1;
		return buscaRec(A, chave, 0);
	}

	public static <T> int busca(T[] A, T chave, Comparator<? super T> compResultado) {
		if (A == null || A.length == 0) return -1;
		return buscaRec(A, chave, compResultado, 0);
	}

	private static <T> int buscaRec(T[] A, T chave, int index) {
		if (index >= A.length) return -1;
		if (Objects.equals(A[index], chave)) return index;
		return buscaRec(A, chave, index + 1);
	}

	private static <T> int buscaRec(T[] A, T chave, Comparator<? super T> compResultado, int index) {
		if (index >= A.length) return -1;
		if (compResultado.compare(A[index], chave) == 0) return index;
		return buscaRec(A, chave, compResultado, index + 1);
	}

	public static int busca(int[] A, int chave) {
		if (A == null || A.length == 0) return -1;
		return buscaRec(A, chave, 0);
	}

	private static int buscaRec(int[] A, int chave, int index) {
		if (index >= A.length) return -1;
		if (A[index] == chave) return index;
		return buscaRec(A, chave, index + 1);
	}

}