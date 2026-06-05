package sorting;

public class SelectionSortEstavel {
/* ---------------------------------------------------------------
    * Versão ESTÁVEL
    * Encontra o mínimo, desloca os elementos entre i e min
    * uma posição à direita, e insere o mínimo em i.
// --------------------------------------------------------------- */
    public static <T extends Comparable<T>> void sortEstavel(T[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j].compareTo(A[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                T minElem = A[min];
                // desloca elementos de i até min-1 uma posição à direita
                for (int k = min; k > i; k--) {
                    A[k] = A[k - 1];
                }
                A[i] = minElem;
            }
        }
    }
 
    // ---------------------------------------------------------------
    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i]   = A[j];
        A[j]   = temp;
    }
}
