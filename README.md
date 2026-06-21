# EDA_projeto1

Projeto da Unidade 1 da disciplina de Estrutura de Dados, com implementação e análise de desempenho de algoritmos de busca e ordenação em Java.

## Objetivo

O projeto compara o comportamento prático de algoritmos clássicos de busca e ordenação, trabalhando com:

- objetos da classe `Estudante`
- arrays primitivos `int[]` para comparação adicional entre QuickSort e `Arrays.sort(int[])`

Os testes medem tempo de execução com `System.nanoTime()` e geram um CSV com os resultados.

## Algoritmos implementados

### Ordenação

- BubbleSort
- BubbleSort otimizado
- SelectionSort
- SelectionSort estável
- InsertionSort
- MergeSort
- TimSort do Java
- QuickSort
- QuickSort com shuffle
- QuickSort em `int[]`
- CountingSort

### Busca

- Busca linear iterativa
- Busca linear recursiva
- Busca binária iterativa
- Busca binária recursiva
- Busca linear em duas pontas

## Classe `Estudante`

A classe `Estudante` implementa `Comparable<Estudante>` com a seguinte ordem natural:

1. nota decrescente
2. nome crescente
3. matrícula crescente

Essa ordem é usada pelos algoritmos de ordenação que trabalham com objetos.

## Estrutura do projeto

```text
projeto-unidade1-eda/
	src/
		app/
		experiments/
		model/
		search/
		sorting/
	bin/
video_pdf/
relatorio/
```

## Execução

O ponto de entrada do projeto é `app.Main`.

### Pelo terminal

Depois de compilar, você pode executar com:

```bash
java -cp projeto-unidade1-eda/bin app.Main benchmark
```

Também é possível usar:

```bash
java -cp projeto-unidade1-eda/bin app.Main menu
```

### Pelo menu

Ao rodar sem argumentos em um ambiente com console, o programa abre um menu com as opções:

- executar benchmark e gerar CSV
- mostrar tabela do CSV gerado
- sair

## Benchmark

O benchmark faz as medições com:

- 20 execuções por cenário
- 5 execuções iniciais descartadas como warm-up
- tamanhos diferentes de vetor para ordenação e busca
- cenários aleatório, ordenado e inverso

Os resultados são gravados em:

```text
relatorio/resultados-desempenho.csv
```

Esse arquivo é sobrescrito sempre que o benchmark é executado novamente.

## Observações sobre os testes

- Para objetos, os estudantes são gerados com nomes fictícios e notas aleatórias.
- O CountingSort usa a nota como chave principal.
- A comparação extra com `int[]` serve para analisar desempenho entre QuickSort próprio e `Arrays.sort(int[])`.

## Requisitos

- Java 25 ou compatível
- Ambiente para compilar e executar arquivos `.java`

## Saída gerada

O projeto produz principalmente:

- `relatorio/resultados-desempenho.csv`
- saída textual no console com o resumo dos tempos medidos


