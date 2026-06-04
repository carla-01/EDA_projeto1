package model;

public class Estudante {
    private String nome;
    private String matricula;
    private double nota;

    public Estudante(String nome, String matricula, double nota) {
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public double getNota() { return nota; }

    @Override
    public String toString() {
        return nome + " (" + matricula + ") - " + nota;
    }
}
