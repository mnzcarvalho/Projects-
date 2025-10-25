package miniifood.entities;

import java.time.LocalDateTime;

public class AvaliacaoPedido extends BaseEntity{
    private int sacolaId;
    private int nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public AvaliacaoPedido(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public AvaliacaoPedido(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, int sacolaId, int nota, String comentario, LocalDateTime dataAvaliacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.sacolaId = sacolaId;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getSacolaId() {
        return sacolaId;
    }

    public void setSacolaId(int sacolaId) {
        this.sacolaId = sacolaId;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public String toString() {
        return "AvaliacaoPedido{" +
                "sacolaId=" + sacolaId +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", dataAvaliacao=" + dataAvaliacao +
                '}';
    }
}
