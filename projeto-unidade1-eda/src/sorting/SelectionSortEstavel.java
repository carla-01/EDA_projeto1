package sorting;

import java.util.Comparator;


public class SelectionSortEstavel {

    public static <T extends Comparable<T>> void sortEstavel(T[] A) {
        sortEstavel(A, Comparator.naturalOrder());
    }

    public static <T> void sortEstavel(T[] A, java.util.Comparator<? super T> compResultado) {
        if (A == null || A.length <= 1) return;
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (compResultado.compare(A[j], A[min]) < 0) {
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
}
