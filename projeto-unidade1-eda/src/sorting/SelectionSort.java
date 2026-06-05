package sorting;

public class SelectionSort {

// ---------------------------------------------------------------
/* Versão do SLIDE:
 *   - Encontra o menor elemento e troca com a posição i
 *   - Θ(n²) comparações; O(n) trocas
 *   - A versão com swap direto NÃO é estável
 *     Ex.: [2a, 2b, 1] → swap(2a,1) → [1, 2b, 2a]  (2a e 2b invertidos) */
// ---------------------------------------------------------------
    public static <T extends Comparable<T>> void sort(T[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j].compareTo(A[min]) < 0) {
                    min = j;
                }
            }
            swap(A, i, min);
        }
}
}