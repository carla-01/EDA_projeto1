package sorting;



public class InsertionSort {
     
    public static <T extends Comparable<T>> void sort(T[] A) {
        int n = A.length;
        for (int j = 1; j < n; j++) {       // equivale a "for j ← 2 to n"
            T key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i].compareTo(key) > 0) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = key;
        }
    }
}