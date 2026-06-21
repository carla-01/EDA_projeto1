package sorting;

import java.util.Comparator;


public class SelectionSort {


    public static <T extends Comparable<T>> void sort(T[] A) {
        sort(A, Comparator.naturalOrder());
    }

    public static <T> void sort(T[] A, Comparator<? super T> compResultado) {
        if (A == null || A.length <= 1) return;
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (compResultado.compare(A[j], A[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) SortUtils.swap(A, i, min);
        }
    }
}