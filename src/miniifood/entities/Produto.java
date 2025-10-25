package miniifood.entities;

import java.time.LocalDateTime;

public class Produto extends BaseEntity{
    private String nome;
    private String descricao;
    private double preco;
    private boolean disponivel;
    private boolean destaque;
    private int categoriaProdutoId;
    private int lojaId;
    private int categoriaTipoProdutoId;
    private LocalDateTime dataCriacao;

    public Produto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public Produto(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String descricao, double preco, boolean disponivel, boolean destaque, int categoriaProdutoId, int lojaId, int categoriaTipoProdutoId, LocalDateTime dataCriacao1) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.destaque = destaque;
        this.categoriaProdutoId = categoriaProdutoId;
        this.lojaId = lojaId;
        this.categoriaTipoProdutoId = categoriaTipoProdutoId;
        this.dataCriacao = dataCriacao1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }

    public int getCategoriaProdutoId() {
        return categoriaProdutoId;
    }

    public void setCategoriaProdutoId(int categoriaProdutoId) {
        this.categoriaProdutoId = categoriaProdutoId;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public int getCategoriaTipoProdutoId() {
        return categoriaTipoProdutoId;
    }

    public void setCategoriaTipoProdutoId(int categoriaTipoProdutoId) {
        this.categoriaTipoProdutoId = categoriaTipoProdutoId;
    }

    @Override
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", disponivel=" + disponivel +
                '}';
    }
}
