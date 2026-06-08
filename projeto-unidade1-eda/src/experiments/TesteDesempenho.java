package experiments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Estudante;
import search.BuscaBinariaIterativa;
import search.BuscaBinariaRecursiva;
import search.BuscaDuasPontas;
import search.BuscaLinearIterativa;
import search.BuscaLinearRecursiva;
import sorting.BubbleSort;
import sorting.BubbleSortOtimizado;
import sorting.CountingSort;
import sorting.InsertionSort;
import sorting.MergeSort;
import sorting.QuickSort;
import sorting.QuickSortJava;
import sorting.QuickSortShuffle;
import sorting.SelectionSort;
import sorting.SelectionSortEstavel;
import sorting.TimSortJava;

public class TesteDesempenho {

	private static final int REPETICOES = 20;
	private static final int WARMUP = 5;
	private static final long SEED_BASE = 123456789L;

	private static final int[] TAMANHOS_QUADRATICOS = { 1000, 5000, 10000 };
	private static final int[] TAMANHOS_LOG_N = { 20000, 100000, 500000 };
	private static final int[] TAMANHOS_BUSCA = { 20000, 100000, 500000 };

	private TesteDesempenho() {
	}

	public static void executarPadrao() {
		System.out.println("Iniciando benchmark...");
		System.out.println("Resultados no console e em relatorio/resultados-desempenho.csv");
		List<Resultado> resultados = new ArrayList<Resultado>();
		System.out.println("1/4 ordenacoes quadráticas");
		resultados.addAll(medirOrdenacoesQuadraticas());
		System.out.println("2/4 ordenacoes O(n log n)");
		resultados.addAll(medirOrdenacoesLogN());
		System.out.println("3/4 comparacao dos QuickSorts em int[]");
		resultados.addAll(medirQuickSortsInteiros());
		System.out.println("4/4 buscas");
		resultados.addAll(medirBuscas());
		System.out.println("Benchmark concluido, imprimindo resultados e gravando CSV...");
		imprimirResultados(resultados);
		escreverCsv(resultados, Paths.get("relatorio", "resultados-desempenho.csv"));
		System.out.println("Arquivo gerado em relatorio/resultados-desempenho.csv");
	}

	private static List<Resultado> medirOrdenacoesQuadraticas() {
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (int tamanho : TAMANHOS_QUADRATICOS) {
			for (String cenario : new String[] { "aleatorio", "ordenado", "inverso" }) {
				resultados.add(medirOrdenacao("BubbleSort", tamanho, cenario, BubbleSortRunner.INSTANCE));
				resultados.add(medirOrdenacao("BubbleSortOtimizado", tamanho, cenario, BubbleSortOtimizadoRunner.INSTANCE));
				resultados.add(medirOrdenacao("SelectionSort", tamanho, cenario, SelectionSortRunner.INSTANCE));
				resultados.add(medirOrdenacao("SelectionSortEstavel", tamanho, cenario, SelectionSortEstavelRunner.INSTANCE));
				resultados.add(medirOrdenacao("InsertionSort", tamanho, cenario, InsertionSortRunner.INSTANCE));
			}
		}
		return resultados;
	}

	private static List<Resultado> medirOrdenacoesLogN() {
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (int tamanho : TAMANHOS_LOG_N) {
			for (String cenario : new String[] { "aleatorio", "ordenado", "inverso" }) {
				resultados.add(medirOrdenacao("MergeSort", tamanho, cenario, MergeSortRunner.INSTANCE));
				resultados.add(medirOrdenacao("TimSortJava", tamanho, cenario, TimSortJavaRunner.INSTANCE));
				resultados.add(medirOrdenacao("QuickSort", tamanho, cenario, QuickSortRunner.INSTANCE));
				resultados.add(medirOrdenacao("QuickSortShuffle", tamanho, cenario, QuickSortShuffleRunner.INSTANCE));
				resultados.add(medirOrdenacao("CountingSort", tamanho, cenario, CountingSortRunner.INSTANCE));
			}
		}
		return resultados;
	}

	private static List<Resultado> medirQuickSortsInteiros() {
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (int tamanho : TAMANHOS_LOG_N) {
			resultados.add(medirOrdenacaoInt("QuickSortJava", tamanho, "aleatorio", QuickSortJavaRunner.INSTANCE));
			resultados.add(medirOrdenacaoInt("Arrays.sort(int[])", tamanho, "aleatorio", ArraysSortIntRunner.INSTANCE));
		}
		return resultados;
	}

	private static List<Resultado> medirBuscas() {
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (int tamanho : TAMANHOS_BUSCA) {
			Estudante[] base = GeradorDados.gerarEstudantesAleatorios(tamanho, seedPara("busca", tamanho));
			Estudante[] ordenado = GeradorDados.ordenarNaturalmente(base);
			Estudante alvoPresente = ordenado[tamanho / 2];
			Estudante alvoAusente = new Estudante("ZZZ Ausente", String.format("%08d", tamanho + 9999), 11);

			resultados.add(medirBusca("BuscaLinearIterativa", tamanho, "presente", ordenado, alvoPresente, BuscaLinearIterativaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaLinearRecursiva", tamanho, "presente", ordenado, alvoPresente, BuscaLinearRecursivaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaBinariaIterativa", tamanho, "presente", ordenado, alvoPresente, BuscaBinariaIterativaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaBinariaRecursiva", tamanho, "presente", ordenado, alvoPresente, BuscaBinariaRecursivaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaDuasPontas", tamanho, "presente", ordenado, alvoPresente, BuscaDuasPontasRunner.INSTANCE));

			resultados.add(medirBusca("BuscaLinearIterativa", tamanho, "ausente", ordenado, alvoAusente, BuscaLinearIterativaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaLinearRecursiva", tamanho, "ausente", ordenado, alvoAusente, BuscaLinearRecursivaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaBinariaIterativa", tamanho, "ausente", ordenado, alvoAusente, BuscaBinariaIterativaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaBinariaRecursiva", tamanho, "ausente", ordenado, alvoAusente, BuscaBinariaRecursivaRunner.INSTANCE));
			resultados.add(medirBusca("BuscaDuasPontas", tamanho, "ausente", ordenado, alvoAusente, BuscaDuasPontasRunner.INSTANCE));
		}
		return resultados;
	}

	private static Resultado medirOrdenacao(String algoritmo, int tamanho, String cenario, SortRunner runner) {
		long total = 0L;
		String erro = null;
		for (int i = 0; i < WARMUP + REPETICOES; i++) {
			Estudante[] dados = prepararCenarioEstudantes(tamanho, cenario, seedPara(algoritmo, tamanho, cenario, i));
			try {
				long inicio = System.nanoTime();
				runner.sort(dados);
				long duracao = System.nanoTime() - inicio;
				if (i >= WARMUP) total += duracao;
			} catch (Throwable t) {
				erro = t.getClass().getSimpleName();
				System.out.println("[AVISO] " + algoritmo + " falhou em " + cenario + " tamanho=" + tamanho + ": " + erro);
				break;
			}
		}
		if (erro == null) {
			return new Resultado("sort", algoritmo, cenario, tamanho, total / REPETICOES);
		}
		return new Resultado("sort", algoritmo, cenario, tamanho, -1L);
	}

	private static Resultado medirOrdenacaoInt(String algoritmo, int tamanho, String cenario, IntSortRunner runner) {
		long total = 0L;
		String erro = null;
		for (int i = 0; i < WARMUP + REPETICOES; i++) {
			int[] dados = prepararCenarioInteiros(tamanho, cenario, seedPara(algoritmo, tamanho, cenario, i));
			try {
				long inicio = System.nanoTime();
				runner.sort(dados);
				long duracao = System.nanoTime() - inicio;
				if (i >= WARMUP) total += duracao;
			} catch (Throwable t) {
				erro = t.getClass().getSimpleName();
				System.out.println("[AVISO] " + algoritmo + " falhou em " + cenario + " tamanho=" + tamanho + ": " + erro);
				break;
			}
		}
		return new Resultado("sort-int", algoritmo, cenario, tamanho, erro == null ? total / REPETICOES : -1L);
	}

	private static Resultado medirBusca(String algoritmo, int tamanho, String cenario, Estudante[] dados, Estudante alvo, SearchRunner runner) {
		long total = 0L;
		String erro = null;
		for (int i = 0; i < WARMUP + REPETICOES; i++) {
			try {
				long inicio = System.nanoTime();
				runner.search(dados, alvo);
				long duracao = System.nanoTime() - inicio;
				if (i >= WARMUP) total += duracao;
			} catch (Throwable t) {
				erro = t.getClass().getSimpleName();
				System.out.println("[AVISO] " + algoritmo + " falhou em " + cenario + " tamanho=" + tamanho + ": " + erro);
				break;
			}
		}
		if (erro == null) return new Resultado("search", algoritmo, cenario, tamanho, total / REPETICOES);
		return new Resultado("search", algoritmo, cenario, tamanho, -1L);
	}

	private static Estudante[] prepararCenarioEstudantes(int tamanho, String cenario, long seed) {
		Estudante[] base = GeradorDados.gerarEstudantesAleatorios(tamanho, seed);
		if ("ordenado".equals(cenario)) {
			return GeradorDados.ordenarNaturalmente(base);
		}
		if ("inverso".equals(cenario)) {
			return GeradorDados.inverter(GeradorDados.ordenarNaturalmente(base));
		}
		return base;
	}

	private static int[] prepararCenarioInteiros(int tamanho, String cenario, long seed) {
		int[] base = GeradorDados.gerarInteirosAleatorios(tamanho, Math.max(1000, tamanho * 2), seed);
		if ("ordenado".equals(cenario)) {
			int[] copia = Arrays.copyOf(base, base.length);
			Arrays.sort(copia);
			return copia;
		}
		if ("inverso".equals(cenario)) {
			int[] copia = Arrays.copyOf(base, base.length);
			Arrays.sort(copia);
			for (int i = 0, j = copia.length - 1; i < j; i++, j--) {
				int tmp = copia[i];
				copia[i] = copia[j];
				copia[j] = tmp;
			}
			return copia;
		}
		return base;
	}

	private static void imprimirResultados(List<Resultado> resultados) {
		System.out.println("categoria;algoritmo;cenario;tamanho;tempo_medio_ns");
		for (Resultado resultado : resultados) {
			System.out.println(resultado);
		}
	}

	private static void escreverCsv(List<Resultado> resultados, Path caminho) {
		try {
			Files.createDirectories(caminho.getParent());
			try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
				writer.write("categoria;algoritmo;cenario;tamanho;tempo_medio_ns");
				writer.newLine();
				for (Resultado resultado : resultados) {
					writer.write(resultado.toCsv());
					writer.newLine();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Falha ao escrever resultados em CSV", e);
		}
	}

	private static long seedPara(Object... partes) {
		long seed = SEED_BASE;
		for (Object parte : partes) {
			seed = seed * 31L + (parte == null ? 0L : parte.hashCode());
		}
		return seed;
	}

	private interface SortRunner {
		void sort(Estudante[] dados);
	}

	private interface IntSortRunner {
		void sort(int[] dados);
	}

	private interface SearchRunner {
		int search(Estudante[] dados, Estudante alvo);
	}

	private static final class Resultado {
		private final String categoria;
		private final String algoritmo;
		private final String cenario;
		private final int tamanho;
		private final long tempoMedioNs;

		private Resultado(String categoria, String algoritmo, String cenario, int tamanho, long tempoMedioNs) {
			this.categoria = categoria;
			this.algoritmo = algoritmo;
			this.cenario = cenario;
			this.tamanho = tamanho;
			this.tempoMedioNs = tempoMedioNs;
		}

		private String toCsv() {
			return categoria + ";" + algoritmo + ";" + cenario + ";" + tamanho + ";" + tempoMedioNs;
		}

		@Override
		public String toString() {
			return toCsv();
		}
	}

	private enum BubbleSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { BubbleSort.sort(dados); }
	}

	private enum BubbleSortOtimizadoRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { BubbleSortOtimizado.sortOtimizado(dados); }
	}

	private enum SelectionSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { SelectionSort.sort(dados); }
	}

	private enum SelectionSortEstavelRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { SelectionSortEstavel.sortEstavel(dados); }
	}


	

	private enum InsertionSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { InsertionSort.sort(dados); }
	}

	private enum MergeSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { MergeSort.sort(dados); }
	}

	private enum TimSortJavaRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { TimSortJava.sort(dados); }
	}

	private enum QuickSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { QuickSort.sort(dados); }
	}

	private enum QuickSortShuffleRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { QuickSortShuffle.sort(dados); }
	}

	private enum CountingSortRunner implements SortRunner {
		INSTANCE;
		@Override public void sort(Estudante[] dados) { CountingSort.sort(dados); }
	}

	private enum QuickSortJavaRunner implements IntSortRunner {
		INSTANCE;
		@Override public void sort(int[] dados) { QuickSortJava.sort(dados); }
	}

	private enum ArraysSortIntRunner implements IntSortRunner {
		INSTANCE;
		@Override public void sort(int[] dados) { Arrays.sort(dados); }
	}

	private enum BuscaLinearIterativaRunner implements SearchRunner {
		INSTANCE;
		@Override public int search(Estudante[] dados, Estudante alvo) { return BuscaLinearIterativa.busca(dados, alvo); }
	}

	private enum BuscaLinearRecursivaRunner implements SearchRunner {
		INSTANCE;
		@Override public int search(Estudante[] dados, Estudante alvo) { return BuscaLinearRecursiva.busca(dados, alvo); }
	}

	private enum BuscaBinariaIterativaRunner implements SearchRunner {
		INSTANCE;
		@Override public int search(Estudante[] dados, Estudante alvo) { return BuscaBinariaIterativa.busca(dados, alvo); }
	}

	private enum BuscaBinariaRecursivaRunner implements SearchRunner {
		INSTANCE;
		@Override public int search(Estudante[] dados, Estudante alvo) { return BuscaBinariaRecursiva.busca(dados, alvo); }
	}

	private enum BuscaDuasPontasRunner implements SearchRunner {
		INSTANCE;
		@Override public int search(Estudante[] dados, Estudante alvo) { return BuscaDuasPontas.busca(dados, alvo); }
	}

}
