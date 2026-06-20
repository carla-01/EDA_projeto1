package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * Ordem final desta classe: nota decrescente, com desempate natural de Estudante.
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

		Estudante[] output = new Estudante[A.length];
		int[] posicao = new int[range];
		for (int i = range - 2; i >= 0; i--) {
			posicao[i] = posicao[i + 1] + count[i + 1];
		}

		@SuppressWarnings("unchecked")
		List<Estudante>[] buckets = new List[range];
		for (int i = 0; i < range; i++) {
			buckets[i] = new ArrayList<Estudante>(count[i]);
		}

		for (Estudante estudante : A) {
			buckets[estudante.getNota() - minNota].add(estudante);
		}

		for (int i = 0; i < range; i++) {
			Estudante[] bucket = buckets[i].toArray(new Estudante[0]);
			Arrays.sort(bucket);
			System.arraycopy(bucket, 0, output, posicao[i], bucket.length);
		}

		System.arraycopy(output, 0, A, 0, A.length);
	}
}