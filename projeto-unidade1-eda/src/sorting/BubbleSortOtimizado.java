package sorting;

public class BubbleSortOtimizado {

 /* Versão OTIMIZADA do BubbleSort:
 *   - Igual ao slide, mas interrompe se nenhuma troca ocorreu na passagem
 *   - Melhor caso Ω(n) (vetor já ordenado)
 *   - Pior caso Θ(n²)
 */
    public static <T extends Comparable<T>> void sortOtimizado(T[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) break; // vetor já ordenado
        }
    }

    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i]   = A[j];
        A[j]   = temp;
    }
}
