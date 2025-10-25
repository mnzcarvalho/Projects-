package miniifood.entities;

import java.time.LocalDateTime;

public class HistoricoPedidoStatus extends BaseEntity{
    private int sacolaId;
    private int statusPedidoId;
    private LocalDateTime dataStatus;
    private String observacao;

    public HistoricoPedidoStatus(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public HistoricoPedidoStatus(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, int sacolaId, int statusPedidoId, LocalDateTime dataStatus, String observacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.sacolaId = sacolaId;
        this.statusPedidoId = statusPedidoId;
        this.dataStatus = dataStatus;
        this.observacao = observacao;
    }

    public int getSacolaId() {
        return sacolaId;
    }

    public void setSacolaId(int sacolaId) {
        this.sacolaId = sacolaId;
    }

    public int getStatusPedidoId() {
        return statusPedidoId;
    }

    public void setStatusPedidoId(int statusPedidoId) {
        this.statusPedidoId = statusPedidoId;
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
        return "HistoricoPedidoStatus{" +
                "sacolaId=" + sacolaId +
                ", statusPedidoId=" + statusPedidoId +
                ", dataStatus=" + dataStatus +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
