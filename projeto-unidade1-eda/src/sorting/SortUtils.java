package sorting;

import java.util.Objects;

/**
 * Utilitários usados pelos algoritmos de ordenação.
 */
public final class SortUtils {
    private SortUtils() {}

    public static <T> void swap(T[] a, int i, int j) {
        Objects.checkIndex(i, a.length);
        Objects.checkIndex(j, a.length);
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}