package sorting;
import java.util.Arrays;

/**
 * MergeSort
 *
 * O MergeSort é um algoritmo baseado na estratégia "dividir para conquistar".
 * Ele divide o vetor em partes menores até que cada parte possua apenas um elemento.
 * Em seguida, as partes são combinadas novamente de forma ordenada.
 *
 * Características:
 * - Complexidade: O(n log n) em qualquer situação.
 * - Utiliza memória auxiliar para realizar as intercalações.
 * - Mantém a ordem original de elementos iguais (algoritmo estável).
 *
 * Além da implementação clássica, esta classe também apresenta uma versão
 * utilizando o TimSort, algoritmo adotado internamente pelo Java para ordenar objetos.
 */
public class MergeSort {
    
}
    public static <T extends Comparable<T>> void sort(T[] A) {
        if (A.length <= 1) return;
        mergeSort(A, 0, A.length - 1);
    }

    /**
     * Divide o vetor recursivamente em duas partes até restarem
     * subvetores com apenas um elemento.
     */
    private static <T extends Comparable<T>> void mergeSort(T[] A, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(A, left, mid);
            mergeSort(A, mid + 1, right);

            merge(A, left, mid, right);
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
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] A, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Object[] L = new Object[n1];
        Object[] R = new Object[n2];

        // Copia os elementos da metade esquerda
        for (int i = 0; i < n1; i++) {
            L[i] = A[left + i];
        }

        // Copia os elementos da metade direita
        for (int j = 0; j < n2; j++) {
            R[j] = A[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        // Compara os elementos das duas metades e insere
        // no vetor principal em ordem crescente
        while (i < n1 && j < n2) {

            // O uso de <= preserva a estabilidade da ordenação
            if (((T) L[i]).compareTo((T) R[j]) <= 0) {
                A[k++] = (T) L[i++];
            } else {
                A[k++] = (T) R[j++];
            }
        }

        // Copia os elementos restantes da metade esquerda
        while (i < n1) {
            A[k++] = (T) L[i++];
        }

        // Copia os elementos restantes da metade direita
        while (j < n2) {
            A[k++] = (T) R[j++];
        }
    }

    // -----------------------------------------------------------------
    // Ordenação utilizando o TimSort da biblioteca padrão do Java.
    // -----------------------------------------------------------------

    /**
     * Ordena o vetor utilizando Arrays.sort().
     *
     * Para objetos, o Java utiliza internamente o TimSort,
     * um algoritmo otimizado que combina características do
     * MergeSort e do InsertionSort, oferecendo excelente
     * desempenho em aplicações reais.
     */
    public static <T extends Comparable<T>> void sortTimSort(T[] A) {
        Arrays.sort(A);
    }
}