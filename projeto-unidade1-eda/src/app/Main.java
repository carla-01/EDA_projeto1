package app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import experiments.TesteDesempenho;

public class Main {

	private static final Path CSV_RESULTADO = Paths.get("relatorio", "resultados-desempenho.csv");

	public static void main(String[] args) {
		if (args.length == 0) {
			if (System.console() == null) {
				TesteDesempenho.executarPadrao();
				return;
			}
			executarMenuInterativo();
			return;
		}

		String comando = args[0].trim().toLowerCase();
		if ("benchmark".equals(comando) || "all".equals(comando)) {
			TesteDesempenho.executarPadrao();
			return;
		}

		if ("menu".equals(comando) || "help".equals(comando)) {
			executarMenuInterativo();
			return;
		}

		System.out.println("Uso: java app.Main [benchmark|all|menu|help]");
	}

	private static void executarMenuInterativo() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println();
				System.out.println("=== Análise de Algoritmos de Busca e Ordenação ===");
				System.out.println("1 - Executar benchmark e gerar CSV");
				System.out.println("2 - Mostrar tabela do CSV gerado");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");

				if (!scanner.hasNextLine()) {
					System.out.println("Entrada encerrada.");
					return;
				}

				String opcao = scanner.nextLine().trim().toLowerCase();
				if ("0".equals(opcao) || "sair".equals(opcao) || "exit".equals(opcao)) {
					System.out.println("Saindo.");
					return;
				}
				if ("1".equals(opcao) || "benchmark".equals(opcao) || "all".equals(opcao)) {
					TesteDesempenho.executarPadrao();
					continue;
				}
				if ("2".equals(opcao)) {
					mostrarTabelaCsv();
					continue;
				}

				System.out.println("Opção inválida.");
			}
		}
	}

	private static void mostrarTabelaCsv() {
		if (!Files.exists(CSV_RESULTADO)) {
			System.out.println("O CSV ainda não existe. Execute a opção 1 primeiro.");
			return;
		}

		System.out.println();
		System.out.println("Tabela resumida do CSV gerado:");
		System.out.printf("%-12s %-24s %-12s %-10s %-16s%n", "categoria", "algoritmo", "cenario", "tamanho", "tempo_medio_ns");
		System.out.println("--------------------------------------------------------------------------------");

		try {
			List<String> linhas = Files.readAllLines(CSV_RESULTADO, StandardCharsets.UTF_8);
			for (int i = 1; i < linhas.size(); i++) {
				String[] partes = linhas.get(i).split(";");
				if (partes.length < 5) {
					continue;
				}
				System.out.printf("%-12s %-24s %-12s %-10s %-16s%n",
						partes[0], partes[1], partes[2], partes[3], partes[4]);
			}
			System.out.println();
			System.out.println("Tabela exibida com sucesso a partir de " + CSV_RESULTADO);
		} catch (IOException e) {
			System.out.println("Falha ao ler o CSV: " + e.getMessage());
		}
	}
}
