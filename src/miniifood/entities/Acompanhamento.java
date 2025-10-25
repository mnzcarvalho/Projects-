package miniifood.entities;

import java.time.LocalDateTime;

public class Acompanhamento extends BaseEntity{
    private String nome;
    private String descricao;
    private double valorAdicional;
    private boolean obrigatorio;
    private int maximoPermitido;


    public Acompanhamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public Acompanhamento(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String descricao, double valorAdicional, boolean obrigatorio, int maximoPermitido) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.descricao = descricao;
        this.valorAdicional = valorAdicional;
        this.obrigatorio = obrigatorio;
        this.maximoPermitido = maximoPermitido;
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

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public int getMaximoPermitido() {
        return maximoPermitido;
    }

    public void setMaximoPermitido(int maximoPermitido) {
        this.maximoPermitido = maximoPermitido;
    }

    @Override
    public String toString() {
        return "Acompanhamento{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valorAdicional=" + valorAdicional +
                ", obrigatorio=" + obrigatorio +
                ", maximoPermitido=" + maximoPermitido +
                '}';
    }
}
