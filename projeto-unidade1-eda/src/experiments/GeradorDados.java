package experiments;

import java.util.Arrays;
import java.util.Random;

import model.Estudante;

public class GeradorDados {

	private static final String[] PRIMEIROS_NOMES = {
		"Ana", "Bruno", "Carla", "Diego", "Eva", "Felipe", "Giovana", "Henrique",
		"Iris", "Joao", "Kai", "Laura", "Marcos", "Nina", "Otavio", "Paula",
		"Rafael", "Sofia", "Tiago", "Valeria"
	};

	private static final String[] SOBRENOMES = {
		"Almeida", "Barbosa", "Costa", "Dias", "Esteves", "Ferreira", "Gomes",
		"Haddad", "Iglesias", "Lima", "Melo", "Nogueira", "Oliveira", "Pereira",
		"Queiroz", "Rocha", "Silva", "Souza", "Teixeira", "Viana"
	};

	private GeradorDados() {
	}

	public static Estudante[] gerarEstudantesAleatorios(int tamanho, long seed) {
		return gerarEstudantesAleatorios(tamanho, new Random(seed));
	}

	public static Estudante[] gerarEstudantesAleatorios(int tamanho, Random random) {
		Estudante[] estudantes = new Estudante[tamanho];
		for (int i = 0; i < tamanho; i++) {
			String nome = PRIMEIROS_NOMES[random.nextInt(PRIMEIROS_NOMES.length)] + " "
				+ SOBRENOMES[random.nextInt(SOBRENOMES.length)] + " " + i;
			String matricula = String.format("%08d", i + 1);
			int nota = random.nextInt(11);
			estudantes[i] = new Estudante(nome, matricula, nota);
		}
		return estudantes;
	}

	public static int[] gerarInteirosAleatorios(int tamanho, int limite, long seed) {
		return gerarInteirosAleatorios(tamanho, limite, new Random(seed));
	}

	public static int[] gerarInteirosAleatorios(int tamanho, int limite, Random random) {
		int[] valores = new int[tamanho];
		for (int i = 0; i < tamanho; i++) {
			valores[i] = random.nextInt(limite);
		}
		return valores;
	}

	public static Estudante[] copiarEstudantes(Estudante[] origem) {
		return origem == null ? null : Arrays.copyOf(origem, origem.length);
	}

	public static Estudante[] ordenarNaturalmente(Estudante[] origem) {
		Estudante[] copia = copiarEstudantes(origem);
		if (copia != null) Arrays.sort(copia);
		return copia;
	}

	public static Estudante[] inverter(Estudante[] origem) {
		if (origem == null) return null;
		Estudante[] copia = copiarEstudantes(origem);
		for (int i = 0, j = copia.length - 1; i < j; i++, j--) {
			Estudante tmp = copia[i];
			copia[i] = copia[j];
			copia[j] = tmp;
		}
		return copia;
	}

	public static Estudante[] embaralhar(Estudante[] origem, long seed) {
		return embaralhar(origem, new Random(seed));
	}

	public static Estudante[] embaralhar(Estudante[] origem, Random random) {
		if (origem == null) return null;
		Estudante[] copia = copiarEstudantes(origem);
		for (int i = copia.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Estudante tmp = copia[i];
			copia[i] = copia[j];
			copia[j] = tmp;
		}
		return copia;
	}

}