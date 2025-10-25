package miniifood.entities;

import java.time.LocalDateTime;

public class ProdutoSacola extends BaseEntity{
    private int sacolaId;
    private int produtoId;
    private int quantidade;
    private double precoUnitario;
    private String observacao;

    public ProdutoSacola(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public ProdutoSacola(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, int sacolaId, int produtoId, int quantidade, double precoUnitario, String observacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.sacolaId = sacolaId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.observacao = observacao;
    }

    public int getSacolaId() {
        return sacolaId;
    }

    public void setSacolaId(int sacolaId) {
        this.sacolaId = sacolaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ProdutoSacola{" +
                "sacolaId=" + sacolaId +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}