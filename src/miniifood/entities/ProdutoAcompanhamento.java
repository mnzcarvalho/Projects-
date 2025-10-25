package miniifood.entities;

import java.time.LocalDateTime;

public class ProdutoAcompanhamento extends BaseEntity{
    private int produtoId;
    private int acompanhamentoId;

    public ProdutoAcompanhamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public ProdutoAcompanhamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, int produtoId, int acompanhamentoId) {
        super(id, dataCriacao, dataAtualizacao);
        this.produtoId = produtoId;
        this.acompanhamentoId = acompanhamentoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getAcompanhamentoId() {
        return acompanhamentoId;
    }

    public void setAcompanhamentoId(int acompanhamentoId) {
        this.acompanhamentoId = acompanhamentoId;
    }

    @Override
    public String toString() {
        return "ProdutoAcompanhamento{" +
                "acompanhamentoId=" + acompanhamentoId +
                '}';
    }
}
