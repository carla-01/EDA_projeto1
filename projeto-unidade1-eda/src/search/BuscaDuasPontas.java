package search;

public class BuscaDuasPontas {

	public static <T> int busca(T[] A, T chave) {
		if (A == null || A.length == 0) return -1;
		int n = A.length;
		for (int i = 0; i <= (n - 1) / 2; i++) {
			T left = A[i];
			if (left == null ? chave == null : left.equals(chave)) return i;
			int j = n - 1 - i;
			if (j != i) {
				T right = A[j];
				if (right == null ? chave == null : right.equals(chave)) return j;
			}
		}
		return -1;
	}

	public static int busca(int[] A, int chave) {
		if (A == null || A.length == 0) return -1;
		int n = A.length;
		for (int i = 0; i <= (n - 1) / 2; i++) {
			if (A[i] == chave) return i;
			int j = n - 1 - i;
			if (j != i && A[j] == chave) return j;
		}
		return -1;
	}

}
