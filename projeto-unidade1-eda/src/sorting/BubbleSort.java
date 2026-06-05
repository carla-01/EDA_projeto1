package sorting;

public class BubbleSort {

/* BUBBLESORT
 Versão do SLIDE:
 *   - Percorre o vetor n-1 vezes
 *   - A cada passagem faz flutuar o maior elemento para o topo
 *   - Θ(n²) em todos os casos */

    public static <T extends Comparable<T>> void sort(T[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                }
            }
        }
    }
}
