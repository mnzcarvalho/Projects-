package miniifood.entities;

import java.time.LocalDateTime;

public class HistoricoPagamento extends BaseEntity{
    private int sacolaId;
    private int statusPagamentoId;
    private LocalDateTime dataStatus;
    private String observacao;

    public HistoricoPagamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public HistoricoPagamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, int sacolaId, int statusPagamentoId, LocalDateTime dataStatus, String observacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.sacolaId = sacolaId;
        this.statusPagamentoId = statusPagamentoId;
        this.dataStatus = dataStatus;
        this.observacao = observacao;
    }

    public int getSacolaId() {
        return sacolaId;
    }

    public void setSacolaId(int sacolaId) {
        this.sacolaId = sacolaId;
    }

    public int getStatusPagamentoId() {
        return statusPagamentoId;
    }

    public void setStatusPagamentoId(int statusPagamentoId) {
        this.statusPagamentoId = statusPagamentoId;
    }

    public LocalDateTime getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(LocalDateTime dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "HistoricoPagamento{" +
                "sacolaId=" + sacolaId +
                ", statusPagamentoId=" + statusPagamentoId +
                ", dataStatus=" + dataStatus +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
