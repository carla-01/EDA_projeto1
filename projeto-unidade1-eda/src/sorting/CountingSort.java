package sorting;

import model.Estudante;

/**
 * CountingSort para objetos Estudante usando a nota como chave.
 *
 * Como counting sort nao ordena objetos diretamente por comparacao,
 * esta implementacao mapeia o campo nota para contadores e reconstrói
 * o array de forma estavel.
 *
 * Complexidade:
 * - Tempo: O(n + k), onde k = (maxNota - minNota + 1)
 * - Memoria extra: O(n + k)
 *
 * Ordem final desta classe: nota decrescente.
 */
public class CountingSort {

	public static void sort(Estudante[] A) {
		sortByNotaDesc(A);
	}

	public static void sortByNotaDesc(Estudante[] A) {
		if (A == null || A.length <= 1) return;

		int minNota = A[0].getNota();
		int maxNota = A[0].getNota();
		for (Estudante estudante : A) {
			int nota = estudante.getNota();
			if (nota < minNota) minNota = nota;
			if (nota > maxNota) maxNota = nota;
		}

		int range = maxNota - minNota + 1;
		int[] count = new int[range];

		for (Estudante estudante : A) {
			count[estudante.getNota() - minNota]++;
		}

		// Acumulado para ordem decrescente
		for (int i = range - 2; i >= 0; i--) {
			count[i] += count[i + 1];
		}

		Estudante[] output = new Estudante[A.length];
		// Percurso reverso para manter estavel
		for (int i = A.length - 1; i >= 0; i--) {
			Estudante estudante = A[i];
			int idx = estudante.getNota() - minNota;
			int pos = count[idx] - 1;
			output[pos] = estudante;
			count[idx]--;
		}

		System.arraycopy(output, 0, A, 0, A.length);
	}
}