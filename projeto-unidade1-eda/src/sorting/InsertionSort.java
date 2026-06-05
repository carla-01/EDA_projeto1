package sorting;

/**
 * InsertionSort
 *
 * Lógica: semelhante a ordenar cartas na mão.
 * - Para cada elemento j (a partir do 2º), guarda como "key"
 * - Desloca para a direita todos os elementos maiores que key
 * - Insere key na posição correta
 *
 * Complexidade:
 *   - Melhor caso  Ω(n)   — vetor já ordenado (while nunca executa)
 *   - Pior  caso   Θ(n²)  — vetor em ordem inversa
 *
 * Propriedades: in-place, STABLE
 */

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