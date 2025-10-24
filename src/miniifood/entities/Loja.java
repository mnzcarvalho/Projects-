package miniifood.entities;

import java.time.LocalDateTime;

public class Loja extends BaseEntity{
    private String nome;
    private String descricao;
    private String telefone;
    private String email;
    private String cnpj;
    private double valorMinimo;
    private double taxaEntrega;
    private int tempoEntregaMin;
    private int tempoEntregaMax;
    private boolean aberta;
    private int categoriaTipoLojaId;


    public Loja(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
    }


    public Loja(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, boolean ativo, String nome, String descricao, String telefone, String email, String cnpj, double valorMinimo, double taxaEntrega, int tempoEntregaMin, int tempoEntregaMax, boolean aberta, int categoriaTipoLojaId) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.valorMinimo = valorMinimo;
        this.taxaEntrega = taxaEntrega;
        this.tempoEntregaMin = tempoEntregaMin;
        this.tempoEntregaMax = tempoEntregaMax;
        this.aberta = aberta;
        this.categoriaTipoLojaId = categoriaTipoLojaId;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public int getTempoEntregaMin() {
        return tempoEntregaMin;
    }

    public void setTempoEntregaMin(int tempoEntregaMin) {
        this.tempoEntregaMin = tempoEntregaMin;
    }

    public int getTempoEntregaMax() {
        return tempoEntregaMax;
    }

    public void setTempoEntregaMax(int tempoEntregaMax) {
        this.tempoEntregaMax = tempoEntregaMax;
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public int getCategoriaTipoLojaId() {
        return categoriaTipoLojaId;
    }

    public void setCategoriaTipoLojaId(int categoriaTipoLojaId) {
        this.categoriaTipoLojaId = categoriaTipoLojaId;
    }

    @Override
    public String toString() {
        return "Loja: " + this.nome + " id: " + this.getId();
    }
}
