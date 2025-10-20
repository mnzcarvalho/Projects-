package sistemaCityFlow.model;

public class Rua {

    private String nome;
    private int cruzamentos;

    public Rua(String nome, int cruzamentos) {
        this.nome = nome;
        this.cruzamentos = cruzamentos;
    }

    public String getNome() {
        return nome;
    }

    public int getCruzamentos() {
        return cruzamentos;
    }
}
