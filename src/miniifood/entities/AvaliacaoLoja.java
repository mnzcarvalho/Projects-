package miniifood.entities;

import java.time.LocalDateTime;

//task 4
public class AvaliacaoLoja extends BaseEntity {
    private String descricao;
    private int nota;
    private int lojaId;
    private int userId;
    private LocalDateTime dataAvaliacao;


    public AvaliacaoLoja(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public AvaliacaoLoja(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String descricao, int nota, int lojaId, int userId, LocalDateTime dataAvaliacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.descricao = descricao;
        this.nota = nota;
        this.lojaId = lojaId;
        this.userId = userId;
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public String toString() {
        return "AvaliacaoLoja - descricao: " + descricao;
    }
}
