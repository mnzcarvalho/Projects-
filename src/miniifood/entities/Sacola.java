package miniifood.entities;

import java.time.LocalDateTime;

public class Sacola extends BaseEntity{
    private String numeroPedido;
    private int userId;
    private int lojaId;
    private int enderecoId;
    private int entregadorId;
    private int cupomId;
    private int formaPagamentoId;
    private double subtotal;
    private double taxaEntrega;
    private double desconto;
    private double valorTotal;
    private double trocoPara;
    private String observacao;
    private LocalDateTime dataPedido;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataEntrega;

    public Sacola(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public Sacola(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String numeroPedido, int userId, int lojaId, int enderecoId, int entregadorId, int cupomId, int formaPagamentoId, double subtotal, double taxaEntrega, double desconto, double valorTotal, double trocoPara, String observacao, LocalDateTime dataPedido, LocalDateTime dataConfirmacao, LocalDateTime dataEntrega) {
        super(id, dataCriacao, dataAtualizacao);
        this.numeroPedido = numeroPedido;
        this.userId = userId;
        this.lojaId = lojaId;
        this.enderecoId = enderecoId;
        this.entregadorId = entregadorId;
        this.cupomId = cupomId;
        this.formaPagamentoId = formaPagamentoId;
        this.subtotal = subtotal;
        this.taxaEntrega = taxaEntrega;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.trocoPara = trocoPara;
        this.observacao = observacao;
        this.dataPedido = dataPedido;
        this.dataConfirmacao = dataConfirmacao;
        this.dataEntrega = dataEntrega;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public int getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(int entregadorId) {
        this.entregadorId = entregadorId;
    }

    public int getCupomId() {
        return cupomId;
    }

    public void setCupomId(int cupomId) {
        this.cupomId = cupomId;
    }

    public int getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(int formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getTrocoPara() {
        return trocoPara;
    }

    public void setTrocoPara(double trocoPara) {
        this.trocoPara = trocoPara;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @Override
    public String toString() {
        return "Sacola{" +
                "numeroPedido='" + numeroPedido + '\'' +
                ", userId=" + userId +
                ", lojaId=" + lojaId +
                ", enderecoId=" + enderecoId +
                ", entregadorId=" + entregadorId +
                ", cupomId=" + cupomId +
                ", formaPagamentoId=" + formaPagamentoId +
                ", subtotal=" + subtotal +
                ", taxaEntrega=" + taxaEntrega +
                ", desconto=" + desconto +
                ", valorTotal=" + valorTotal +
                ", trocoPara=" + trocoPara +
                ", observacao='" + observacao + '\'' +
                ", dataPedido=" + dataPedido +
                ", dataConfirmacao=" + dataConfirmacao +
                ", dataEntrega=" + dataEntrega +
                '}';
    }
}
