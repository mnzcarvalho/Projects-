package miniifood.entities;

import java.time.LocalDateTime;

public class CategoriaTipoProduto extends BaseEntity{
    private String nome;

    public CategoriaTipoProduto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public CategoriaTipoProduto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Tipo do Produto: " + this.nome;
    }


}
