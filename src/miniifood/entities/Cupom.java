package miniifood.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cupom extends BaseEntity{
    private String codigo;
    private String descricao;
    private String tipoDesconto;
    private double valorDesconto;
    private double valorMinimoPedido;
    private LocalDate dataValidadeInicio;
    private LocalDate dataValidadeFim;
    private int quantidadeDisponivel;
    private int quantidadeUsada;
    private boolean ativo;

    public Cupom(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public Cupom(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String codigo, String descricao, String tipoDesconto, double valorDesconto, double valorMinimoPedido, LocalDate dataValidadeInicio, LocalDate dataValidadeFim, int quantidadeDisponivel, int quantidadeUsada, boolean ativo) {
        super(id, dataCriacao, dataAtualizacao);
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoDesconto = tipoDesconto;
        this.valorDesconto = valorDesconto;
        this.valorMinimoPedido = valorMinimoPedido;
        this.dataValidadeInicio = dataValidadeInicio;
        this.dataValidadeFim = dataValidadeFim;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeUsada = quantidadeUsada;
        this.ativo = ativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorMinimoPedido() {
        return valorMinimoPedido;
    }

    public void setValorMinimoPedido(double valorMinimoPedido) {
        this.valorMinimoPedido = valorMinimoPedido;
    }

    public LocalDate getDataValidadeInicio() {
        return dataValidadeInicio;
    }

    public void setDataValidadeInicio(LocalDate dataValidadeInicio) {
        this.dataValidadeInicio = dataValidadeInicio;
    }

    public LocalDate getDataValidadeFim() {
        return dataValidadeFim;
    }

    public void setDataValidadeFim(LocalDate dataValidadeFim) {
        this.dataValidadeFim = dataValidadeFim;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getQuantidadeUsada() {
        return quantidadeUsada;
    }

    public void setQuantidadeUsada(int quantidadeUsada) {
        this.quantidadeUsada = quantidadeUsada;
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
        return "Cupom{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoDesconto='" + tipoDesconto + '\'' +
                ", valorDesconto=" + valorDesconto +
                ", valorMinimoPedido=" + valorMinimoPedido +
                ", dataValidadeInicio=" + dataValidadeInicio +
                ", dataValidadeFim=" + dataValidadeFim +
                ", quantidadeUsada=" + quantidadeUsada +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", ativo=" + ativo +
                '}';
    }
}
