package miniifood.entities;

import java.time.LocalDateTime;

public class FormaPagamento extends BaseEntity{
    private String nome;
    private boolean ativo;

    public FormaPagamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public FormaPagamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, boolean ativo) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "FormaPagamento{" +
                "nome='" + nome + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
