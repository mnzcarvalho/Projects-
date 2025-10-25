package miniifood.entities;

import java.time.LocalDateTime;

public class CategoriaProduto extends BaseEntity{
    private String nome;
    private int lojaId;
    private int ordem;

    public CategoriaProduto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public CategoriaProduto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, int lojaId, int ordem) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.lojaId = lojaId;
        this.ordem = ordem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return "CategoriaProduto - nome: " + nome;
    }
}
