package sorting;

/**
 * MergeSort clássico por divisão e conquista.
 *
 * Características:
 * - Complexidade: O(n log n) em qualquer situação.
 * - Utiliza memória auxiliar para realizar as intercalações.
 * - Mantém a ordem original de elementos iguais (algoritmo estável).
 */
public class MergeSort {
    public static <T extends Comparable<T>> void sort(T[] A) {
        sort(A, java.util.Comparator.naturalOrder());
    }

    public static <T> void sort(T[] A, java.util.Comparator<? super T> compResultado) {
        if (A == null || A.length <= 1) return;
        mergeSort(A, 0, A.length - 1, compResultado);
    }

    /**
     * Divide o vetor recursivamente em duas partes até restarem
     * subvetores com apenas um elemento.
     */
    private static <T> void mergeSort(T[] A, int left, int right, java.util.Comparator<? super T> compResultado) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(A, left, mid, compResultado);
            mergeSort(A, mid + 1, right, compResultado);

            merge(A, left, mid, right, compResultado);
        }
    }

    /**
     * Une duas partes já ordenadas do vetor em uma única sequência ordenada.
     *
     * A primeira parte vai de left até mid.
     * A segunda parte vai de mid + 1 até right.
     *
     * Para facilitar a intercalação, são criados dois vetores auxiliares
     * que armazenam temporariamente os elementos de cada metade.
     */
    private static <T> void merge(T[] A, int left, int mid, int right, java.util.Comparator<? super T> compResultado) {
        T[] L = java.util.Arrays.copyOfRange(A, left, mid + 1);
        T[] R = java.util.Arrays.copyOfRange(A, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        // Compara os elementos das duas metades e insere
        // no vetor principal em ordem crescente
        while (i < L.length && j < R.length) {
            if (compResultado.compare(L[i], R[j]) <= 0) {
                A[k++] = L[i++];
            } else {
                A[k++] = R[j++];
            }
        }

        while (i < L.length) A[k++] = L[i++];
        while (j < R.length) A[k++] = R[j++];
    }

}