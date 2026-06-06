package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Estudante implements Comparable<Estudante>, Serializable {
    private static final long serialVersionUID = 1L;

    private final String nomeCompleto;
    private final String numeroMatricula;
    private final int nota;

    public Estudante(String nomeCompleto, String numeroMatricula, int nota) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) throw new IllegalArgumentException("nomeCompleto is required");
        if (numeroMatricula == null || numeroMatricula.trim().isEmpty()) throw new IllegalArgumentException("numeroMatricula is required");
        this.nomeCompleto = nomeCompleto.trim();
        this.numeroMatricula = numeroMatricula.trim();
        this.nota = nota;
    }

    public String getNomeCompleto() { return nomeCompleto; }
    public String getNumeroMatricula() { return numeroMatricula; }
    public int getNota() { return nota; }

    @Override
    public String toString() {
        return nomeCompleto + " (" + numeroMatricula + ") - " + nota;
    }

    @Override
    public int compareTo(Estudante o) {
        if (o == null) return -1;
          int resultado = Integer.compare(o.nota, this.nota); // nota desc
          if (resultado != 0) return resultado;
          resultado = this.nomeCompleto.compareTo(o.nomeCompleto); // nome asc
          if (resultado != 0) return resultado;
          return this.numeroMatricula.compareTo(o.numeroMatricula); // matrícula asc
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudante that = (Estudante) o;
        return nota == that.nota &&
                 Objects.equals(nomeCompleto, that.nomeCompleto) &&
                 Objects.equals(numeroMatricula, that.numeroMatricula);
    }

    @Override
    public int hashCode() {
           return Objects.hash(nomeCompleto, numeroMatricula, nota);
    }

    // comparators para experiments
    public static final Comparator<Estudante> BY_NOTA_DESC = (a,b) -> Integer.compare(b.nota, a.nota);
    public static final Comparator<Estudante> BY_NOME_ASC = Comparator.comparing(Estudante::getNomeCompleto);
    public static final Comparator<Estudante> BY_MATRICULA_ASC = Comparator.comparing(Estudante::getNumeroMatricula);
}
