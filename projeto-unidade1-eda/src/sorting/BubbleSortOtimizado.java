package sorting;

import java.util.Comparator;

public class BubbleSortOtimizado {



    public static <T extends Comparable<T>> void sortOtimizado(T[] A) {
        sortOtimizado(A, Comparator.naturalOrder());
    }

    public static <T> void sortOtimizado(T[] A, Comparator<? super T> compResultado) {
        if (A == null || A.length <= 1) return;

        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (compResultado.compare(A[j], A[j + 1]) > 0) {
                    SortUtils.swap(A, j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) break; // vetor já ordenado
        }
    }
}
