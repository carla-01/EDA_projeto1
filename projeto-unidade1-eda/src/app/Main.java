package app;

import experiments.TesteDesempenho;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            TesteDesempenho.executarPadrao();
            return;
        }

        String comando = args[0].trim().toLowerCase();
        if ("benchmark".equals(comando) || "all".equals(comando)) {
            TesteDesempenho.executarPadrao();
            return;
        }

        System.out.println("Uso: java app.Main [benchmark|all]");
    }
}
