package sistemaLoja.model;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private static int contador = 1;
    private final int id;
    private LocalDate dataCadastro;

    {
        id = contador++;
        dataCadastro = LocalDate.now();
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Cliente #" + id + " - " + nome + " (desde " + dataCadastro + ")";
    }
}
