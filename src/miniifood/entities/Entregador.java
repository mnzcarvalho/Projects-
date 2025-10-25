package miniifood.entities;

import java.time.LocalDateTime;

public class Entregador extends BaseEntity{
    private String nome;
    private String telefone;
    private String cpf;
    private String cnh;
    private String tipoVeiculo;
    private String placaVeiculo;
    private boolean disponivel;
    private boolean ativo;
    private LocalDateTime dataCadastro;

    public Entregador(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
    }

    public Entregador(int id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String telefone, String cpf, String cnh, String tipoVeiculo, String placaVeiculo, boolean disponivel, boolean ativo, LocalDateTime dataCadastro) {
        super(id, dataCriacao, dataAtualizacao);
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cnh = cnh;
        this.tipoVeiculo = tipoVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.disponivel = disponivel;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Entregador{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", disponivel=" + disponivel +
                ", ativo=" + ativo +
                '}';
    }
}
