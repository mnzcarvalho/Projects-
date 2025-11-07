package desafioCadastro.model;

import desafioCadastro.enums.TipoPet;

public class Pet {
    private String nome;
    private TipoPet tipo;
    private char sexo;
    private Endereco endereco;
    private int idade;
    private double peso;
    private String raca;

    public String getNome() {
        return nome;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public char getSexo() {
        return sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public String getRaca() {
        return raca;
    }
}
