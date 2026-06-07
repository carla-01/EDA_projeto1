package search;

import java.util.Comparator;
import java.util.Objects;

public class BuscaLinearIterativa {

	public static <T> int busca(T[] A, T chave) {
		if (A == null || A.length == 0) return -1;
		for (int i = 0; i < A.length; i++) {
			if (Objects.equals(A[i], chave)) return i;
		}
		return -1;
	}

	public static <T> int busca(T[] A, T chave, Comparator<? super T> compResultado) {
		if (A == null || A.length == 0) return -1;
		for (int i = 0; i < A.length; i++) {
			if (compResultado.compare(A[i], chave) == 0) return i;
		}
		return -1;
	}

	public static int busca(int[] A, int chave) {
		if (A == null || A.length == 0) return -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == chave) return i;
		}
		return -1;
	}

}